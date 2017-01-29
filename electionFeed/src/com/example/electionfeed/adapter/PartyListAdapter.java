package com.example.electionfeed.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.electionfeed.Constant;
import com.example.electionfeed.R;
import com.example.electionfeed.model.Party;

public class PartyListAdapter extends ArrayAdapter<Party> {

	private ArrayList<Party> partyList;
	private Context context;
	
	public PartyListAdapter(Context context, int resource, ArrayList<Party> parties) {
		super(context, resource, parties);
		this.context = context;
		this.partyList = getPartyList();
	}
	
	public ArrayList<Party> getPartyList() {
		ArrayList<Party> partyList = new ArrayList<Party>();
		for(int i=0; i<Constant.PARTY_LIST.length; ++i) {
			partyList.add(new Party(Constant.PARTY_LIST[i], ""));
		}
		return partyList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.party_list_item, parent, false);
	    TextView textView = (TextView) rowView.findViewById(R.id.party_name);
	    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
	    
	    textView.setText(partyList.get(position).getPartyName());
	    // imageView.setText(partyList.get(position).getPartyImageString());
	    return rowView;
	}

}
