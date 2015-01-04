package cz.vaclavhoblik.pocket;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {


    public interface OnPickerSelectedListener {
        public void onArticleSelected(Uri articleUri);
    }

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

    public void onDateSet(DatePicker view, int year, int     month, int day) {
        Log.d("aaaa", Integer.toString(year));

        // IMPORTANT this value starts from 0
        Log.d("aaaa", Integer.toString(month));
        Log.d("aaaa", Integer.toString(day));


        TextView textView = (TextView) getActivity().findViewById(R.id.dateText);
        textView.setText(new StringBuilder()
            .append(day).append(".")
            .append(month + 1).append(".")
            .append(year).append(" "));
    }
}
