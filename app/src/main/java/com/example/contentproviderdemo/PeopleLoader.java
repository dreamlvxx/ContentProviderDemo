package com.example.contentproviderdemo;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.loader.content.AsyncTaskLoader;

public class PeopleLoader extends AsyncTaskLoader<Cursor> {

    private final static Uri STUDENT_URI = Uri.parse("content://" + PeopleContentProvider.AUTHRITY + "/people");

    public PeopleLoader(@NonNull Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public Cursor loadInBackground() {
        Cursor cursor = getContext().getContentResolver().query(STUDENT_URI,null,null,null);
        Log.e("xxx", "loadInBackground: execute count = " + cursor.getCount());
        return cursor;
    }
}
