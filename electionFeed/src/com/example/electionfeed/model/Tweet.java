package com.example.electionfeed.model;

import java.util.Date;

public class Tweet {
	private String tweet;
	private String url;
	private Date date;
	
	public Tweet(String tweet, String url, String date) {
		this.tweet = tweet;
		this.url = url;
		this.date = new Date();
	}
	
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
