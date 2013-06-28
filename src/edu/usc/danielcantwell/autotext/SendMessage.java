/**
 * 
 */
package edu.usc.danielcantwell.autotext;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;
import edu.usc.danielcantwell.autotext.MainActivity;

/**
 * @author Daniel
 * 
 */
public class SendMessage extends Service {

	String phoneNo = MainActivity.phoneNo;
	String message = MainActivity.message;

	@Override
	public void onCreate() {
		Toast.makeText(this, "Send Message Activity", Toast.LENGTH_SHORT).show();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub

		Toast.makeText(this, "OnStart", Toast.LENGTH_SHORT).show();

		sendSMS(phoneNo, message);

		//Intent i = new Intent(SendMessage.this, MainActivity.class);
		//i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		//startActivity(i);

		return super.onStartCommand(intent, flags, startId);
	}

	private void sendSMS(String phoneNumber, String message) {
		PendingIntent pi = PendingIntent.getActivity(this, 0,
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
		Toast.makeText(this, "Send Message Activity onDestroy()",
				Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Send Message Activity onUnbind()",
				Toast.LENGTH_LONG).show();
		return super.onUnbind(intent);
	}

}
