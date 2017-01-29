package a.service.proj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.apache.http.client.ClientProtocolException;

import twitter4j.HashtagEntity;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterApiCalls {

	static String consumer_key="PpCnb7TEziEcXzoRrpVgyg";
	static String consumer_secret="ySR8oPTxmnF75IwFlJN4zz12WcMOYgoNaPxkxbCqvc";
	static String access_token="89261937-BIF7heMBid1BsT9VUyMqSYqfCFrLEYn8abUrnBQcJ";
	static String access_token_secret="w80wq8XLz3LrxAG2y1WrgQz73YZCGyVkyOfURGa5NiJyJ";
	
	static List<String> bjpHashTags = Arrays.asList("modi", "bjp");
	static List<String> aapHashTags = Arrays.asList("aap", "kejriwal");
	static List<String> congHashTags = Arrays.asList("congress", "rahul");
	
	static List<String> criticHandles =Arrays.asList("sardesairajdeep", "aajtak");//, "StarNews", "ndtv", "ibnlive");
	
	String twitter_uri="https://www.api.twitter.com/1.1/";
	
	public static HashMap<String,String> getTweetsFromUserWoTime(String username) throws ClientProtocolException, IOException, TwitterException{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(consumer_key)
		  .setOAuthConsumerSecret(consumer_secret)
		  .setOAuthAccessToken(access_token)
		  .setOAuthAccessTokenSecret(access_token_secret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Paging page = new Paging (1, 100);
		Twitter twitter = tf.getInstance();
		List<Status> statuses = twitter.getUserTimeline(username,page);
		HashMap<String,String> tweets=new HashMap();
		for (Status status : statuses) {

			//System.out.println(status.getUser().getScreenName() + ":" +  status.getText()+":https://twitter.com/"+username+"/status/"+status.getId());
			tweets.put(status.getText(), ":https://twitter.com/"+username+"/status/"+status.getId());
		}

		return tweets;	
	}
	public static HashMap<String,List<String>> getTweetsFromUserName(String username) throws ClientProtocolException, IOException, TwitterException{
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(consumer_key)
		  .setOAuthConsumerSecret(consumer_secret)
		  .setOAuthAccessToken(access_token)
		  .setOAuthAccessTokenSecret(access_token_secret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Paging page = new Paging (1, 100);
		Twitter twitter = tf.getInstance();
		List<Status> statuses = twitter.getUserTimeline(username,page);
		HashMap<String,List<String>> tweets=new HashMap();
		
		for (Status status : statuses) {
			List<String> list=new ArrayList<String>();
			
			//System.out.println(status.getUser().getScreenName() + ":" +  status.getCreatedAt()+":"+status.getText()+":https://twitter.com/"+username+"/status/"+status.getId());
			System.out.println(String.valueOf(status.getCreatedAt().getTime()));
			list.add(String.valueOf(status.getCreatedAt().getTime()));
			list.add("https://twitter.com/"+username+"/status/"+status.getId());
			tweets.put(status.getText(), list);
		}
		
		return tweets;
		
	}
	public static HashMap<String,List<String>> getTweetsFromHashTag(String hash) throws TwitterException{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(consumer_key)
		  .setOAuthConsumerSecret(consumer_secret)
		  .setOAuthAccessToken(access_token)
		  .setOAuthAccessTokenSecret(access_token_secret);
		Query query= new Query(hash);
		query.setCount(100);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		//Paging page = new Paging (1, 1500);
		QueryResult result = twitter.search(query);
		List<Status> statuses = new ArrayList<Status>();
		statuses.addAll(result.getTweets());
		query=result.nextQuery();
		int counter=0;
		while(query!=null || counter==10){
			result=twitter.search(query);
			statuses.addAll(result.getTweets());
			query=result.nextQuery();
			counter++;
		}
		/*HashMap<String,String> tweets=new HashMap();
	    for (Status status : statuses) {
	    	//System.out.println(status.getUser().getName() + ":" +  status.getText()+":https://twitter.com/"+status.getUser().getScreenName()+"/status/"+status.getId());
			tweets.put(status.getText(), "https://twitter.com/"+status.getUser().getScreenName()+"/status/"+status.getId());
		
	    }*/
		HashMap<String,List<String>> tweets=new HashMap();
		
		for (Status status : statuses) {
			List<String> list=new ArrayList<String>();
			
			//System.out.println(status.getUser().getScreenName() + ":" +  status.getCreatedAt()+":"+status.getText()+":https://twitter.com/"+username+"/status/"+status.getId());
		//	System.out.println(String.valueOf(status.getCreatedAt().getTime()));
			list.add(String.valueOf(status.getCreatedAt().getTime()));
			list.add("https://twitter.com/"+status.getUser().getScreenName()+"/status/"+status.getId());
			tweets.put(status.getText(), list);
		}
		return tweets;
		
	}
	
	public static HashMap<String,List<String>> getTweetsFromSearch(String keyword) throws TwitterException{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(consumer_key)
		  .setOAuthConsumerSecret(consumer_secret)
		  .setOAuthAccessToken(access_token)
		  .setOAuthAccessTokenSecret(access_token_secret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		Paging page = new Paging (1, 100);
		System.out.println(keyword);
		QueryResult result = twitter.search(new Query(keyword));
		List<Status> statuses = result.getTweets();
		/*HashMap<String,String> tweets=new HashMap();
	    for (Status status : statuses) {
	    	//System.out.println(status.getUser().getName() + ":" +  status.getText()+":https://twitter.com/"+status.getUser().getScreenName()+"/status/"+status.getId());
			tweets.put(status.getText(), "https://twitter.com/"+status.getUser().getScreenName()+"/status/"+status.getId());
		
	    }*/
		HashMap<String,List<String>> tweets=new HashMap();
		
		for (Status status : statuses) {
			List<String> list=new ArrayList<String>();
			
			//System.out.println(status.getUser().getScreenName() + ":" +  status.getCreatedAt()+":"+status.getText()+":https://twitter.com/"+username+"/status/"+status.getId());
			System.out.println(String.valueOf(status.getCreatedAt().getTime()));
			list.add(String.valueOf(status.getCreatedAt().getTime()));
			list.add("https://twitter.com/"+status.getUser().getScreenName()+"/status/"+status.getId());
			tweets.put(status.getText(), list);
		}
		return tweets;
		
	}
	
	public static HashMap<String,List<String>> getTweetsFromJunta(String party) throws TwitterException{
		//based on hashtags
		HashMap<String,List<String>> combinedTweets=new HashMap<>();
		int i=0;
		if(party.equals("bjp")){
		while(i<bjpHashTags.size()){
			combinedTweets.putAll(getTweetsFromHashTag(bjpHashTags.get(i)));
			i++;
		}
		i=0;
		}else if(party.equals("congress")){
			while(i<congHashTags.size()){
				combinedTweets.putAll(getTweetsFromHashTag(congHashTags.get(i)));
				i++;
			}
			i=0;
		}else if(party.equals("aap")){
			while(i<aapHashTags.size()){
				combinedTweets.putAll(getTweetsFromHashTag(aapHashTags.get(i)));
				i++;
			}
			i=0;
		}
		
		return combinedTweets;
	}
	
	public static HashMap<String,List<String>> getTweetsFromCritics(String party) throws TwitterException{
		//based on hashtags and critic tags
		HashMap<String,List<String>> combinedTweets=new HashMap<>();
		int i=0,j=0;
		if(party.equals("bjp")){
		for(i=0;i<bjpHashTags.size();i++){
			for(j=0;j<criticHandles.size();j++){
				combinedTweets.putAll(getTweetsFromSearch(bjpHashTags.get(i)+" @"+criticHandles.get(j)));
			}
		}
		}else if(party.equals("congress")){
			for(i=0;i<congHashTags.size();i++){
				for(j=0;j<criticHandles.size();j++){
					combinedTweets.putAll(getTweetsFromSearch(congHashTags.get(i)+" @"+criticHandles.get(j)));
				}
			}}else if(party.equals("aap")){
				for(i=0;i<aapHashTags.size();i++){
					for(j=0;j<criticHandles.size();j++){
						combinedTweets.putAll(getTweetsFromSearch(aapHashTags.get(i)+" @"+criticHandles.get(j)));
					}
				}
		}
		
		return combinedTweets;
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException, TwitterException{
		getTweetsFromUserName("pyth0n_");
		//getTweetsFromHashTag("aap");
		//getTweetsFromJunta("bjp");
	}
	
}