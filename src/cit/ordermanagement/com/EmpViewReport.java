package cit.ordermanagement.com;

import java.sql.SQLException;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class EmpViewReport extends Activity {

	String getempbydaterange="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emp_view_report);
		
		TextView displayreport = (TextView) findViewById(R.id.tvviewempreport);
		
		Customerdbwork getempreport = new Customerdbwork(this);
		
		Intent getvaluefromintent = getIntent();
		
		String timesinlongs[] = getvaluefromintent.getStringArrayExtra("senddaterange");
		try
		{
			getempreport.open();
			
			getempbydaterange= 	getempreport.generalreport(timesinlongs);
			
			getempreport.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
	
		
		displayreport.setText(getempbydaterange);
		
		Calendar cs = Calendar.getInstance();
		cs.getTime();
		
	
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		getMenuInflater().inflate(R.menu.emp_view_report, menu);
				
		return true;
		
		
		
		
		
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		String toemailid[] = {"gunabca@gmail.com"};
		int id = item.getItemId();
		
		
		if(id==R.id.menu_emp_email)
		{
			
			Intent sendempviewreport = new Intent(android.content.Intent.ACTION_SEND);
			
			sendempviewreport.putExtra(android.content.Intent.EXTRA_EMAIL,toemailid );
			sendempviewreport.putExtra(android.content.Intent.EXTRA_SUBJECT, "Employee viewreport");
			//sendempviewreport.putExtra(android.content.Intent.EXTRA_CC,)
			sendempviewreport.setType("text/plain");
			sendempviewreport.putExtra(android.content.Intent.EXTRA_TEXT, getempbydaterange);
			
			
			startActivity(sendempviewreport);
			
			
			
			return true;
			
			
		}
		
		return super.onOptionsItemSelected(item);
	}

	
}
