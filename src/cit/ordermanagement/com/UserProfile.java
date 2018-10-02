package cit.ordermanagement.com;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Button;

public class UserProfile extends Activity implements OnClickListener{

	String filename ="userprofile";
	SharedPreferences spup;
	
	
	
	EditText etpref01,etpref02,etpref03,etpref04
	,etpref05,etpref06,etpref07,etpref08,etpref09,etpref10,etpref11,etpref12,etpref13,
	etpref14,etpref15,etpref16,etpref17,etpref18,etpref19,etpref20;
	
	String spref01 = "";
	String spref02 = "";
	String spref03 = "";
	String spref04 = "";
	String spref05 = "";
	String spref06 = "";
	String spref07 = "";
	String spref08 = "";
	String spref09 = "";
	String spref10 = "";
	String spref11 = "";
	String spref12 = "";
	String spref13 = "";
	String spref14 = "";
	String spref15 = "";
	String spref16 = "";
	String spref17 = "";
	String spref18 = "";
	String spref19 = "";
	String spref20 = "";
	
	
	
	Button btsaveupdateup;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		
		spup= getSharedPreferences(filename, 0);
		etpref01 = (EditText) findViewById(R.id.etpref01);
		etpref02 = (EditText) findViewById(R.id.etpref02);
		etpref03 = (EditText) findViewById(R.id.etpref03);
		etpref04 = (EditText) findViewById(R.id.etpref04);
		etpref05 = (EditText) findViewById(R.id.etpref05);
		etpref06 = (EditText) findViewById(R.id.etpref06);
		etpref07 = (EditText) findViewById(R.id.etpref07);
		etpref08 = (EditText) findViewById(R.id.etpref08);
		etpref09 = (EditText) findViewById(R.id.etpref09);
		etpref10 = (EditText) findViewById(R.id.etpref10);
		etpref11 = (EditText) findViewById(R.id.etpref11);
		etpref12 = (EditText) findViewById(R.id.etpref12);
		
		etpref13 = (EditText) findViewById(R.id.etpref13);
		etpref14 = (EditText) findViewById(R.id.etpref14);
		etpref15 = (EditText) findViewById(R.id.etpref15);
		etpref16 = (EditText) findViewById(R.id.etpref16);
		etpref17 = (EditText) findViewById(R.id.etpref17);
		etpref18 = (EditText) findViewById(R.id.etpref18);
		etpref19 = (EditText) findViewById(R.id.etpref19);
		etpref20 = (EditText) findViewById(R.id.etpref20);
		
		
		
		
		etpref01.setText(spup.getString("keyspref01", "enter value"));
		etpref02.setText(spup.getString("keyspref02", "enter value"));
		etpref03.setText(spup.getString("keyspref03", "enter value"));
		etpref04.setText(spup.getString("keyspref04", "enter value"));
		etpref05.setText(spup.getString("keyspref05", "enter value"));
		etpref06.setText(spup.getString("keyspref06", "enter value"));
		etpref07.setText(spup.getString("keyspref07", "enter value"));
		etpref08.setText(spup.getString("keyspref08", "enter value"));
		etpref09.setText(spup.getString("keyspref09", "enter value"));
		etpref10.setText(spup.getString("keyspref10", "enter value"));
		etpref11.setText(spup.getString("keyspref11", "enter value"));
		etpref12.setText(spup.getString("keyspref12", "enter value"));
		// Termsand conditions
		etpref13.setText(spup.getString("keyspref13", "enter value"));
		etpref14.setText(spup.getString("keyspref14", "enter value"));
		etpref15.setText(spup.getString("keyspref15", "enter value"));
		etpref16.setText(spup.getString("keyspref16", "enter value"));
		etpref17.setText(spup.getString("keyspref17", "enter value"));
		etpref18.setText(spup.getString("keyspref18", "enter value"));
		etpref19.setText(spup.getString("keyspref19", "enter value"));
		etpref20.setText(spup.getString("keyspref20", "enter value"));
		
		
		
		
		
		
		
		btsaveupdateup = (Button) findViewById(R.id.btsaveupdateup);
		
		btsaveupdateup.setOnClickListener(this);
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
			
	}
	
	@Override
	public void onClick(View v)
	{
		
		
		
		switch(v.getId())
		{
		
		case R.id.btsaveupdateup:
			SharedPreferences.Editor spupedit = spup.edit();
			
			spref01 = etpref01.getText().toString();
			spref02 = etpref02.getText().toString();
			spref03 = etpref03.getText().toString();
			spref04 = etpref04.getText().toString();
			spref05 = etpref05.getText().toString();
			spref06 = etpref06.getText().toString();
			spref07 = etpref07.getText().toString();
			spref08 = etpref08.getText().toString();
			spref09 = etpref09.getText().toString();
			spref10 = etpref10.getText().toString();
			spref11 = etpref11.getText().toString();
			spref12 = etpref12.getText().toString();
			spref13 = etpref13.getText().toString();
			spref14 = etpref14.getText().toString();
			spref15 = etpref15.getText().toString();
			spref16 = etpref16.getText().toString();
			spref17 = etpref17.getText().toString();
			spref18 = etpref18.getText().toString();
			spref19 = etpref19.getText().toString();
			spref20 = etpref20.getText().toString();
			
			
			
			
			
			spupedit.putString("keyspref01", spref01);
			spupedit.putString("keyspref02", spref02);
			spupedit.putString("keyspref03", spref03);
			spupedit.putString("keyspref04", spref04);
			spupedit.putString("keyspref05", spref05);
			spupedit.putString("keyspref06", spref06);
			spupedit.putString("keyspref07", spref07);
			spupedit.putString("keyspref08", spref08);
			spupedit.putString("keyspref09", spref09);
			spupedit.putString("keyspref10", spref10);
			spupedit.putString("keyspref11", spref11);
			spupedit.putString("keyspref12", spref12);
			
			spupedit.putString("keyspref13", spref13);
			spupedit.putString("keyspref14", spref14);
			spupedit.putString("keyspref15", spref15);
			spupedit.putString("keyspref16", spref16);
			spupedit.putString("keyspref17", spref17);
			spupedit.putString("keyspref18", spref18);
			spupedit.putString("keyspref19", spref19);
			spupedit.putString("keyspref20", spref20);
			
			
			
			spupedit.commit();
			
			
			
			break;
		
		
		
		
		
		}
		
		
		
		
		
	}

	
	
	
	
	
	
}
