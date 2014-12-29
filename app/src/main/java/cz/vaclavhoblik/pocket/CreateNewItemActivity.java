package cz.vaclavhoblik.pocket;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

            EditText value = (EditText) findViewById(R.id.edit_message);

            // TODO [hoblik, B] Find some more usefull validation.
            try {
                Float parsedValue = Float.parseFloat(value.getText().toString());
                createdItem.setValue(parsedValue);
                dbHelper.addItem(createdItem);
            } catch (NumberFormatException e) {
                Toast toast = Toast.makeText(getApplicationContext(), "Validation Successful", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
    }
}
