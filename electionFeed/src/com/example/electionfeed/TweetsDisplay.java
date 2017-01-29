package com.example.electionfeed;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.electionfeed.adapter.TweetListAdapter;
import com.example.electionfeed.model.Tweet;
import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;

import com.examplefeed.api.ApiCall;

public class TweetsDisplay extends FragmentActivity {
		

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tweets_display);	
		Intent intent = getIntent();
		String partyName = intent.getExtras().getString(Constant.PARTY_NAME);
		partyName = Constant.BJP;
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_janta).toUpperCase(l);
			case 1:
				return getString(R.string.title_critics).toUpperCase(l);
			case 2:
				return getString(R.string.title_party).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		
		private TextView positive_tweet;
		private TextView negative_tweet;
		private TextView neutral_tweet;
		private ListView tweet_display;
		private ArrayList<Tweet> tweets;
		private TweetListAdapter tweetListAdapter;
		private Fragment fragment;
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			positive_tweet = (Button) rootView
					.findViewById(R.id.positive_tweet);
			negative_tweet = (Button) rootView
					.findViewById(R.id.negative_tweet);
			neutral_tweet = (Button) rootView
					.findViewById(R.id.neutral_tweet);
			tweet_display = (ListView) rootView
					.findViewById(R.id.listView_tweets);
			
			fragment = this;
			
			positive_tweet.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(fragment.getActivity(), SentinmentTweetsDisplay.class);
					startActivity(intent);
					
				}
			});
			
			negative_tweet.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(fragment.getActivity(), SentinmentTweetsDisplay.class);
					startActivity(intent);
					
				}
			});
			
			neutral_tweet.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(fragment.getActivity(), SentinmentTweetsDisplay.class);
					startActivity(intent);
					
				}
			});			
			
			
			// call api			
			tweets = new ArrayList<Tweet>();			
			final int tab_no = getArguments().getInt(ARG_SECTION_NUMBER);			
			tweetListAdapter = new TweetListAdapter(this.getActivity(), R.layout.tweet_display_item, tweets);			
			tweet_display.setAdapter(tweetListAdapter);	
			callTweetService(Constant.BJPHandle, tab_no);			
			return rootView;
		}	
		
		public void callTweetService(final String partyName, final int type) {
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
					if(type == 3) {
						String result = ApiCall.getPartyFeeds(partyName);
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
					} else if(type == 2) {
						
						String result = ApiCall.getFeedsForPartyByCritic(partyName);
						HashMap jsonData = (HashMap) parser.parseJson(result);
						Iterator it = jsonData.entrySet().iterator();
						while (it.hasNext()) {
					        Map.Entry pairs = (Map.Entry)it.next();		 
					        tweet = (String)pairs.getKey();
					        values = (ArrayList<String>)pairs.getValue();
					        tweets.add(new Tweet(tweet, values.get(0), values.get(1)));					        
						}
						tweetListAdapter.notifyDataSetChanged();
						//.postDelayed(this, 10000);
						
					} else if(type == 1) {
						
						String result = ApiCall.getJuntaFeeds(partyName);
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
				}
			};
			handler.postDelayed(runnable, 0);
		}
	}	

}