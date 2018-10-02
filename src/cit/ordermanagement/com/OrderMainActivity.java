package cit.ordermanagement.com;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OrderMainActivity extends Activity implements OnClickListener{

	
	Button wbutton1,wbutton2,wbutton3,wbutton4,wbutton5;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_main);
		
		wbutton1 = (Button) findViewById(R.id.wbutton1);
		wbutton2 = (Button) findViewById(R.id.wbutton2);
		wbutton3 = (Button) findViewById(R.id.wbutton3);
		wbutton4 = (Button) findViewById(R.id.wbutton4);
		wbutton5 = (Button) findViewById(R.id.wbutton5);
		
		
		wbutton1.setOnClickListener(this);
		wbutton2.setOnClickListener(this);
		wbutton3.setOnClickListener(this);
		wbutton4.setOnClickListener(this);
		wbutton5.setOnClickListener(this);
		
		SharedPreferences mainsp = getSharedPreferences("userprofile",0);
		
		//SharedPreferences.Editor mainspeditor = mainsp.edit();
		
		String getrolelevel =mainsp.getString("spusercategory", "Normal");
		
		if("Normal".equals(getrolelevel))
		{
			
			wbutton5.setEnabled(false);
			
			
		}else if("SuperUser".equals(getrolelevel) || "Admin".equals(getrolelevel))
		{
			
			
			wbutton5.setEnabled(true);
			
			
		}
		else if("newuser".equals(getrolelevel))
			
		{
			
			Intent newusertoapp = new Intent("com.ordermanagement.cit.SETTINGS");
			startActivity(newusertoapp);
			finish();
			
		}
		else
		{
			wbutton5.setEnabled(false);
			
			
		}
		
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
   switch(v.getId())
   
   {
   
   case R.id.wbutton1:
	   
	   Intent customerinfointentnorder = new Intent("com.ordermanagement.cit.NEWCUSTOMER");
	   startActivity(customerinfointentnorder);
	   
	   
//	   Intent neworderintent = new Intent("com.ordermanagement.cit.NEWORDER");
//	   startActivity(neworderintent);
	   
	   
	   break;
	   
	   
 case R.id.wbutton2:
	   
		Intent existingorders = new Intent("com.ordermanagement.cit.EXISTINGORDERS");
		startActivity(existingorders);
		
	   break;
 case R.id.wbutton3:
	   
	   Intent gotoreports = new Intent("com.ordermanagement.cit.REPORTS");
	   startActivity(gotoreports);
	   break;
   
 case R.id.wbutton4:
	   
	  Intent startpayments = new Intent("com.ordermanagement.cit.PAYMENTS_R");
	  startActivity(startpayments);
	   
	   break;
	   
	   
	 case R.id.wbutton5:
		   
		   Intent launchsettings = new Intent("com.ordermanagement.cit.SETTINGS");
		   startActivity(launchsettings);
		   break;
   }
		
		
		
	}
}
