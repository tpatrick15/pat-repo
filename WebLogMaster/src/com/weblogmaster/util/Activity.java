/**
 * 
 */
package com.weblogmaster.util;

import java.util.StringTokenizer;

/**
 * @author Patrick
 *
 */

public class Activity {

	/**
	 * 
	 */
	private String httpRequest;
	private String URL;
	private String resourceId;
	private String protocol;
	private String protocolVersion;
	
	public Activity(String reqString) {
		
		StringTokenizer tok = new StringTokenizer(reqString);
		String[] tokens = new String[tok.countTokens()];
		int index = 0;
		while (tok.hasMoreElements()){
			tokens[index++] = tok.nextToken();
		}
		httpRequest = tokens[0];
		URL = tokens[1];
		resourceId = URL;
		String[] protocolInfo = tokens[2].split("/");
		protocol = protocolInfo[0];
		protocolVersion = protocolInfo[1];
	}

	/**
	 * @return the httpRequest
	 */
	public String getHttpRequest() {
		return httpRequest;
	}

	/**
	 * @param httpRequest the httpRequest to set
	 */
	public void setHttpRequest(String httpRequest) {
		this.httpRequest = httpRequest;
	}

	/**
	 * @return the uRL
	 */
	public String getURL() {
		return URL;
	}

	/**
	 * @param uRL the uRL to set
	 */
	public void setURL(String uRL) {
		URL = uRL;
	}

	/**
	 * @return the resourceId
	 */
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * @return the protocolVersion
	 */
	public String getProtocolVersion() {
		return protocolVersion;
	}

	/**
	 * @param protocolVersion the protocolVersion to set
	 */
	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	

}
