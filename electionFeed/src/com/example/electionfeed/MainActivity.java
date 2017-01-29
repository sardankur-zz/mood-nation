package com.example.electionfeed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

// Landing class on start of application
public class MainActivity extends Activity {	

	private ListView navDrawerList;
	private String[] NAV_DRAWER_LIST_STRING = { "Voice of People", "Tweet", "Compare", "BJP", "Congress", "AAP"};
	private DrawerLayout navDrawer;
	private static RelativeLayout mainPage;	
	private Activity activity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		activity = this;
		navDrawerList = (ListView)findViewById(R.id.nav_drawer_list);
		navDrawer = (DrawerLayout)findViewById(R.id.nav_drawer);
		mainPage = (RelativeLayout)findViewById(R.id.main_page);		
		
		ArrayAdapter<String> navDrawerListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, NAV_DRAWER_LIST_STRING);
		navDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				switch(position) {
					case 0:						
					break;
					
					case 1:
							break;
					case 2:
							Intent intent_compare = new Intent(activity, CompareParty.class);
							activity.startActivity(intent_compare);
							break;
					case 3:	//BJP
							Intent intent_tweets = new Intent(activity, TweetsDisplay.class);
							//Bundle bundle = new Bundle();
			                //bundle.putString(Constant.PARTY_NAME, Constant.BJP);
							//intent_tweets.putExtras(bundle);
							intent_tweets.putExtra(Constant.PARTY_NAME, Constant.BJP);
							activity.startActivity(intent_tweets);
							break;
					case 4:	//Congress
							Intent intent_tweets2 = new Intent(activity, TweetsDisplay.class);
							intent_tweets2.putExtra(Constant.PARTY_NAME, Constant.CONGRESS);
							activity.startActivity(intent_tweets2);
							break;
					case 5:	//AAP
							Intent intent_tweets3 = new Intent(activity, TweetsDisplay.class);
							intent_tweets3.putExtra(Constant.PARTY_NAME, Constant.AAP);
							activity.startActivity(intent_tweets3);
							break;
					default:						
				}
				
			}
			
		});
		navDrawerList.setAdapter(navDrawerListAdapter);		
		
		final Button party = (Button) findViewById(R.id.button1);
		party.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, PartyList.class);
                activity.startActivity(intent);
            }
        });
		
		final Button compare = (Button) findViewById(R.id.button2);
		compare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, CompareParty.class);
                activity.startActivity(intent);
            }
        });
		
	}			

}
