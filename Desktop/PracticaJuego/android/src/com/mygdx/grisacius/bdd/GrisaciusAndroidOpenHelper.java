package com.mygdx.grisacius.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class GrisaciusAndroidOpenHelper extends SQLiteOpenHelper {

    public GrisaciusAndroidOpenHelper(Context context) {
        super(context, GrisaciusDataBaseAndroid.getDatabaseName(), null, GrisaciusDataBaseAndroid.getDatabaseVersion());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(GrisaciusDataBaseAndroid.getDatabaseCreationQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(GrisaciusDataBaseAndroid.getDatabaseUpdateQuery());
    }

}
