/**
 * 
 */
package edu.usc.danielcantwell.autotext;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import edu.usc.danielcantwell.autotext.MainActivity;

/**
 * @author Daniel
 *
 */
public class SendMessageActivity extends Activity {
	
	String phoneNo = MainActivity.phoneNo;
	String message = MainActivity.message;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		sendSMS(phoneNo, message);
	}
	
	private void sendSMS(String phoneNumber, String message)
	{
		PendingIntent pi = PendingIntent.getActivity(this, 0,
				new Intent(this, SendMessageActivity.class), 0);
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNo, null, message, pi, null);
	}

}
