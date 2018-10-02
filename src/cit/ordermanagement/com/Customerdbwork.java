package cit.ordermanagement.com;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Customerdbwork {

	private static final String DATABASE_NAME = "OrderManagementDB";
	private static final String DATABASE_TABLE = "customer_details";
	private static final String DATABASE_TABLE2 = "order_details";
	private static final String DATABASE_TABLE3 = "employee_details";
	private static final int DATABASE_VERSION = 7;

	// Creating Field Names Representing the values that are got from the
	// customers

	public static final String CUSTOMER_ID = "customer_id";
	public static final String CUSTOMER_NAME = "customer_name";
	public static final String CUSTOMER_MOBILENO = "customer_mobileno";
	public static final String CUSTOMER_DATE = "customer_date";
	public static final String CUSTOMER_EMAILID = "customer_emailid";
	public static final String CUSTOMER_ADDRESS = "customer_address";

	// Creating fields for second table

	public static final String ORDER_ID = "order_id";
	public static final String ORDER_DATE = "order_date";
	public static final String ORDER_STATUS = "order_status";
	public static final String ORDER_CUSTOMERMOBILE = "order_customermobile";
	public static final String ORDER_NAME = "order_name";
	public static final String ORDER_DESCRIPTION = "order_description";
	public static final String ORDER_TYPE = "order_type";
	public static final String ORDER_ASSIGNEDTO = "order_assignedto";
	public static final String ORDER_ASSIGNEDBY = "order_assignedby";
	public static final String ORDER_PROBLEMRPT = "order_problemrpt";
	// public static final String ORDER_PROBLEMANALYSIS =
	// "order_problemanalysis"; field modified to accessories details, replaced
	// by below field
	public static final String ORDER_ACCESSORIES = "order_accessories"; // field
																		// replacing
																		// the
																		// problem
																		// analysis
	public static final String ORDER_SOLUTION = "order_solution";
	public static final String ORDER_ETC = "order_etc";
	public static final String ORDER_DUEDATE = "order_duedate";
	public static final String ORDER_AMTDUE = "order_amtdue";
	public static final String ORDER_PYMTRECD = "order_pymtrecd";
	public static final String ORDER_PYMTRECDON = "order_pymtrecdon";
	public static final String ORDER_PYMTSTATUS = "order_pymtstatus";
	// Additional fields added to the order table
	public static final String ORDER_DELCONFIRM = "order_delconfirm";
	public static final String ORDER_DELCONFIRMDATE = "order_delconfirmdate";
	public static final String ORDER_COMPLETEDACTUALDATE = "order_completedactualdate";
	public static final String ORDER_PRIORITY = "order_priority";
	public static final String ORDER_COMPLEXITY = "order_complexity";
	public static final String ORDER_SPARESCOST="order_sparescost";

	// Database table #3 containing the employee records to be maintained
	// including the password

	public static final String EMP_ID = "emp_id"; // 1
	public static final String EMP_FIRST_NAME = "emp_first_name";// 2
	public static final String EMP_LAST_NAME = "emp_last_name";// 3
	public static final String EMP_USERNAME = "emp_username";// 4
	public static final String EMP_PASSWORD = "emp_password";// 5
	public static final String EMP_MOBILENUMBER = "emp_mobilenumber";// 6
	public static final String EMP_EMAILID = "emp_emailid";// 7
	public static final String EMP_ADDRESS = "emp_address";// 8
	public static final String EMP_ACCESS = "emp_access";

	private dbhelper customdatahelper;
	private SQLiteDatabase newdb;
	private Context context;

	private class dbhelper extends SQLiteOpenHelper {
		public dbhelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub

			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + CUSTOMER_ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT," + CUSTOMER_NAME
					+ " TEXT NOT NULL," + CUSTOMER_MOBILENO + " TEXT NOT NULL,"
					+ CUSTOMER_DATE + " INTEGER," + CUSTOMER_EMAILID
					+ " TEXT NOT NULL," + CUSTOMER_ADDRESS + " TEXT NOT NULL)");

			db.execSQL("CREATE TABLE " + DATABASE_TABLE2 + " ("

			+ ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," // field 1
					+ ORDER_DATE + " INTEGER," // field 2
					+ ORDER_STATUS + " TEXT," // field 3
					+ ORDER_CUSTOMERMOBILE + " TEXT," // field 4
					+ ORDER_NAME + " TEXT," // field 5
					+ ORDER_DESCRIPTION + " TEXT," // field 6
					+ ORDER_TYPE + " TEXT," // field 7
					+ ORDER_ASSIGNEDTO + " TEXT," // field 8
					+ ORDER_ASSIGNEDBY + " TEXT," // field 9
					+ ORDER_PROBLEMRPT + " TEXT," // field 10
					+ ORDER_ACCESSORIES + " TEXT," // field 11
					+ ORDER_SOLUTION + " TEXT," // field 12
					+ ORDER_ETC + " TEXT," // field 13
					+ ORDER_DUEDATE + " INTEGER," // field 14
					+ ORDER_AMTDUE + " TEXT ," // field 15
					+ ORDER_PYMTRECD + " TEXT ," // field 16
					+ ORDER_PYMTRECDON + " INTEGER," // field 17

					+ ORDER_PYMTSTATUS + " TEXT," // field 18
					// newly added fields
					+ ORDER_DELCONFIRM + " TEXT," // field 19
					+ ORDER_DELCONFIRMDATE + " INTEGER," // field 20
					+ ORDER_COMPLETEDACTUALDATE + " INTEGER," // field 21
					+ ORDER_PRIORITY + " TEXT," // field 22
					+ ORDER_COMPLEXITY + " TEXT," // field 23
					+ ORDER_SPARESCOST + " TEXT)" // field 24

			);

			db.execSQL("CREATE TABLE " + DATABASE_TABLE3 + "(" + EMP_ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT," + EMP_FIRST_NAME
					+ " TEXT," + EMP_LAST_NAME + " TEXT," + EMP_USERNAME
					+ " TEXT," + EMP_PASSWORD + " TEXT," + EMP_MOBILENUMBER
					+ " TEXT," + EMP_EMAILID + " TEXT," + EMP_ADDRESS
					+ " TEXT," + EMP_ACCESS + " TEXT" + ")");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// T ODO Auto-generated method stub

			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE2);

			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE3);

			onCreate(db);

		}

	}

	public Customerdbwork(Context c) {

		context = c;
	}

	public Customerdbwork open() throws SQLException {

		customdatahelper = new dbhelper(context);
		newdb = customdatahelper.getWritableDatabase();
		return this;

	}

	public long addcustomer(String id, String Name, String emailid,
			String phone, String fromdate, String Address)

	{
		ContentValues cv = new ContentValues();
		// cv.put(CUSTOMER_ID, id );
		cv.put(CUSTOMER_NAME, Name);
		cv.put(CUSTOMER_MOBILENO, phone);
		cv.put(CUSTOMER_DATE, fromdate);
		cv.put(CUSTOMER_EMAILID, emailid);
		cv.put(CUSTOMER_ADDRESS, Address);

		return newdb.insert(DATABASE_TABLE, null, cv);

	}

	public Long addorder(String setnewitemdate, String getstatus,
			String setnewordercustomermobile,
			String setnewitemname,
			String setnewitemdesc,
			String cat_type,
			String setassignedto,
			String setassignedby,
			String setproblemrpt,
			String setaccessories, // new field modified.
			String setsolution, String setetc, String setduedate,
			String settvpaydue, String settvpayreceived,
			String settvpayreceivedon, String getPaymenttype,
			String setdelconfirm, String setdelconfrimdate,
			String setcompletedactualdate, String setpriority,
			String setcomplexity,String setsparescost

	)

	{
		long rowid = 0;
		ContentValues cvorder = new ContentValues();

		cvorder.put("ORDER_DATE", setnewitemdate);
		cvorder.put("ORDER_STATUS", getstatus);
		cvorder.put("ORDER_CUSTOMERMOBILE", setnewordercustomermobile);
		cvorder.put("ORDER_NAME", setnewitemname);
		cvorder.put("ORDER_DESCRIPTION", setnewitemdesc);
		cvorder.put("ORDER_TYPE", cat_type);
		cvorder.put("ORDER_ASSIGNEDTO", setassignedto);
		cvorder.put("ORDER_ASSIGNEDBY", setassignedby);
		cvorder.put("ORDER_PROBLEMRPT", setproblemrpt);
		cvorder.put("ORDER_ACCESSORIES", setaccessories);
		cvorder.put("ORDER_SOLUTION", setsolution);
		cvorder.put("ORDER_ETC", setetc);
		cvorder.put("ORDER_DUEDATE", setduedate);
		cvorder.put("ORDER_AMTDUE", settvpaydue);
		cvorder.put("ORDER_PYMTRECD", settvpayreceived);
		cvorder.put("ORDER_PYMTRECDON", settvpayreceivedon);
		cvorder.put("ORDER_PYMTSTATUS", getPaymenttype);
		cvorder.put("ORDER_DELCONFIRM", setdelconfirm);
		cvorder.put("ORDER_DELCONFIRMDATE", setdelconfrimdate);
		cvorder.put("ORDER_COMPLETEDACTUALDATE", setcompletedactualdate);
		cvorder.put("ORDER_PRIORITY", setpriority);
		cvorder.put("ORDER_COMPLEXITY", setcomplexity);
		cvorder.put("ORDER_SPARESCOST", setsparescost);
		
		rowid = newdb.insert(DATABASE_TABLE2, null, cvorder);

		String[] Columnorderid = { "ORDER_ID" };
		Cursor getorderid = newdb.query(DATABASE_TABLE2, Columnorderid,
				"_rowid_" + "=" + rowid, null, null, null, null);

		long getorderidvalue = 0;

		for (getorderid.moveToFirst(); !getorderid.isAfterLast(); getorderid
				.moveToNext()) {
			getorderidvalue = getorderid.getLong(0);
		}

		return getorderidvalue;

	}

	public void close()

	{
		newdb.close();

	}

	public String viewdetails() {
		String result = "";

		String[] columnname = { "CUSTOMER_ID", "CUSTOMER_NAME",
				"CUSTOMER_DATE", "CUSTOMER_MOBILENO", "CUSTOMER_EMAILID",
				"CUSTOMER_ADDRESS" };

		Cursor ReadCustomer = newdb.query(DATABASE_TABLE, columnname, null,
				null, null, null, null);

		int i = ReadCustomer.getColumnIndex(CUSTOMER_ID);
		int j = ReadCustomer.getColumnIndex(CUSTOMER_NAME);
		int k = ReadCustomer.getColumnIndex(CUSTOMER_DATE);
		int l = ReadCustomer.getColumnIndex(CUSTOMER_MOBILENO);
		int m = ReadCustomer.getColumnIndex(CUSTOMER_EMAILID);
		int n = ReadCustomer.getColumnIndex(CUSTOMER_ADDRESS);

		for (ReadCustomer.moveToFirst(); !ReadCustomer.isAfterLast(); ReadCustomer
				.moveToNext())

		{

			result = result + ReadCustomer.getString(i) + ","
				+"\""	+ ReadCustomer.getString(j)  +"\","
					+"\""+ ReadCustomer.getString(k) +"\","
					+"\"" +ReadCustomer.getString(l) +"\","
					+"\"" +ReadCustomer.getString(m) +"\","
					+"\"" +ReadCustomer.getString(n) +"\"," + " \n ";

		}

		return result;

	}

	public String printallreport() {

		String allcolumnresult = "";
		String[] allcolumns = { "ORDER_ID", // 0
				"ORDER_DATE", // 1
				"ORDER_STATUS", // 2
				"ORDER_CUSTOMERMOBILE", // 3
				"ORDER_NAME", // 4
				"ORDER_DESCRIPTION", // 5
				"ORDER_TYPE", // 6
				"ORDER_ASSIGNEDTO", // 7
				"ORDER_ASSIGNEDBY", // 8
				"ORDER_PROBLEMRPT", // 9
				"ORDER_ACCESSORIES", // 10
				"ORDER_SOLUTION", // 11
				"ORDER_ETC", // 12
				"ORDER_DUEDATE", // 13
				"ORDER_AMTDUE", // 14
				"ORDER_PYMTRECD", // 15
				"ORDER_PYMTRECDON", // 16
				"ORDER_PYMTSTATUS", // 17
				"ORDER_DELCONFIRM", // 18
				"ORDER_DELCONFIRMDATE", // 19
				"ORDER_COMPLETEDACTUALDATE", // 20
				"ORDER_PRIORITY", // 21
				"ORDER_COMPLEXITY",// 22
				"ORDER_SPARESCOST"

		};

		// Total columns 23

		Cursor getallcolorders = newdb.query(DATABASE_TABLE2, allcolumns, null,
				null, null, null, null);

		SimpleDateFormat dateformatonreport = new SimpleDateFormat("dd/MM/yyyy");
		Calendar orderdatecal = Calendar.getInstance();
		Calendar orderduedate = Calendar.getInstance();
		Calendar orderpymtrecd = Calendar.getInstance();
		Calendar orderdelivered = Calendar.getInstance();
		Calendar ordercompleted = Calendar.getInstance();
		String OrderDateR = null;

		String orderduedateR = null;

		String orderpymtrecdR = null;

		String orderdeliveredR = null;

		String ordercompletedR = null;
		;
		for (getallcolorders.moveToFirst(); !getallcolorders.isAfterLast(); getallcolorders
				.moveToNext())

		{
			if (getallcolorders.getString(1) == null) {

			} else {

				orderdatecal.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(1)));
				OrderDateR = dateformatonreport.format(orderdatecal.getTime());

			}

			if (getallcolorders.getString(13) == null) {

			} else {

				orderduedate.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(13)));
				orderduedateR = dateformatonreport.format(orderduedate
						.getTime());
			}
			if (getallcolorders.getString(16) == null) {

			} else {
				orderpymtrecd.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(16)));
				orderpymtrecdR = dateformatonreport.format(orderpymtrecd
						.getTime());
			}

			if (getallcolorders.getString(19) == null) {

			} else {

				orderdelivered.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(19)));
				orderdeliveredR = dateformatonreport.format(orderdelivered
						.getTime());
			}
			if (getallcolorders.getString(20) == null) {

			} else {
				ordercompleted.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(20)));
				ordercompletedR = dateformatonreport.format(ordercompleted
						.getTime());
			}

			allcolumnresult = allcolumnresult
					+ "\n**************************************\n"
					+ "Order ID			:" + getallcolorders.getString(0)+ "\n"
					+ "************************************\n"
					+ "Order Date		:" + OrderDateR + "\n"
					+ "Order Status 	:" + getallcolorders.getString(2) + "\n" 
					+ "Customer Mobile	:" + getallcolorders.getString(3) + "\n"
					+ "Order Details:-"	+ "\n"
					+ "Item Name		:" + getallcolorders.getString(4)+ "\n"
					+ "Description		:" + getallcolorders.getString(5)+ "\n"
					+ "Type				:" + getallcolorders.getString(6) + "\n"
					+ "Assigned TO		:" + getallcolorders.getString(7) + "\n"
					+ "Assigned By		:" + getallcolorders.getString(8) + "\n"
					+ "Problem Reported	:" + getallcolorders.getString(9) + "\n" 
					+ "Accessories		:" + getallcolorders.getString(10) + "\n" 
					+ "Comments/Reason  :" + getallcolorders.getString(11)+ "\n"
					+ "Estimated Time	:" + getallcolorders.getString(12) + "\n"
					+ "DueDate			:" + orderduedateR + "\n"
					+ "Amount Due		:" + getallcolorders.getString(14) + "\n"
					+ "Amount Received	:" + getallcolorders.getString(15) + "\n" 
					+ "Spare(s) cost    :" + getallcolorders.getString(23)+"\n" 
					+ "Received On		:" + orderpymtrecdR + "\n"
					+ "Payment Status	:" + getallcolorders.getString(17) + "\n"
					+ "Delivered(Y/N)	:" + getallcolorders.getString(18) + "\n"
					+ "Delivered DATE	:" + orderdeliveredR + "\n"
					+ "Completed Date	:" + ordercompletedR + "\n"
					+ "Priority 		:" + getallcolorders.getString(21) + "\n"
					+ "Complexity		:" + getallcolorders.getString(22);
					

		}

		return allcolumnresult;

	}

	public Cursor getorderid(String mobilenumber) {

		String sql = "SELECT rowid _id, "
			    + ORDER_ID +","
				+ ORDER_NAME + ","
				+ "date(("+ORDER_DATE +"/1000)," + "'unixepoch') as order_date,"
				+  ORDER_STATUS + ","
				+ ORDER_PYMTSTATUS 
				+ " from  order_details WHERE ORDER_CUSTOMERMOBILE="
				+ mobilenumber;

		Cursor getordersbymob = newdb.rawQuery(sql, null);
		return getordersbymob;

		
		
		
	}

	public ContentValues readwriteexistorder(String etneworderidsearchs)

	{

		String[] allcolumns = { "ORDER_ID", // 0
				"ORDER_DATE", // 1
				"ORDER_STATUS", // 2
				"ORDER_CUSTOMERMOBILE", // 3
				"ORDER_NAME", // 4
				"ORDER_DESCRIPTION", // 5
				"ORDER_TYPE", // 6
				"ORDER_ASSIGNEDTO", // 7
				"ORDER_ASSIGNEDBY", // 8
				"ORDER_PROBLEMRPT", // 9
				"ORDER_ACCESSORIES", // 10
				"ORDER_SOLUTION", // 11
				"ORDER_ETC", // 12
				"ORDER_DUEDATE", // 13
				"ORDER_AMTDUE", // 14
				"ORDER_PYMTRECD", // 15
				"ORDER_PYMTRECDON", // 16
				"ORDER_PYMTSTATUS", // 17
				"ORDER_DELCONFIRM", // 18
				"ORDER_DELCONFIRMDATE", // 19
				"ORDER_COMPLETEDACTUALDATE", // 20
				"ORDER_PRIORITY", // 21
				"ORDER_COMPLEXITY", //22
		"ORDER_SPARESCOST"   //23		
		}; 

		Long orderidtosearch = Long.parseLong(etneworderidsearchs);

		Cursor existvaluescursor = newdb.query(DATABASE_TABLE2, allcolumns,
				"ORDER_ID" + "=" + orderidtosearch, null, null, null, null);

		int count = existvaluescursor.getCount();
		ContentValues exist_order = null;

		if (!(count == 0))

		{

			exist_order = new ContentValues();

			for (existvaluescursor.moveToFirst(); !existvaluescursor
					.isAfterLast(); existvaluescursor.moveToNext()) {

				exist_order.put("ORDERID", existvaluescursor.getString(0));
				exist_order.put("ORDERDATE", existvaluescursor.getString(1));
				exist_order.put("ORDERSTATUS", existvaluescursor.getString(2));
				exist_order.put("ORDERCUSTOMERMOBILE",
						existvaluescursor.getString(3));
				exist_order.put("ORDERNAME", existvaluescursor.getString(4));
				exist_order.put("ORDERDESCRIPTION",
						existvaluescursor.getString(5));
				exist_order.put("ORDER_TYPE", existvaluescursor.getString(6));

				exist_order.put("ORDERASSIGNEDTO",
						existvaluescursor.getString(7));
				exist_order.put("ORDERASSIGNEDBY",
						existvaluescursor.getString(8));
				exist_order.put("ORDERPROBLEMRPT",
						existvaluescursor.getString(9));
				exist_order.put("ORDERACCESSORIES",
						existvaluescursor.getString(10));
				exist_order.put("ORDERSOLUTION",
						existvaluescursor.getString(11));
				exist_order.put("ORDERETC", existvaluescursor.getString(12));
				exist_order
						.put("ORDERDUEDATE", existvaluescursor.getString(13));
				exist_order.put("ORDERAMTDUE", existvaluescursor.getString(14));
				exist_order.put("ORDERPYMTRECD",
						existvaluescursor.getString(15));
				exist_order.put("ORDERPYMTRECDON",
						existvaluescursor.getString(16));
				exist_order.put("ORDERPYMTSTATUS",
						existvaluescursor.getString(17));
				// new fields
				exist_order.put("ORDERDELCONFIRM",
						existvaluescursor.getString(18));
				exist_order.put("ORDERDELCONFIRMDATE",
						existvaluescursor.getString(19));

				exist_order.put("ORDERCOMPLETEDACTUALDATE",
						existvaluescursor.getString(20));
				exist_order.put("ORDERPRIORITY",
						existvaluescursor.getString(21));
				exist_order.put("ORDERCOMPLEXITY",
						existvaluescursor.getString(22));
				exist_order.put("ORDERSPARESCOST",
						existvaluescursor.getString(23));
			}

		}

		return exist_order;
	}

	public String vieworder() {
		String orderresult = "";
		String[] getimportantcolumn = { "ORDER_ID", "ORDER_CUSTOMERMOBILE",
				"ORDER_TYPE", "ORDER_DUEDATE" };

		Cursor getcursorfororder = newdb.query(DATABASE_TABLE2,
				getimportantcolumn, null, null, null, null, null);

		for (getcursorfororder.moveToFirst(); !getcursorfororder.isAfterLast(); getcursorfororder
				.moveToNext())

		{

			orderresult = orderresult + getcursorfororder.getInt(0) + " "
					+ getcursorfororder.getString(1) + " "
					+ getcursorfororder.getString(2) + " "
					+ getcursorfororder.getString(3) + " \n";

		}
		return orderresult;

	}

	public int deleterecord(String getmobileno) {

		return newdb.delete(DATABASE_TABLE, "CUSTOMER_MOBILENO" + "="
				+ getmobileno, null);

	}

	public ContentValues searchdbbymobile(String mobileno) {

		// TODO Auto-generated method stub

		String Cs_id = "";
		String Cs_name = "";
		String Cs_date = "";
		String Cs_mobileno = "";
		String Cs_emailid = "";
		String Cs_address = "";

		String[] retcolumns = { "CUSTOMER_ID", "CUSTOMER_NAME",
				"CUSTOMER_DATE", "CUSTOMER_MOBILENO", "CUSTOMER_EMAILID",
				"CUSTOMER_ADDRESS" };

		Cursor getvalues = newdb.query(DATABASE_TABLE, retcolumns,
				"CUSTOMER_MOBILENO" + "=" + mobileno, null, null, null, null);

		int igetvalues = getvalues.getColumnIndex(CUSTOMER_ID);
		int jgetvalues = getvalues.getColumnIndex(CUSTOMER_NAME);
		int kgetvalues = getvalues.getColumnIndex(CUSTOMER_DATE);
		int lgetvalues = getvalues.getColumnIndex(CUSTOMER_MOBILENO);
		int mgetvalues = getvalues.getColumnIndex(CUSTOMER_EMAILID);
		int ngetvalues = getvalues.getColumnIndex(CUSTOMER_ADDRESS);
		ContentValues getcusinfo = new ContentValues();
		if (!getvalues.isAfterLast())

		{
			getvalues.moveToFirst();

			Cs_id = getvalues.getString(igetvalues);
			Cs_name = getvalues.getString(jgetvalues);
			Cs_date = getvalues.getString(kgetvalues);
			Cs_mobileno = getvalues.getString(lgetvalues);
			Cs_emailid = getvalues.getString(mgetvalues);
			Cs_address = getvalues.getString(ngetvalues);

			getcusinfo.put("kCs_id", Cs_id);
			getcusinfo.put("kCs_name", Cs_name);
			getcusinfo.put("kCs_date", Cs_date);
			getcusinfo.put("kCs_mobileno", Cs_mobileno);
			getcusinfo.put("kCs_emailid", Cs_emailid);
			getcusinfo.put("kCs_address", Cs_address);

		}
		return getcusinfo;

	}

	public int updateorderindb(ContentValues cvtoupdate) {
		// TODO Auto-generated method stub

		Long orderidl = cvtoupdate.getAsLong("ORDER_ID");

		return newdb.update(DATABASE_TABLE2, cvtoupdate, ORDER_ID + "="
				+ orderidl, null);

	}

	public int updatecustomer(String scustomerid, String sname,
			String semailid, String smobileno, String sdate, String saddress) {
		// TODO Auto-generated method stub

		ContentValues cvu = new ContentValues();

		Long getcustomerid = Long.parseLong(scustomerid);

		// cvu.put(CUSTOMER_ID, scustomerid );
		cvu.put(CUSTOMER_NAME, sname);
		cvu.put(CUSTOMER_MOBILENO, smobileno);
		cvu.put(CUSTOMER_DATE, sdate);
		cvu.put(CUSTOMER_EMAILID, semailid);
		cvu.put(CUSTOMER_ADDRESS, saddress);

		return newdb.update(DATABASE_TABLE, cvu, CUSTOMER_ID + "="
				+ getcustomerid, null);

	}

	// Orders due today

	public String printorderstoday() {

		String allcolumnresult = "";
		String[] allcolumns = { "ORDER_ID", // 0
				"ORDER_DATE", // 1
				"ORDER_STATUS", // 2
				"ORDER_CUSTOMERMOBILE", // 3
				"ORDER_NAME", // 4
				"ORDER_DESCRIPTION", // 5
				"ORDER_TYPE", // 6
				"ORDER_ASSIGNEDTO", // 7
				"ORDER_ASSIGNEDBY", // 8
				"ORDER_PROBLEMRPT", // 9
				"ORDER_ACCESSORIES", // 10
				"ORDER_SOLUTION", // 11
				"ORDER_ETC", // 12
				"ORDER_DUEDATE", // 13
				"ORDER_AMTDUE", // 14
				"ORDER_PYMTRECD", // 15
				"ORDER_PYMTRECDON", // 16
				"ORDER_PYMTSTATUS", // 17
				"ORDER_DELCONFIRM", // 18
				"ORDER_DELCONFIRMDATE", // 19
				"ORDER_COMPLETEDACTUALDATE", // 20
				"ORDER_PRIORITY", // 21
				"ORDER_COMPLEXITY", // 22
				"ORDER_SPARESCOST"

		};

		// Total columns 23

		Cursor getallcolorders = newdb.query(DATABASE_TABLE2, allcolumns, null,
				null, null, null, null);

		SimpleDateFormat dateformatonreport = new SimpleDateFormat("dd/MM/yyyy");
		Calendar orderdatecal = Calendar.getInstance();
		Calendar orderduedate = Calendar.getInstance();
		Calendar orderpymtrecd = Calendar.getInstance();
		Calendar orderdelivered = Calendar.getInstance();
		Calendar ordercompleted = Calendar.getInstance();
		String OrderDateR = null;

		String orderduedateR = null;

		String orderpymtrecdR = null;

		String orderdeliveredR = null;

		String ordercompletedR = null;
		;

		for (getallcolorders.moveToFirst(); !getallcolorders.isAfterLast(); getallcolorders
				.moveToNext())

		{
			if (getallcolorders.getString(1) == null) {

			} else {

				orderdatecal.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(1)));
				OrderDateR = dateformatonreport.format(orderdatecal.getTime());

			}

			if (getallcolorders.getString(13) == null) {

			} else {

				orderduedate.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(13)));

				orderduedateR = dateformatonreport.format(orderduedate
						.getTime());
			}

			// Trying to skip the for Loop
			Calendar currentdate = Calendar.getInstance();

			if (orderduedate.get(Calendar.DAY_OF_YEAR) == currentdate
					.get(Calendar.DAY_OF_YEAR)
					&& orderduedate.get(Calendar.YEAR) == currentdate
							.get(Calendar.YEAR))

			{

			} else

			{

				continue;
			}

			if (getallcolorders.getString(16) == null) {

			} else {
				orderpymtrecd.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(16)));
				orderpymtrecdR = dateformatonreport.format(orderpymtrecd
						.getTime());
			}

			if (getallcolorders.getString(19) == null) {

			} else {

				orderdelivered.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(19)));
				orderdeliveredR = dateformatonreport.format(orderdelivered
						.getTime());
			}
			if (getallcolorders.getString(20) == null) {

			} else {
				ordercompleted.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(20)));
				ordercompletedR = dateformatonreport.format(ordercompleted
						.getTime());
			}

			allcolumnresult = allcolumnresult
					+ "\n*************************************************************************\n"
					+ "Order ID :"	+ getallcolorders.getString(0)+ "\n"
					+ "\n*************************************************************************\n"
					+ "Order Date     :- " + OrderDateR + "\n" 
					+ "Order Status   :-"	+ getallcolorders.getString(2) + "\n"
					+ "Customer Mobile:-"+ getallcolorders.getString(3) + "\n"
					+ "Order Details  :" + "\n"
					+ "Item Name      :" + getallcolorders.getString(4) + "\n"
					+ "Description    :" + getallcolorders.getString(5) + "\n"
					+ "Type           :" + getallcolorders.getString(6) + "\n"
					+ "Assigned To    :" + getallcolorders.getString(7) + "\n"
					+ "Assigned by    :" + getallcolorders.getString(8) + "\n"
					+ "Problem Report :" + getallcolorders.getString(9) + "\n"
					+ "Accessories    :" + getallcolorders.getString(10) + "\n"
					+ "Comments       :" + getallcolorders.getString(11)+ "\n"
					+ "ETC(In Hours)  :" + getallcolorders.getString(12) + "\n"
					+ "Due Date       :" + orderduedateR + "\n"
					+ "Amount Due     :" + getallcolorders.getString(14) + "\n"
					+ "Amount Received:" + getallcolorders.getString(15)+ "\n"
					+ "Spare(s) Cost  :" + getallcolorders.getString(23)+ "\n"  // new column inserted here
					+ "Pymt Recd On   :" + orderpymtrecdR + "\n"
					+ "Payment Status :" + getallcolorders.getString(17) + "\n"
					+ "DELIVERED (Y/N):" + getallcolorders.getString(18) + "\n" 
					+ "DELIVERD DATE  :" + orderdeliveredR + "\n"
					+ "Completed Date :" + ordercompletedR + "\n"
					+ "Priority       :" + getallcolorders.getString(21) + "\n"
					+ "Complexity     :" + getallcolorders.getString(22);

		}

		return allcolumnresult;

	}

	public String ViewAlljobs()
	{

		String allcolumnresult = "";
		String[] allcolumns = { "ORDER_ID", // 0
				"ORDER_DATE", // 1
				"ORDER_STATUS", // 2
				"ORDER_CUSTOMERMOBILE", // 3
				"ORDER_NAME", // 4
				"ORDER_DESCRIPTION", // 5
				"ORDER_TYPE", // 6
				"ORDER_ASSIGNEDTO", // 7
				"ORDER_ASSIGNEDBY", // 8
				"ORDER_PROBLEMRPT", // 9
				"ORDER_ACCESSORIES", // 10
				"ORDER_SOLUTION", // 11
				"ORDER_ETC", // 12
				"ORDER_DUEDATE", // 13
				"ORDER_AMTDUE", // 14
				"ORDER_PYMTRECD", // 15
				"ORDER_PYMTRECDON", // 16
				"ORDER_PYMTSTATUS", // 17
				"ORDER_DELCONFIRM", // 18
				"ORDER_DELCONFIRMDATE", // 19
				"ORDER_COMPLETEDACTUALDATE", // 20
				"ORDER_PRIORITY", // 21
				"ORDER_COMPLEXITY", // 22
				"ORDER_SPARESCOST"

		};

		// Total columns 23

		Cursor getallcolorders = newdb.query(DATABASE_TABLE2, allcolumns, null,
				null, null, null, null);

		SimpleDateFormat dateformatonreport = new SimpleDateFormat("dd/MM/yyyy");
		Calendar orderdatecal = Calendar.getInstance();
		Calendar orderduedate = Calendar.getInstance();
		Calendar orderpymtrecd = Calendar.getInstance();
		Calendar orderdelivered = Calendar.getInstance();
		Calendar ordercompleted = Calendar.getInstance();
		String OrderDateR = null;

		String orderduedateR = null;

		String orderpymtrecdR = null;

		String orderdeliveredR = null;

		String ordercompletedR = null;
		;

		for (getallcolorders.moveToFirst(); !getallcolorders.isAfterLast(); getallcolorders
				.moveToNext())

		{
			if (getallcolorders.getString(1) == null) {

			} else {

				orderdatecal.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(1)));
				OrderDateR = dateformatonreport.format(orderdatecal.getTime());

			}

			if (getallcolorders.getString(13) == null) {

			} else {

				orderduedate.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(13)));

				orderduedateR = dateformatonreport.format(orderduedate
						.getTime());
			}

		
			if (getallcolorders.getString(16) == null) {

			} else {
				orderpymtrecd.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(16)));
				orderpymtrecdR = dateformatonreport.format(orderpymtrecd
						.getTime());
			}

			if (getallcolorders.getString(19) == null) {

			} else {

				orderdelivered.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(19)));
				orderdeliveredR = dateformatonreport.format(orderdelivered
						.getTime());
			}
			if (getallcolorders.getString(20) == null) {

			} else {
				ordercompleted.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(20)));
				ordercompletedR = dateformatonreport.format(ordercompleted
						.getTime());
			}

			allcolumnresult = allcolumnresult
					+ "\n*************************************************************************\n"
					+ "Order ID :"	+ getallcolorders.getString(0)+ "\n"
					+ "\n*************************************************************************\n"
					+ "Order Date     :- " + OrderDateR + "\n" 
					+ "Order Status   :-"	+ getallcolorders.getString(2) + "\n"
					+ "Customer Mobile:-"+ getallcolorders.getString(3) + "\n"
					+ "Order Details  :" + "\n"
					+ "Item Name      :" + getallcolorders.getString(4) + "\n"
					+ "Description    :" + getallcolorders.getString(5) + "\n"
					+ "Type           :" + getallcolorders.getString(6) + "\n"
					+ "Assigned To    :" + getallcolorders.getString(7) + "\n"
					+ "Assigned by    :" + getallcolorders.getString(8) + "\n"
					+ "Problem Report :" + getallcolorders.getString(9) + "\n"
					+ "Accessories    :" + getallcolorders.getString(10) + "\n"
					+ "Comments       :" + getallcolorders.getString(11)+ "\n"
					+ "ETC(In Hours)  :" + getallcolorders.getString(12) + "\n"
					+ "Due Date       :" + orderduedateR + "\n"
					+ "Amount Due     :" + getallcolorders.getString(14) + "\n"
					+ "Amount Received:" + getallcolorders.getString(15)+ "\n"
					+ "Spare(s) Cost  :" + getallcolorders.getString(23)+ "\n"  // new column inserted here
					+ "Pymt Recd On   :" + orderpymtrecdR + "\n"
					+ "Payment Status :" + getallcolorders.getString(17) + "\n"
					+ "DELIVERED (Y/N):" + getallcolorders.getString(18) + "\n" 
					+ "DELIVERD DATE  :" + orderdeliveredR + "\n"
					+ "Completed Date :" + ordercompletedR + "\n"
					+ "Priority       :" + getallcolorders.getString(21) + "\n"
					+ "Complexity     :" + getallcolorders.getString(22);

		}

		return allcolumnresult;

	}
	
	
	// Orders with Pending payments

	public String paymentpending() {

		String allcolumnresult = "";
		String[] allcolumns = { "ORDER_ID", // 0
				"ORDER_DATE", // 1
				"ORDER_STATUS", // 2
				"ORDER_CUSTOMERMOBILE", // 3
				"ORDER_NAME", // 4
				"ORDER_DESCRIPTION", // 5
				"ORDER_TYPE", // 6
				"ORDER_ASSIGNEDTO", // 7
				"ORDER_ASSIGNEDBY", // 8
				"ORDER_PROBLEMRPT", // 9
				"ORDER_ACCESSORIES", // 10
				"ORDER_SOLUTION", // 11
				"ORDER_ETC", // 12
				"ORDER_DUEDATE", // 13
				"ORDER_AMTDUE", // 14
				"ORDER_PYMTRECD", // 15
				"ORDER_PYMTRECDON", // 16
				"ORDER_PYMTSTATUS", // 17
				"ORDER_DELCONFIRM", // 18
				"ORDER_DELCONFIRMDATE", // 19
				"ORDER_COMPLETEDACTUALDATE", // 20
				"ORDER_PRIORITY", // 21
				"ORDER_COMPLEXITY", // 22
				"ORDER_SPARESCOST"

		};

		// Total columns 23

		Cursor getallcolorders = newdb.query(DATABASE_TABLE2, allcolumns, null,
				null, null, null, null);

		SimpleDateFormat dateformatonreport = new SimpleDateFormat("dd/MM/yyyy");
		Calendar orderdatecal = Calendar.getInstance();
		Calendar orderduedate = Calendar.getInstance();
		Calendar orderpymtrecd = Calendar.getInstance();
		Calendar orderdelivered = Calendar.getInstance();
		Calendar ordercompleted = Calendar.getInstance();
		String OrderDateR = null;

		String orderduedateR = null;

		String orderpymtrecdR = null;

		String orderdeliveredR = null;

		String ordercompletedR = null;
		;

		for (getallcolorders.moveToFirst(); !getallcolorders.isAfterLast(); getallcolorders
				.moveToNext())

		{
			if (getallcolorders.getString(1) == null) {

			} else {

				orderdatecal.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(1)));
				OrderDateR = dateformatonreport.format(orderdatecal.getTime());

			}

			if (getallcolorders.getString(13) == null) {

			} else {

				orderduedate.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(13)));

				orderduedateR = dateformatonreport.format(orderduedate
						.getTime());
			}

			// Trying to skip the for Loop for payments which are not pending

			if (getallcolorders.getString(17).equals("Pending"))

			{

			} else if (getallcolorders.getString(17).equals("Partial")) {

			}

			else

			{

				continue;
			}

			if (getallcolorders.getString(16) == null) {

			} else {
				orderpymtrecd.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(16)));
				orderpymtrecdR = dateformatonreport.format(orderpymtrecd
						.getTime());
			}

			if (getallcolorders.getString(19) == null) {

			} else {

				orderdelivered.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(19)));
				orderdeliveredR = dateformatonreport.format(orderdelivered
						.getTime());
			}
			if (getallcolorders.getString(20) == null) {

			} else {
				ordercompleted.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(20)));
				ordercompletedR = dateformatonreport.format(ordercompleted
						.getTime());
			}

			allcolumnresult = allcolumnresult
					+ "\n************************************************************************************************************************\n"
					+ "OrderID 				:"+ getallcolorders.getString(0) + "\n"
					+ "\n************************************************************************************************************************\n"
					+ "OrderDate			:" + OrderDateR + "\n" 
					+ "Order Status 		:" + getallcolorders.getString(2) + "\n"
					+ "Customer Mobile		:" + getallcolorders.getString(3) + "\n" 
					+ "Order Details		:-" + "\n" 
					+ "ITEM_NAME			:" + getallcolorders.getString(4) + "\n" 
					+ "DESCRIPTION			:" + getallcolorders.getString(5) + "\n"
					+ "ITEM TYPE			:" + getallcolorders.getString(6) + "\n" 
					+ "ASSIGNED TO			:" + getallcolorders.getString(7) + "\n"
					+ "ASSIGNED BY			:" + getallcolorders.getString(8) + "\n"
					+ "PROBLEM REPORTED		:" + getallcolorders.getString(9) + "\n"
					+ "Accessories          :" + getallcolorders.getString(10) + "\n"
					+ "SUGGESTED SOLUTION   :" + getallcolorders.getString(11)+ "\n"
					+ "ETC(in Hours)		:" + getallcolorders.getString(12)+ "\n"
					+ "Due Date				:" + orderduedateR + "\n"
					+ "Amt Estimate 		:" + getallcolorders.getString(14) + "\n"
					+ "Amount Received		:" + getallcolorders.getString(15) + "\n"
					+ "Spare(s) Cost		:" + getallcolorders.getString(23) + "\n" // new col for spares
					+ "Received on			:" + orderpymtrecdR + "\n"
					+ "Payment Status		:" + getallcolorders.getString(17)+ "\n"
					+ "Delivered            :" + getallcolorders.getString(18) + "\n" 
					+ "Delivered date		:" + orderdeliveredR + "\n"
					+ "Completed Date       :" + ordercompletedR + "\n"
					+ "PRIORITY				:" + getallcolorders.getString(21) + "\n" 
					+ "COMPLEXITY			:" + getallcolorders.getString(22);

		}

		return allcolumnresult;

	}

	public String printorder(Long orderid) {
		String mobilenofromorder = null;
		String csname = null;
		String csmobileno = null;
		String csemail = null;
		String allcolumnresult = "";
		String[] allcolumns = { "ORDER_ID", // 0
				"ORDER_DATE", // 1
				"ORDER_STATUS", // 2
				"ORDER_CUSTOMERMOBILE", // 3
				"ORDER_NAME", // 4
				"ORDER_DESCRIPTION", // 5
				"ORDER_TYPE", // 6
				"ORDER_ASSIGNEDTO", // 7
				"ORDER_ASSIGNEDBY", // 8
				"ORDER_PROBLEMRPT", // 9
				"ORDER_ACCESSORIES", // 10
				"ORDER_SOLUTION", // 11
				"ORDER_ETC", // 12
				"ORDER_DUEDATE", // 13
				"ORDER_AMTDUE", // 14
				"ORDER_PYMTRECD", // 15
				"ORDER_PYMTRECDON", // 16
				"ORDER_PYMTSTATUS", // 17
				"ORDER_DELCONFIRM", // 18
				"ORDER_DELCONFIRMDATE", // 19
				"ORDER_COMPLETEDACTUALDATE", // 20
				"ORDER_PRIORITY", // 21
				"ORDER_COMPLEXITY", // 22
				"ORDER_SPARESCOST"

		};

		// Total columns 23

		Cursor getallcolorders = newdb.query(DATABASE_TABLE2, allcolumns,
				ORDER_ID + "=" + orderid, null, null, null, null);

		SimpleDateFormat dateformatonreport = new SimpleDateFormat("dd/MM/yyyy");
		Calendar orderdatecal = Calendar.getInstance();

		String OrderDateR = null;

		for (getallcolorders.moveToFirst(); !getallcolorders.isAfterLast(); getallcolorders
				.moveToNext())

		{
			if (getallcolorders.getString(1) == null) {

			} else {

				orderdatecal.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(1)));
				mobilenofromorder = getallcolorders.getString(3);
				OrderDateR = dateformatonreport.format(orderdatecal.getTime());

			}

			String[] Colidfromcustomer = { "CUSTOMER_NAME",
					"CUSTOMER_MOBILENO", "CUSTOMER_EMAILID" };

			Cursor getcustdetails = newdb.query(DATABASE_TABLE,
					Colidfromcustomer, CUSTOMER_MOBILENO + "="
							+ mobilenofromorder, null, null, null, null);

			for (getcustdetails.moveToFirst(); !getcustdetails.isAfterLast(); getcustdetails
					.moveToNext()) {

				csname = getcustdetails.getString(0);

				csmobileno = getcustdetails.getString(1);

				csemail = getcustdetails.getString(2);

			}

			allcolumnresult = allcolumnresult
					+ "\n************************************************************\n"
					+ "\nCustomer Name		:" + csname + "\nCustomer Mobileno	:"
					+ csmobileno + "\nCustomer Email		:" + csemail
					+ "\nJob Number 		:" + getallcolorders.getString(0)
					+ "\nDate				:" + OrderDateR + "\nCUSTOMER MOBILE:-"
					+ getallcolorders.getString(3) + "\nItem Name:-"
					+ getallcolorders.getString(4) + "\nDescription:-"
					+ getallcolorders.getString(5) + "\n" + "\nType:-"
					+ getallcolorders.getString(6) + "\n Problem Reported:-"
					+ getallcolorders.getString(9) + "\n"
					+ "\nAdditional Accessories:-"
					+ getallcolorders.getString(10) + "\nComments:-"
					+ getallcolorders.getString(11) + "\nService Charges:-"
					+ getallcolorders.getString(14);

		}

		return allcolumnresult;

	}

	public ContentValues printorderdirect(Long orderid) {

		ContentValues cvforprint = new ContentValues();
		String mobilenofromorder = null;
		String csname = null;
		String csmobileno = null;
		String csemail = null;

		String[] allcolumns = { "ORDER_ID", // 0
				"ORDER_DATE", // 1
				"ORDER_STATUS", // 2
				"ORDER_CUSTOMERMOBILE", // 3
				"ORDER_NAME", // 4
				"ORDER_DESCRIPTION", // 5
				"ORDER_TYPE", // 6
				"ORDER_ASSIGNEDTO", // 7
				"ORDER_ASSIGNEDBY", // 8
				"ORDER_PROBLEMRPT", // 9
				"ORDER_ACCESSORIES", // 10
				"ORDER_SOLUTION", // 11
				"ORDER_ETC", // 12
				"ORDER_DUEDATE", // 13
				"ORDER_AMTDUE", // 14
				"ORDER_PYMTRECD", // 15
				"ORDER_PYMTRECDON", // 16
				"ORDER_PYMTSTATUS", // 17
				"ORDER_DELCONFIRM", // 18
				"ORDER_DELCONFIRMDATE", // 19
				"ORDER_COMPLETEDACTUALDATE", // 20
				"ORDER_PRIORITY", // 21
				"ORDER_COMPLEXITY" // 22

		};

		// Total columns 23

		Cursor getallcolorders = newdb.query(DATABASE_TABLE2, allcolumns,
				ORDER_ID + "=" + orderid, null, null, null, null);

		SimpleDateFormat dateformatonreport = new SimpleDateFormat("dd/MM/yyyy");
		Calendar orderdatecal = Calendar.getInstance();

		String OrderDateR = null;

		for (getallcolorders.moveToFirst(); !getallcolorders.isAfterLast(); getallcolorders
				.moveToNext())

		{
			if (getallcolorders.getString(1) == null) {

			} else {

				orderdatecal.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(1)));
				mobilenofromorder = getallcolorders.getString(3);
				OrderDateR = dateformatonreport.format(orderdatecal.getTime());

			}

			String[] Colidfromcustomer = { "CUSTOMER_NAME",
					"CUSTOMER_MOBILENO", "CUSTOMER_EMAILID" };

			Cursor getcustdetails = newdb.query(DATABASE_TABLE,
					Colidfromcustomer, CUSTOMER_MOBILENO + "="
							+ mobilenofromorder, null, null, null, null);

			for (getcustdetails.moveToFirst(); !getcustdetails.isAfterLast(); getcustdetails
					.moveToNext()) {

				csname = getcustdetails.getString(0);

				csmobileno = getcustdetails.getString(1);

				csemail = getcustdetails.getString(2);

			}

			cvforprint.put("JOB ID", getallcolorders.getString(0)); // 1
			cvforprint.put("Date", OrderDateR);
			cvforprint.put("Item Name", getallcolorders.getString(4));
			cvforprint.put("Description", getallcolorders.getString(5));
			cvforprint.put("Type", getallcolorders.getString(6));
			cvforprint.put("Problem", getallcolorders.getString(9));
			cvforprint.put("Additional Accessories",
					getallcolorders.getString(10));
			cvforprint.put("Comments", getallcolorders.getString(11));
			cvforprint.put("Service Charges", getallcolorders.getString(14));
			cvforprint.put("CUSTOMER_NAME", csname);
			cvforprint.put("CUSTOMER_MOBILENO", csmobileno);
			cvforprint.put("CUSTOMER_EMAILID", csemail);

		}

		return cvforprint;

	}

	public String paymentstatus(String status) {

		String allcolumnresult = "";
		String[] allcolumns = { "ORDER_ID", // 0
				"ORDER_DATE", // 1
				"ORDER_STATUS", // 2
				"ORDER_CUSTOMERMOBILE", // 3
				"ORDER_NAME", // 4
				"ORDER_DESCRIPTION", // 5
				"ORDER_TYPE", // 6
				"ORDER_ASSIGNEDTO", // 7
				"ORDER_ASSIGNEDBY", // 8
				"ORDER_PROBLEMRPT", // 9
				"ORDER_ACCESSORIES", // 10
				"ORDER_SOLUTION", // 11
				"ORDER_ETC", // 12
				"ORDER_DUEDATE", // 13
				"ORDER_AMTDUE", // 14
				"ORDER_PYMTRECD", // 15
				"ORDER_PYMTRECDON", // 16
				"ORDER_PYMTSTATUS", // 17
				"ORDER_DELCONFIRM", // 18
				"ORDER_DELCONFIRMDATE", // 19
				"ORDER_COMPLETEDACTUALDATE", // 20
				"ORDER_PRIORITY", // 21
				"ORDER_COMPLEXITY", // 22
				"ORDER_SPARESCOST"

		};

		// Total columns 23

		Cursor getallcolorders = newdb.query(DATABASE_TABLE2, allcolumns, null,
				null, null, null, null);

		SimpleDateFormat dateformatonreport = new SimpleDateFormat("dd/MM/yyyy");
		Calendar orderdatecal = Calendar.getInstance();
		Calendar orderduedate = Calendar.getInstance();
		Calendar orderpymtrecd = Calendar.getInstance();
		Calendar orderdelivered = Calendar.getInstance();
		Calendar ordercompleted = Calendar.getInstance();
		String OrderDateR = null;

		String orderduedateR = null;

		String orderpymtrecdR = null;

		String orderdeliveredR = null;

		String ordercompletedR = null;
		;

		for (getallcolorders.moveToFirst(); !getallcolorders.isAfterLast(); getallcolorders
				.moveToNext())

		{
			if (getallcolorders.getString(1) == null) {

			} else {

				orderdatecal.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(1)));
				OrderDateR = dateformatonreport.format(orderdatecal.getTime());

			}

			if (getallcolorders.getString(13) == null) {

			} else {

				orderduedate.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(13)));

				orderduedateR = dateformatonreport.format(orderduedate
						.getTime());
			}

			// Trying to skip the for Loop for payments which are not pending

			if (getallcolorders.getString(17).equals(status))

			{

			}

			else

			{

				continue;
			}

			if (getallcolorders.getString(16) == null) {

			} else {
				orderpymtrecd.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(16)));
				orderpymtrecdR = dateformatonreport.format(orderpymtrecd
						.getTime());
			}

			if (getallcolorders.getString(19) == null) {

			} else {

				orderdelivered.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(19)));
				orderdeliveredR = dateformatonreport.format(orderdelivered
						.getTime());
			}
			if (getallcolorders.getString(20) == null) {

			} else {
				ordercompleted.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(20)));
				ordercompletedR = dateformatonreport.format(ordercompleted
						.getTime());
			}

			allcolumnresult = allcolumnresult
					+ "------------------------------------------------\n"
					+ "Job Card 	     :-"	+ getallcolorders.getString(0)+ "\n"
					+ "-------------------------------------------------\n"
					+ "Job Date	    	 :" + OrderDateR + "\n" 
					+ "Job Status		 :"	+ getallcolorders.getString(2) + "\n"
					+ "Customer Mobile   :"+ getallcolorders.getString(3)+ "\n"
					+ "Order Details:" + "\n"
					+ "Name		    	 :"	 + getallcolorders.getString(4) + "\n"
					+ "Description		 :" + getallcolorders.getString(5) + "\n"
					+ "Product Type      :" + getallcolorders.getString(6) + "\n"
					+ "Assigned TO       :" + getallcolorders.getString(7) + "\n"
					+ "Assigned BY       :" + getallcolorders.getString(8) + "\n"
					+ "Problem Reported  :" + getallcolorders.getString(9) + "\n"
					+ "Addl Accessories  :"	+ getallcolorders.getString(10) + "\n"
					+ "Comments          :"	+ getallcolorders.getString(11)	 + "\n"
					+ "ORDER_ETC		 :" + getallcolorders.getString(12) + "\n"
					+ "DUEDATE			 :" + orderduedateR + "\n"
					+ "Estimated Amt     :"	+ getallcolorders.getString(14) + "\n"
					+ "Spare(s) cost     :" + getallcolorders.getString(15)	+ "\n" 
					+ "Amount Received   :" + getallcolorders.getString(15)	+ "\n" 
					
					+ "Received on       :" + orderpymtrecdR + "\n"
					+ "Payment Status    :" + getallcolorders.getString(17) + "\n"
					+ "Delivered         :" + getallcolorders.getString(18)	+ "\n" 
					+ "Delivery Date     :" + orderdeliveredR + "\n"
					+ "Completed  Date   :" + ordercompletedR + "\n"
					+ "Priority          :" + getallcolorders.getString(21) + "\n"
					+ "Complexity        :" + getallcolorders.getString(22) + "\n"
					;

		}

		return allcolumnresult;

	}

	public String pendingorder() {
		// TODO Auto-generated method stub

		String allcolumnresult = "";
		String[] allcolumns = { "ORDER_ID", // 0
				"ORDER_DATE", // 1
				"ORDER_STATUS", // 2
				"ORDER_CUSTOMERMOBILE", // 3
				"ORDER_NAME", // 4
				"ORDER_DESCRIPTION", // 5
				"ORDER_TYPE", // 6
				"ORDER_ASSIGNEDTO", // 7
				"ORDER_ASSIGNEDBY", // 8
				"ORDER_PROBLEMRPT", // 9
				"ORDER_ACCESSORIES", // 10
				"ORDER_SOLUTION", // 11
				"ORDER_ETC", // 12
				"ORDER_DUEDATE", // 13
				"ORDER_AMTDUE", // 14
				"ORDER_PYMTRECD", // 15
				"ORDER_PYMTRECDON", // 16
				"ORDER_PYMTSTATUS", // 17
				"ORDER_DELCONFIRM", // 18
				"ORDER_DELCONFIRMDATE", // 19
				"ORDER_COMPLETEDACTUALDATE", // 20
				"ORDER_PRIORITY", // 21
				"ORDER_COMPLEXITY", // 22
				"ORDER_SPARESCOST"

		};

		// Total columns 23

		Cursor getallcolorders = newdb.query(DATABASE_TABLE2, allcolumns, null,
				null, null, null, null);

		SimpleDateFormat dateformatonreport = new SimpleDateFormat("dd/MM/yyyy");
		Calendar orderdatecal = Calendar.getInstance();
		Calendar orderduedate = Calendar.getInstance();
		Calendar orderpymtrecd = Calendar.getInstance();
		Calendar orderdelivered = Calendar.getInstance();
		Calendar ordercompleted = Calendar.getInstance();
		String OrderDateR = null;

		String orderduedateR = null;

		String orderpymtrecdR = null;

		String orderdeliveredR = null;

		String ordercompletedR = null;
		;

		for (getallcolorders.moveToFirst(); !getallcolorders.isAfterLast(); getallcolorders
				.moveToNext())

		{
			if (getallcolorders.getString(1) == null) {

			} else {

				orderdatecal.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(1)));
				OrderDateR = dateformatonreport.format(orderdatecal.getTime());

			}

			if (getallcolorders.getString(13) == null) {

			} else {

				orderduedate.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(13)));

				orderduedateR = dateformatonreport.format(orderduedate
						.getTime());
			}

			if (getallcolorders.getString(16) == null) {

			} else {
				orderpymtrecd.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(16)));
				orderpymtrecdR = dateformatonreport.format(orderpymtrecd
						.getTime());
			}

			if (getallcolorders.getString(19) == null) {

			} else {

				orderdelivered.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(19)));
				orderdeliveredR = dateformatonreport.format(orderdelivered
						.getTime());
			}
			if (getallcolorders.getString(20) == null) {

			} else {
				ordercompleted.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(20)));
				ordercompletedR = dateformatonreport.format(ordercompleted
						.getTime());
			}

			if (getallcolorders.getString(2).equalsIgnoreCase("Closed"))

			{

				continue;

			}

			allcolumnresult = allcolumnresult
					+ "\n-----------------------------------------------\n"
					+ "Job Card   # :-"	+ getallcolorders.getString(0)+ "\n"  
					+ "Job Date   	:-"	+ OrderDateR+ "\n" 
					+ "Job Status   :-"	+ getallcolorders.getString(2)+ "\n" 
					+ "--------------------------------------------------" + "\n" 
					+ "Customer Mobile		:" + getallcolorders.getString(3)  + "\n" 
					+ "ITEM_NAME		    :" + getallcolorders.getString(4)  + "\n" 
					+ "DESCRIPTION			:" + getallcolorders.getString(5)  + "\n"
					+ "ITEM TYPE			:" + getallcolorders.getString(6)  + "\n"
					+ "ASSIGNED TO			:" + getallcolorders.getString(7)  + "\n" 
					+ "ASSIGNED BY			:" + getallcolorders.getString(8)  + "\n"
					+ "PROBLEM REPORTED		:" + getallcolorders.getString(9) + "\n"
					+ "Additional Accesso 	:" + getallcolorders.getString(10)
					+ "\n"
					+ "Comments/Reason   	:"+ getallcolorders.getString(11) + "\n" 
					+ "ORDER_ETC			:"+ getallcolorders.getString(12) + "\n"
					+ "DUEDATE				:"+ orderduedateR + "\n" 
					+ "Estimated Amt		:"+ getallcolorders.getString(14) + "\n"
					+ "Spare(s) Cost		:" + getallcolorders.getString(23)+ "\n"    // new colum for spares
					+ "Amount Received		:" + getallcolorders.getString(15)+ "\n" 
					+ "Received on			:" + orderpymtrecdR + "\n"
					+ "Payment Status		:" + getallcolorders.getString(17)
					+ "\n" + "DELIVERED 			:" + getallcolorders.getString(18)
					+ "\n" + "DELIVERD DATE		:" + orderdeliveredR + "\n"
					+ "Completed Actual Date:" + ordercompletedR + "\n"
					+ "PRIORITY				:" + getallcolorders.getString(21) + "\n"
					+ "COMPLEXITY			:" + getallcolorders.getString(22);

		}

		return allcolumnresult;
	}

	// EMPLOYEE TABLE FUNCTIONS
	public String addemployee(ContentValues empev) {

		String empidreturn = "null ";
		Long rowidtoreturn = (long) 0;

		rowidtoreturn = newdb.insert(DATABASE_TABLE3, null, empev);

		empidreturn = String.valueOf(rowidtoreturn);

		return empidreturn;

	}

	public int updateemployee(String empidtoupdate, ContentValues cvtoupdate) {

		Long empid = Long.valueOf(empidtoupdate);

		int j = newdb.update(DATABASE_TABLE3, cvtoupdate, EMP_ID + "=" + empid,
				null);

		return j;

	}

	public ContentValues reademployee(String employeeid) {

		Long id = Long.valueOf(employeeid);
		String cols[] = { "EMP_ID", "EMP_FIRST_NAME", "EMP_LAST_NAME",
				"EMP_USERNAME", "EMP_PASSWORD", "EMP_MOBILENUMBER",
				"EMP_EMAILID", "EMP_ADDRESS", "EMP_ACCESS" };
		Cursor cursorforemp = newdb.query(DATABASE_TABLE3, cols,
				"EMP_ID=" + id, null, null, null, null);
		ContentValues cvempget = new ContentValues();
		for (cursorforemp.moveToFirst(); !cursorforemp.isAfterLast(); cursorforemp
				.moveToNext())

		{
			cvempget.put("empid", cursorforemp.getString(0));
			cvempget.put("empfirstname", cursorforemp.getString(1));
			cvempget.put("emplastname", cursorforemp.getString(2));
			cvempget.put("empusername", cursorforemp.getString(3));
			cvempget.put("emppassword", cursorforemp.getString(4));
			cvempget.put("empmobilenumber", cursorforemp.getString(5));
			cvempget.put("empemailid", cursorforemp.getString(6));
			cvempget.put("empaddress", cursorforemp.getString(7));
			cvempget.put("empaccess", cursorforemp.getString(8));

		}

		return cvempget;

	}

	public String checkread(String Username, String Password) {

		String getuserlevel = null;
		String checkfor[] = { Username, Password };

		try {

			Cursor checkcursor = newdb.rawQuery("SELECT " + EMP_USERNAME + ","
					+ EMP_PASSWORD + "," + EMP_ACCESS + " FROM "
					+ DATABASE_TABLE3 + " WHERE " + EMP_USERNAME + "=? AND "
					+ EMP_PASSWORD + "=?", checkfor);

			if ((checkcursor.getCount() == 1)) {

				checkcursor.moveToFirst();

				getuserlevel = checkcursor.getString(2);

			}
			else
			{
				getuserlevel = "none";
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return getuserlevel;

	}
	
	public Boolean checkusername(String getusername)
	{
		
		String coluser[] = {getusername};
		
	//	Boolean getresult=true;
				
				Cursor getusername1 = newdb.rawQuery("SELECT " + EMP_USERNAME +" FROM " + DATABASE_TABLE3 + 
						" WHERE " + EMP_USERNAME +"=?" , coluser);
		
		
		
		if(getusername1.getCount()==0)
		{
			
		
			return false;
		}
		
		else{
		
		
		return true;
		
		}
		
		
		
		
		
	}
	

	public int delemployee(String empid) {

		String emptodel[] = { empid };

		int rows = newdb.delete(DATABASE_TABLE3, EMP_ID + "=?", emptodel);

		return rows;
	}

	public List<String> getusernames() {
		List<String> userlist = new ArrayList<String>();

		String Colusername[] = { EMP_USERNAME };
		Cursor csr = newdb.query(DATABASE_TABLE3, Colusername, null, null,
				null, null, null);

		for (csr.moveToFirst(); !csr.isAfterLast(); csr.moveToNext()) {

			userlist.add(csr.getString(0));

		}

		return userlist;

	}

	public String generalreport(String getcols[]) {

		String allcolumnresult = "";
		String[] allcolumns = { "ORDER_ID", // 0
				"ORDER_DATE", // 1
				"ORDER_STATUS", // 2
				"ORDER_CUSTOMERMOBILE", // 3
				"ORDER_NAME", // 4
				"ORDER_DESCRIPTION", // 5
				"ORDER_TYPE", // 6
				"ORDER_ASSIGNEDTO", // 7
				"ORDER_ASSIGNEDBY", // 8
				"ORDER_PROBLEMRPT", // 9
				"ORDER_ACCESSORIES", // 10
				"ORDER_SOLUTION", // 11
				"ORDER_ETC", // 12
				"ORDER_DUEDATE", // 13
				"ORDER_AMTDUE", // 14
				"ORDER_PYMTRECD", // 15
				"ORDER_PYMTRECDON", // 16
				"ORDER_PYMTSTATUS", // 17
				"ORDER_DELCONFIRM", // 18
				"ORDER_DELCONFIRMDATE", // 19
				"ORDER_COMPLETEDACTUALDATE", // 20
				"ORDER_PRIORITY", // 21
				"ORDER_COMPLEXITY", // 22
				"ORDER_SPARESCOST" //23

		};

		// Total columns 23

		Cursor getallcolorders = newdb.query(DATABASE_TABLE2, allcolumns,
				ORDER_DATE + ">=? AND " + ORDER_DATE + "<=?" + " AND  "
						+ ORDER_ASSIGNEDTO + "=?", getcols, null, null,
				ORDER_ASSIGNEDTO);
		
		SimpleDateFormat dateformatonreport = new SimpleDateFormat("dd/MM/yyyy");
		Calendar orderdatecal = Calendar.getInstance();
		Calendar orderduedate = Calendar.getInstance();
		Calendar orderpymtrecd = Calendar.getInstance();
		Calendar orderdelivered = Calendar.getInstance();
		Calendar ordercompleted = Calendar.getInstance();
		String OrderDateR = null;

		String orderduedateR = null;

		String orderpymtrecdR = null;

		String orderdeliveredR = null;

		String ordercompletedR = null;
		;
		for (getallcolorders.moveToFirst(); !getallcolorders.isAfterLast(); getallcolorders
				.moveToNext())

		{
			if (getallcolorders.getString(1) != null) {

				orderdatecal.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(1)));
				OrderDateR = dateformatonreport.format(orderdatecal.getTime());

			}

			if (getallcolorders.getString(13) == null) {

			} else {

				orderduedate.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(13)));
				orderduedateR = dateformatonreport.format(orderduedate
						.getTime());
			}
			if (getallcolorders.getString(16) == null) {

			} else {
				orderpymtrecd.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(16)));
				orderpymtrecdR = dateformatonreport.format(orderpymtrecd
						.getTime());
			}

			if (getallcolorders.getString(19) == null) {

			} else {

				orderdelivered.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(19)));
				orderdeliveredR = dateformatonreport.format(orderdelivered
						.getTime());
			}
			if (getallcolorders.getString(20) == null) {

			} else {
				ordercompleted.setTimeInMillis(Long.valueOf(getallcolorders
						.getString(20)));
				ordercompletedR = dateformatonreport.format(ordercompleted
						.getTime());
			}

			allcolumnresult = allcolumnresult
					+ "-----------------------------------------\n"
					+ "Order Id         :"	+ getallcolorders.getString(0)	+ "\n"
					+ "Assigned To      :"	+ getallcolorders.getString(7)	+ "\n"
					+ "-----------------------------------------\n"
					+ "Order Date       :" + OrderDateR + "\n"
					+ "Order Status     :" + getallcolorders.getString(2)+ "\n"
					+ "Customer Mobile  :" + getallcolorders.getString(3) + "\n"
					+ "Order Details    :" + "\n"
					+ "Item Name        :" + getallcolorders.getString(4) + "\n"
					+ "Description      :" + getallcolorders.getString(5)
					+ "\n" + "Type             :"
					+ getallcolorders.getString(6) + "\n"
					+ "Assigned TO      :" + getallcolorders.getString(7)+ "\n"
					+ "Assigned By      :"
					+ getallcolorders.getString(8) + "\n"
					+ "Problem Reported :" + getallcolorders.getString(9)
					+ "\n" + "Additional       :" + "\n"
					+ "Accessories      :"
					+ getallcolorders.getString(10) + "\n"
					+ "Comments         :" + getallcolorders.getString(11)+ "\n"
					+ "Estimated Time   :" 	+ getallcolorders.getString(12) + "\n"
					+ "DueDate          :" + orderduedateR + "\n"
					+ "Estimated Amt    :" + getallcolorders.getString(14)+ "\n" 
					+ "Spares Cost      :" + getallcolorders.getString(23) + "\n"
					+ "Amount Received  :" + getallcolorders.getString(15) + "\n"
					+ "Received On      :" + orderpymtrecdR + "\n"
					+ "Payment Status   :" + getallcolorders.getString(17)
					+ "\n" + "DELIVERED(Y/N)   :"
					+ getallcolorders.getString(18) + "\n"
					+ "DELIVERD DATE    :" + orderdeliveredR + "\n"
					+ "Completed Date   :" + ordercompletedR + "\n"
					+ "PRIORITY         :" + getallcolorders.getString(21)
					+ "\n" + "COMPLEXITY       :"
					+ getallcolorders.getString(22) + "\n";

		}

