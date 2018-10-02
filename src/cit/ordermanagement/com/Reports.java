package cit.ordermanagement.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Reports extends Activity implements OnClickListener {

	Button reportbtviewneworder;
	Button rptorderduetoday;
	Button rptpendingdue;
	Button rptbyemp;
	Button rptconsolidated;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reports);

		reportbtviewneworder = (Button) findViewById(R.id.reportbtviewneworder);
		rptorderduetoday = (Button) findViewById(R.id.rptorderduetoday);
		rptpendingdue =(Button) findViewById(R.id.rptorderpending);
				rptbyemp = (Button) findViewById(R.id.btordersbyemp);
				
				rptconsolidated= (Button) findViewById(R.id.btconsolidated);
		reportbtviewneworder.setOnClickListener(this);
		rptorderduetoday.setOnClickListener(this);
		
		rptpendingdue.setOnClickListener(this);
		rptbyemp.setOnClickListener(this);
		
		rptconsolidated.setOnClickListener(this);
	}


	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.reportbtviewneworder:

			Intent launchrptviewneworder = new Intent(
					"com.ordermanagement.cit.VIEWORDER");

			startActivity(launchrptviewneworder);

			break;

		case R.id.rptorderduetoday:

			Intent duetoday = new Intent(
					"com.ordermanagement.cit.VIEWORDERDUETODAY");

			startActivity(duetoday);

			break;
			
		case R.id.rptorderpending:
			
			Intent pendingorder = new Intent(
					"com.ordermanagement.cit.VIEWPENDINGORDER");

			startActivity(pendingorder);

				break;
				
				
		case R.id.btordersbyemp:
			
			Intent startreportbyemp = new Intent("com.ordermanagement.cit.EMPREPORTS");
			startActivity(startreportbyemp);
			
			
			break;
			
		case R.id.btconsolidated:
			
			Intent startconsolidatedmain = new Intent("com.ordermanagement.cit.CONSOLIDATEDREPORTS");
			startActivity(startconsolidatedmain);
			
			
		
			break;
			

		}

	}
}
