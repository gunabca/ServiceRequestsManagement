package cit.ordermanagement.com;

import android.app.Activity;
import android.content.ContentValues;
import android.view.View.*;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
public class Employee extends Activity implements OnClickListener{

	EditText empid,empfirstname, emplastname, empusername, emppassword, empmobilenumber, empemailid,empaddress;
	String empids=null;
	String empfirstnames=null;
	String emplastnames=null;
	String empusernames=null;
	String emppasswords=null;
	String empmobilenumbers=null;
	String empemailids=null;
	String empaddresss=null;
	String empaccess= null;
	String accesslevels[] = {"Normal","SuperUser","Admin" } ;
	Spinner spaccesslevel;
	ArrayAdapter<String> accessadapt;
	
	Button create, saveupdate,view,viewall,delete;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee);
		
		
		
		empid = (EditText) findViewById(R.id.etempid);
		empfirstname= (EditText) findViewById(R.id.etfirstname);
		emplastname= (EditText) findViewById(R.id.etlastname);
		empusername = (EditText) findViewById(R.id.etempusername);
		emppassword = (EditText) findViewById(R.id.etemppassword);
		empmobilenumber = (EditText) findViewById(R.id.etempmobilenumber);
		empemailid = (EditText) findViewById(R.id.etempemailid);
		empaddress = (EditText) findViewById(R.id.etempaddress);
		
		
		spaccesslevel = (Spinner)findViewById(R.id.spaccesslevel);
		accessadapt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,accesslevels);
		spaccesslevel.setAdapter(accessadapt);
		
		
	create = (Button) findViewById(R.id.empcreate);
	saveupdate= (Button) findViewById(R.id.empsaveupdate);
	view = (Button) findViewById(R.id.empviewdetails);
	viewall = (Button) findViewById(R.id.empviewalldetails);

	delete = (Button) findViewById(R.id.empdelete);
	
	create.setOnClickListener(this);
	saveupdate.setOnClickListener(this);
	view.setOnClickListener(this);
	viewall.setOnClickListener(this);
	delete.setOnClickListener(this);
	
	
		
	}

	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		switch(v.getId())
		
		{
		
		case R.id.empcreate:
			String getrow="";
			Boolean oktoupdate = validateuserinputs();
			
			if(oktoupdate)
			{
			
			ContentValues cvemp = new ContentValues();
			
		//	cvemp.put("EMP_ID", empids);
			cvemp.put("EMP_FIRST_NAME", empfirstnames);
			cvemp.put("EMP_LAST_NAME", emplastnames);
			cvemp.put("EMP_USERNAME", empusernames);
			cvemp.put("EMP_PASSWORD", emppasswords);
			cvemp.put("EMP_MOBILENUMBER", empmobilenumbers);
			cvemp.put("EMP_EMAILID", empemailids);
			cvemp.put("EMP_ADDRESS", empaddresss);
			cvemp.put("EMP_ACCESS", empaccess);
			
			Customerdbwork dbemp = new Customerdbwork(this);
			try{
				
				dbemp.open();
				// check if username already exists
				
				Boolean checkval = false;
				checkval = dbemp.checkusername(empusernames);
				
				if(checkval)
				{
					
					Toast toshowuserexists = Toast.makeText(this, "UserName already exists", Toast.LENGTH_SHORT);
					toshowuserexists.show();
			
				}
				
				else
				{
					 getrow=	dbemp.addemployee(cvemp);
						dbemp.close();
						Toast newrow = Toast.makeText(this, "New Employee added"+getrow, Toast.LENGTH_SHORT);
						newrow.show();
				}
				
			}
		catch(Exception e)
			{
			
			e.printStackTrace();
			}
			
			
		
			}
			
			break;
			
			
		case R.id.empsaveupdate:
			
			
			

			empids=empid.getText().toString();
			empfirstnames =empfirstname.getText().toString();
			emplastnames= emplastname.getText().toString();
			empusernames =empusername.getText().toString();
			emppasswords = emppassword.getText().toString();
			empmobilenumbers =empmobilenumber.getText().toString();
			empemailids=empemailid.getText().toString();
			empaddresss=empaddress.getText().toString();
			empaccess = (String) spaccesslevel.getSelectedItem();
			
			
			
			
			ContentValues cvempupdate = new ContentValues();
			
			//cvempupdate.put("EMP_ID", empids);
			cvempupdate.put("EMP_FIRST_NAME", empfirstnames);
			cvempupdate.put("EMP_LAST_NAME", emplastnames);
			cvempupdate.put("EMP_USERNAME", empusernames);
			cvempupdate.put("EMP_PASSWORD", emppasswords);
			cvempupdate.put("EMP_MOBILENUMBER", empmobilenumbers);
			cvempupdate.put("EMP_EMAILID", empemailids);
			cvempupdate.put("EMP_ADDRESS", empaddresss);
			cvempupdate.put("EMP_ACCESS", empaccess);
		
		
		
			Customerdbwork dbupemp = new Customerdbwork(this);
			try{
				
				dbupemp.open();
				
				dbupemp.updateemployee(empids,cvempupdate);
				
				dbupemp.close();
			}
		catch(Exception e)
			{
			
			e.printStackTrace();
			}
			break;
			
		case R.id.empviewdetails:
			
			
			
			empids=empid.getText().toString();
		 				
					ContentValues getempdetails= new ContentValues();
					
					
					Customerdbwork viewdetailsdb = new Customerdbwork(this);
					
							
					try{
						
						
						viewdetailsdb.open();
						getempdetails=	viewdetailsdb.reademployee(empids);
						viewdetailsdb.close();
						
					}catch(Exception e)
					{
						
						e.printStackTrace();
					}
					
					
			//read the values from contentvalue and display it on the screen
					
					
					empid.setText(getempdetails.getAsString("empid"));
					empfirstname.setText(getempdetails.getAsString("empfirstname"));
					emplastname.setText(getempdetails.getAsString("emplastname"));
					empusername.setText(getempdetails.getAsString("empusername"));
					emppassword.setText(getempdetails.getAsString("emppassword"));
					empmobilenumber.setText(getempdetails.getAsString("empmobilenumber"));
					empemailid.setText(getempdetails.getAsString("empemailid"));
					empaddress.setText(getempdetails.getAsString("empaddress"));
					
					empaccess = getempdetails.getAsString("empaccess");
					int i=0;
					
					for(;i<2;i++)
					{
						if(accesslevels[i]!=null)
							
						{
							if(accesslevels[i].equals(empaccess))
							{
								
								break;
							}
							
							
							
							
						}
						
						
					}
				
			  spaccesslevel.setSelection(i);
			
			
			break;
			
			
			
		case R.id.empviewalldetails:
			
			
			Toast temptoast = Toast.makeText(this, "Under-Development", Toast.LENGTH_SHORT);
			temptoast.show();
			
			
			
			break;
			
			
			
		case R.id.empdelete:
			int rows=0;
			empids=empid.getText().toString();
			
			Customerdbwork todelemp = new Customerdbwork(this);
			try{
				
				todelemp.open();
				
			rows=	todelemp.delemployee(empids);
				
				todelemp.close();
			}catch(Exception e)
			{
				e.printStackTrace();
				
			}
			Toast numberdel = Toast.makeText(this, "Number of rows deleted="+rows, Toast.LENGTH_LONG);
			numberdel.show();
			
			
			
			break;
			
			
			
			
		}
		
		
		
		
	}



	private Boolean validateuserinputs() {
		// TODO Auto-generated method stub
		
		Boolean decision= false;
		
		//empids=empid.getText().toString();
		empfirstnames =empfirstname.getText().toString();
		emplastnames= emplastname.getText().toString();
		empusernames =empusername.getText().toString();
		emppasswords = emppassword.getText().toString();
		empmobilenumbers =empmobilenumber.getText().toString();
		empemailids=empemailid.getText().toString();
		empaddresss=empaddress.getText().toString();
		empaccess = (String) spaccesslevel.getSelectedItem();
		
		//Check whether all fields are filled by the user 
		if ( empfirstnames.trim().isEmpty() || emplastnames.trim().isEmpty() || empusernames.trim().isEmpty() 
				|| emppasswords.trim().isEmpty() || empmobilenumbers.trim().isEmpty() || empaddresss.trim().isEmpty()  )
		{
			
			Toast displaymsg = Toast.makeText(this, "Enter values for all the fields above", Toast.LENGTH_SHORT);
			
			displaymsg.show();
			
			decision=false;
					
			
		}
		else
		{
			
			decision=true;
		}
		
		
		return decision;
	}
	
	
	
	

}
