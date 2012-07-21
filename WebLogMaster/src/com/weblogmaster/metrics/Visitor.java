/**
 * 
 */
package com.weblogmaster.metrics;

import java.util.ArrayList;

/**
 * @author Patrick
 *
 */
public class Visitor {

	/**
	 * 
	 */
	private ArrayList<PageVisit> visitedPages = new ArrayList<PageVisit>();
	private String IP;
	
	/**
	 * @return the visitedPages
	 */
	public ArrayList<PageVisit> getVisitedPages() {
		return visitedPages;
	}

	/**
	 * @param visitedPages the visitedPages to set
	 */
	public void setVisitedPages(ArrayList<PageVisit> visitedPages) {
		this.visitedPages = visitedPages;
	}

	/**
	 * @return the iP
	 */
	public String getIP() {
		return IP;
	}

	/**
	 * @param iP the iP to set
	 */
	public void setIP(String iP) {
		IP = iP;
	}

	
	
	public Visitor(String ip) {
		IP = ip;
	}
	
	public void addPage(PageVisit pg){
		if(this.visitedPages.size() > 0){
			visitedPages.get(visitedPages.size()-1).setTimeSpent(pg.getRequest().getRequestTime().getTime());
		}
		visitedPages.add(pg);
	}

}
