package a.service.proj;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jettison.json.JSONArray;

import twitter4j.Status;
import twitter4j.TwitterException;

// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@XmlRootElement
@Path("/moodn")
public class ReturnCalls {
	 @GET
	  @Path("/hashtweet")
	  @Produces(MediaType.APPLICATION_JSON)
	  @Consumes(MediaType.APPLICATION_JSON)
	  public HashMap<String, List<String>> getAllTweetsForHashtag(@QueryParam(value = "hash") String hash) throws ClientProtocolException, IOException, TwitterException {
			System.out.println(hash);	
		  return TwitterApiCalls.getTweetsFromHashTag(hash);
	  }
	  
	 @GET
	  @Path("/usertweet")
	  @Produces(MediaType.APPLICATION_JSON)
	  @Consumes(MediaType.APPLICATION_JSON)
	  public HashMap<String, List<String>> getAllTweetsForUser(@QueryParam(value = "user") String user) throws ClientProtocolException, IOException, TwitterException {
		  return TwitterApiCalls.getTweetsFromUserName(user);
		  
		//  return null;
	  }
	  
	  @GET
	  @Path("/critictweets")
	  @Produces(MediaType.APPLICATION_JSON)
	  @Consumes(MediaType.APPLICATION_JSON)
	  public HashMap<String, List<String>> getTweetsFromCritics(@QueryParam(value = "party") String party) throws ClientProtocolException, IOException, TwitterException {
		  return TwitterApiCalls.getTweetsFromCritics(party);
		  
		//  return null;
	  }
	 
	  @GET
	  @Path("/juntatweets")
	  @Produces(MediaType.APPLICATION_JSON)
	  @Consumes(MediaType.APPLICATION_JSON)
	  public HashMap<String, List<String>> getTweetsFromJunta(@QueryParam(value = "party") String party) throws ClientProtocolException, IOException, TwitterException {
		  return TwitterApiCalls.getTweetsFromJunta(party);
		  
		  //return null;
	  }
	  @GET
	  @Path("/getTweets")
	  @Produces(MediaType.APPLICATION_JSON)
	  @Consumes(MediaType.TEXT_PLAIN)
	  public HashMap<String, List<String>> getTweetsWithSentiments(@QueryParam(value = "party") String party, 
			  @QueryParam(value = "sentiment") int sentiment, 
			  @QueryParam(value = "tweet_from") int tweet_from ) throws ClientProtocolException, TwitterException, IOException{
		   return UpdateTweetData.getTweetsWithSentiment(party, sentiment, tweet_from);
		   
	  }
	  
	  @GET
	  @Path("/getPercentage")
	  @Produces(MediaType.TEXT_PLAIN)
	  @Consumes(MediaType.TEXT_PLAIN)
	  public String getNumbers(@QueryParam(value= "party") String party) throws ClientProtocolException, TwitterException, IOException{
		  return UpdateTweetData.getSentimentNumbers(party);
	  }
}