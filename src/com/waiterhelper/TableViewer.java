package com.waiterhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.waiterhelper.database.DataBase;
import com.waiterhelper.database.DataBase.Order;

public class TableViewer extends Activity {
	
	private DataBase data = DataBase.getInstance();
	private int position;
	private ListView orderList;
	private OrdersAdapter orderAdpt;
	private Button clearSelected;
	private Button clearAll;
	//private Bundle savedTempBundle;
	public Context TableViewerContext = this;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_viewer);
        
        Intent intent = getIntent();
        position = intent.getIntExtra(WaiterHelper.EXTRA_POSITION, 0);

        TextView test = (TextView)findViewById(R.id.tableViewerHeader);
        test.setText(data.get(position).toString());
        
        //savedTempBundle = savedInstanceState;
        
        orderList = (ListView) findViewById(R.id.ordersList);
        
        orderAdpt = new OrdersAdapter(this, R.layout.roworder, (data.get(position)).orders, position);
        orderList.setAdapter(orderAdpt);
        
        clearSelected = (Button) findViewById(R.id.deleteSelectedOrderButton);
        clearAll = (Button) findViewById(R.id.deleteOrderButton);
        
        clearSelected.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
            	Order o;
            	for(int i = 0; i < data.get(position).orders.size();i++){
            		o = data.get(position).orders.get(i);
            		//o.addAddition(data.get(position).orders.get(i).getAdditions());
            		//o.changeName(data.get(position).orders.get(i).getName());
            		if(o.isSelected()) {
            			data.get(position).orders.get(i).clearAdditions();
            			data.get(position).orders.remove(o);
            		}
            	}
            	
            	
                orderAdpt.notifyDataSetChanged();
                //int tempSize = data.get(position).orders.size();
                //for(int i = 0; i < tempSize; i++) {
                //	data.get(position).orders.get(i).checkListener.changePosition(i);
                //}
                

            	// Need to refresh the positions and everything
            }

        });
        
        clearAll.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
            	data.get(position).orders.clear();
            	orderAdpt.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_table_viewer, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
