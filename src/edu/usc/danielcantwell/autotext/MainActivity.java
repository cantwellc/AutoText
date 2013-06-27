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

		// Instantiate button, edittexts, and timepicker
		btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
		txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
		txtMessage = (EditText) findViewById(R.id.txtMessage);
		timePicker = (TimePicker) findViewById(R.id.timePicker);

		// Button on click listener
		btnSendSMS.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				// Get time from timePicker
				selectedHour = timePicker.getCurrentHour();
				selectedMinute = timePicker.getCurrentMinute();
				
				// Set the wanted time in milliseconds
				long selectedTime = (selectedHour * 3600000) + (selectedMinute * 60000);
				
				// Calendar to get current time
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(System.currentTimeMillis());
				c.add(Calendar.SECOND, 20);
				
				// Get text from txtPhoneNo and txtMessage EditText
				phoneNo = txtPhoneNo.getText().toString();
				message = txtMessage.getText().toString();
				
				// If there is a phone number and a message
				if (phoneNo.length() > 0 && message.length() > 0) {
					
					// Create new Intent to start SendMessageActivity service
					Intent myIntent = new Intent(MainActivity.this, SendMessageActivity.class);
					PendingIntent pi = PendingIntent.getService(MainActivity.this, 0, myIntent, 0);
					
					// Create an AlarmManager to start the Intent at a certain time
					AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
					alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
					
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
