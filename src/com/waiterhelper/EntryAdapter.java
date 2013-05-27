package com.waiterhelper;

import java.util.ArrayList;

import com.waiterhelper.database.DataBase;
import com.waiterhelper.database.DataBase.Entry;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EntryAdapter extends ArrayAdapter<Entry>{
	
	private DataBase data = DataBase.getInstance();
	private Context contextHldr;

	public EntryAdapter(Context context, int textViewResourceId, ArrayList<Entry> objects) {
		super(context, textViewResourceId, objects);
		contextHldr = context;
	}

	@Override
	 public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        Entry e = null;
        Button delete;
        final int tempPosition = position;
        
        if( row == null) {
        	LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	row = inflater.inflate(R.layout.row, null);
        }
        
        e = data.get(position);
        if(e !=null){
        	String orderAggregation = "";
        	TextView toptext = (TextView) row.findViewById(R.id.toptext);
        	TextView bottomtext = (TextView) row.findViewById(R.id.bottomtext);
        	toptext.setText(e.getName());
        	toptext.setTextColor(Color.BLACK);
        	toptext.setClickable(true);
        	bottomtext.setText("Orders:");
        	
        	// Aggregate
        	if(e.orders.isEmpty()){
        		bottomtext.setHint("Click to Add Orders");
        	}
        	else {
        		
        		for(int i = 0; i < e.orders.size(); i++) {
        			if(i < e.orders.size()-1) {
        				orderAggregation = orderAggregation + e.orders.get(i).getName() + ", ";
        			}
        			else
            			orderAggregation = orderAggregation + e.orders.get(i).getName();		
        		}
        	}
        	
        	delete = (Button)row.findViewById(R.id.deleteButton);
        	
        	delete.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					data.remove(tempPosition);
					notifyDataSetChanged();
					
				}
			});
        	
        	toptext.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                	
                	// Need to inflate an edit text here if possible
                	//   long click would be better to handle this, short click to table view again
                	CharSequence text = "Clicked Text";
                	int duration = Toast.LENGTH_SHORT;

                	Toast toast = Toast.makeText(contextHldr, text, duration);
                	toast.show();
                }
            });
        	
        	row.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                	
                	// Open the table view for the click
                	EntryAdapter.this.viewTable(tempPosition);
                }

            });
        	
        	bottomtext.setText(orderAggregation);
        }
        return row;
	}
	
    public void viewTable(int position) {
    	// Start TableViewer class with the position of table that was selected.
    	Intent intent = new Intent(contextHldr, TableViewer.class);
    	intent.putExtra(WaiterHelper.EXTRA_POSITION, position);
    	contextHldr.startActivity(intent);
    	return;
    }


}
