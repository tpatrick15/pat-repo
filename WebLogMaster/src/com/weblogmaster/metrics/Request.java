/**
 * 
 */
package com.weblogmaster.metrics;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;


import com.weblogmaster.util.Activity;
import com.weblogmaster.util.UserAgent;

/**
 * @author Patrick
 *
 */

public class Request {

	/**
	 * 
	 */
	private String requestorIP;
	private Date requestTime;
	private Activity activity;
	private int httpStatusCode;
	private long documentSize;
	private String refererURL;
	private UserAgent usrAgent;
	
	
	public Request(String requestString) {
		
		
		StringTokenizer tok = new StringTokenizer(requestString);
		int numberOfTokens = tok.countTokens();
		String[] tokens = new String[numberOfTokens];
		int index = 0;
		while (tok.hasMoreTokens()){
			tokens[index++] = tok.nextToken();
		}
		requestorIP = tokens[0];
		DateFormat format = new SimpleDateFormat("dd/MMM/yy:hh:mm:ss");
		try {
			requestTime = format.parse(tokens[3].substring(1));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(tokens[1].substring(1));
		}
		activity = new Activity(tokens[5].replaceAll("\"", "")+" "+tokens[6]+" "+tokens[7]);
		try{
		httpStatusCode = Integer.parseInt(tokens[8]);
		}
		catch(Exception e){
			httpStatusCode = 0;
		}
		try{
		documentSize = Long.parseLong(tokens[9]);
		}
		catch(Exception e){
			documentSize = 0;
		}
		refererURL = tokens[10].replaceAll("\"", "");
	}


	/**
	 * @return the requestorIP
	 */
	public String getRequestorIP() {
		return requestorIP;
	}


	/**
	 * @param requestorIP the requestorIP to set
	 */
	public void setRequestorIP(String requestorIP) {
		this.requestorIP = requestorIP;
	}


	/**
	 * @return the requestTime
	 */
	public Date getRequestTime() {
		return requestTime;
	}


	/**
	 * @param requestTime the requestTime to set
	 */
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}


	/**
	 * @return the activity
	 */
	public Activity getActivity() {
		return activity;
	}


	/**
	 * @param activity the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}


	/**
	 * @return the httpStatusCode
	 */
	public int getHttpStatusCode() {
		return httpStatusCode;
	}


	/**
	 * @param httpStatusCode the httpStatusCode to set
	 */
	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}


	/**
	 * @return the documentSize
	 */
	public long getDocumentSize() {
		return documentSize;
	}


	/**
	 * @param documentSize the documentSize to set
	 */
	public void setDocumentSize(long documentSize) {
		this.documentSize = documentSize;
	}


	/**
	 * @return the refererURL
	 */
	public String getRefererURL() {
		return refererURL;
	}


	/**
	 * @param refererURL the refererURL to set
	 */
	public void setRefererURL(String refererURL) {
		this.refererURL = refererURL;
	}


	/**
	 * @return the usrAgent
	 */
	public UserAgent getUsrAgent() {
		return usrAgent;
	}


	/**
	 * @param usrAgent the usrAgent to set
	 */
	public void setUsrAgent(UserAgent usrAgent) {
		this.usrAgent = usrAgent;
	}

}
