package cz.vaclavhoblik.pocket;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cz.vaclavhoblik.pocket.R;
import cz.vaclavhoblik.pocket.models.Category;
import cz.vaclavhoblik.pocket.models.Item;

public class CreateNewCategoryActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_category);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_new_category, menu);
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


    /**
     * Submit form action.
     * Creates new item and persists it.
     *
     * @param view
     *
     * @return void
     */
    public void saveCategory(View view) {
        DbHelper dbHelper = new DbHelper(this);

        EditText categoryName = (EditText) findViewById(R.id.category_name);

        Category categoryVo = new Category();

        categoryVo.setName(categoryName.getText().toString());

        dbHelper.addCategory(categoryVo);


        // Redirecting to list
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);


        Toast toast = Toast.makeText(getApplicationContext(), R.string.saved, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}
