package cit.ordermanagement.com;

import java.sql.SQLException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class PayementsPending extends Activity implements OnClickListener{

	TextView tvvieworderdetails;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_order);
		
		tvvieworderdetails =(TextView) findViewById(R.id.tvvieworderdetails);
		
		String getresult="";
		Customerdbwork vdobj  = new Customerdbwork(PayementsPending.this);
		
		try {
			vdobj.open();
		getresult=vdobj.paymentpending();
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
