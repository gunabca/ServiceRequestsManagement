package cit.ordermanagement.com;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EmpReports extends Activity implements OnClickListener{

	Spinner jobassignedtorep;
	EditText getfromdate,gettodate;
	Button generaterep,todate,fromdate;
	String sfromdate=null;
	String stodate=null;
	Long totimel=(long) 0;
	Long fromtimel= (long)0;
	List<String> getusers;
	ArrayAdapter<String> getusersadapt;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emp_reports);
		
		jobassignedtorep = (Spinner) findViewById(R.id.spjobassignedtorep);
		getfromdate = (EditText) findViewById(R.id.etgetfromdate);
		gettodate = (EditText) findViewById(R.id.etgettodate);
		
		
		generaterep = (Button) findViewById(R.id.btgeneraterep);
		todate = (Button) findViewById(R.id.bttodate);
		fromdate = (Button) findViewById(R.id.btfromdate);
		
		generaterep.setOnClickListener(this);
		todate.setOnClickListener(this);
		fromdate.setOnClickListener(this);
		
		
		
		
		Customerdbwork getusersfromdb = new Customerdbwork(this);
		
		
		try{
			
			getusersfromdb.open();
			
		}catch(SQLException e)
		{
			
			e.printStackTrace();
		}
			
		getusers  =getusersfromdb.getusernames();
		
		getusersfromdb.close();
		
		getusersadapt = new ArrayAdapter<String>(EmpReports.this,android.R.layout.simple_spinner_dropdown_item,getusers);
		jobassignedtorep.setAdapter(getusersadapt);
		
		
		
		
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId())
		
		{
		case R.id.btgeneraterep:
			
String getusername = jobassignedtorep.getSelectedItem().toString();
			String timesinlong[] = {String.valueOf(fromtimel),String.valueOf(totimel),getusername};
			Intent startemprepview = new Intent("com.ordermanagement.cit.EMPVIEWREPORT");
			startemprepview.putExtra("senddaterange", timesinlong);
			
			
			startActivity(startemprepview);
			
			
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
	
			DatePickerDialog  spkd = new DatePickerDialog(EmpReports.this, todateclick,cyear,cmonth,cdate);
			
	
			
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
			DatePickerDialog dpgetfrom = new DatePickerDialog(EmpReports.this, fromdatelistener,cyear2,cmonth2,cdate2 );
		
			
			
			
			dpgetfrom.show();
			
			break;
			
			
		
		
		
		
		
		
		
		}
		
		
		
		
		
	}
	


	
	
	
	
	
	
		
	



	
}
	
	
	


