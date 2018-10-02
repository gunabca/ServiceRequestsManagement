package cit.ordermanagement.com;

import java.sql.SQLException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Order_Lookup extends Activity implements OnClickListener,OnItemClickListener{



	Long orderidselected = (long) 0;
    EditText exetnewordercustomermobile;
    ListView displayorders;
    Button olsearch;
	String exetnewordercustomermobiles=null;
	
	 String orderidfinally=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_lookup);
		
		displayorders = (ListView) findViewById(R.id.listordersearchresult);
		exetnewordercustomermobile = (EditText) findViewById(R.id.exetnewordercustomermobile);
		olsearch = (Button) findViewById(R.id.olbtsearchorder);
		
olsearch.setOnClickListener(this);
displayorders.setOnItemClickListener(this);

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		
		exetnewordercustomermobiles = exetnewordercustomermobile.getText().toString();
		
		
		
		if(!(exetnewordercustomermobiles.isEmpty())) 
			
		{
		Customerdbwork getlistoforders = new Customerdbwork(this);
	
		String[] columnsfromtable2 = {
				Customerdbwork.ORDER_ID,
				Customerdbwork.ORDER_NAME,
				Customerdbwork.ORDER_DATE,
				Customerdbwork.ORDER_STATUS,
				Customerdbwork.ORDER_PYMTSTATUS,
				
		}; // 17

		
		int[] viewstobemapped = new int []{R.id.lltvorderid,R.id.lltvorder_name,R.id.lltvorder_date,R.id.lltvorder_status,R.id.lltvorder_pymtstatus };
		
		
		try {
			getlistoforders.open();
			
		Cursor getorders =getlistoforders.getorderid(exetnewordercustomermobiles);
			
		
			
			SimpleCursorAdapter getlist = new SimpleCursorAdapter(
					this, R.layout.listlayout_orderlookup, getorders, columnsfromtable2,viewstobemapped,0)	;		
		//	ListAdapter  getlist = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getorders);
			
			displayorders.setAdapter(getlist);
			
			
			getlistoforders.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
  
		}
		
		
	}
	
	
	
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#finish()
	 */
	@Override
	public void finish() {
		
		Intent sendorderid = new Intent();
		
		sendorderid.putExtra("ORDER_ID", orderidfinally);
		setResult(RESULT_OK, sendorderid);
		orderidfinally="0";
		
		// TODO Auto-generated method stub
		super.finish();
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
       
               
		 TextView ordertext= (TextView) view.findViewById(R.id.lltvorderid);
		 orderidfinally = ordertext.getText().toString();
	

		 
	
		finish();
		
		 
	}

	
	
	
	
	

	/*
	 * Code for menu options to be displayed on the screen or for action bar.
	 * 
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.order__lookup, menu); return true; }
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { // Handle
	 * action bar item clicks here. The action bar will // automatically handle
	 * clicks on the Home/Up button, so long // as you specify a parent activity
	 * in AndroidManifest.xml. int id = item.getItemId(); if (id ==
	 * R.id.action_settings) { return true; } return
	 * super.onOptionsItemSelected(item); }
	 */
}
