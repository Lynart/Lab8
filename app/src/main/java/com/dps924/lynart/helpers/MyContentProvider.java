package com.dps924.lynart.helpers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
//http://www.techotopia.com/index.php/An_Android_Studio_Content_Provider_Example
public class MyContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.dps924.lynart.helpers.content";
    private static final String FRIENDS_TABLE = "friends";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + FRIENDS_TABLE);
    private DatabaseHelper dbHelper;

    public MyContentProvider() {
        dbHelper=new DatabaseHelper(getContext());
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        dbHelper.addRow(values.getAsString("name"));

        //Should return the Id in the URI too but meh
        return Uri.parse("content://" + AUTHORITY + "/" + FRIENDS_TABLE + "/");
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseHelper(getContext());
        return false;
    }

    //Query all the rows in your table
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        return dbHelper.getContent();
    }

    //Add a new row to the table
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        dbHelper.addRow(values.getAsString("name"));
        return 1;
    }
}
