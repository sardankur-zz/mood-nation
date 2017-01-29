package com.examplefeed.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.StrictMode;
import android.util.Log;

public class ApiCall {
	
	public static String getPartyFeeds(String partyName) {
		
		if (android.os.Build.VERSION.SDK_INT > 9) 
		{
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		DefaultHttpClient httpclient = new DefaultHttpClient();		
        HttpGet httpget = new HttpGet("http://env-2443276.j.layershift.co.uk/svc/rest/moodn/usertweet?user=BJP4India");
        httpget.setHeader("Content-Type", "application/json");
        httpget.setHeader("Accept", "application/json");
        HttpResponse response;
        String result = "";
		try {
			response = httpclient.execute(httpget);
		
			BufferedReader in;
		
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		
	        StringBuffer sb = new StringBuffer("");
	        String line = "";
	        String NL = System.getProperty("line.separator");
	        while ((line = in.readLine()) != null) {                    
	            sb.append(line + NL);
	        }
	        in.close();
	        result = sb.toString();  
	        
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getJuntaFeeds(String partyName) {
		
		if (android.os.Build.VERSION.SDK_INT > 9) 
		{
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		DefaultHttpClient httpclient = new DefaultHttpClient();		
        HttpGet httpget = new HttpGet("http://env-2443276.j.layershift.co.uk/svc/rest/moodn/hashtweet?hash=bjp");
        httpget.setHeader("Content-Type", "application/json");
        httpget.setHeader("Accept", "application/json");
        HttpResponse response;
        String result = "";
		try {
			response = httpclient.execute(httpget);
		
			BufferedReader in;
		
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		
	        StringBuffer sb = new StringBuffer("");
	        String line = "";
	        String NL = System.getProperty("line.separator");
	        while ((line = in.readLine()) != null) {                    
	            sb.append(line + NL);
	        }
	        in.close();
	        result = sb.toString();  
	        
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getFeedsForPartyByCritic(String partyName) {
		
		if (android.os.Build.VERSION.SDK_INT > 9) 
		{
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://env-2443276.j.layershift.co.uk/svc/rest/moodn/critictweets?party=bjp");
        HttpResponse response;
        httpget.setHeader("Content-Type", "application/json");
        httpget.setHeader("Accept", "application/json");
        String result = "";
		try {
			response = httpclient.execute(httpget);
		
			BufferedReader in;
		
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		
	        StringBuffer sb = new StringBuffer("");
	        String line = "";
	        String NL = System.getProperty("line.separator");
	        while ((line = in.readLine()) != null) {                    
	            sb.append(line + NL);
	        }
	        in.close();
	        result = sb.toString();  
	        
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getFeedsForPartyByTypeAndSentiment(String partyName, int sentiment, int type) {
		
		if (android.os.Build.VERSION.SDK_INT > 9) 
		{
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://env-2443276.j.layershift.co.uk/svc/rest/moodn/getTweets?party=aap&sentiment=1&tweet_from=0");
        HttpResponse response;
        httpget.setHeader("Content-Type", "text/plain");
        httpget.setHeader("Accept", "application/json");
        String result = "";
		try {
			response = httpclient.execute(httpget);
		
			BufferedReader in;
		
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		
	        StringBuffer sb = new StringBuffer("");
	        String line = "";
	        String NL = System.getProperty("line.separator");
	        while ((line = in.readLine()) != null) {                    
	            sb.append(line + NL);
	        }
	        in.close();
	        result = sb.toString();  
	        
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
