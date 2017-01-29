package com.example.electionfeed;

import java.util.ArrayList;

import com.example.electionfeed.adapter.PartyListAdapter;
import com.example.electionfeed.model.Party;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class PartyList extends Activity {
	
	private ListView partyListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.party_list);	
		partyListView = (ListView)findViewById(R.id.listView1);
		PartyListAdapter partyListAdapter = new PartyListAdapter(this, R.layout.party_list_item, getPartyList());
		partyListView.setAdapter(partyListAdapter);
	}
	
	public ArrayList<Party> getPartyList() {
		ArrayList<Party> partyList = new ArrayList<Party>();
		for(int i=0; i<Constant.PARTY_LIST.length; ++i) {
			partyList.add(new Party(Constant.PARTY_LIST[i], ""));
		}
		return partyList;
	}
	
	
}

	
