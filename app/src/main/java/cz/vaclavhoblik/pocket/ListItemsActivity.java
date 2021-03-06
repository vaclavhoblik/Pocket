package cz.vaclavhoblik.pocket;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cz.vaclavhoblik.pocket.models.Item;


public class ListItemsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);

        DbHelper db = new DbHelper(this);

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Item> contacts = db.findAllItems();


        ArrayList<String> values = new ArrayList<String>();

        ArrayAdapter<String> adapter;

        for (Item cn : contacts) {
            Log.d("sadasd", cn.getDate().toString());

            Long dateUnixtimestampLong = new Long(cn.getDate()) * 1000;

            Date date = new Date();
            date.setTime(dateUnixtimestampLong);

            Log.d("aaaa", date.toString());

            DateFormat format = new SimpleDateFormat("d.M.yyyy", Locale.ENGLISH);
            String dateString = format.format(date);

            values.add(dateString + " - " + cn.getValue().toString());

            String log = "Id: " + cn.getId() + " ,Name: " + cn.getValue() + ", Date: " + cn.getDate();
            Log.d("Name: ", log);
        }

        adapter = new ArrayAdapter<String>(this,
                R.layout.listrow, R.id.label, values);
        setListAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_create_new_entry) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
