package cz.vaclavhoblik.pocket;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.app.Activity;

import java.util.List;

import cz.vaclavhoblik.pocket.models.Item;


public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_create_new_entry  ) {
            Intent i = new Intent(getApplicationContext(), CreateNewItemActivity.class);
            startActivity(i);

            return true;
        } else if (id == R.id.action_list_entries) {
            Intent i = new Intent(getApplicationContext(), ListItemsActivity.class);
            startActivity(i);

            return true;
        } else if (id == R.id.action_create_new_category) {
            Intent i = new Intent(getApplicationContext(), CreateNewCategoryActivity.class);
            startActivity(i);
        } else if (id == R.id.action_list_categories) {
            Intent i = new Intent(getApplicationContext(), ListCategoriesActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
