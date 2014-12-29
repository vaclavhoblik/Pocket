package cz.vaclavhoblik.pocket;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

import cz.vaclavhoblik.pocket.models.Item;

/**
 * Helper class.
 *
 *
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "pocket.db";

    private static final String TABLE_ITEMS = "items";

    /**
     * Constructor
     *
     * @param context
     */
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createItemsTablesString = new String(
                "create table "
                    + TABLE_ITEMS + " ("
                    + " id integer primary key autoincrement, "
                    + " value float"
                    + " );");

        db.execSQL(createItemsTablesString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Dropping existing tables.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);

        //
        onCreate(db);
    }

    public void addItem(Item item) {
        String logvalue = Float.toString(item.getValue());

        Log.d("Name: ", logvalue );
        ContentValues values = new ContentValues();
        values.put("value", item.getValue());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_ITEMS, null, values);
        db.close();

        List<Item> contacts = this  .findAllItems();

        for (Item cn : contacts) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getValue();
            Log.d("Name: ", log);
        }
    }


    public List<Item> findAllItems() {
        List<Item> itemList = new ArrayList<Item>();

        String selectQuery = "SELECT  * FROM " + TABLE_ITEMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();

                // TODO [hoblik, A] Try to find more inteligent way.
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setValue(Float.parseFloat(cursor.getString(1)));

                itemList.add(item);
            } while (cursor.moveToNext());
        }

        // return contact list
        return itemList;
    }
}