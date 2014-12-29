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


        DbHelper db = new DbHelper(this);

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Item> contacts = db.findAllItems();

        for (Item cn : contacts) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getValue();
            Log.d("Name: ", log);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_create_new_entry  ) {
            Intent i = new Intent(getApplicationContext(), CreateNewItemActivity.class);
            startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
