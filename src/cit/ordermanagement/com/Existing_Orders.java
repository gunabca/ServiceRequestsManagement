package cit.ordermanagement.com;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.ContentValues;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;
public class Existing_Orders extends Activity implements OnClickListener {

	EditText etneworderidsearch;
	// EditText etnewordercustomermobilesearch;

	Button btsearchexistorder, exbtsaveneworder, exbtorderLookup, btprintorder;

	// begin Variables from Neworder class

	String[] jobstatus = {"New","Under-Investigation",
			"Work In Progress","Testing","Fixed","Closed","Rework","Returned" };

	String[] paymentstatus = { "NA", "Pending", "Received", "Partial" };

	String[] category = { "DESKTOP", "MONITOR", "LAPTOP", "PRINTER", "TABLET",
			"OTHERS" };

	String[] prioritycat = { "1", "2", "3", "4" };
	String[] complexitycat = { "1", "2", "3" };

	EditText exetneworderid, exetnewitemdate, exetnewordercustomermobile,
			exetnewitemname, exetnewitemdesc, exetassignedto, exetassignedby,
			exetproblemrpt, exetengaccessories, exetsolution, exetetc,
			exetduedate,

			exettvpaydue, exettvpayreceived, exettvpayreceivedon,exetsparescost;

	// new fields added to the order -begin

	EditText etdelconfirm, etdelconfirmdate, etcompletedactualdate, etpriority,
			etcomplexity;

	// new fields added to the order - End

	Spinner exstatusspin;
	Spinner exspinpayment;
	Spinner exorder_type;
	Spinner priorityspin;
	Spinner complexityspin;

	Spinner spchangeassign;
	
	ArrayAdapter<String> spchangeassignadapt;
	List<String> getusernamelist;
	ArrayAdapter<String> exstatusspinadapt, exorder_typeadapt,
			exspinpaymentadapt;

	String setneworderid = null;
	String setnewitemdate = null;

	String setnewitemname = null;
	String setnewitemdesc = null;
	String setassignedto = null;
	String setassignedby = null;
	String setproblemrpt = null;
	String setengaccessories = null;
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

	String setdelconfirm = null;
	String setdelconfirmdate = null;
	String setcompletedactualdate = null;
	String setpriority = null;
	String setcomplexity = null;
	String setsparescost=null;

	// Search fields string

	String etneworderidsearchs = null;
	
	
	// Variables to compare Current user entered values with the values retrieved from the database 
	
	String getPaymenttypeprevious= null;

	// String etnewordercustomermobilesearchs = "";

