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
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * @author Daniel
 * 27 June 2013
 */


public class MainActivity extends Activity {

	Button btnSendSMS;
	EditText txtPhoneNo;
	EditText txtMessage;
	TimePicker timePicker;

	public static int selectedHour;
	public static int selectedMinute;
	
	public static String phoneNo;
	public static String message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
		timePicker = (TimePicker) findViewById(R.id.timePicker);

		// Button Click
		btnSendSMS.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				// Hour (24hr format) and Minute selected from TimePicker
				selectedHour = timePicker.getCurrentHour();
				selectedMinute = timePicker.getCurrentMinute();
				
				// Calendar to pass Selected Time to AlarmManager
				Calendar time = Calendar.getInstance();
				time.set(Calendar.HOUR_OF_DAY, selectedHour);
				time.set(Calendar.MINUTE, selectedMinute);
				time.set(Calendar.SECOND, 0);
				
				// Setting the Phone Number and Message
				txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
				txtMessage = (EditText) findViewById(R.id.txtMessage);
				
				phoneNo = txtPhoneNo.getText().toString();
				message = txtMessage.getText().toString();
				
				// The Phone Number and Message must have content
				if (phoneNo.length() > 0 && message.length() > 0) {
					
					// Creating the Pending Intent
					Intent myIntent = new Intent(MainActivity.this, SendMessage.class);
					PendingIntent pi = PendingIntent.getService(MainActivity.this, 0, myIntent, 0);
					
					// Creating the AlarmManager
					AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
					alarmManager.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pi);
					
					Toast.makeText(MainActivity.this, "Alarm Set", Toast.LENGTH_LONG).show();
					
				} else {
					Toast.makeText(getBaseContext(),
							"Please enter both phone number and message",
							Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
