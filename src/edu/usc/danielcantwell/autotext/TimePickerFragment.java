/**
 * 
 */
package edu.usc.danielcantwell.autotext;

import java.util.Calendar;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

/**
 * @author Daniel
 * 
 */
public class TimePickerFragment extends DialogFragment implements
		TimePickerDialog.OnTimeSetListener {
	
	MainActivity activity = MainActivity.activity;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current time as the default values for the picker
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);

		// Create a new instance of TimePickerDialog and return it
		return new TimePickerDialog(getActivity(), this, hour, minute,
				DateFormat.is24HourFormat(getActivity()));
	}

	@Override
	public void onTimeSet(TimePicker view, int hour, int minute) {
		// TODO Auto-generated method stub
		activity.updateTime(hour, minute);
		activity.updateTimeView();
		
		Log.d("Daniel Cantwell", "Time Picker Set");
	}

}
