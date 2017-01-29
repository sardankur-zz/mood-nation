package com.example.electionfeed.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.electionfeed.R;
import com.example.electionfeed.model.Tweet;

public class TweetListAdapter extends ArrayAdapter<Tweet> {
	private ArrayList<Tweet> tweetList;
	private Context context;
	
	public TweetListAdapter(Context context, int resource, ArrayList<Tweet> tweets) {
		super(context, resource, tweets);
		this.context = context;
		this.tweetList = tweets;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.tweet_display_item, parent, false);
	    TextView textView = (TextView) rowView.findViewById(R.id.tweet_text);
	    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon_tweet);
	    textView.setText(this.tweetList.get(position).getTweet());	   
	    // imageView.setText(partyList.get(position).getPartyImageString());
	    return rowView;
	}

	@Override
	public void add(Tweet object) {				
		super.add(object);
		notifyDataSetChanged();
	}
}

