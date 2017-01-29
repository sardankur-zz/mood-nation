package com.example.electionfeed.model;

public class Party {
	
	private String partyName;
	private String partyImageString;
	
	public Party(String partyName, String partyImageString) {
		this.partyName = partyName;
		this.partyImageString = partyImageString;
	}
	
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getPartyImageString() {
		return partyImageString;
	}
	public void setPartyImageString(String partyString) {
		this.partyImageString = partyString;
	}		

}
