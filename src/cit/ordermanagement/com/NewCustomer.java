
package cit.ordermanagement.com;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.app.DatePickerDialog;

public class NewCustomer extends Activity implements OnClickListener {

	// Edit Text Declarations
public int day=0;
public int month=0;
public int year=0;
public String csdate=null;




	EditText customerid, mobileno, date, name, emailid, address,mobilenosearch;
	String scustomerid = "";
	String sname = "";
	String semailid = "";
	String smobileno = "";
	String sdate = "";
	String saddress = "";
	String getmobilenotosearch = "0";
	Button savecustomer;
	Button viewcustomers;
	Button delcustomerrec;
	Button createneworder;
	String smobilenosearch="";

	Button findnewcustomerbridge;
	Button datelaunchdialog;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_customer);

		
		


		customerid = (EditText) findViewById(R.id.etnewcustomerid);
		mobileno = (EditText) findViewById(R.id.etcustomermobile);
		mobilenosearch = (EditText) findViewById(R.id.etcustomermobilesearch);
		date = (EditText) findViewById(R.id.etnewitemdate);
		name = (EditText) findViewById(R.id.etcustomername);
		emailid = (EditText) findViewById(R.id.etcustomeremail);
		address = (EditText) findViewById(R.id.etcustomeraddress);
		savecustomer = (Button) findViewById(R.id.savenewcustomer);

		viewcustomers = (Button) findViewById(R.id.viewcustomers);
		delcustomerrec = (Button) findViewById(R.id.btdelcustrecord);
		
		viewcustomers.setVisibility(viewcustomers.INVISIBLE);
		delcustomerrec.setVisibility(delcustomerrec.INVISIBLE);

		createneworder = (Button) findViewById(R.id.btcreateneworder);

		findnewcustomerbridge = (Button) findViewById(R.id.btfindnewcustomer);
		
	//	datelaunchdialog  = (Button) findViewById(R.id.ncdatepicker);
	//	datelaunchdialog.setOnClickListener(this);

		savecustomer.setOnClickListener(this);
		viewcustomers.setOnClickListener(this);
		delcustomerrec.setOnClickListener(this);
		findnewcustomerbridge.setOnClickListener(this);
		createneworder.setOnClickListener(this);

	}
	
	public void getinfofromui()

	{
		Calendar newcalendar = Calendar.getInstance();
		SimpleDateFormat dateformatonreport = new SimpleDateFormat("dd/MM/yyyy");

		
		smobilenosearch = mobilenosearch.getText().toString();
		scustomerid = customerid.getText().toString();
		smobileno = mobileno.getText().toString();
		sdate = String.valueOf(newcalendar.getTimeInMillis());
		sname = name.getText().toString();
		semailid = emailid.getText().toString();
		saddress = address.getText().toString();
		
		
		
		
		
		if(scustomerid.isEmpty())
		{
			
			scustomerid="0";
		}
		if(smobileno.isEmpty())
		{
			
			smobileno="0";
		}
		if(sdate.isEmpty())
		{
			
			sdate="0";
		}
		if(sname.isEmpty())
		{
			
			sname="0";
		}
		if(semailid.isEmpty())
		{
			
			semailid="0";
		}
		if(saddress.isEmpty())
		{
			
			saddress="0";
		}
		
		
		
		
		

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId())

		{
		case R.id.savenewcustomer:
			
			getinfofromui();
if(scustomerid.equals("0"))
{
	if(smobileno.equals("0") ||  sdate.equals("0") || sname.equals("0") ||
			saddress.equals("0") )
		
	{
		Toast confirmdatasave = Toast.makeText(getApplicationContext(),
				"Enter all details", android.widget.Toast.LENGTH_SHORT);
		confirmdatasave.show();
		
	}
	else {

		

			Customerdbwork newcustomer = new Customerdbwork(NewCustomer.this);
			try {
				newcustomer.open();

		Long insrow=		newcustomer.addcustomer(scustomerid, sname, semailid,
						smobileno, sdate, saddress);
				Toast confirmdata = Toast.makeText(getApplicationContext(),
						"hello"+insrow, android.widget.Toast.LENGTH_SHORT);
				confirmdata.show();
				newcustomer.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			savecustomer.setEnabled(false);	
	
	}
	
			
}
else
{
	
	Customerdbwork existcust = new Customerdbwork(this);
	try {
		existcust.open();
	int i=	existcust.updatecustomer(scustomerid, sname, semailid,
				smobileno, sdate, saddress);
		
	Toast updatecustomer = Toast.makeText(getApplicationContext(), "rows updated"+i,android.widget.Toast.LENGTH_SHORT);
	updatecustomer.show();
	
		existcust.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	
	
}





			break;

		case R.id.viewcustomers:

			Intent viewcustomersintent = new Intent(
					"com.ordermanagement.cit.VIEWCUSTOMER");
			startActivity(viewcustomersintent);

			break;

		case R.id.btdelcustrecord:

			try {

				Customerdbwork delrec = new Customerdbwork(NewCustomer.this);

				String getmobileno = mobileno.getText().toString();

				// Long mobileno = Long.parseLong(getmobileno);

				delrec.open();

				int norows = delrec.deleterecord(getmobileno);

				String norowss = String.valueOf(norows);

				Toast norowsdeleted = Toast.makeText(this, norowss
						+ "Row(s) deleted", android.widget.Toast.LENGTH_LONG);
				norowsdeleted.show();

				delrec.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case R.id.btfindnewcustomer:
			savecustomer.setEnabled(true);	
			getmobilenotosearch = mobilenosearch.getText().toString();
			if (getmobilenotosearch.isEmpty()) {
				getmobilenotosearch = "0";

			}

			Customerdbwork findrec = new Customerdbwork(NewCustomer.this);
			try {
				findrec.open();
				ContentValues getrec = findrec
						.searchdbbymobile(getmobilenotosearch);

				if (!(getrec.size()==0))

				{
					Calendar newcalendarret = Calendar.getInstance();
					SimpleDateFormat custdate = new SimpleDateFormat("dd/MM/yyyy");
					
					scustomerid = getrec.getAsString("kCs_id");
					sname = getrec.getAsString("kCs_name");
					sdate = getrec.getAsString("kCs_date");
					smobileno = getrec.getAsString("kCs_mobileno");
					semailid = getrec.getAsString("kCs_emailid");
					saddress = getrec.getAsString("kCs_address");

					newcalendarret.setTimeInMillis(Long.valueOf(sdate));
					
					// setting the values retrieved

					customerid.setText(scustomerid);
					mobileno.setText(smobileno);
					date.setText(custdate.format(newcalendarret.getTime()));
					name.setText(sname);
					emailid.setText(semailid);
					address.setText(saddress);
					;

				} else {
					Toast recnotfound = Toast.makeText(getApplicationContext(),
							"Customer not found in Database",
							android.widget.Toast.LENGTH_LONG);
				//	recnotfound.setGravity(android.view.Gravity, 0,0);
					recnotfound.show();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			findrec.close();

			break;

		case R.id.btcreateneworder:

			smobileno = mobileno.getText().toString();

			Intent neworderintent = new Intent(
					"com.ordermanagement.cit.NEWORDER");

			neworderintent.putExtra("mobilenumber", smobileno);
			startActivity(neworderintent);
			createneworder.setEnabled(false);
		
			break;

		
		}

	}
	
		
		
		@Override
		public void onConfigurationChanged(Configuration newConfig) {
			// TODO Auto-generated method stub
			super.onConfigurationChanged(newConfig);
			;
			
			Configuration checkconfig = newConfig;
			
			if(checkconfig.equals(Configuration.SCREENLAYOUT_SIZE_LARGE))
			{		
				setRequestedOrientation(Configuration.ORIENTATION_LANDSCAPE);
			
			}
			else
			   
			{
				setRequestedOrientation(Configuration.ORIENTATION_PORTRAIT);
				
			
				
				
			
				}
		
		
	
		}
		
		
}
