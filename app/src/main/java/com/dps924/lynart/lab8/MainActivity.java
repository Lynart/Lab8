package com.dps924.lynart.lab8;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.dps924.lynart.helpers.DatabaseHelper;
import com.dps924.lynart.helpers.MyContentProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    MyContentProvider provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        provider = new MyContentProvider();

        copyDatabase();

        DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
        //Why this works is beyond me; how do you deal with multiple databases then?
        helper.Test();

        String url = "content://com.dps924.lynart.helpers.content/friends/";
        Uri uri = Uri.parse(url);

        final ContentResolver resolver = getContentResolver();
        Cursor c = resolver.query(uri, null, null, null, null);
        c.moveToFirst();
        if(c!=null) {
            for (int i = 0; i < c.getCount(); i++) {
                Log.d("QUERY", c.getString(0));
                c.moveToNext();
            }
        }
    }

    private void copyDatabase() {
        try{
            String destPath = "/data/data/" + getApplicationContext().getPackageName() + "/databases/";

            //Check if path exists
            File destPathFile = new File(destPath);
            if(!destPathFile.exists()){
                destPathFile.mkdirs();
            }

            File destFile = new File(destPath + "friends.db");
            if(!destFile.exists()){
                Log.d("databaseCreation", "Making DB, don't fail");
                copyFile(getApplicationContext().getAssets().open("friends.db"),
                        new FileOutputStream(destPath+"/"+"friends.db"));
            }
        }

        catch(FileNotFoundException e){
            Log.d("databaseCreation", e.toString());
        }
        catch(IOException e){
            Log.d("databaseCreation", e.toString());
        }
        catch(Exception e){
            Log.d("databaseCreation", e.toString());
        }
    }

    public void copyFile(InputStream inputStream, OutputStream outputStream) throws IOException
    {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0)
            outputStream.write(buffer, 0, length);
        inputStream.close();
        outputStream.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
