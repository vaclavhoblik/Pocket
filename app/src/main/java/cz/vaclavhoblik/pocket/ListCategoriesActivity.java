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

import cz.vaclavhoblik.pocket.R;
import cz.vaclavhoblik.pocket.models.Category;
import cz.vaclavhoblik.pocket.models.Item;

public class ListCategoriesActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categories);


        // Getting all categories.
        DbHelper db = new DbHelper(this);

        List<Category> categories = db.findAllCategories();
        ArrayList<String> values  = new ArrayList<String>();

        for (Category category : categories) {
            values.add(category.getName().toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.listrow, R.id.label, values);

        setListAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_categories, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
