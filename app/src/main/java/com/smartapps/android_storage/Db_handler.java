package com.smartapps.android_storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by priya on 3/30/2017.
 */

public class Db_handler{
    public static final String COL1 = "ItemName";
    public static final String COL2 = "ItemPrice";
    public static final String COL3 = "ItemReview";
    public static final String COL4 = "ItemDescription";
    public static final String TABLE_NAME = "product_details";
    public static final String DATABASE_NAME = "DataStorage.db";
    public static final int DATABASE_VERSION = 4;
    public static String TABLE_CREATE = "create table " + TABLE_NAME + " (" + COL1 + " TEXT, " + COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT);";
    Context context;
    public static DataBaseHelper dbHelper;
    public static SQLiteDatabase db;
    public Db_handler(Context context) {
        this.context = context;
        dbHelper = new DataBaseHelper(context);
    }
    public String search(String name){
        String rtn = "";
        String itemName="";
        String itemPrice="";
        String itemReview="";
        String itemDescription="";
        String selectQuery = "Select ItemName,ItemPrice,ItemReview,ItemDescription from " + TABLE_NAME + " WHERE "+COL1+" = '"+ name + "'";

        read();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                do {

                    itemName=cursor.getString(cursor.getColumnIndex(COL1));
                    itemPrice=cursor.getString(cursor.getColumnIndex(COL2));
                    itemReview=cursor.getString(cursor.getColumnIndex(COL3));
                    itemDescription=cursor.getString(cursor.getColumnIndex(COL4));

                    rtn=itemName+","+itemPrice+","+itemReview+","+itemDescription;
                    Toast emptyToast = Toast.makeText(context, itemDescription, Toast.LENGTH_LONG);
                    emptyToast.show();
                } while (cursor.moveToNext());
            }
        }

        cursor.close();
        return rtn;
    }
    public long insert(String ItemName, String ItemPrice, String ItemReview, String ItemDescription) throws IOException {

        open();
        ContentValues content = new ContentValues();
        content.put(COL1, ItemName);
        content.put(COL2, ItemPrice);
        content.put(COL3, ItemReview);
        content.put(COL4, ItemDescription);
        Toast.makeText(context,
                ItemName+ItemPrice+ItemReview+ItemDescription, Toast.LENGTH_LONG).show();
        return db.insert(TABLE_NAME, null, content);
    }
    public static SQLiteDatabase read() {
        db = dbHelper.getReadableDatabase();
        return db;
    }
    public Db_handler open() {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public Cursor retrieve(String[] reqCols, String sort) {
        read();
        return db.query(TABLE_NAME, reqCols, null, null, null, null, sort);
    }
    public void close() {
        dbHelper.close();
    }

    private class DataBaseHelper extends SQLiteOpenHelper {

        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(TABLE_CREATE);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

    }

         @Override
         public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

         }
     }

}
