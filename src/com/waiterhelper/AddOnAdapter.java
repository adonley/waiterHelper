package com.waiterhelper;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AddOnAdapter extends ArrayAdapter<String>{

	private Context contextHldr;
	private List<String> addOns;
	
	public AddOnAdapter(Context context, int textViewResourceId, List<String> objects) {
		super(context, textViewResourceId, objects);
		contextHldr = context;
		addOns = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		TextView addOnText;
		
		if(row == null) {
			LayoutInflater inflater = (LayoutInflater) contextHldr.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.rowaddon, null);
		}
		
		if(!(addOns.isEmpty()) && position < addOns.size()) {
			addOnText = (TextView) row.findViewById(R.id.addOnText);
			addOnText.setText(addOns.get(position).toString());
		}
		
		return row;
	}

}
