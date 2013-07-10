/**
 * SendMessage Class
 * extends Service
 * 
 * called by MainActivity
 */
package edu.usc.danielcantwell.autotext;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Daniel
 * 27 June 2013
 */

public class SendMessage extends Service {
	
	int serviceCount;
	
	// Get the Phone Number and Message from the main activity
	String phoneNo;
	String message;
	

	@Override // When this Service is first called
	public void onCreate() {
		Log.d("Daniel Cantwell", "Send Message Service Created");
	}

	@Override // Each time this Service is called
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		Log.d("Daniel Cantwell", "Send Message Service Started");

		Bundle extras = intent.getExtras();
		
		if (extras != null) {
			serviceCount = extras.getInt("serviceNumber");
			phoneNo = extras.getString("phoneNumber");
			message = extras.getString("textMessage");
		}

		// Send the message
		sendSMS(phoneNo, message, serviceCount);

		// Stop Service after the message is sent
		stopSelf();
		
		return super.onStartCommand(intent, flags, startId);
	}

	private void sendSMS(String phoneNumber, String message, int serviceCount) {
		
		Log.d("Daniel Cantwell", "Message Sent");
		
		// Create a PendingIntent for SmsManager
		PendingIntent pi = PendingIntent.getActivity(this, serviceCount,
				new Intent(this, SendMessage.class), 0);
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNo, null, message, pi, null);

		Toast.makeText(this, "Message Sent", Toast.LENGTH_LONG).show();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "MyAlarmService.onBind()", Toast.LENGTH_LONG)
				.show();
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//Toast.makeText(this, "Send Message Service Ended", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Send Message Activity onUnbind()",
				Toast.LENGTH_LONG).show();
		return super.onUnbind(intent);
	}

}
