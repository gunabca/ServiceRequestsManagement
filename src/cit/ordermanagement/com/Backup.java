package cit.ordermanagement.com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Backup extends Activity implements OnClickListener {

	Button btbackup1;
	EditText etbackupview;
	String getrecords = null;
	FileWriter fwforexcel;
	public final int WRITE_PERM = 1;
	// File pathforexp =null;
	String filename1 = null;
	String filename2 = null;
	String albumname = "/ormbackup";

	Calendar ctoday = Calendar.getInstance();

	String getrecordscursor = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_backup);

		btbackup1 = (Button) findViewById(R.id.btbackup1);
		etbackupview = (EditText) findViewById(R.id.etbackupview);
		btbackup1.setOnClickListener(this);
		ctoday.getTime();

		filename1 = "Backup" + ctoday.getTimeInMillis() / 1000 + ".csv";
		filename2 = "CSBackup" + ctoday.getTimeInMillis() / 1000 + ".csv";
		
		
		checkfile();

	}

	public void checkfile() {
		// check state for Mounting of externage storage
		String status = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(status)) {

			Toast mediastatus1 = Toast.makeText(this, "Media is Ready",
					Toast.LENGTH_SHORT);
			mediastatus1.show();

		} else {
			Toast mediastatus = Toast.makeText(this, "Media is not Ready",
					Toast.LENGTH_SHORT);
			mediastatus.show();

		}

		// Check state for Availability of External storage

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED_READ_ONLY))

		{
			Toast mediastatus = Toast.makeText(this, "Media -Read only",
					Toast.LENGTH_SHORT);
			mediastatus.show();

		}
		// Check for presence of Permission to read the External storage
		
		int i =ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE); 
		if(i==PackageManager.PERMISSION_DENIED) 
			
		{
			
			String permsissions[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
			ActivityCompat.requestPermissions(this, permsissions, WRITE_PERM);
			
			
			
		}
		else
		{
		
			
			
			
		}
		
		
		
	
		
		
		
	}

@Override
public void onRequestPermissionsResult(int requestCode,
		String[] permissions, int[] grantResults) {
	// TODO Auto-generated method stub
	super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	
	if(requestCode==WRITE_PERM)
	{
		
		btbackup1.setVisibility(View.VISIBLE);
		
	}
	
	else
	{
		btbackup1.setVisibility(View.INVISIBLE);
		
	}
	
	
	
	
}
	

	
		
	public void createfile(String getrecordscursor, String csdata) {

		Toast mediastatus2 = Toast.makeText(this, "inside create file module",
				Toast.LENGTH_SHORT);
		mediastatus2.show();

		try {

			// createfile.mkdirs();

			File createfile = Environment.getExternalStorageDirectory();

			File createcustomdir = new File(createfile, albumname);

			createcustomdir.mkdirs();

			File newfile = new File(createcustomdir, filename1);
			File customerdata = new File(createcustomdir, filename2);
			// newfile.creat

			fwforexcel = new FileWriter(newfile, true);
			BufferedWriter bwforexcel = new BufferedWriter(fwforexcel);

			bwforexcel.write(getrecordscursor);
			bwforexcel.flush();
			bwforexcel.close();

			fwforexcel.close();

			FileWriter csbackup = new FileWriter(customerdata, true);
			BufferedWriter csbackupexcel = new BufferedWriter(csbackup);
			csbackupexcel.write(csdata);
			csbackupexcel.flush();
			csbackupexcel.close();

			MediaScannerConnection.scanFile(this,
					new String[] { newfile.getPath() }, null, null);

			MediaScannerConnection.scanFile(this,
					new String[] { customerdata.getPath() }, null, null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			etbackupview.setText(e.getMessage());
			Toast mediastatus4 = Toast.makeText(this, "FIle NOT CREATED",
					Toast.LENGTH_SHORT);
			mediastatus4.show();
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		// StringBuilder sb1 = new StringBuilder();

		String customerdetails = null;

		Customerdbwork csdbforcsv = new Customerdbwork(this);

		try {
			csdbforcsv.open();
			getrecords = csdbforcsv.dataconversion();
			customerdetails = csdbforcsv.viewdetails();

			csdbforcsv.close();
			// etbackupview.setText(getrecords);
		} catch (Exception e) {
			e.printStackTrace();

			Toast tm = Toast.makeText(this, "No records found",
					Toast.LENGTH_SHORT);
			tm.show();
		}

	//	checkfile();
		createfile(getrecords, customerdetails);
		sendemail(getrecords);

	}

	private void sendemail(String getrecords) {
		// TODO Auto-generated method stub

		Intent sendemailcsv = new Intent(android.content.Intent.ACTION_SEND);
		sendemailcsv.setType("text/plain");
		sendemailcsv.putExtra(android.content.Intent.EXTRA_SUBJECT, "CSV");
		sendemailcsv.putExtra(android.content.Intent.EXTRA_TEXT, getrecords);
		// sendemailcsv.putExtra(android.content.Intent.EXTRA,getrecords);
		startActivity(sendemailcsv);

	}

}
