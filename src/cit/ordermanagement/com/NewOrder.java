package cit.ordermanagement.com;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cit.ordermanagement.com.Dateomfragment.datevalues;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewOrder extends Activity implements OnClickListener, datevalues {

	
	
	
	String[] jobstatus = { "New","Under-Investigation","Work In Progress", "Testing", "Fixed", "Closed",
			"Rework", "Returned" };

	String[] paymentstatus = { "NA", "Pending", "Received", "Partial" };

	String[] category = { "DESKTOP", "MONITOR", "LAPTOP", "PRINTER", "TABLET",
			"OTHERS" };

	String[] prioritycat = { "1", "2", "3", "4" };
	String[] complexitycat = { "1", "2", "3" };

	EditText etneworderid, etnewitemdate, etnewordercustomermobile,
			etnewitemname, etnewitemdesc,  etassignedby,
			etproblemrpt, etengaccessories, etsolution, etetc, etduedate,
			ettvpaydue, ettvpayreceived, ettvpayreceivedon,etsparescost;
	
	//EditText etassignedto;

	// new fields added to the order -begin

	EditText etdelconfirm, etdelconfirmdate, etcompletedactualdate;

	// new fields added to the order - End

	Spinner statusspin;
	Spinner spinpayment;
	Spinner order_type;
	Spinner priorityspin;
	Spinner complexityspin;
	
	
	Spinner spusername;
	
	ArrayAdapter<String> jobstatusadap;
    ArrayAdapter<String> getusername;
    
	String setneworderid = null;
	String setnewitemdate = null;

	String setnewitemname = null;
	String setnewitemdesc = null;
	String setassignedto = null;
	String setassignedby = null;
	String setproblemrpt = null;

	String setsolution = null;
	String setetc = null;
	String setduedate = null;
	String settvpaydue = null;
	String settvpayreceived = null;

	String setnewordercustomermobile = null;
	String settvpayreceivedon = null;

	String getstatus = null;

	String getPaymenttype = null;

	String cat_type = null;
	String setaccessories = null;
	String setdelconfirm = null;
	String setdelconfirmdate = null;
	String setcompletedactualdate = null;
	String setpriority = null;
	String setcomplexity = null;
	String setsparescost = null;
String orderidfromdb=null;


	
	Button saveorder;
	Button vworder;
	Button pickdatebt;
	Button btprintorder;
	Button btnext;
	Long getorderdetails=(long) 0;
	
	
	// Date buttons and date pickers

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.neworder);

		Customerdbwork getuser = new Customerdbwork(this);
		List<String> assigntolist = new ArrayList<String>();
		try {
			getuser.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 assigntolist = 	getuser.getusernames();
		getuser.close();
		
	
		
		
		Intent getmobilenofromintent = getIntent();
		String mobilenofromcustomer = getmobilenofromintent
				.getStringExtra("mobilenumber");

		etneworderid = (EditText) findViewById(R.id.etneworderid);
		etnewitemdate = (EditText) findViewById(R.id.etnewitemdate);
		etnewordercustomermobile = (EditText) findViewById(R.id.etnewordercustomermobile);
		etnewitemname = (EditText) findViewById(R.id.etnewitemname);
		etnewitemdesc = (EditText) findViewById(R.id.etnewitemdesc);
	//	etassignedto = (EditText) findViewById(R.id.etassignedto);
		etassignedby = (EditText) findViewById(R.id.etassignedby);
		etproblemrpt = (EditText) findViewById(R.id.etproblemrpt);
		etengaccessories = (EditText) findViewById(R.id.etengaccessories);
		etsolution = (EditText) findViewById(R.id.etsolution);
		etetc = (EditText) findViewById(R.id.etetc);
		etduedate = (EditText) findViewById(R.id.etduedate);
		ettvpaydue = (EditText) findViewById(R.id.ettvpaydue);
		ettvpayreceived = (EditText) findViewById(R.id.ettvpayreceived);
		// ettvpayreceivedon = (EditText) findViewById(R.id.ettvpayreceivedon);
		// field has been removed.

		// New fields added

		etdelconfirm = (EditText) findViewById(R.id.etdelconfirm);
		etsparescost = (EditText) findViewById(R.id.etsparescost);
		// etdelconfirmdate = (EditText) findViewById(R.id.etdelconfirmdate);
		// field has been removed.
		// etcompletedactualdate = (EditText)
		// findViewById(R.id.etcompletedactualdate);
		// etpriority = (EditText) findViewById(R.id.etpriority);
		// etcomplexity = (EditText) findViewById(R.id.etcomplexity);

		etnewordercustomermobile.setText(mobilenofromcustomer);

		statusspin = (Spinner) findViewById(R.id.statusspin);
		spinpayment = (Spinner) findViewById(R.id.spinpayment);
		order_type = (Spinner) findViewById(R.id.order_type);
		priorityspin = (Spinner) findViewById(R.id.priorityspin);
		complexityspin = (Spinner) findViewById(R.id.complexityspin);

		
		spusername = (Spinner) findViewById(R.id.spjobassignedto);
		
		getusername = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, assigntolist);

		spusername.setAdapter(getusername);
		
		
		jobstatusadap = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, jobstatus);
		statusspin.setAdapter(jobstatusadap);

		ArrayAdapter<String> pymtadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, paymentstatus);

		spinpayment.setAdapter(pymtadapter);

		ArrayAdapter<String> typeadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, category);
		order_type.setAdapter(typeadapter);

		ArrayAdapter<String> prioritycatadapt = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, prioritycat);

		priorityspin.setAdapter(prioritycatadapt);

		ArrayAdapter<String> complexadapt = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, complexitycat);

		complexityspin.setAdapter(complexadapt);

		saveorder = (Button) findViewById(R.id.btsaveneworder);
		vworder = (Button) findViewById(R.id.btviewneworder);
		btprintorder = (Button) findViewById(R.id.btprintorder);
		btnext = (Button) findViewById(R.id.btnext);
		
		saveorder.setOnClickListener(this);
		vworder.setOnClickListener(this);
		btprintorder.setOnClickListener(this);
		btnext.setOnClickListener(this);
       
		// Date picker functions to be incorporated.

		pickdatebt = (Button) findViewById(R.id.neworderduedatebt);

		pickdatebt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btnext:
			
			Intent nextnewcustomer = new Intent("com.ordermanagement.cit.NEWCUSTOMER");
			startActivity(nextnewcustomer);
				
			break;
		
		
		case R.id.btsaveneworder:
			
			checkforusername();
			if (performorderformvalidation())

			{

				Customerdbwork neworderentry = new Customerdbwork(NewOrder.this);

				try {
					neworderentry.open();

			 getorderdetails =		neworderentry.addorder(setnewitemdate, getstatus,
							setnewordercustomermobile, setnewitemname,
							setnewitemdesc, cat_type, setassignedto,
							setassignedby, setproblemrpt, setaccessories,
							setsolution, setetc, setduedate, settvpaydue,
							settvpayreceived, settvpayreceivedon,
							getPaymenttype, setdelconfirm, setdelconfirmdate,
							setcompletedactualdate, setpriority, setcomplexity,setsparescost);
				
		//	 subject = "Reg: Service Order placed with NewTech Computers OrderID:-" +getorderdetails.getAsLong("ORDER_ID");
			 
			 
			 
				neworderentry.close();
							Toast showroderidcreated = Toast.makeText(this, "New Order has been created ORDER ID is :" +getorderdetails, Toast.LENGTH_LONG);
							showroderidcreated.show();
							
							saveorder.setEnabled(false);	
						

				

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
	
			
			break;

		case R.id.btviewneworder:

			Intent vieworderintent = new Intent(
					"com.ordermanagement.cit.VIEWORDER");
			startActivity(vieworderintent);

			break;

		case R.id.neworderduedatebt:

			DialogFragment displaydatepick = new Dateomfragment();

			displaydatepick.show(getFragmentManager(), "Datepicker");

			break;

		case R.id.btprintorder:
			
			Intent printviewintent = new Intent("com.ordermanagement.cit.PRINTVIEW");
			printviewintent.putExtra("orderid", String.valueOf(getorderdetails));
			startActivity(printviewintent);
			
			break;
			
		}

	}

	private void checkforusername() {
		// TODO Auto-generated method stub
		
		
		
		
		
		
		
	}

	// Method which performs validations for all the fields from the order form
	private Boolean performorderformvalidation() {

		Boolean resultflag = false;
		// spinners grouped together

		getstatus = (String) statusspin.getSelectedItem();
		getPaymenttype = (String) spinpayment.getSelectedItem();
		cat_type = (String) order_type.getSelectedItem();
		setpriority = (String) priorityspin.getSelectedItem();
		setcomplexity = (String) complexityspin.getSelectedItem();
		
		// setneworderid=etneworderid.getText().toString();
		// setnewitemdate = etnewitemdate.getText().toString();
		setnewordercustomermobile = etnewordercustomermobile.getText()
				.toString();
		setnewitemname = etnewitemname.getText().toString();
		setnewitemdesc = etnewitemdesc.getText().toString();
		
		
	//	setassignedto = etassignedto.getText().toString();
		
		setassignedto =(String) spusername.getSelectedItem();
		// get value from sharedpreference and load it here
		
		SharedPreferences getassignedby = getSharedPreferences("userprofile",0);
		setassignedby = getassignedby.getString("spusername", "");
	//	setassignedby = etassignedby.getText().toString();
		
		setproblemrpt = etproblemrpt.getText().toString();
		setaccessories = etengaccessories.getText().toString();
		setsolution = etsolution.getText().toString();
		setetc = etetc.getText().toString();
		// setduedate = etduedate.getText().toString();
		settvpaydue = ettvpaydue.getText().toString();
		settvpayreceived = ettvpayreceived.getText().toString();
		// settvpayreceivedon = ettvpayreceivedon.getText().toString();

		// new fields added.

		setdelconfirm = etdelconfirm.getText().toString();
		// setdelconfirmdate = etdelconfirmdate.getText().toString();
		// setcompletedactualdate = etcompletedactualdate.getText().toString();

		// setpriority = etpriority.getText().toString();
		// setcomplexity = etcomplexity.getText().toString();

		// Check for presence of customer mobile

		// Set the new order received date
		Calendar getcsdate = Calendar.getInstance();

		setnewitemdate = String.valueOf(getcsdate.getTimeInMillis());
		
		setsparescost = etsparescost.getText().toString();

		// Rule 1:-Setting up delivery confirmation date
            if (setdelconfirm.equals("Y")) {

			Calendar delconfirmdate = Calendar.getInstance();

			setdelconfirmdate = String
					.valueOf(delconfirmdate.getTimeInMillis());
			
			resultflag = true;

		}else if(setdelconfirm.equals("N"))
			
		{
			resultflag = true;
			
		}
		else
		{
			resultflag = false;
			
		}
        //*****************************************************************
		// Rule #2  Check for presence of customer mobile number
        //*****************************************************************

		if (setnewordercustomermobile.isEmpty())

		{

			String Message = "Enter the Customer Mobile number";

			displaymessage(Message);

			resultflag = false;

		} else {

			resultflag = true;
		}

	      //*****************************************************************
		//  Rule: #3 Check new item name
	      //*****************************************************************
		if (setnewitemname.isEmpty())

		{

			String Message = "Enter the Item Name";

			displaymessage(Message);

			resultflag = false;

		} else {

			resultflag = true;
		}
	     //*****************************************************************
		// Rule #4 :check the serial number
		
	     //*****************************************************************
		if (setnewitemdesc.isEmpty())

		{

			String Message = "Enter the serial number ";

			displaymessage(Message);

			resultflag = false;

		} else {

			resultflag = true;
		}
	     //*****************************************************************
		// Rule#5: Check to whom the order is assigned
		
	     //*****************************************************************
	

		
		if (setassignedto.isEmpty())

		{

			String Message = "Please enter the assignedto field";

			displaymessage(Message);

			resultflag = false;

		} else {

			resultflag = true;
		}
		 //*****************************************************************
		// Rule#5: Check to who assigned the job/order
	  //*****************************************************************
		

		if (setassignedby.isEmpty())

		{

			String Message = "Please enter the assigned by field";

			displaymessage(Message);

			resultflag = false;

		} else {

			resultflag = true;
		}

		// Set the Problem field

		if (setproblemrpt.isEmpty())

		{

			String Message = "Please enter the problem  by field";

			displaymessage(Message);

			resultflag = false;

		} else {

			resultflag = true;
		}

		if ((getPaymenttype.equalsIgnoreCase("Received"))||(getPaymenttype.equalsIgnoreCase("Partial")) )

		{

			Calendar pay = Calendar.getInstance();
			Long getsysdatems = pay.getTimeInMillis();

			settvpayreceivedon = String.valueOf(getsysdatems);

		}
		

		// Set the Delivered on date field

		return resultflag;
		// TODO Auto-generated method stub

	}

	public void displaymessage(String getmessage)

	{
		Toast disptoast = Toast.makeText(this, getmessage, Toast.LENGTH_LONG);
		disptoast.show();

	}

	@Override
	public void getdatefromdialog(Long timeinms) {
		// TODO Auto-generated method stub

		String duedates = String.valueOf(timeinms);

		Calendar duedatecal = Calendar.getInstance();
		duedatecal.setTimeInMillis(timeinms);

		SimpleDateFormat duedateformat = new SimpleDateFormat("dd/MM/yyyy");

		// setduedate = duedateformat.format(duedatecal.getTime());

		setduedate = duedates;
		etduedate.setText(duedateformat.format(duedatecal.getTime()));
	}

}
