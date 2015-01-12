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

import cz.vaclavhoblik.pocket.models.Category;
import cz.vaclavhoblik.pocket.models.Item;

/**
 * Helper class.
 * Implement conection to SQLite.
 * Provides methods for persist models.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "pocket.db";

    private static final String TABLE_ITEMS    = "items";
    private static final String TABLE_CATEGORY = "category";

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
        // Creating items table
        String createItemsTableString = new String(
                "create table "
                    + TABLE_ITEMS + " ("
                    + " id integer primary key autoincrement, "
                    + " value float, "
                    + " date integer,"
                    + " category_id integer default 0 "
                    + " );");

        // Creating category table
        String createCategoryTablesString = new String(
                "create table "
                        + TABLE_CATEGORY + " ("
                        + " id integer primary key autoincrement, "
                        + " name text "
                        + " );");

        db.execSQL(createItemsTableString);
        db.execSQL(createCategoryTablesString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Dropping existing tables.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);

        //
        onCreate(db);
    }

    // ======== ITEMS =========

    /**
     * Saving new item (cost).
     *
     * @param item Item model
     *
     * @return void
     */
    public void addItem(Item item) {
        ContentValues values = new ContentValues();
        values.put("value", item.getValue());
        values.put("date", item.getDate());

        Log.d("saving to database", values.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }

    /**
     * Getting all items.
     *
     * @return List<Item> List of items
     */
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
                item.setDate(Integer.parseInt(cursor.getString(2)));

                itemList.add(item);
            } while (cursor.moveToNext());
        }

        // return contact list
        return itemList;
    }


    // ======== Categories =========

    /**
     * Saving new category (cost).
     *
     * @param category Category model
     *
     * @return void
     */
    public void addCategory(Category category) {
        ContentValues values = new ContentValues();
        values.put("name", category.getName());

        Log.d("saving to database", values.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_CATEGORY, null, values);
        db.close();
    }

    /**
     * Getting all categories.
     *
     * @return List<Category> List of categories
     */
    public List<Category> findAllCategories() {
        List<Category> categories = new ArrayList<Category>();

        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();

                // TODO [hoblik, A] Try to find more inteligent way.
                category.setId(Integer.parseInt(cursor.getString(0)));
                category.setName(cursor.getString(1));

                categories.add(category);
            } while (cursor.moveToNext());
        }

        // return contact list
        return categories;
    }

}