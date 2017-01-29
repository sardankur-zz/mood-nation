package com.example.electionfeed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.example.electionfeed.adapter.TweetListAdapter;
import com.example.electionfeed.model.Tweet;
import com.examplefeed.api.ApiCall;
import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.TextView;

public class SentinmentTweetsDisplay extends Activity {
	
	private ListView sentiment_tweet_list;
	private ArrayList<Tweet> tweets;
	private TextView sentiment_Header;
	private TweetListAdapter tweetListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sentiment_tweet_display);
		sentiment_tweet_list = (ListView) findViewById(R.id.listView2);
		/*sentiment_Header = (TextView) findViewById(R.id.tweet_sentiment_header);
		sentiment_Header.setText(Constant.BJP);*/
		tweets = new ArrayList<Tweet>();
		//get the type from intent
		
		int type = 4;		
		tweetListAdapter = new TweetListAdapter(this, R.layout.tweet_display_item, tweets);			
		sentiment_tweet_list.setAdapter(tweetListAdapter);	
		callTweetService("bjp", 1, 0);	
						
	}		
	
	public void callTweetService(final String partyName, final int sentiment, final int type) {
		JsonParserFactory factory=JsonParserFactory.getInstance();
		final JSONParser parser= factory.newJsonParser();
		
		final Handler handler = new Handler();
		Runnable runnable = new Runnable() {
			public void run() {				
				handler.removeCallbacks(this);
				String tweet = "";
				String tweetURL = "";
				ArrayList<String> values;
				// call API		
				
					String result = ApiCall.getFeedsForPartyByTypeAndSentiment(partyName,sentiment, type);
					HashMap jsonData = (HashMap) parser.parseJson(result);
					Iterator it = jsonData.entrySet().iterator();
					while (it.hasNext()) {
				        Map.Entry pairs = (Map.Entry)it.next();		 
				        tweet = (String)pairs.getKey();
				        values = (ArrayList<String>)pairs.getValue();
				        tweets.add(new Tweet(tweet, values.get(0), values.get(1)));					        							
				    }
					tweetListAdapter.notifyDataSetChanged();
					//handler.postDelayed(this, 10000);																											
			}
		};
		handler.postDelayed(runnable, 0);
	}
}
