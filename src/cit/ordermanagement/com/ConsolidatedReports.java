package cit.ordermanagement.com;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class ConsolidatedReports extends Activity implements OnClickListener {
	Spinner jobassignedtorep;
	EditText getfromdate,gettodate;
	Button generaterep,todate,fromdate;
	String sfromdate=null;
	String stodate=null;
	Long totimel=(long) 0;
	Long fromtimel= (long)0;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consolidated_reports);
		
		
		
		 
		getfromdate = (EditText) findViewById(R.id.etgetfromdate);
		gettodate = (EditText) findViewById(R.id.etgettodate);
		
		
		generaterep = (Button) findViewById(R.id.btgeneraterep);
		todate = (Button) findViewById(R.id.bttodate);
		fromdate = (Button) findViewById(R.id.btfromdate);
		
		generaterep.setOnClickListener(this);
		todate.setOnClickListener(this);
		fromdate.setOnClickListener(this);
		
		
		 
		
	 
		
	}

	

	
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId())
		
		{
		case R.id.btgeneraterep:
			
		 
			String timesinlong[] = {String.valueOf(fromtimel),String.valueOf(totimel)};
			
				
			
			
			
			Intent startconsolidatedreview = new Intent("com.ordermanagement.cit.CONSOLIDATEDVIEW");
			startconsolidatedreview.putExtra("senddaterange", timesinlong);
			startActivity(startconsolidatedreview);
			
			
			
			
			break;
			
			
		case R.id.bttodate:
			Calendar getdate = Calendar.getInstance();
			
			int cdate = getdate.get(Calendar.DAY_OF_MONTH);
			int cmonth = getdate.get(Calendar.MONTH);
			int cyear = getdate.get(Calendar.YEAR);
		
			DatePickerDialog.OnDateSetListener todateclick = new DatePickerDialog.OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				Calendar getinms = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss.SSS");
				
				
				String getdate = String.valueOf(dayOfMonth)+"/"+String.valueOf(monthOfYear+1) + "/" +
				String.valueOf(year);
				
				getinms.set(year, monthOfYear, dayOfMonth, 23, 59, 59);
				getinms.set(Calendar.MILLISECOND,999);
				
		 totimel = getinms.getTimeInMillis();
		
				gettodate.setText(getdate);
				
				
			}
		};
	
			DatePickerDialog  spkd = new DatePickerDialog(ConsolidatedReports.this, todateclick,cyear,cmonth,cdate);
			
	
			
		spkd.show();
			
	
	
			break;
			
			
			
			
		case R.id.btfromdate:
			
	Calendar getdate2 = Calendar.getInstance();
			
			int cdate2 = getdate2.get(Calendar.DAY_OF_MONTH);
			int cmonth2 = getdate2.get(Calendar.MONTH);
			int cyear2 = getdate2.get(Calendar.YEAR);
			
			
			DatePickerDialog.OnDateSetListener fromdatelistener = new DatePickerDialog.OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					// TODO Auto-generated method stub
					 Calendar getfromcal = Calendar.getInstance();
					String getdatefrom = String.valueOf(dayOfMonth)+"/"+String.valueOf(monthOfYear+1) + "/" +
							String.valueOf(year);
					
					getfromcal.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
				//	getfromcal.set(Calendar.MILLISECOND, 999);
					fromtimel = getfromcal.getTimeInMillis();
					getfromdate.setText(getdatefrom);
					
					
				}
			};
			DatePickerDialog dpgetfrom = new DatePickerDialog(ConsolidatedReports.this, fromdatelistener,cyear2,cmonth2,cdate2 );
		
			
			
			
			dpgetfrom.show();
			
			break;
			
			
		
		
		
		
		
		
		
		}
		
		
		
		
		
	}
	
	
	
	
	
	
}