//	String getdatesonly[] = {getcols[0],getcols[1]};
		
		String sqlconsolidated = "SELECT "+ORDER_ASSIGNEDTO 
				+  ",COUNT(" + ORDER_ID +")"
				+ ",SUM(" + ORDER_PYMTRECD +")"
				+ ",SUM(" + ORDER_AMTDUE +")"
				+ ",SUM(" + ORDER_SPARESCOST +")"
				+ ", SUM(" + ORDER_PYMTRECD +")-SUM(" + ORDER_SPARESCOST +")" + " AS NETAMT"
				+ " FROM " + DATABASE_TABLE2 
				+" WHERE " + ORDER_DATE +">=?"
				+ " AND " + ORDER_DATE +"<=?" 
				+ " AND " + ORDER_ASSIGNEDTO +"=?"
				+ " GROUP BY "+ORDER_ASSIGNEDTO;
		  
		
	
	Cursor getconsolreport = 	newdb.rawQuery(sqlconsolidated, getcols);
	String	consolreport_s = "";
	
	for(getconsolreport.moveToFirst();!getconsolreport.isAfterLast();getconsolreport.moveToNext())
	{
		
			consolreport_s = consolreport_s 
				+"-----------------------------------------" +"\n" 
				+ "Consolidated Report by Employee" +"\n" 
				+"-----------------------------------------" +"\n" 
				+"Employee Name    : " + getconsolreport.getString(0) +"\n" 
				+"Total Orders     : " + getconsolreport.getString(1) +"\n" 
				+"Total Payment Recevied : " + getconsolreport.getString(2) +"\n" 
				+"Estimated Amount    : " + getconsolreport.getString(3) +"\n"
				+"Total Spare(s) cost : " + getconsolreport.getString(4) +"\n"
				+"------------------------------------------" +"\n" 
				+"Net Amount          : " + getconsolreport.getString(5) +"\n"
				+"------------------------------------------" +"\n" ;
	}
		String finalreport = null;
		finalreport = consolreport_s + allcolumnresult;
		return finalreport;

	}
	

