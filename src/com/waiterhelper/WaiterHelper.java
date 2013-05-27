package com.waiterhelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.waiterhelper.database.DataBase;

public class WaiterHelper extends Activity {

	public final static String EXTRA_POSITION = "com.waiterhelper.POSITION";
	private ListView tableDisplay;
	private EntryAdapter entryAdapter;
	private DataBase data = DataBase.getInstance();
	private Button add;
	private boolean adapterCreated = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_helper);
        
        this.initiateList();
  
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_waiter_helper, menu);
        return true;
    }
    
    public void initiateList(){
    	
        // Begin to populate the list
        tableDisplay = (ListView) findViewById(R.id.android_listOfTables);
        
        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View header = inflater.inflate(R.layout.header, null);
        tableDisplay.addHeaderView(header,null,false);
        
        // Temporary Data (NEED A READ FROM DISK EVENTUALLY IMPLEMENTED HERE
        // 		onStart and onStop have to be addressed too for saving the info entered.
        data.testPopulate();
        
        this.entryAdapter = new EntryAdapter(this, R.layout.row, data.getList());
        tableDisplay.setAdapter(entryAdapter);
        adapterCreated = true;
    	
        add = (Button) findViewById(R.id.addButton);
        
        // Create new entry when the add button is pressed
        add.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				data.add(data.new Entry());
				entryAdapter.notifyDataSetChanged();
			}
		});
        
        return;
    	
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	if(adapterCreated) { 
    		entryAdapter.notifyDataSetChanged();
    	}
    }
    

    
}
