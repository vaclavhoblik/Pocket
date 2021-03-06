package cz.vaclavhoblik.pocket;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cz.vaclavhoblik.pocket.models.Category;
import cz.vaclavhoblik.pocket.models.Item;


public class CreateNewItemActivity extends FragmentActivity {

    private TextView    selectedDateText;
    private DatePicker  datePicker;
    private Button      btnChangeDate;

    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_item);

        setCurrentDateOnView();



        // Getting all categories.
        DbHelper db = new DbHelper(this);

        List<Category> categories = db.findAllCategories();
        ArrayList<String> values  = new ArrayList<String>();

        for (Category category : categories) {
            values.add(category.getName().toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, values);

        Spinner spinner = (Spinner) findViewById(R.id.category);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_new_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    /**
     * Submit form action.
     * Creates new item and persists it.
     *
     * @param view
     *
     * @return void
     */
    public void createItem(View view) {

            DbHelper dbHelper = new DbHelper(this);

            Item createdItem = new Item();

            EditText value     = (EditText) findViewById(R.id.value);
            TextView date      = (TextView) findViewById(R.id.dateText);

            // TODO [hoblik, B] Find some more usefull validation.
            try {
                // === Preparing value ===
                Float parsedValue = Float.parseFloat(value.getText().toString());
                createdItem.setValue(parsedValue);

                // === Preparing date ===
                DateFormat format = new SimpleDateFormat("d.M.yyyy", Locale.ENGLISH);
                Date dateObj      = format.parse(date.getText().toString());

                Long timeInMiliseconds = dateObj.getTime();
                Long timeInSeconds     = timeInMiliseconds / 1000;

                createdItem.setDate(timeInSeconds.intValue());

                dbHelper.addItem(createdItem);

                // Redirecting to list
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

                Toast toast = Toast.makeText(getApplicationContext(), R.string.saved, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

            // TODO [hoblik, A] Some meaning full messages.
            } catch (NumberFormatException e) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.error_invalid_format, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            } catch (ParseException e) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.generic_error , Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
    }

    // display current date
    public void setCurrentDateOnView() {
        selectedDateText = (TextView) findViewById(R.id.dateText);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        selectedDateText.setText(new StringBuilder()
            .append(day).append(".")
            .append(month + 1).append(".")
            .append(year).append(" "));
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }
}
