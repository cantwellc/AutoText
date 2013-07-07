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
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;
import edu.usc.danielcantwell.autotext.MainActivity;

/**
 * @author Daniel
 * 27 June 2013
 */

public class SendMessage extends Service {
	
	int serviceCount = MainActivity.serviceCount;
	
	// Get the Phone Number and Message from the main activity
	String phoneNo = MainActivity.phoneNo;
	String message = MainActivity.message;;
	

	@Override // When this Service is first called
	public void onCreate() {
		//Toast.makeText(this, "Send Message Activity", Toast.LENGTH_SHORT).show();
	}

	@Override // Each time this Service is called
	public int onStartCommand(Intent intent, int flags, int startId) {

		//Toast.makeText(this, "OnStart", Toast.LENGTH_SHORT).show();

		// Send the message
		sendSMS(phoneNo, message);

		// Stop Service after the message is sent
		stopSelf();
		
		return super.onStartCommand(intent, flags, startId);
	}

	private void sendSMS(String phoneNumber, String message) {
		
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
