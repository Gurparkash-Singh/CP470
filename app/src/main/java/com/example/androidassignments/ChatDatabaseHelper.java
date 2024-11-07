package com.example.androidassignments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "Messages.db";
    private final static int VERSION_NUM = 1;
    public final static String TABLE_NAME = "Messages";
    public final static String KEY_ID = "ID";
    public final static String KEY_MESSAGE = "Message";

    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(\n";
        createTable += KEY_ID + " INT PRIMARY_KEY AUTO_INCREMENT,\n";
        createTable += KEY_MESSAGE + " TEXT NOT NULL\n";
        createTable += ");";
        db.execSQL(createTable);

        Log.i("ChatDatabaseHelper", "Calling onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String deleteTable = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(deleteTable);
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + i + "newVersion=" + i1);
        onCreate(db);
    }
}
