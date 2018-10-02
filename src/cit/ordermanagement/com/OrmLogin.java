package cit.ordermanagement.com;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OrmLogin extends Activity implements OnClickListener{

	String ormusername=null;
	String ormpassword=null;
	String[] ormusernamearray;
	String[] ormpasswordarray;
	Button login,cancel;
	EditText etusername,etpassword;
	String filename="userprofile";
	String usercategory=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ormlogin);
		
		 etusername = (EditText) findViewById(R.id.etusername);
		 etpassword = (EditText) findViewById(R.id.etpassword);
				
		Button login = (Button) findViewById(R.id.btlogin);
		Button cancel= (Button) findViewById(R.id.btcancel);
	//	shporm = getSharedPreferences(filename, 0);
		//shporme.putString("spusername", "admin");
	//	shporme.putString("sppassword", "admin01");
	//	shporme.putString("spcategory", "level1");
	//	shporme.commit();

	
		
		login.setOnClickListener(this);
		cancel.setOnClickListener(this);
		
	}




	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		switch(v.getId())
		{
		
		case R.id.btlogin:
			
			 	SharedPreferences shporm = getSharedPreferences(filename,0);
				SharedPreferences.Editor shporme = shporm.edit();
				
				
			
			
			ormusername = etusername.getText().toString();
			ormpassword = etpassword.getText().toString();
			
			String isuserauthorized = "none";
			
			Customerdbwork checkforuser = new Customerdbwork(this);
			
			try{
				
				checkforuser.open();
				
				
				isuserauthorized = checkforuser.checkread(ormusername, ormpassword);
				
						
				
			}catch(Exception e)
			{
				
				
				e.printStackTrace();
			}
			
			
			if("Normal".equals(isuserauthorized) || "SuperUser".equals(isuserauthorized) ||  "Admin".equals(isuserauthorized) )
			{
				finish();
				Intent startmain = new Intent("android.intent.action.ORDERMAINACTIVITY");
				startActivity(startmain);
				usercategory=isuserauthorized;
				shporme.putString("spusername", ormusername);
				shporme.putString("sppassword", ormpassword);
				shporme.putString("spusercategory", usercategory);
				shporme.commit();
				
				
				
			}
		
			else if("admin".equals(ormusername))
			{
				
				if("admin01".equals(ormpassword))
				{
					
					
					finish();
					Intent startmain = new Intent("android.intent.action.ORDERMAINACTIVITY");
					startActivity(startmain);
					usercategory="SuperUser";
					shporme.putString("spusername", ormusername);
					shporme.putString("sppassword", ormpassword);
					shporme.putString("spusercategory", usercategory);
					shporme.commit();
					
				}
					
			}
			
			else if("trial".equals(ormusername))
			{
				
				if("trial01".equals(ormpassword))
				{
					
					
					finish();
					Intent startmain = new Intent("android.intent.action.ORDERMAINACTIVITY");
					startActivity(startmain);
					usercategory="newuser";
					shporme.putString("spusername", ormusername);
					shporme.putString("sppassword", ormpassword);
					shporme.putString("spusercategory", usercategory);
					shporme.commit();
					
				}
				
			}
		
			else
			{
				Toast displaytext = Toast.makeText(this, "Invalid username/Password", Toast.LENGTH_SHORT);
				displaytext.show();
				
				
			}
			
			break;
			
			
			
		case R.id.btcancel:
			
			finish();
			break;
		
		
		
		}
		
		
		
	}

	
	
	
	
	
	
	
}
