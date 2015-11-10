package com.dps924.lynart.helpers;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by Lynart on 11/9/2015.
 * http://www.vogella.com/tutorials/AndroidSQLite/article.html#tutorialusecp
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "friends.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DBHelper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        //Do nothing, database already exists...I think
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {
        //Empty because this will never happen (in this app)
    }

    public void Test() {
        Cursor wtf = getReadableDatabase().rawQuery("select * from friends", null);
        wtf.moveToFirst();
        Log.d("Testresult", wtf.getString(0)+wtf.getString(1));
    }

    public Cursor getContent(){
        Cursor rc = getReadableDatabase().rawQuery("select name from friends", null);
        return rc;
    }

    public void addRow(String name) {
        getReadableDatabase().execSQL("insert into friends values(111,'" + name +"', 'vince.lee.lien@gmail.com', '6478844889')");
    }
}
