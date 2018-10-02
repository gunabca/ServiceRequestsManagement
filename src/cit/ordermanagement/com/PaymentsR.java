package cit.ordermanagement.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PaymentsR extends Activity implements OnClickListener{

	
	Button PartialPay,ReceivedPay,PendingPay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payments_r);
		
		PartialPay = (Button)findViewById(R.id.partialpay);
		ReceivedPay = (Button)findViewById(R.id.receivedpay);
		PendingPay = (Button)findViewById(R.id.pendingpay);
	
		PartialPay.setOnClickListener(this);
		ReceivedPay.setOnClickListener(this);
		PendingPay.setOnClickListener(this);
		
	}
	
	
	
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		Intent pending = new Intent(
				"com.ordermanagement.cit.PAYMENTS_REPORT");
		
		switch(v.getId())
		
		{
		case R.id.pendingpay:
			
			
		
       pending.putExtra("Status", "Pending");
			startActivity(pending);

		break;
		
		
		
		case R.id.receivedpay:
			
			
       pending.putExtra("Status", "Received");
			startActivity(pending);
			
			break;
			
		case R.id.partialpay:
			
			
       pending.putExtra("Status", "Partial");
			startActivity(pending);
			break;
		
		
		
		
		
		
		
		
		
		
		}
	}


}
