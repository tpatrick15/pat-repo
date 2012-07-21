/**
 * 
 */
package com.weblogmaster.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.weblogmaster.metrics.*;

/**
 * @author Patrick
 *
 */
//@SuppressWarnings("unused")
public class WebLogMaster {

	
	private Map<String,Visitor> visitors = new HashMap<String,Visitor>();
	private long unique_Visitors = 0;
	private double total_Sessions = 0;
	private double bounce_rate = 0;
	private double exit_rate = 0;
	private Map<String,Integer> bouncers = new HashMap<String,Integer>();
	private Map<String,Integer> PageTimeCounter = new HashMap<String,Integer>();
	private Map<String,Integer> hitCounter = new HashMap<String,Integer>();
	private Map<String, Integer> exitPages = new HashMap<String,Integer>();
	private File myOutPutFile;
	BufferedWriter out;
	private static long numLines = 0;
	/**
	 * @return the numLines
	 */
	public static long getNumLines() {
		return numLines;
	}

	/**
	 * @param numLines the numLines to set
	 */
	public static void setNumLines(long numLines) {
		WebLogMaster.numLines = numLines;
	}

	/**
	 * 
	 */
	public WebLogMaster(String output) {
		myOutPutFile = new File(output);
		try {
			out = new BufferedWriter(new FileWriter(myOutPutFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void processLine(String line){
		
		PageVisit vis = new PageVisit(line);
		if(!visitors.containsKey(vis.getVisitorIP())){
			visitors.put(vis.getVisitorIP(), new Visitor(vis.getVisitorIP()));
			visitors.get(vis.getVisitorIP()).addPage(vis);
		}
		else{
			visitors.get(vis.getVisitorIP()).addPage(vis);
		}
		unique_Visitors = visitors.size();
	}
	
	public void processSessions(){
		
		for(Map.Entry<String, Visitor> entry: visitors.entrySet()){
			ArrayList<Session> sessions = new ArrayList<Session>();
			int sessionIndex = 0;
			ArrayList<PageVisit> pages = entry.getValue().getVisitedPages();
			Date lastReqTime = pages.get(0).getRequest().getRequestTime();
			int value = 0;
			int thisValue = 0;
			for(int i = 0; i < pages.size(); i++){
				
				String key = pages.get(i).getRequest().getActivity().getResourceId();
				if(hitCounter.containsKey(key)){
					int count = hitCounter.get(key) + 1;
					hitCounter.put(key, count);
				}
				else{
					hitCounter.put(key, 1);
				}
				if(PageTimeCounter.containsKey(key)){
					value = (int) (PageTimeCounter.get(key) + pages.get(i).getTimeSpent());
					if(value > 0)
						PageTimeCounter.put(key, value);
					}					
				else{
					thisValue = (int) pages.get(i).getTimeSpent();
					if(thisValue > 0)
						PageTimeCounter.put(key, thisValue);
					}
				
				if ((i == 0) || !(pages.get(i).getRequest().getRequestTime().getTime() - lastReqTime.getTime() <= Session.MAX_SESSION_DURATION)){
					Session newSess = new Session(pages.get(i).getVisitorIP(),pages.get(i).getRequest().getRequestTime());
					newSess.addPage(pages.get(i));
					sessions.add(newSess);
					sessionIndex++;
				}
				else{
					sessions.get(sessionIndex - 1).addPage(pages.get(i));
				}
			}
			String key;
			int value1 = 0;
			for(int i = 0; i < sessions.size(); i++){
				//Bouncers
				if(sessions.get(i).getPages().size() == 1){
					key = sessions.get(i).getPages().get(0).getRequest().getActivity().getResourceId();
					if(bouncers.containsKey(key)){
						value1 = bouncers.get(key) + 1;
						bouncers.put(key, value1);
					}
					else{
						bouncers.put(key, 1);
					}
						
				}
				//Exits
				else{
					key = sessions.get(i).getPages().get(sessions.get(i).getPages().size()-1).getRequest().getActivity().getResourceId();
					if(exitPages.containsKey(key)){
						value1 = exitPages.get(key) + 1;
						exitPages.put(key, value1);
					}
					else{
						exitPages.put(key, 1);
					}
				}
					
			}
			
			total_Sessions += sessions.size();
			
		}
	}
	
	public void printPageStatistics() throws IOException{
		long time = 0;
		System.out.println();
		System.out.println();
		System.out.println();	
		System.out.println();
		ValueComparator bv =  new ValueComparator(hitCounter);
        TreeMap<String,Integer> sorted = new TreeMap<String,Integer>(bv);
        sorted.putAll(hitCounter);
		System.out.println();
        System.out.print("Popularity Report:");
		System.out.println();
		for(Map.Entry<String, Integer> entry: sorted.entrySet()){
			System.out.print("Page ["+entry.getKey()+"]: " + entry.getValue() + " hits.");
			System.out.println();
		}
		System.out.println();
		System.out.println();
		System.out.print("Time spent on each page (in seconds)");
		System.out.println();
		ValueComparator bvc =  new ValueComparator(PageTimeCounter);
        TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);
        sorted_map.putAll(PageTimeCounter);
		for(Map.Entry<String, Integer> entry: sorted_map.entrySet()){
			try{
			System.out.print("Resource ["+entry.getKey()+"]: " + entry.getValue()/1000 + " seconds.");
			System.out.println();
			time += entry.getValue();
			}
			catch(Exception e){
				System.out.println("Error here: "+ e.getMessage());
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.print("Total time spent on the site: " + time/1000 + " seconds!");
        
		
	}
	
	public void printBounce() throws IOException{
		bounce_rate = (bouncers.size()/total_Sessions) * 100;
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.print("Number of total Lines processed: " + numLines);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.print("Number of total Sessions: " + total_Sessions);
		System.out.println();
		System.out.print("Number of unique visitors: " + unique_Visitors);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.print("Bounce Rate: " + bounce_rate + "%.");
		System.out.println();
		ValueComparator bvc =  new ValueComparator(bouncers);
        TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);
        sorted_map.putAll(bouncers);
		for(Map.Entry<String, Integer> entry: sorted_map.entrySet()){
			System.out.print("Resource ["+entry.getKey()+"]: " + entry.getValue());
			System.out.println();
		}
	}
	
	public void printExits() throws IOException{
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		exit_rate = (exitPages.size()/total_Sessions) * 100;
		System.out.print("Exit Rate: " + exit_rate + "%.");
		System.out.println();
		ValueComparator bvc =  new ValueComparator(exitPages);
        TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);
        sorted_map.putAll(exitPages);
		for(Map.Entry<String, Integer> entry: sorted_map.entrySet()){
			System.out.print("Resource ["+entry.getKey()+"]: " + entry.getValue());
			System.out.println();
		}
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
			if (args.length != 2){
				System.err.println("Only one argument needed. Usage WebLogMaster [WebLogFile].");
			}
			else{
			WebLogMaster master = new WebLogMaster(args[1]);
			File file = new File(args[0]);
			//File file = new File("/Users/Patrick/log.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			
			while ((line = br.readLine()) != null) {
			   master.processLine(line);
			   numLines++;
			}
			br.close();
			
			master.processSessions();
			
			//Print rates here
			master.printBounce();
			master.printExits();
			
			//Print page stats here:
			master.printPageStatistics();
			
			}

	}
	
	

	/**
	 * @return the visitors
	 */
	public Map<String, Visitor> getVisitors() {
		return visitors;
	}

	/**
	 * @param visitors the visitors to set
	 */
	public void setVisitors(Map<String, Visitor> visitors) {
		this.visitors = visitors;
	}

	/**
	 * @return the unique_Visitors
	 */
	public long getUnique_Visitors() {
		return unique_Visitors;
	}

	/**
	 * @param unique_Visitors the unique_Visitors to set
	 */
	public void setUnique_Visitors(long unique_Visitors) {
		this.unique_Visitors = unique_Visitors;
	}

}
