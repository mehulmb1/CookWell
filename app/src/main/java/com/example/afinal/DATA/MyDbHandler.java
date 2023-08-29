package com.example.afinal.DATA;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.afinal.PARAMS.Parmas;
public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler(Context context) {
        super(context, Parmas.DB_NAME, null, Parmas.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Parmas.TABLE_NAME +
                "(" + "INTEGER PRIMARY KEY"+Parmas.KEY_ID +")";

        Log.d("dbmehul", "Query being run is : "+ create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insert(String tableName, Object o, String imgid) {
    }
}