	// End of Variables from Neworder class

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_existing__orders);

		
		Customerdbwork getusername = new Customerdbwork(this);
		
		try{
			getusername.open();
		}catch(SQLException e)
		{
			
			
			e.printStackTrace();
		}
		
		getusernamelist = getusername.getusernames();
		
		getusername.close();
		
		
		spchangeassign = (Spinner) findViewById(R.id.spchangejobassignedto);
		
		spchangeassignadapt = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item,getusernamelist);
		
		spchangeassign.setAdapter(spchangeassignadapt);
		
		
		
		exetneworderid = (EditText) findViewById(R.id.exetneworderid);
		exetneworderid.setEnabled(false);
		exetnewitemdate = (EditText) findViewById(R.id.exetnewitemdate);
		exetnewordercustomermobile = (EditText) findViewById(R.id.exetnewordercustomermobile);
		exetnewitemname = (EditText) findViewById(R.id.exetnewitemname);
		exetnewitemdesc = (EditText) findViewById(R.id.exetnewitemdesc);
		exetassignedto = (EditText) findViewById(R.id.exetassignedto);
		exetassignedby = (EditText) findViewById(R.id.exetassignedby);
		exetproblemrpt = (EditText) findViewById(R.id.exetproblemrpt);
		exetengaccessories = (EditText) findViewById(R.id.exetengaccessories);
		exetsolution = (EditText) findViewById(R.id.exetsolution);
		exetetc = (EditText) findViewById(R.id.exetetc);
		exetduedate = (EditText) findViewById(R.id.exetduedate);
		exettvpaydue = (EditText) findViewById(R.id.exettvpaydue);
		exettvpayreceived = (EditText) findViewById(R.id.exettvpayreceived);
		exettvpayreceivedon = (EditText) findViewById(R.id.exettvpayreceivedon);

		etneworderidsearch = (EditText) findViewById(R.id.etneworderidsearch);
		// etnewordercustomermobilesearch = (EditText)
		// findViewById(R.id.etnewordercustomermobilesearch);

		// New fields added

		etdelconfirm = (EditText) findViewById(R.id.etdelconfirm);
		etdelconfirmdate = (EditText) findViewById(R.id.etdelconfirmdate);
		etcompletedactualdate = (EditText) findViewById(R.id.etcompletedactualdate);
		
		exetsparescost = (EditText) findViewById(R.id.etsparescost);
		// etpriority = (EditText) findViewById(R.id.etpriority);
		// etcomplexity = (EditText) findViewById(R.id.etcomplexity);

		btsearchexistorder = (Button) findViewById(R.id.btsearchexistorder);
		exbtsaveneworder = (Button) findViewById(R.id.exbtsaveneworder);

		exbtorderLookup = (Button) findViewById(R.id.btorderlookup);
		
		btprintorder = (Button) findViewById(R.id.btprintorder);
		
		btsearchexistorder.setOnClickListener(this);

		exbtsaveneworder.setOnClickListener(this);
		exbtorderLookup.setOnClickListener(this);

		exstatusspin = (Spinner) findViewById(R.id.exstatusspin);
		exspinpayment = (Spinner) findViewById(R.id.exspinpayment);
		exorder_type = (Spinner) findViewById(R.id.exorder_type);
		priorityspin = (Spinner) findViewById(R.id.priorityspin);
		complexityspin = (Spinner) findViewById(R.id.complexityspin);

		exstatusspinadapt = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, jobstatus);

		exorder_typeadapt = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, category);
		exspinpaymentadapt = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, paymentstatus);

		ArrayAdapter<String> prioritycatadapt = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, prioritycat);

		priorityspin.setAdapter(prioritycatadapt);

		ArrayAdapter<String> complexadapt = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, complexitycat);

		complexityspin.setAdapter(complexadapt);

		exstatusspin.setAdapter(exstatusspinadapt);
		exspinpayment.setAdapter(exspinpaymentadapt);
		exorder_type.setAdapter(exorder_typeadapt);
		
		btprintorder.setOnClickListener(this);

