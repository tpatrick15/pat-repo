/**
 * 
 */
package com.weblogmaster.metrics;

import java.util.ArrayList;

/**
 * @author Patrick
 *
 */
@SuppressWarnings("unused")
public class PageVisit {

	/**
	 * 
	 */
	private String visitorIP;
	private Request request;
	private long timeSpent = 0;
	
	
	public PageVisit(String line) {
		
		request = new Request(line);
		visitorIP = request.getRequestorIP();
		
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
	 * @return the request
	 */
	public Request getRequest() {
		return request;
	}
	/**
	 * @param request the request to set
	 */
	public void setRequest(Request request) {
		this.request = request;
	}




	/**
	 * @return the timeSpent
	 */
	public long getTimeSpent() {
		return timeSpent;
	}




	/**
	 * @param timeSpent the timeSpent to set
	 */
	public void setTimeSpent(long newReqTime) {
		this.timeSpent = newReqTime - request.getRequestTime().getTime();
	}

}
