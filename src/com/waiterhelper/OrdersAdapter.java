package com.waiterhelper;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.waiterhelper.database.DataBase;
import com.waiterhelper.database.DataBase.Order;

public class OrdersAdapter extends ArrayAdapter<Order>{
	
	private Context contextHldr;
	private List<Order> orders;
	protected int entryPos;

	public OrdersAdapter(Context context, int textViewResourceId, List<Order> objects, int entryPosition) {
		super(context, textViewResourceId, objects);
		
		contextHldr = context;
		orders = objects;
		entryPos = entryPosition;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row = convertView;
		TextView orderName;
		TextView addOnNames;
		CheckBox select;
		//OnCheckedChangeListenerCustom checkListener;
		//ListView addOnsList;
		//AddOnAdapter addOnAdpt;
		
		if(row == null) {
			LayoutInflater inflater = (LayoutInflater) contextHldr.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.roworder, null);
		}
		
		if(position < orders.size() && orders.get(position).getName() != null) {
			orderName  =  (TextView) row.findViewById(R.id.ordersText);
			orderName.setText(orders.get(position).getName());
			select = (CheckBox) row.findViewById(R.id.orderCheckBox);
			select.setChecked(false);
			orders.get(position).checkListener = new OnCheckedChangeListenerCustom(position);
	        select.setOnCheckedChangeListener(orders.get(position).checkListener);
		}
		
		if(orders.get(position).hasAddons()) {
			List <String> additions = orders.get(position).getAdditions();
			String aggro = "";
			
			for(String s : additions) {
				aggro = aggro + s + " ";
			}
			addOnNames = (TextView) row.findViewById(R.id.addOnTextView);
			addOnNames.setText(aggro);

			//addOnsList = (ListView) row.findViewById(R.id.addOnList);
			//addOnAdpt = new AddOnAdapter(contextHldr, R.layout.rowaddon, orders.get(position).getAdditions());
			//addOnsList.setAdapter(addOnAdpt);
		}
		else {
			addOnNames = (TextView) row.findViewById(R.id.addOnTextView);
			addOnNames.setText("");
		}
	
		return row;
		
	}
	
	public class OnCheckedChangeListenerCustom implements OnCheckedChangeListener {

		private int position;
		private DataBase data = DataBase.getInstance();
		
		
		public void changePosition(int tempPosi) {
			this.position = tempPosi;
		}
		
		public OnCheckedChangeListenerCustom(int tempPosition) {
			this.position = tempPosition;
		}
		
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			data.get(entryPos).orders.get(position).changeSelected();
		}
		
	};

}
