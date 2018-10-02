package cit.ordermanagement.com;

import java.sql.SQLException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Consolidatedview extends Activity {
	String getempbydaterange1=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consolidatedview);
		
		TextView displayreport = (TextView) findViewById(R.id.tvconsolidatedview);
		
		Customerdbwork getempreport = new Customerdbwork(this);
		
		Intent getvaluefromintent = getIntent();
		
		String timesinlongs[] = getvaluefromintent.getStringArrayExtra("senddaterange");
		try
		{
			getempreport.open();
			
			 getempbydaterange1= 	getempreport.consolidatedreport(timesinlongs);
				
				getempreport.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	
		
		displayreport.setText(getempbydaterange1);
		
	
	
	
		
		
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		getMenuInflater().inflate(R.menu.consolidated_view, menu);
		
		return true;
		
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
	
		String toemailid[] =  {"gunabca@gmail.com"};
		
		int id1 = item.getItemId();
		if(id1==R.id.itemsendemail)
			
		{
			
			Intent createemail = new Intent(android.content.Intent.ACTION_SEND);
			createemail.putExtra(android.content.Intent.EXTRA_EMAIL,toemailid );
			createemail.putExtra(android.content.Intent.EXTRA_SUBJECT, "Consolidated Report");
			createemail.setType("text/plain");
			createemail.putExtra(android.content.Intent.EXTRA_TEXT, getempbydaterange1);
			startActivity(createemail);
			
			return true;
						
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	
	
}
