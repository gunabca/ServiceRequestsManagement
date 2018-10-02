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

public class View_order extends Activity  {

	TextView tvvieworderdetailsbridge;
	
	String getresult="";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_order);
		
		tvvieworderdetailsbridge =(TextView) findViewById(R.id.tvvieworderdetails);
		
		
		Customerdbwork vdobj  = new Customerdbwork(View_order.this);
		
		try {
			vdobj.open();
		getresult=vdobj.ViewAlljobs();
			vdobj.close();
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		tvvieworderdetailsbridge.setText(getresult);
		
		
		
		
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
			
			
			String emailreport= "All Job cards Report\n";
			
			String emailidtemp[]= {"gunabca@gmail.com"};
			String subject = "All Job cards sent to  email";
			
			Intent sendallviewreports = new Intent(android.content.Intent.ACTION_SEND);
			
			
			sendallviewreports.putExtra(android.content.Intent.EXTRA_EMAIL,emailidtemp);
			sendallviewreports.putExtra(android.content.Intent.EXTRA_SUBJECT,subject);
			sendallviewreports.setType("type/plain");
			sendallviewreports.putExtra(android.content.Intent.EXTRA_TEXT, getresult);
			
			startActivity(sendallviewreports);
			
			
			
			
			
			
			return true;
		}	return super.onOptionsItemSelected(item);
	
	}









	
}
