package cit.ordermanagement.com;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument.PageInfo;
import android.print.pdf.PrintedPdfDocument;
import android.graphics.pdf.PdfDocument;

public class PrintView extends Activity implements OnClickListener {

	SharedPreferences spforprint;
	String filename = "userprofile";
	ContentValues cvorderncustomer = null;
	Button printjobcard;
	String getorderids = null;
	Long getorderl = (long) 0;
	String reportview = null;
	TextView reporttextview;

	String problem_reported = "";
	String accessories = "";
	String comments = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_print_view);

		Intent getorderintent = getIntent();
		getorderids = getorderintent.getStringExtra("orderid");
		getorderl = Long.valueOf(getorderids);
/*
		printjobcard = (Button) findViewById(R.id.btprintjobcard);
		reporttextview = (TextView) findViewById(R.id.reporttextview);
		printjobcard.setOnClickListener(this);
*/
		spforprint = getSharedPreferences(filename, 0);
		
		printoncanvas();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub


	}

	public void printoncanvas()
	{

		Customerdbwork otp = new Customerdbwork(this);

		try {
			otp.open();

			reportview = otp.printorder(getorderl);

			cvorderncustomer = otp.printorderdirect(getorderl);

			// reporttextview.setText(reportview);

			otp.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintManager printManager = (PrintManager) this
				.getSystemService(Context.PRINT_SERVICE);

		String jobName = this.getString(R.string.app_name) + " Document";

		printManager.print(jobName, new pdfadapter(this), null);
		
	}
	
	class pdfadapter extends PrintDocumentAdapter {
		Context cdapt;
		private int pageheight;
		private int pagewidth;

		public PdfDocument mypdfdocument;
		public int totalpages = 1;

		public pdfadapter(Context cont) {
			cdapt = cont;

		}

		@Override
		public void onLayout(PrintAttributes oldAttributes,
				PrintAttributes newAttributes,
				CancellationSignal cancellationSignal,
				LayoutResultCallback callback, Bundle extras) {

			mypdfdocument = new PrintedPdfDocument(cdapt, newAttributes);

			pageheight = newAttributes.getMediaSize().getHeightMils() / 1000 * 72;
			pagewidth = newAttributes.getMediaSize().getWidthMils() / 1000 * 72;

			if (cancellationSignal.isCanceled()) {
				callback.onLayoutCancelled();
				return;
			}

			if (totalpages > 0) {

				PrintDocumentInfo.Builder builder = new PrintDocumentInfo.Builder(
						"Job_Card").setPageCount(totalpages).setContentType(
						PrintDocumentInfo.CONTENT_TYPE_DOCUMENT);

				PrintDocumentInfo information = builder.build();

				callback.onLayoutFinished(information, true);

			} else {
				callback.onLayoutFailed("Page Count is zero");

			}
			// TODO Auto-generated method stub

		}

		@Override
		public void onWrite(PageRange[] pages,
				ParcelFileDescriptor destination,
				CancellationSignal cancellationSignal,
				WriteResultCallback callback) {
			// TODO Auto-generated method stub

			for (int i = 0; i < totalpages; i++) {

				if (pageInRange(pages, i)) {

					PageInfo newPage = new PageInfo.Builder(pagewidth,
							pageheight, i).create();
					PdfDocument.Page page = mypdfdocument.startPage(newPage);

					if (cancellationSignal.isCanceled()) {
						callback.onWriteCancelled();
						mypdfdocument.close();
						mypdfdocument = null;
						return;
					}

					drawPage(page, i);

					mypdfdocument.finishPage(page);

				}

			}
			try {

				mypdfdocument.writeTo(new FileOutputStream(destination
						.getFileDescriptor()));
			} catch (IOException ie) {
				callback.onWriteFailed(ie.toString());
			} finally {

				mypdfdocument.close();
				mypdfdocument = null;
			}

			callback.onWriteFinished(pages);

		}

		private boolean pageInRange(PageRange[] pageRanges, int page) {
			for (int i = 0; i < pageRanges.length; i++) {
				if ((page >= pageRanges[i].getStart())
						&& (page <= pageRanges[i].getEnd()))
					return true;
			}
			return false;
		}

		public void drawPage(PdfDocument.Page page, int pagenumber) {
			Canvas canvas = page.getCanvas();

			pagenumber++; // Make sure page numbers start at 1

			int titleBaseLine = 72;
			int leftMargin = 54;

			// 1 point is equal to 1/72 of an inch
			// page size is 8.5 inch * 11 inch - standard letter format in
			// USA-216 x 279 mm
			// page size is 210 x 297 mm -8.3 x 11.7 in
			// 597.6 * 842.4
			// A4 measures 210 × 297 millimeters or 8.27 × 11.69 inches.
			// In PostScript, its dimensions are rounded off to 595 × 842
			// points.
			// Folded twice, an A4 sheet fits in a C6 size envelope (114 × 162
			// mm). Part of the ISO 216 standard.

			// Actuals from the system - 792 & 576 - confirmed value - 8 inch

			// Settings for the shape
			Paint paintshape = new Paint();
			paintshape.setColor(Color.BLACK);
			Rect r = new Rect();
			paintshape.setStyle(Style.STROKE);
			paintshape.setStrokeWidth(2);
			r.top = 50;
			r.right = 540;
			r.left = 50;
			r.bottom = 792;
			canvas.drawRect(r, paintshape);

			// settings for lines drawn

			Paint paintline = new Paint();
			paintline.setStrokeWidth(1);

			// Settings for words
			Paint paint = new Paint();
			paint.setColor(Color.BLACK);
			paint.setTextSize(10);
			paint.setTypeface(Typeface.MONOSPACE);
			// paint.setStyle(Style.FILL);

			// settings for headings
			Paint painth = new Paint();
			painth.setColor(Color.BLACK);
			painth.setTextSize(10);
			painth.setTypeface(Typeface.MONOSPACE);
			painth.setStyle(Style.FILL);

			// Settings for terms and conditions
			Paint painttandc = new Paint();
			painttandc.setColor(Color.BLACK);
			painttandc.setTextSize(7);
			painttandc.setTypeface(Typeface.MONOSPACE);
			painttandc.setStyle(Style.FILL);

			// Collecting information from db-contentvalues and mapping it to
			// local variables
			problem_reported = cvorderncustomer.getAsString("Problem");
			accessories = cvorderncustomer
					.getAsString("Additional Accessories");
			comments = cvorderncustomer.getAsString("Comments");

			// int j = problem_reported.

			// Drawing on the canvase **********************
			canvas.drawText(spforprint.getString("keyspref01", "Enter "
					+ "a value"), leftMargin, titleBaseLine, painth);
			canvas.drawText(
					spforprint.getString("keyspref02", "Enter a value")
							+ ","
							+ spforprint.getString("keyspref03",
									"Enter a value"), leftMargin,
					titleBaseLine + 20, paint);
			canvas.drawText(
					spforprint.getString("keyspref04", "Enter a value"),
					leftMargin, titleBaseLine + 35, paint);
			canvas.drawText(
					spforprint.getString("keyspref05", "Enter a value")
							+ "- "
							+ spforprint.getString("keyspref06",
									"Enter a value"), leftMargin,
					titleBaseLine + 50, paint);

			canvas.drawText(
					"Lan:"
							+ spforprint.getString("keyspref07",
									"Enter a value")
							+ ",Mobile: "
							+ spforprint.getString("keyspref08",
									"Enter a value"), leftMargin,
					titleBaseLine + 65, paint);
			canvas.drawText(
					"Email:"
							+ spforprint.getString("keyspref09",
									"Enter a value"), leftMargin,
					titleBaseLine + 80, paint);
			canvas.drawLine(50, titleBaseLine + 90, 540, titleBaseLine + 90,
					paintshape);

			canvas.drawText(
					"Job Card#" + cvorderncustomer.getAsString("JOB ID"), 396,
					titleBaseLine, paint);
			canvas.drawText("Date:" + cvorderncustomer.getAsString("Date"),
					396, titleBaseLine + 25, paint);
			canvas.drawLine(390, 50, 390, titleBaseLine + 35, paintshape); // verticl
																			// line
			canvas.drawLine(390, titleBaseLine + 10, 540, titleBaseLine + 10,
					paintshape); // small horizontalline

			canvas.drawLine(390, titleBaseLine + 35, 540, titleBaseLine + 35,
					paintshape); // small horizontalline

			canvas.drawText(
					"Customer Name:  "
							+ cvorderncustomer.getAsString("CUSTOMER_NAME"),
					leftMargin, titleBaseLine + 110, paint);

			canvas.drawText(
					"Mobile:  "
							+ cvorderncustomer.getAsString("CUSTOMER_MOBILENO"),
					leftMargin, titleBaseLine + 125, paint);
			canvas.drawText(
					"Email:  "
							+ cvorderncustomer.getAsString("CUSTOMER_EMAILID"),
					leftMargin, titleBaseLine + 140, paint);
			canvas.drawLine(50, titleBaseLine + 160, 540, titleBaseLine + 160,
					paintline);

			canvas.drawText(
					"Product Name:  "
							+ cvorderncustomer.getAsString("Item Name"),
					leftMargin, titleBaseLine + 180, paint);

			canvas.drawText(
					"Serial Number:  "
							+ cvorderncustomer.getAsString("Description"),
					leftMargin, titleBaseLine + 195, paint);

			if (problem_reported.length() > 70) {
				int x = problem_reported.lastIndexOf(" ", 70);

				if (x == -1) {
					x = 70;
				}

				canvas.drawText("Problem Reported:  ", leftMargin,
						titleBaseLine + 210, paint);
				canvas.drawText(problem_reported, 0, x, leftMargin,
						titleBaseLine + 225, paint); // line one

				if (problem_reported.length() > 140) {
					int y = problem_reported.lastIndexOf(" ", 140);

					if (y == -1) {
						y = 140;
					}
					canvas.drawText(problem_reported, x + 1, y, leftMargin,
							titleBaseLine + 240, paint);
					canvas.drawText(problem_reported, y + 1,
							problem_reported.length(), leftMargin,
							titleBaseLine + 255, paint);
				} else {
					canvas.drawText(problem_reported, x + 1,
							problem_reported.length(), leftMargin,
							titleBaseLine + 240, paint);
				}

			} else {

				canvas.drawText("Problem Reported:  ", leftMargin,
						titleBaseLine + 210, paint);
				canvas.drawText(problem_reported, leftMargin,
						titleBaseLine + 225, paint);

			}

			if (accessories.length() > 70) {
				int x = accessories.lastIndexOf(" ", 70);

				if (x == -1) {
					x = 70;
				}

				canvas.drawText("Accessories:  ", leftMargin,
						titleBaseLine + 270, paint);
				canvas.drawText(accessories, 0, x, leftMargin,
						titleBaseLine + 285, paint);

				if (accessories.length() > 140) {
					int y = accessories.lastIndexOf(" ", 140);
					if (y == -1) {
						y = 140;
					}

					canvas.drawText(accessories, x + 1, y, leftMargin,
							titleBaseLine + 300, paint);
					canvas.drawText(accessories, y + 1, accessories.length(),
							leftMargin, titleBaseLine + 315, paint);
				} else {
					canvas.drawText(accessories, x + 1, accessories.length(),
							leftMargin, titleBaseLine + 300, paint);
				}
			} else {
				canvas.drawText("Accessories:  ", leftMargin,
						titleBaseLine + 270, paint);
				canvas.drawText(accessories, leftMargin, titleBaseLine + 285,
						paint);
			}

			if (comments.length() > 70) {
				int x = comments.lastIndexOf(" ", 70);

				if (x == -1) {
					x = 70;
				}

				canvas.drawText("Comments:-", leftMargin, titleBaseLine + 330,
						paint);
				canvas.drawText(comments, 0, x, leftMargin,
						titleBaseLine + 345, paint);

				if (comments.length() > 140) {
					int y = comments.lastIndexOf(" ", 140);
					if (y == -1) {
						y = 140;
					}

					canvas.drawText(comments, x + 1, y, leftMargin,
							titleBaseLine + 360, paint); // 2nd linebreak
					canvas.drawText(comments, y + 1, comments.length(),
							leftMargin, titleBaseLine + 375, paint);
				} else {
					canvas.drawText(comments, x + 1, comments.length(),
							leftMargin, titleBaseLine + 360, paint);
				}
			} else {
				canvas.drawText("Comments:-", leftMargin, titleBaseLine + 330,
						paint);
				canvas.drawText(comments, leftMargin, titleBaseLine + 345,
						paint);
			}

			canvas.drawLine(50, titleBaseLine + 400, 540, titleBaseLine + 400,
					paintline);
			canvas.drawText("Terms and Conditions:-", leftMargin,
					titleBaseLine + 415, paint);

			canvas.drawText(spforprint.getString("keyspref13", ""), leftMargin,
					titleBaseLine + 425, painttandc);
			canvas.drawText(spforprint.getString("keyspref14", ""), leftMargin,
					titleBaseLine + 435, painttandc);
			canvas.drawText(spforprint.getString("keyspref15", ""), leftMargin,
					titleBaseLine + 445, painttandc);
			canvas.drawText(spforprint.getString("keyspref16", ""), leftMargin,
					titleBaseLine + 455, painttandc);
			canvas.drawText(spforprint.getString("keyspref17", ""), leftMargin,
					titleBaseLine + 465, painttandc);
			canvas.drawText(spforprint.getString("keyspref18", ""), leftMargin,
					titleBaseLine + 475, painttandc);
			canvas.drawText(spforprint.getString("keyspref19", ""), leftMargin,
					titleBaseLine + 485, painttandc);
			canvas.drawText(spforprint.getString("keyspref20", ""), leftMargin,
					titleBaseLine + 495, painttandc);

			canvas.drawLine(50, titleBaseLine + 530, 540, titleBaseLine + 530,
					paintline);
			canvas.drawText(
					"Job Estimate:- Rs "
							+ cvorderncustomer.getAsString("Service Charges")
							+ "/-", leftMargin, titleBaseLine + 545, paint);
			canvas.drawLine(50, titleBaseLine + 560, 540, titleBaseLine + 560,
					paintline);

			canvas.drawText("Received the Goods in Good Condition", 300,
					pageheight - 60, paint);

			canvas.drawText(
					"For "
							+ spforprint.getString("keyspref01",
									"Enter a value"), leftMargin,
					pageheight - 80, paint);

			canvas.drawText("Customer Signature", pagewidth - 200,
					pageheight - 20, paint);
			canvas.drawText("Authorized Signature", leftMargin,
					pageheight - 20, paint);

		}

	}

}
