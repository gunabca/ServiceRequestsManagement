package cit.ordermanagement.com;

import java.sql.SQLException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewCustomer extends Activity {

	
	TextView customerlist;
	

	Customerdbwork dbviewdetails;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_customer_form);
	customerlist = (TextView) findViewById(R.id.tvviewdetails);
	
	
	
	dbviewdetails = new Customerdbwork(this);
	try {
		dbviewdetails.open();
		String cdetails = dbviewdetails.viewdetails();
		
		dbviewdetails.close();
		customerlist.setText(cdetails);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	


	
	
	
	
	
	
	}
	
	
	
	
	
	


}
