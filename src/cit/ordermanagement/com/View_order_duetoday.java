package cit.ordermanagement.com;

import java.sql.SQLException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class View_order_duetoday extends Activity implements OnClickListener{

	TextView tvvieworderdetails;
	
	String getresult="";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_order);
		
		tvvieworderdetails =(TextView) findViewById(R.id.tvvieworderdetails);
		
	
		Customerdbwork vdobj  = new Customerdbwork(View_order_duetoday.this);
		
		try {
			vdobj.open();
		getresult=vdobj.printorderstoday();
			vdobj.close();
			
			tvvieworderdetails.setTypeface(Typeface.MONOSPACE);
			tvvieworderdetails.setText(getresult);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

	
	
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_order, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			
			
			
			String toemailid[] = {"gunabca@gmail.com"};
			

			Intent sendempviewreport = new Intent(android.content.Intent.ACTION_SEND);
			
			sendempviewreport.putExtra(android.content.Intent.EXTRA_EMAIL,toemailid );
			sendempviewreport.putExtra(android.content.Intent.EXTRA_SUBJECT, "Due Today");
			//sendempviewreport.putExtra(android.content.Intent.EXTRA_CC,)
			sendempviewreport.setType("text/plain");
			sendempviewreport.putExtra(android.content.Intent.EXTRA_TEXT, getresult);
			
			
			startActivity(sendempviewreport);
			
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}









	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		
		
	}
}
