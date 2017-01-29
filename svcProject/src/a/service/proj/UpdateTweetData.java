package a.service.proj;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;

import twitter4j.Status;
import twitter4j.TwitterException;

public class UpdateTweetData {
	
	/*Tweet_Data consists of party (name of the political party), tweet (the tweet about the party),
	 sentiment (flag to determine if the tweet is positive, negative or neutral, 0 for positive, 1 for 
	 negative and 2 for neutral) and tweet_from (flag to determine if the tweet is from junta or critic,
	 0 for critic and 1 for junta)  */
	
	static Map<String, List<String>> hashTagMapping = new HashMap<String, List<String>>();
	static List<String> userNameMapping = new ArrayList<String>();
	
	public static HashMap<String, List<String>> getPublicTweets(String partyName) throws TwitterException{
		updateHashTagMap();
		String partyNameToLower = partyName.toLowerCase();
				
		List<String> hashTags = hashTagMapping.get(partyNameToLower);
		HashMap<String, List<String>> tweets = new HashMap<>();
		HashMap<String, List<String>> tweetList = new HashMap<>();
		
		for (int i=0; i<hashTags.size(); i++){
			tweets = TwitterApiCalls.getTweetsFromHashTag(hashTags.get(i));
			tweetList.putAll(tweets);
		}
		
		return tweetList;
	}
	
	public static HashMap<String, List<String>> getCriticTweets(String partyName) throws ClientProtocolException, IOException, TwitterException{
		updateUserNameMapping();
		HashMap<String, List<String>> tweetList = new HashMap<String, List<String>>();
		String partyNameToLower = partyName.toLowerCase();
		List<String> hashTags = hashTagMapping.get(partyNameToLower);
		
		for (String userName : userNameMapping){
			HashMap<String,List<String>> tweets = TwitterApiCalls.getTweetsFromUserName(userName);
			
			Set<String> keySet = tweets.keySet();
			List<String> keyList = new ArrayList<String>(keySet);
			
			for(int i = 0; i<keyList.size(); i++){
				for(int j=0; j<hashTags.size(); j++){
					if(keyList.get(i).contains(hashTags.get(j)))
						tweetList.put(keyList.get(i), tweets.get(keyList.get(i)));
				}
			}
		}
		
		return tweetList;
	}
	
	public static HashMap<String, List<String>> getTweetsWithSentiment(String partyName, int sentiment, int tweet_from) throws TwitterException, ClientProtocolException, IOException{
		//Sentiment: 0 for positive, 1 for negative and 2 for neutral
		//tweet_from: 0 for public and 1 for junta
		HashMap<String, List<String>> tweetList = null;
		
		if(tweet_from == 0)
			tweetList = getPublicTweets(partyName);
		else
			tweetList = getCriticTweets(partyName);
		
		HashMap<String, List<String>> list = new HashMap();
		
		Set<String> keySet = tweetList.keySet();
		List<String> keyList = new ArrayList<String>(keySet);
		
		int count = 0;
		
		for(int i=0; i<keyList.size() && count<10 ; i++){
			count++;
			if(SentiAnalysisCalls.getSentiAnalysis(URLEncoder.encode(keyList.get(i),"UTF-8")) == sentiment)
					list.put(keyList.get(i), tweetList.get(keyList.get(i)));
		}
		
		return list;
	}
	public static String getSentimentNumbers(String partyName) throws TwitterException, ClientProtocolException, IOException{
		//Sentiment: 0 for positive, 1 for negative and 2 for neutral
		//tweet_from: 0 for public and 1 for junta
		HashMap<String, List<String>> tweetList = null;
		
		tweetList = getPublicTweets(partyName);
		
		HashMap<String, List<String>> list = new HashMap();
		
		Set<String> keySet = tweetList.keySet();
		List<String> keyList = new ArrayList<String>(keySet);
		
		int jpos = 0,jneg=0,jneut=0,count=0;
		
		for(int i=0; i<keyList.size() && count<10 ; i++){
			count++;
			if(SentiAnalysisCalls.getSentiAnalysis(URLEncoder.encode(keyList.get(i),"UTF-8")) == 0){
				jneut++;
			}else  	if(SentiAnalysisCalls.getSentiAnalysis(URLEncoder.encode(keyList.get(i),"UTF-8")) == 1){
				jpos++;
			}else 	if(SentiAnalysisCalls.getSentiAnalysis(URLEncoder.encode(keyList.get(i),"UTF-8")) == -1){
				jneg++;
			} 

		}
		
		tweetList = getCriticTweets(partyName);
		
		keySet = tweetList.keySet();
		keyList = new ArrayList<String>(keySet);
		
		int cpos = 0,cneg=0,cneut=0;
		count = 0;
		
		for(int i=0; i<keyList.size() && count<10 ; i++){
			count++;
			if(SentiAnalysisCalls.getSentiAnalysis(URLEncoder.encode(keyList.get(i),"UTF-8")) == 0){
				cneut++;
			}else  	if(SentiAnalysisCalls.getSentiAnalysis(URLEncoder.encode(keyList.get(i),"UTF-8")) == 1){
				cpos++;
			}else 	if(SentiAnalysisCalls.getSentiAnalysis(URLEncoder.encode(keyList.get(i),"UTF-8")) == -1){
				cneg++;
			} 
		}
		
		int tot = 10;
		StringBuilder sb = new StringBuilder();
		
		sb.append(getPercantage(jpos,tot));
		sb.append(",");
		sb.append(getPercantage(jneut,tot));
		sb.append(",");
		sb.append(getPercantage(jneg,tot));
		sb.append(",");
		
		sb.append(getPercantage(cpos,tot));
		sb.append(",");
		sb.append(getPercantage(cneut,tot));
		sb.append(",");
		sb.append(getPercantage(cneg,tot));
		
		return sb.toString();
	}
	
	public static int getPercantage(int a, int tot){
		//System.out.println((a* 100)/tot);
		return ((a* 100)/tot);
	}
	
	public int countTweets(String partyName, int sentiment, int tweet_from){
		int sentiments = 0;
		return sentiments;
	}
	
	public static void updateHashTagMap(){
		List<String> values = new ArrayList<String>();
		values.add("Modi");
		values.add("bjp");
		values.add("NaMo");
		values.add("liesofmodigovt");
		hashTagMapping.put("bjp", values);
		
		values = new ArrayList<String>();
		values.add("congress");
		values.add("abkibaarmodikihaar");
		values.add("rahulgandhi");
		values.add("soniagandhi");
		hashTagMapping.put("congress", values);
		
		values = new ArrayList<String>();
		values.add("aap");
		values.add("kejriwalinvaranasi");
		values.add("Kejriwal");
		values.add("KrantiKariAAPGovt");
		hashTagMapping.put("aap", values);
	
	}
	
	public static void updateUserNameMapping(){
		userNameMapping.add("sardesairajdeep");
		userNameMapping.add("aajtaknews");
		userNameMapping.add("StarNews");
		userNameMapping.add("ZeeNews");
		userNameMapping.add("ndtv");
	}
	
	
	public static void main(String args[]) throws TwitterException, ClientProtocolException, IOException {
	/*	updateHashTagMap();
		updateUserNameMapping();
		getSentimentNumbers("bjp");*/
		getPercantage(10, 40);
	}
	
}
