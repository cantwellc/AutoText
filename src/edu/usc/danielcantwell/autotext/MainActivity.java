package edu.usc.danielcantwell.autotext;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
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

		btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
		txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
		txtMessage = (EditText) findViewById(R.id.txtMessage);
		timePicker = (TimePicker) findViewById(R.id.timePicker);
		
		selectedHour = timePicker.getCurrentHour();
		selectedMinute = timePicker.getCurrentMinute();

		btnSendSMS.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				phoneNo = txtPhoneNo.getText().toString();
				message = txtMessage.getText().toString();
				if (phoneNo.length() > 0 && message.length() > 0) {
					Intent myIntent = new Intent(MainActivity.this, BackgroundActivity.class);
					MainActivity.this.startActivity(myIntent);
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