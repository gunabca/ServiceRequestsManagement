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

public class Settings extends Activity implements OnClickListener{

	Button managecustomerbt;
	Button manageemployeesbt;
	Button userprofile;
	Button tobackup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
	
		
		
		managecustomerbt = (Button) findViewById(R.id.settingsbutton1);
		manageemployeesbt = (Button) findViewById(R.id.settingsbutton2);
		
		userprofile = (Button) findViewById(R.id.settingsbutton3);
		tobackup = (Button) findViewById(R.id.settingsbutton4);
		
		SharedPreferences spfornewuser = getSharedPreferences("userprofile", 0);
		
		String getuserrole = spfornewuser.getString("spusercategory", "0");
		
		if("newuser".equals(getuserrole))
		{
			managecustomerbt.setEnabled(false);
			
			
		}
		
		managecustomerbt.setOnClickListener(this);
		manageemployeesbt.setOnClickListener(this);
		userprofile.setOnClickListener(this);
		tobackup.setOnClickListener(this);
	}

	
		


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		
		switch(v.getId())
		{
		
		case R.id.settingsbutton1:
			
			
			
			Intent launchmanagecustomers = new Intent("com.ordermanagement.cit.MANAGECUSTOMERS");
			startActivity(launchmanagecustomers);
			
			break;
			
		case R.id.settingsbutton2:
			
			
			
			
			Intent launchmanageemp = new Intent("com.ordermanagement.cit.EMPLOYEE");
			startActivity(launchmanageemp);
			break;
		
		
	case R.id.settingsbutton3:
			
			
		Intent launchuserprofile = new Intent("com.ordermanagement.cit.USERPROFILE");
		startActivity(launchuserprofile);
		
			 
			break;
			
			
	case R.id.settingsbutton4:
		
		
		Intent launchbackup = new Intent("com.ordermanagement.cit.BACKUP");
		startActivity(launchbackup);
		
			 
			break;
			
		
		}
		
	}
	
	
	

















}
