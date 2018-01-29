package com.sudharshan.user.anasnmnt11_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Created by User on 04-01-2018.
 */

public class DBhelper extends SQLiteOpenHelper {
    //SQLiteOpenHelper:A helper class to manage database creation and version management
    public DBhelper(Context context)
    //Context:Interface to global information about an application environment
    {
        super(context,"productDB",null,3);
        //factory:to get the class instantiated
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Called when the database is created for the first time. This is where the creation of tables and the initial population of the tables should happen.
        // Parameters
        // db	SQLiteDatabase: The database.

        db.execSQL("CREATE TABLE TABLE_PRODUCT(COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,COLUMN_PRODUCTNAME TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Called when the database needs to be upgraded. The implementation should use this method to drop tables, add tables, or do
        // anything else it needs to// upgrade to the new schema version.
        //db	SQLiteDatabase: The database.
        //  oldVersion	int: The old database version.
        // newVersion	int: The new database version.
        db.execSQL("DROP TABLE IF EXISTS TABLE_PRODUCT");
        //here it will executes once we drop the exists table and create new database
        onCreate(db);//creates the new database
    }
    public void Addproducts(String productname){
        //we add products givind the permission to write the database
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values  = new ContentValues();
//inserting the values in appropriate columns
        values.put("COLUMN_PRODUCTNAME",productname);
        db.insert("TABLE_PRODUCT",null,values);
    }
    public ArrayList Databasetoarray(){
        //we created a method databasetoarray and adding the values in form of array list by giving permission to read
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db =getReadableDatabase();
//here it will show the database
        String query = "SELECT * FROM TABLE_PRODUCT";
        Cursor c = db.rawQuery(query,null);
        //This interface provides random read-write access to the result set returned by a database query.
        //cursor will move to first until cursor is in last column
        //it need to verify column index and need to move to next index
        c.moveToFirst();
        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("COLUMN_PRODUCTNAME"))!=null){
                arrayList.add(c.getString(c.getColumnIndex("COLUMN_PRODUCTNAME")));
                c.moveToNext();

            }

        }
        db.close();
        //once the operation completes the database will close and returns the arraylist
        return arrayList;
    }
}