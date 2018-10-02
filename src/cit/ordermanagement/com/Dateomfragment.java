package cit.ordermanagement.com;

import java.util.Calendar;
import java.util.Date;

import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.app.Dialog;
public class Dateomfragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	
	public int getday,getmonth,getyear = 0 ;
	
	/* (non-Javadoc)
	 * @see android.app.DialogFragment#onCreateDialog(android.os.Bundle)
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Calendar calinst = Calendar.getInstance();
	int i = calinst.get(Calendar.YEAR);
	int j = calinst.get(Calendar.MONTH);
	int k = calinst.get(Calendar.DAY_OF_MONTH);
	
		
		
		
return new DatePickerDialog(getActivity(),this , i, j, k);
	}
	
	public interface datevalues
	{
		
		public void getdatefromdialog(Long timeinms);
		
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		getday = dayOfMonth;
		getmonth = monthOfYear;
		getyear = year;
		Calendar datecheck = Calendar.getInstance();

		datecheck.set(getyear, getmonth, getday);

		Long timeinms = datecheck.getTimeInMillis();   
/*
switch(view.getTag().toString())
{
case "startdialog":


datevalues getdateinter = (datevalues) getActivity();
getdateinter.getdatefromdialog(timeinms);

	
break;


}  */
	

datevalues getdateinter = (datevalues) getActivity();
getdateinter.getdatefromdialog(timeinms);
Toast checktag = Toast.makeText(getActivity(), view.getId(), Toast.LENGTH_LONG)	;	
checktag.show();
		
}
	
	}

	
	
	
	
	
	
	
	
	
	
	


