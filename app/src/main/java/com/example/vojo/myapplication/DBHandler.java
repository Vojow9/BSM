package com.example.vojo.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vojo on 13.11.2017.
 */

public class DBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "messages";
    private static final String TABLE_MESSAGES_DETAIL = "message";

    private static final String KEY_ID = "_id";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_TEXT = "text";

    public DBHandler(Context context){super(context,DATABASE_NAME,null,DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db){

        String CREATE_MESSAGE_DETAIL_TABLE = "CREATE TABLE " + TABLE_MESSAGES_DETAIL + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_TEXT + " TEXT" + ")" ;

        db.execSQL(CREATE_MESSAGE_DETAIL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES_DETAIL);

        onCreate(db);
    }

    public void addMessage(Message newMsg) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_PASSWORD, newMsg.getPassword());
        values.put(KEY_TEXT, newMsg.getText());

        // Inserting Row
        db.insert(TABLE_MESSAGES_DETAIL, null, values);
        db.close(); // Closing database connection
    }


    public boolean updateMessage(int updId, String upPass, String upText) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(KEY_PASSWORD, upPass);
        args.put(KEY_TEXT, upText);

        return db.update(TABLE_MESSAGES_DETAIL, args, KEY_ID + "=" + updId, null) > 0;
    }

    public Message findMessage(String pass){
        String query = "Select * FROM " + TABLE_MESSAGES_DETAIL + " WHERE " + KEY_PASSWORD + " = \"" + pass +"\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Message message = new Message();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            message.set_id(Integer.parseInt(cursor.getString(0)));
            message.setPassword(cursor.getString(1));
            message.setText(cursor.getString(2));
            cursor.close();
        } else {
            message = null;
        }
        db.close();

        return message;

    }




}
