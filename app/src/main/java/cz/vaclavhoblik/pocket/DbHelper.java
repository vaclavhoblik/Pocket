package cz.vaclavhoblik.pocket;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

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
                    + " amount float, "
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

        ContentValues values = new ContentValues();
        values.put("value", item.getValue());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }
}