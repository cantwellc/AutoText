/**
 * 
 */
package edu.usc.danielcantwell.autotext;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * @author Daniel
 *
 */
public class BackgroundActivity extends BroadcastReceiver{
	
	int selectedHour = MainActivity.selectedHour;
	int selectedMinute = MainActivity.selectedMinute;
	
	int hourInMilliseconds = selectedHour * 3600000;
	int minuteInMilliseconds = selectedMinute * 60000;
	
	int time = hourInMilliseconds + minuteInMilliseconds;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		/*
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        // Put here YOUR code.
        Toast.makeText(context, "AutoText Message Sent", Toast.LENGTH_LONG).show(); // For example

        wl.release();
        */
		
		Toast.makeText(context, "In Background Activity", Toast.LENGTH_LONG).show();
		
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, BackgroundActivity.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.set(AlarmManager.RTC_WAKEUP, time, pi);
        
        Intent myIntent=new Intent(context, SendMessageActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
	}
}
