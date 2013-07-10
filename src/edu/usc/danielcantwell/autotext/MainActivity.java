/**
 * MainActivity Class
 * extends Activity
 * Called when app launches
 */
package edu.usc.danielcantwell.autotext;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Daniel 27 June 2013
 */

public class MainActivity extends Activity {

	Button btnSendSMS;
	Button btnSetTime;
	Button btnSetDate;
	Button btnViewPending;
	Button btnExitAndCancel;

	EditText txtPhoneNo;
	EditText txtMessage;

	TextView timeAndDate;

	String sDate;

	String phoneNo;
	String message;

	int serviceCount = 0;

	public Calendar time = Calendar.getInstance();
	
	public static MainActivity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		activity = this;

		btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
		btnSetTime = (Button) findViewById(R.id.btnSetTime);
		btnSetDate = (Button) findViewById(R.id.btnSetDate);
		btnViewPending = (Button) findViewById(R.id.btnViewPending);
		btnExitAndCancel = (Button) findViewById(R.id.exitButton);
		
		timeAndDate = (TextView) findViewById(R.id.viewTextAndDate);

		// Send SMS Button Click
		btnSendSMS.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Setting the Phone Number and Message
				txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
				txtMessage = (EditText) findViewById(R.id.txtMessage);

				phoneNo = txtPhoneNo.getText().toString();
				message = txtMessage.getText().toString();

				// The Phone Number and Message must have content
				if (phoneNo.length() > 0 && message.length() > 0) {

					// Creating the Pending Intent
					Intent myIntent = new Intent(MainActivity.this,
							SendMessage.class);
					myIntent.putExtra("phoneNumber", phoneNo);
					myIntent.putExtra("textMessage", message);
					myIntent.putExtra("serviceNumber", serviceCount);
					PendingIntent pi = PendingIntent.getService(
							MainActivity.this, serviceCount, myIntent, 0);

					// Creating the AlarmManager
					AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
					alarmManager.set(AlarmManager.RTC_WAKEUP,
							time.getTimeInMillis(), pi);

					serviceCount++;

					Toast.makeText(MainActivity.this, "Alarm Set to: " + sDate,
							Toast.LENGTH_LONG).show();

				} else {
					Toast.makeText(getBaseContext(),
							"Please enter both phone number and message",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		/*
		 * Set Time OnClickListener
		 */
		btnSetTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DialogFragment newFragment = new TimePickerFragment();
				newFragment.show(getFragmentManager(), "timePicker");
			}
		});

		/*
		 * Set Date OnClickListener
		 */
		btnSetDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DialogFragment newFragment = new DatePickerFragment();
				newFragment.show(getFragmentManager(), "datePicker");
			}
		});

		/*
		 * Starts the PendingMessages Activity
		 */
		btnViewPending.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						PendingMessages.class);
				startActivity(intent);
			}
		});

		/*
		 * Exits the App and Cancels Services
		 */
		btnExitAndCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	@Override
	// When the back key is pressed, close the app but don't cancel the pending
	// intent
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);

			startActivity(intent);
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void updateTime(int hour, int minute) {
		time.set(Calendar.HOUR_OF_DAY, hour);
		time.set(Calendar.MINUTE, minute);
		time.set(Calendar.SECOND, 0);
		Log.d("Daniel Cantwell", "Hour and Minute updated");
	}
	
	public void updateTime(int year, int month, int day) {
		time.set(Calendar.YEAR, year);
		time.set(Calendar.MONTH, month);
		time.set(Calendar.DAY_OF_MONTH, day);
		Log.d("Daniel Cantwel", "Year, Month, and Day updated");
	}
	
	public void updateTimeView() {
		sDate = time.get(Calendar.HOUR_OF_DAY) + ":" + time.get(Calendar.MINUTE) + ":00   "
				+ time.get(Calendar.MONTH) + "/"
				+ time.get(Calendar.DAY_OF_MONTH) + "/"
				+ time.get(Calendar.YEAR);
		timeAndDate.setText(sDate);
		Log.d("Daniel Cantwell", "TextView updated");
	}

}
