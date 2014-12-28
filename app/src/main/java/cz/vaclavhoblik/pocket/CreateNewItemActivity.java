package cz.vaclavhoblik.pocket;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cz.vaclavhoblik.pocket.DbHelper;
import cz.vaclavhoblik.pocket.models.Item;


public class CreateNewItemActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_item);
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

    public void createItem(View view) {
        if (view.getId() == R.id.action_settings) {
            DbHelper dbHelper = new DbHelper(this);

            Item createdItem = new Item();

            createdItem.setValue(new Float(123));

            dbHelper.addItem(createdItem);
        }
    }
}
