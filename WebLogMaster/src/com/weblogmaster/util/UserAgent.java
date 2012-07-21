/**
 * 
 */
package com.weblogmaster.util;

import java.util.StringTokenizer;

/**
 * @author Patrick
 *
 */

public class UserAgent {

	/**
	 * 
	 */
	private String OS;
	private String agentName;
	private String language;
	public UserAgent(String userString) {
		
		StringTokenizer tok = new StringTokenizer(userString);
		String[] tokens = new String[tok.countTokens()];
		int index = 0;
		while (tok.hasMoreElements()){
			tokens[index++] = tok.nextToken();
		}
		agentName = tokens[0].replaceAll("\"", "");
		
	}
	
	
	
	
	/**
	 * @return the oS
	 */
	public String getOS() {
		return OS;
	}
	/**
	 * @param oS the oS to set
	 */
	public void setOS(String oS) {
		OS = oS;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @param agentName the agentName to set
	 */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	/**
	 * @return the agentName
	 */
	public String getAgentName() {
		return agentName;
	}

}
