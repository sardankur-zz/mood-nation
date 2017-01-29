package a.service.proj;

import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import twitter4j.TwitterException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class SentiAnalysisCalls {
	
	
	static String alchemy_api_key="c5b603f02b5f01d2a033a2b20f26d6fae688fe7d";//c4dcd11a5f381349e93d56d6f4e5dbd0f29cd576
	static String uri="http://access.alchemyapi.com/calls/text/TextGetTextSentiment?apikey=c5b603f02b5f01d2a033a2b20f26d6fae688fe7d&text=";
	public static List<String> values = new ArrayList<String>();
	static {
	
	
	values.add("%23password+%23ebay");
	values.add("%23security+%23ebay+%23hack");
	values.add("%23privacy+%23ebay");
	values.add("%23hack+%23ebay");
	values.add("%23infosec+%23ebay");
	values.add("%23vulnerability+%23ebay");
	values.add("%23compromised+%23ebay");
	values.add("%23flaw+%23ebay");
	values.add("%23paypal+%23hack");
	values.add("%23ebay+%23hacked");
	values.add("%23ebay+%23password");
	values.add("ebay breach");
	values.add("password ebay");
	values.add("ebay hacked");
	values.add("ebay hack");
	values.add("ebay hijack");
	values.add("ebay password reset");
	values.add("ebay reset");
	values.add("ebay cyber attack");
	
	}
	public static int getSentiAnalysis(String text) throws ClientProtocolException, IOException{
		
		Client client = Client.create();
		String score;
		WebResource webResource = client
		   .resource(uri+text);
		
		ClientResponse response = webResource.accept("application/json")
                   .get(ClientResponse.class);
		
		if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}
			String output = response.getEntity(String.class);
	 
		//	System.out.println("Output from Server .... \n");
		//	System.out.println(output);
			if(output.contains("positive")){
				System.out.println("positive");
				score=output.split("<score>")[1].split("</score>"+output.split("<score>")[1].split("</score>")[0])[0];
				return 1;
					
			}else if(output.contains("neutral")){
				System.out.println("neutral"+output.split("<score>")[1].split("</score>")[0]);
				//score=output.split("<score>")[1].split("</score>")[0];
				return 0;
					
			}else if(output.contains("negative")){
				System.out.println("negative"+output.split("<score>")[1].split("</score>")[0]);
				score=output.split("<score>")[1].split("</score>")[0];
				return -1;
					
			}
			
			return 1;		
	}
public static String getSentiAnalysisWithScore(String text) throws ClientProtocolException, IOException{
		
		Client client = Client.create();
		String score;
		WebResource webResource = client
		   .resource(uri+text);
 
		ClientResponse response = webResource.accept("application/json")
                   .get(ClientResponse.class);
		
		if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}
			String output = response.getEntity(String.class);
	 
		//System.out.println("Output from Server .... \n");
		//	System.out.println(output);
			if(output.contains("positive")){
			//	System.out.println("positive");
				score=output.split("<score>")[1].split("</score>")[0];
				return "positive"+"----"+score;
					
			}else if(output.contains("neutral")){
				//System.out.println("neutral"+output.split("<score>")[1].split("</score>")[0]);
				//score=output.split("<score>")[1].split("</score>")[0];
				return "neutral----"+0;
					
			}else if(output.contains("negative")){
				//System.out.println("negative"+output.split("<score>")[1].split("</score>")[0]);
				score=output.split("<score>")[1].split("</score>")[0];
				return "negative----"+score;
					
			}
			
			return "positive----0.5";		
	}
public static HashMap<String, List<String>> getTweetsFromHashTag() throws TwitterException{
			
	HashMap<String, List<String>> tweets = new HashMap<>();
	HashMap<String, List<String>> tweetList = new HashMap<>();
	
	for (int i=0; i<values.size(); i++){
		tweets = TwitterApiCalls.getTweetsFromHashTag(values.get(i));
		tweetList.putAll(tweets);
		System.out.println(tweetList.size());
	}
	
	return tweetList;
}	
	
public static HashMap<String,List<String>> getSentiResults() throws TwitterException, ClientProtocolException, IOException{
	HashMap<String,List<String>> tweetList=new HashMap<>();
	tweetList=getTweetsFromHashTag();
	System.out.println(tweetList.keySet().size());
	 File file = new File("filename.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		BufferedWriter bw = new BufferedWriter(fw);
	for(String t:tweetList.keySet()){
		 String key =t.toString();
         String value = tweetList.get(t).toString(); 
         
        // System.out.println(key + "---" + value);
         
         System.out.println(key+"----"+getSentiAnalysisWithScore(URLEncoder.encode(key,"UTF-8")));
         
			// if file doesnt exists, then create it
			
			bw.write(key+"----"+getSentiAnalysisWithScore(URLEncoder.encode(key,"UTF-8"))+"\n");
			
	}
	bw.close();
//	System.out.println(tweetList);
	return null;
}
	public static void main(String[] args) throws ClientProtocolException, IOException{
	//	getSentiAnalysisWithScore(URLEncoder.encode("hurray #technology #online", "UTF-8"));
		try {
			getSentiResults();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}	
	