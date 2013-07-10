/**
 * 
 */
package edu.usc.danielcantwell.autotext;

import java.util.Calendar;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

/**
 * @author Daniel
 * 
 */
public class DatePickerFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	MainActivity activity = MainActivity.activity;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
		// TODO Auto-generated method stub
		activity.updateTime(year, month, day);
		activity.updateTimeView();
		
		Log.d("Daniel Cantwell", "Date Picker Set");
	}

}