public String consolidatedreport(String getdates[]){
	
	// Setting up of from date and to date 
	Calendar convertdate1 = Calendar.getInstance();
	convertdate1.setTimeInMillis(Long.valueOf(getdates[0]));

	SimpleDateFormat indianformat1 = new SimpleDateFormat("dd/MM/yyyy");



	Calendar convertdate2 = Calendar.getInstance();
	convertdate2.setTimeInMillis(Long.valueOf(getdates[1]));
	
	// Overall status for entire shop
	String sqlconsolidatedall = "SELECT " 
			+  "COUNT(" + ORDER_ID +")"
			+ ",SUM(" + ORDER_PYMTRECD +")"
			+ ",SUM(" + ORDER_AMTDUE +")"
			+ ",SUM(" + ORDER_SPARESCOST +")"
			+ ", SUM(" + ORDER_PYMTRECD +")-SUM(" + ORDER_SPARESCOST +")" + " AS NETAMT"
			+ " FROM " + DATABASE_TABLE2 
			+" WHERE " + ORDER_DATE +">=?"
			+ " AND " + ORDER_DATE +"<=?" ;
					
	  

Cursor getconsolreportall = 	newdb.rawQuery(sqlconsolidatedall, getdates);


String	consolreport_sall = "";

for(getconsolreportall.moveToFirst();!getconsolreportall.isAfterLast();getconsolreportall.moveToNext())
{
	
	consolreport_sall = consolreport_sall 
			+"***************************************" +"\n" 
			+ "Consolidated Report" +"\n" 
			+ "From :" + indianformat1.format(convertdate1.getTime())  +"\n"
			+ "To   :" +    indianformat1.format(convertdate2.getTime())+"\n"
			+"***************************************" +"\n" 
			+"Total Orders  : " + getconsolreportall.getString(0) +"\n" 
			+"Total Payment Recevied : " + getconsolreportall.getString(1) +"\n" 
			+"Estimated Amount    : " + getconsolreportall.getString(2) +"\n"
			+"Total Spare(s) cost : " + getconsolreportall.getString(3) +"\n"
			+"***************************************" +"\n" 
			+"Net Amt             : " + getconsolreportall.getString(4) +"\n"
			+"***************************************" +"\n" ;
}
	
	// Code for generating only the status counts for the given period 


String onlystatuscount = "SELECT " 
+ORDER_STATUS
+  ",COUNT(" + ORDER_ID +")"
+ " FROM " + DATABASE_TABLE2 
+" WHERE " + ORDER_DATE +">=?"
+ " AND " + ORDER_DATE +"<=?" 
+ " GROUP BY "
+ ORDER_STATUS;




//SimpleDateFormat indianformat2 = new SimpleDateFormat("dd/MM/yyyy");



Cursor getstatuscount = newdb.rawQuery(onlystatuscount, getdates);
String jobstatuscount="";
String titleofreport="";
String reporttitlestatuscount =
			"***************************************" +"\n" 
			+"Consolidated Job status report \n from:" + indianformat1.format(convertdate1.getTime())  
			+ "  to:" +    indianformat1.format(convertdate2.getTime())+"\n"
			+"***************************************"+"\n"  ;
jobstatuscount = reporttitlestatuscount;

for(getstatuscount.moveToFirst();!getstatuscount.isAfterLast();getstatuscount.moveToNext())

{


	jobstatuscount = jobstatuscount 
	+"***************************************" +"\n"
	+"Order/Job Status : " + getstatuscount.getString(0) +"\n" 
	+"Order/Job Count : " + getstatuscount.getString(1) +"\n" 
      +"***************************************" +"\n" ;


}

// Status count code end ----

// Code for generating only the Payment counts for the given period 


String sqlconsolidatedallp = "SELECT " 
	
		+ "SUM(" + ORDER_PYMTRECD +")"
		+ ",SUM(" + ORDER_AMTDUE +")"
		+ ",SUM(" + ORDER_SPARESCOST +")"
		+ ", SUM(" + ORDER_PYMTRECD +")-SUM(" + ORDER_SPARESCOST +")" + " AS NETAMT"
		+ " FROM " + DATABASE_TABLE2 
		+" WHERE " + ORDER_PYMTRECDON +">=?"
		+ " AND " + ORDER_PYMTRECDON +"<=?" ;
				
  

Cursor getconsolreportallp = 	newdb.rawQuery(sqlconsolidatedallp, getdates);


String	consolreport_sallp = "";

for(getconsolreportallp.moveToFirst();!getconsolreportallp.isAfterLast();getconsolreportallp.moveToNext())
{

consolreport_sallp = consolreport_sallp
		+"***************************************" +"\n" 
		+ "Consolidated Payment Report - Period" +"\n" 
		+ "From :" + indianformat1.format(convertdate1.getTime())  +"\n"
		+ "To   :" +    indianformat1.format(convertdate2.getTime())+"\n"
		+"***************************************" +"\n" 
		+"Total Payment Recevied : " + getconsolreportallp.getString(0) +"\n" 
		+"Estimated Amount    : " + getconsolreportallp.getString(1) +"\n"
		+"Total Spare(s) cost : " + getconsolreportallp.getString(2) +"\n"
		+"***************************************" +"\n" 
		+"Net Amt             : " + getconsolreportallp.getString(3) +"\n"
		+"***************************************" +"\n" ;
}



//Payment count code end ----

	
	// Overall Status by the employees 
	
	String sqlconsolidated = "SELECT "+ORDER_ASSIGNEDTO 
	       	+  ",COUNT(" + ORDER_ID +")"
			+ ",SUM(" + ORDER_PYMTRECD +")"
			+ ",SUM(" + ORDER_AMTDUE +")"
				+ ",SUM(" + ORDER_SPARESCOST +")"
					+ ", SUM(" + ORDER_PYMTRECD +")-SUM(" + ORDER_SPARESCOST +")" + " AS NETAMT"
			+ " FROM " + DATABASE_TABLE2 
			+" WHERE " + ORDER_DATE +">=?"
			+ " AND " + ORDER_DATE +"<=?" 
			+ " GROUP BY "+ORDER_ASSIGNEDTO
			+"," + ORDER_STATUS;


Cursor getconsolreport = 	newdb.rawQuery(sqlconsolidated, getdates);


String	consolreport_s = "";

for(getconsolreport.moveToFirst();!getconsolreport.isAfterLast();getconsolreport.moveToNext())
{
	
		consolreport_s = consolreport_s 
			+"***************************************" +"\n" 
			+ "Consolidated Report by Employees" +"\n" 
			+"***************************************" +"\n" 
			+"Employee Name : " + getconsolreport.getString(0) +"\n" 
			+"Total Orders  : " + getconsolreport.getString(1) +"\n" 
			+"Total Payment Recevied : " + getconsolreport.getString(2) +"\n" 
			+"Estimated Amount    : " + getconsolreport.getString(3) +"\n"
			+"Total Spare(s) cost : " + getconsolreport.getString(4) +"\n"
			+"***************************************" +"\n" 
			+"Net Amt             : " + getconsolreport.getString(5) +"\n"
			+"***************************************" +"\n" ;
}
	
// Code for generating Consolidated Report for each employee by status

String sqlconsolidatedbyjobstatus = "SELECT "+ORDER_ASSIGNEDTO 
        +  ","+ORDER_STATUS
		+  ",COUNT(" + ORDER_ID +")"
		+ ",SUM(" + ORDER_PYMTRECD +")"
		+ ",SUM(" + ORDER_AMTDUE +")"
			+ ",SUM(" + ORDER_SPARESCOST +")"
				+ ", SUM(" + ORDER_PYMTRECD +")-SUM(" + ORDER_SPARESCOST +")" + " AS NETAMT"
		+ " FROM " + DATABASE_TABLE2 
		+" WHERE " + ORDER_DATE +">=?"
		+ " AND " + ORDER_DATE +"<=?" 
		+ " GROUP BY "+ORDER_ASSIGNEDTO
		+"," + ORDER_STATUS;




//SimpleDateFormat indianformat2 = new SimpleDateFormat("dd/MM/yyyy");



Cursor getconsolreportbystatus = newdb.rawQuery(sqlconsolidatedbyjobstatus, getdates);
String reportaddbystatus="";
String reporttitle = "***************************************" +"\n" 
					+"Consolidated Report for Employees \nby Job status from:" + indianformat1.format(convertdate1.getTime())  
					+ "to:" +    indianformat1.format(convertdate2.getTime())+"\n" 
					+"***************************************"+"\n"  ;
reportaddbystatus = reporttitle;

for(getconsolreportbystatus.moveToFirst();!getconsolreportbystatus.isAfterLast();getconsolreportbystatus.moveToNext())

{
	
	
	reportaddbystatus = reportaddbystatus 
			+"***************************************" +"\n"
			+"Employee Name    : " + getconsolreportbystatus.getString(0) +"\n"
			+"Order/Job Status : " + getconsolreportbystatus.getString(1) +"\n" 
			+"Order/Job Count : " + getconsolreportbystatus.getString(2) +"\n" 
		    +"Total Payment Recevied : " + getconsolreportbystatus.getString(3) +"\n" 
			+"Total Estimated Amount : " + getconsolreportbystatus.getString(4) +"\n"
			+"Total Spare(s) cost    : " + getconsolreportbystatus.getString(5) +"\n"
			+"Net Amount             : "  + getconsolreportbystatus.getString(6) +"\n"
		    +"***************************************" +"\n" ;
	
	
}

String finalreportconsolidate = consolreport_sall+jobstatuscount+ consolreport_s+reportaddbystatus +consolreport_sallp;

	return finalreportconsolidate;

			
	
}

