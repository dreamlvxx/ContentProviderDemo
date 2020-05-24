package com.example.contentproviderdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static Uri STUDENT_URI = Uri.parse("content://" + PeopleContentProvider.AUTHRITY + "/people");

    private LoaderManager loaderManager;

    private SimpleCursorAdapter cursorAdapter;

    private ListView listView;
    private TextView add_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        add_data = findViewById(R.id.add_data);
        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertValue();
            }
        });

        loaderManager = LoaderManager.getInstance(this);
        cursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.activity_list_item,
                null,
                new String[]{"name"},
                new int[]{android.R.id.text1},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        loaderManager.initLoader(0, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @NonNull
            @Override
            public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
                return new PeopleLoader(MainActivity.this);
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
                Log.e("xxx", "onLoadFinished: execute");
                cursorAdapter.swapCursor(data);
            }

            @Override
            public void onLoaderReset(@NonNull Loader<Cursor> loader) {
                Log.e("xxx", "onLoaderReset: execute");
                cursorAdapter.swapCursor(null);
            }
        });

    }

    private void insertValue() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",0);
        contentValues.put("name","peter");
        getContentResolver().insert(STUDENT_URI,contentValues);
    }
}
