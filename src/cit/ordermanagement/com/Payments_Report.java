package cit.ordermanagement.com;

import java.sql.SQLException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Payments_Report extends Activity implements OnClickListener{

	TextView tvvieworderdetails;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_order);
		
		tvvieworderdetails =(TextView) findViewById(R.id.tvvieworderdetails);
		Intent statusi = getIntent();
		
		String status = statusi.getStringExtra("Status");
		
		
		String getresult="";
		Customerdbwork vdobj  = new Customerdbwork(Payments_Report.this);
		
		try {
			vdobj.open();
		getresult=vdobj.paymentstatus(status);
			vdobj.close();
			
			tvvieworderdetails.setText(getresult);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

	
	
	
	
	
	
	
	







	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		
		
	}
}
