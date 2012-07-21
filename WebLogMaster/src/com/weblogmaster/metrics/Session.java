/**
 * 
 */
package com.weblogmaster.metrics;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Patrick
 *
 */
//@SuppressWarnings("unused")
public class Session {

	/**
	 * 
	 */
	public static final long MAX_SESSION_DURATION = 1800000;
	private String visitorIP;
	private Date startTime;
	private ArrayList<PageVisit> pages = new ArrayList<PageVisit>();
	
	public Session(String IPAddress, Date start) {
		
		visitorIP = IPAddress;
		startTime = start;
	}
	
	public void addPage(PageVisit pg){
		this.pages.add(pg);
	}
	/**
	 * @return the visitorIP
	 */
	public String getVisitorIP() {
		return visitorIP;
	}

	/**
	 * @param visitorIP the visitorIP to set
	 */
	public void setVisitorIP(String visitorIP) {
		this.visitorIP = visitorIP;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the pages
	 */
	public ArrayList<PageVisit> getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(ArrayList<PageVisit> pages) {
		this.pages = pages;
	}

}