spchangeassign.setOnItemSelectedListener(new OnItemSelectedListener(){
			
			
			

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				TextView tvgetvalue = (TextView)view;
				exetassignedto.setText(tvgetvalue.getText().toString());
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
			
			
		});
		
		
	}

	public void readedittext()

	{
		// etnewordercustomermobilesearchs = etnewordercustomermobilesearch
		// .getText().toString();
		setneworderid = exetneworderid.getText().toString();
		// setnewitemdate = exetnewitemdate.getText().toString();
		// setnewordercustomermobile = exetnewordercustomermobile.getText()
		// .toString();
		// setnewitemname = exetnewitemname.getText().toString();
		// setnewitemdesc = exetnewitemdesc.getText().toString();
		setassignedto = exetassignedto.getText().toString();
		setassignedby = exetassignedby.getText().toString();
		// setproblemrpt = exetproblemrpt.getText().toString();
		// setengaccessories = exetengaccessories.getText().toString();
		setsolution = exetsolution.getText().toString();
		// setetc = exetetc.getText().toString();
		// setduedate = exetduedate.getText().toString();
		settvpaydue = exettvpaydue.getText().toString();
		settvpayreceived = exettvpayreceived.getText().toString();

	
		// settvpayreceivedon = exettvpayreceivedon.getText().toString();

		getstatus = (String) exstatusspin.getSelectedItem();
		getPaymenttype = (String) exspinpayment.getSelectedItem();
		
		setsparescost = exetsparescost.getText().toString();

		
		// Rule #1
		
		if ((getPaymenttype.equalsIgnoreCase("Received"))|| (getPaymenttype.equalsIgnoreCase("Partial")))

		{
			
			if(!getPaymenttypeprevious.equalsIgnoreCase("Received"))
			{
			

			Calendar setpayreceived = Calendar.getInstance();
			Long setpayreceivedlong = setpayreceived.getTimeInMillis();

			settvpayreceivedon = String.valueOf(setpayreceivedlong);
			
			
			}

		}
		
		
		
		
		
		// cat_type = (String) exorder_type.getSelectedItem();

		// new fields added.

		setpriority = (String) priorityspin.getSelectedItem();
		setcomplexity = (String) complexityspin.getSelectedItem();

		setdelconfirm = etdelconfirm.getText().toString();
		
		//Rule #2
		if (setdelconfirm.equalsIgnoreCase("Y"))

		{

			Calendar delconfirmcal = Calendar.getInstance();
			Long delconlong = delconfirmcal.getTimeInMillis();

			setdelconfirmdate = String.valueOf(delconlong);
		}
//Rule #3 
		
		if ("Closed".equalsIgnoreCase(getstatus))
		{
			
			
			if (setdelconfirm.equalsIgnoreCase("Y"))
			{
				if ((getPaymenttype.equalsIgnoreCase("Received")))

				{
				
				Calendar getcompletedcal = Calendar.getInstance();
				setcompletedactualdate = String.valueOf(getcompletedcal.getTimeInMillis());
				}
				else
				{
					
					Toast dispmsg = Toast.makeText(this, "Cannot change the status to closed without receiving the payment", Toast.LENGTH_LONG);
							dispmsg.show();
							
							exstatusspin.setSelection(3);
							
				};
				
			} else
			{

				Toast dispmsg1 = Toast.makeText(this, "Cannot change the status to closed without Delivering the product", Toast.LENGTH_LONG);
						dispmsg1.show();
				
						exstatusspin.setSelection(3);
			}
			
			
			
			
			
		}
	
		
		// setdelconfirmdate = etdelconfirmdate.getText().toString();

		// setcompletedactualdate = etcompletedactualdate.getText().toString();

	}

	public void getandpostvaluesfromcursor(ContentValues getordcontent) {

		SimpleDateFormat dateformat1 = new SimpleDateFormat("dd/MM/yyyy");
		if (!(getordcontent == null)) {

			setneworderid = getordcontent.getAsString("ORDERID");

			// Converting and displaying Date for new order
			Calendar neworderdatecal = Calendar.getInstance();

			setnewitemdate = getordcontent.getAsString("ORDERDATE");

			setnewitemname = getordcontent.getAsString("ORDERNAME");
			setnewordercustomermobile = getordcontent
					.getAsString("ORDERCUSTOMERMOBILE");

			setnewitemdesc = getordcontent.getAsString("ORDERDESCRIPTION");
			setassignedto = getordcontent.getAsString("ORDERASSIGNEDTO");
			setassignedby = getordcontent.getAsString("ORDERASSIGNEDBY");
			setproblemrpt = getordcontent.getAsString("ORDERPROBLEMRPT");
			setengaccessories = getordcontent.getAsString("ORDERACCESSORIES");
			setsolution = getordcontent.getAsString("ORDERSOLUTION");
			setetc = getordcontent.getAsString("ORDERETC");

			// Converting and displaying Date for due date
			Calendar duedatecal = Calendar.getInstance();

			setduedate = getordcontent.getAsString("ORDERDUEDATE");

			settvpaydue = getordcontent.getAsString("ORDERAMTDUE");
			settvpayreceived = getordcontent.getAsString("ORDERPYMTRECD");

			// Converting and displaying the Payment Received date

			settvpayreceivedon = getordcontent.getAsString("ORDERPYMTRECDON");

			getstatus = getordcontent.getAsString("ORDERSTATUS");

			getPaymenttype = getordcontent.getAsString("ORDERPYMTSTATUS");
			getPaymenttypeprevious = getPaymenttype;

			cat_type = getordcontent.getAsString("ORDER_TYPE");

			setdelconfirm = getordcontent.getAsString("ORDERDELCONFIRM");

			
			// Converting and displaying the delivery confirmation date
			Calendar delcon = Calendar.getInstance();

			
			
			setdelconfirmdate = getordcontent
					.getAsString("ORDERDELCONFIRMDATE");

			// Converting and displaying Compleedactualdate

			setcompletedactualdate = getordcontent
					.getAsString("ORDERCOMPLETEDACTUALDATE");

			setpriority = getordcontent.getAsString("ORDERPRIORITY");
			setcomplexity = getordcontent.getAsString("ORDERCOMPLEXITY");
			setsparescost=getordcontent.getAsString("ORDERSPARESCOST");
			
// Displaying the values on the screen_________________________________________
			exetneworderid.setText(setneworderid);
			Calendar itemdatecal = Calendar.getInstance();
			if(setnewitemdate==null)
			{
			// do nothing
			}else 
			{
			itemdatecal.setTimeInMillis(Long.valueOf(setnewitemdate));
			exetnewitemdate.setText(dateformat1.format(itemdatecal.getTime()));
						}
			exetnewitemname.setText(setnewitemname);
			exetnewordercustomermobile.setText(setnewordercustomermobile);
			exetnewitemdesc.setText(setnewitemdesc);
			exetassignedto.setText(setassignedto);
			exetassignedby.setText(setassignedby);
			exetproblemrpt.setText(setproblemrpt);
			exetengaccessories.setText(setengaccessories);
			exetsolution.setText(setsolution);
			exetetc.setText(setetc);
			exetsparescost.setText(setsparescost);
			if(setduedate==null)
			{
				
				
			}
			else
			{
				
				Calendar duedate1 = Calendar.getInstance();
				duedate1.setTimeInMillis(Long.valueOf(setduedate));
			
			exetduedate.setText(dateformat1.format(duedate1.getTime()));
			}
			exettvpaydue.setText(settvpaydue);
			exettvpayreceived.setText(settvpayreceived);
			if(settvpayreceivedon==null || settvpayreceivedon.isEmpty())
			{
				
			}else{
				
			
			Calendar pymtreceivedcal = Calendar.getInstance();
			pymtreceivedcal.setTimeInMillis(Long.valueOf(settvpayreceivedon));
		
			exettvpayreceivedon.setText(dateformat1.format(pymtreceivedcal.getTime()));
			
			}
			
			
			etdelconfirm.setText(setdelconfirm);

			
			if(setdelconfirmdate==null  || setdelconfirmdate.isEmpty())
			{
				
			}
			else
			{
			
			Calendar setdelconfirmcal = Calendar.getInstance();
			setdelconfirmcal.setTimeInMillis(Long.valueOf(setdelconfirmdate));
			
			etdelconfirmdate.setText(dateformat1.format(setdelconfirmcal.getTime()));
			}
			
			if(setcompletedactualdate==null  || setcompletedactualdate.isEmpty() )
			{
				
			}
			else
			{
			Calendar setcompletedactualdatecal = Calendar.getInstance();
			setcompletedactualdatecal.setTimeInMillis(Long.valueOf(setcompletedactualdate));
			
			etcompletedactualdate.setText(dateformat1.format(setcompletedactualdatecal.getTime()));
			
			}
			
			// etpriority.setText(setpriority);
			// etcomplexity.setText(setcomplexity);

			int i = 0;
			int j = 0;
			int k = 0;
			int l = 0;
			int m = 0;

			for (; i < (jobstatus.length - 1); i++) {

				if (getstatus.equals(jobstatus[i]))
					break;

			}

			for (; j < (paymentstatus.length - 1); j++)

			{

				if (getPaymenttype.equals(paymentstatus[j]))
					break;
			}

			for (; k < (category.length - 1); k++)

			{
				if (cat_type.equals(category[k]))
					break;

			}

			for (; l < (prioritycat.length - 1); l++)

			{

				if (setpriority.equals(prioritycat[l])) {

					break;
				}

			}

			for (; m < (complexitycat.length - 1); m++)

			{

				if (setpriority.equals(complexitycat[m])) {

					break;
				}

			}

			exstatusspin.setSelection(i);
			exspinpayment.setSelection(j);
			exorder_type.setSelection(k);
			priorityspin.setSelection(l);
			complexityspin.setSelection(m);

		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.btsearchexistorder:

			Customerdbwork getexistorder = new Customerdbwork(this);
			readedittext();
			try {

				etneworderidsearchs = etneworderidsearch.getText().toString();
				if (etneworderidsearchs.isEmpty()) {
					etneworderidsearchs = "0";
				}

				getexistorder.open();

				ContentValues getordcontent = getexistorder
						.readwriteexistorder(etneworderidsearchs);

				if (getordcontent == null) {
					Toast ordernotfound = Toast.makeText(this,
							"Order is not found in the database",
							Toast.LENGTH_LONG);
					ordernotfound.show();

				} else {

					getandpostvaluesfromcursor(getordcontent);

				}

				getexistorder.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case R.id.exbtsaveneworder:
			
			if((setneworderid==null) || setneworderid.isEmpty())
			{
				Toast novalue = Toast.makeText(
						this, "No record updated",
					 Toast.LENGTH_LONG);
				novalue.show();
			} else
			{
				
			int norowsupdate = 0;
			ContentValues cvtoupdate = new ContentValues();
			readedittext();

			Long order_idlong = Long.parseLong(setneworderid);

			cvtoupdate.put("ORDER_ID", order_idlong);
			// cvtoupdate.put("ORDERDATE", ));
			cvtoupdate.put("ORDER_STATUS", getstatus);
			// cvtoupdate.put("ORDERCUSTOMERMOBILE",
			// existvaluescursor.getString(3));
			// cvtoupdate.put("ORDERNAME", existvaluescursor.getString(4));
			// cvtoupdate.put("ORDERDESCRIPTION",
			// existvaluescursor.getString(5));
			// cvtoupdate.put("ORDER_TYPE", existvaluescursor.getString(6));

			cvtoupdate.put("ORDER_ASSIGNEDTO", setassignedto);
			cvtoupdate.put("ORDER_ASSIGNEDBY", setassignedby);
			// cvtoupdate.put("ORDERPROBLEMRPT",
			// existvaluescursor.getString(9));
			cvtoupdate.put("ORDER_ACCESSORIES", setengaccessories);
			cvtoupdate.put("ORDER_SOLUTION", setsolution);
			// cvtoupdate.put("ORDERETC", existvaluescursor.getString(12));
			// cvtoupdate.put("ORDERDUEDATE", setduedate);
			cvtoupdate.put("ORDER_AMTDUE", settvpaydue);
			cvtoupdate.put("ORDER_PYMTRECD", settvpayreceived);
			cvtoupdate.put("ORDER_PYMTRECDON", settvpayreceivedon); // 1
			cvtoupdate.put("ORDER_PYMTSTATUS", getPaymenttype);

			cvtoupdate.put("ORDER_DELCONFIRM", setdelconfirm);
			cvtoupdate.put("ORDER_DELCONFIRMDATE", setdelconfirmdate); // 2
			cvtoupdate.put("ORDER_COMPLETEDACTUALDATE", setcompletedactualdate); // 3
			cvtoupdate.put("ORDER_PRIORITY", setpriority);
			cvtoupdate.put("ORDER_COMPLEXITY", setcomplexity);
			cvtoupdate.put("ORDER_SPARESCOST", setsparescost);

			// Code to validate the Receipt of payment and update the Payement
			// received date.

			Customerdbwork updateorder = new Customerdbwork(this);

			try {
				updateorder.open();

				norowsupdate = updateorder.updateorderindb(cvtoupdate);

				updateorder.close();

				Toast displayrowsupdated = Toast.makeText(
						this, "Order updated("+norowsupdate+")" ,
						android.widget.Toast.LENGTH_LONG);
				displayrowsupdated.show();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		
			 
			break;

		case R.id.btorderlookup:

			Intent orderlookupintent = new Intent(
					"com.ordermanagement.cit.ORDERLOOKUP");

			startActivityForResult(orderlookupintent, 100);

			break;
			
		case R.id.btprintorder:
			
			
			Intent printorderintent = new Intent("com.ordermanagement.cit.PRINTVIEW");
			printorderintent.putExtra("orderid", String.valueOf(setneworderid));
			startActivity(printorderintent);
			break;

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		if (requestCode == 100) {

			if (resultCode == RESULT_OK)

			{

				etneworderidsearch.setText((data.getStringExtra("ORDER_ID")));
			}

			super.onActivityResult(requestCode, resultCode, data);
		}

	}

	
		
	
}