public String dataconversion() {

	String allcolumnresult = "";
	String[] allcolumns = { "ORDER_ID", // 0
			"ORDER_DATE", // 1
			"ORDER_STATUS", // 2
			"ORDER_CUSTOMERMOBILE", // 3
			"ORDER_NAME", // 4
			"ORDER_DESCRIPTION", // 5
			"ORDER_TYPE", // 6
			"ORDER_ASSIGNEDTO", // 7
			"ORDER_ASSIGNEDBY", // 8
			"ORDER_PROBLEMRPT", // 9
			"ORDER_ACCESSORIES", // 10
			"ORDER_SOLUTION", // 11
			"ORDER_ETC", // 12
			"ORDER_DUEDATE", // 13
			"ORDER_AMTDUE", // 14
			"ORDER_PYMTRECD", // 15
			"ORDER_PYMTRECDON", // 16
			"ORDER_PYMTSTATUS", // 17
			"ORDER_DELCONFIRM", // 18
			"ORDER_DELCONFIRMDATE", // 19
			"ORDER_COMPLETEDACTUALDATE", // 20
			"ORDER_PRIORITY", // 21
			"ORDER_COMPLEXITY",// 22
			"ORDER_SPARESCOST"

	};

	// Total columns 23

	Cursor getallcolorders = newdb.query(DATABASE_TABLE2, allcolumns, null,
			null, null, null, null);

	SimpleDateFormat dateformatonreport = new SimpleDateFormat("dd/MM/yyyy");
	Calendar orderdatecal = Calendar.getInstance();
	Calendar orderduedate = Calendar.getInstance();
	Calendar orderpymtrecd = Calendar.getInstance();
	Calendar orderdelivered = Calendar.getInstance();
	Calendar ordercompleted = Calendar.getInstance();
	String OrderDateR = null;

	String orderduedateR = null;

	String orderpymtrecdR = null;

	String orderdeliveredR = null;

	String ordercompletedR = null;
	;
	for (getallcolorders.moveToFirst(); !getallcolorders.isAfterLast(); getallcolorders
			.moveToNext())

	{
		if (getallcolorders.getString(1) == null) {

		} else {

			orderdatecal.setTimeInMillis(Long.valueOf(getallcolorders
					.getString(1)));
			OrderDateR = dateformatonreport.format(orderdatecal.getTime());

		}

		if (getallcolorders.getString(13) == null) {

		} else {

			orderduedate.setTimeInMillis(Long.valueOf(getallcolorders
					.getString(13)));
			orderduedateR = dateformatonreport.format(orderduedate
					.getTime());
		}
		if (getallcolorders.getString(16) == null) {

		} else {
			orderpymtrecd.setTimeInMillis(Long.valueOf(getallcolorders
					.getString(16)));
			orderpymtrecdR = dateformatonreport.format(orderpymtrecd
					.getTime());
		}

		if (getallcolorders.getString(19) == null) {

		} else {

			orderdelivered.setTimeInMillis(Long.valueOf(getallcolorders
					.getString(19)));
			orderdeliveredR = dateformatonreport.format(orderdelivered
					.getTime());
		}
		if (getallcolorders.getString(20) == null) {

		} else {
			ordercompleted.setTimeInMillis(Long.valueOf(getallcolorders
					.getString(20)));
			ordercompletedR = dateformatonreport.format(ordercompleted
					.getTime());
		}

		allcolumnresult = allcolumnresult
				+ getallcolorders.getString(0)+ ","
				+ OrderDateR + ","
				+ getallcolorders.getString(2) + ","
				+ getallcolorders.getString(3) + ","
				+ getallcolorders.getString(4) + ","   // item name
				+ "\""+ getallcolorders.getString(5)+ "\","
				+ getallcolorders.getString(6) + ","
				+ getallcolorders.getString(7) + ","
				+"\""+ getallcolorders.getString(8) + "\","
				+"\""+ getallcolorders.getString(9) + "\","
				+"\""+ getallcolorders.getString(10)+ "\","
				+"\""+ getallcolorders.getString(11)+ "\","
				+ getallcolorders.getString(12) + "," 
				+ orderduedateR +  "," 
				 + getallcolorders.getString(14) + "," 
				 + getallcolorders.getString(15) + ","  
				+ getallcolorders.getString(23)+ "," 
				+ orderpymtrecdR + "," 
				 + getallcolorders.getString(17) + "," 
				 + getallcolorders.getString(18) + "," 
				 + orderdeliveredR + "," 
				+ ordercompletedR + "," 
				 + getallcolorders.getString(21)+ "," 
				+ getallcolorders.getString(22)+ "\n" ;
				

	}

	return allcolumnresult;

}



}
