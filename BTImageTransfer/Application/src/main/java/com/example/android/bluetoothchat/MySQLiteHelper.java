package com.example.android.bluetoothchat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_IMAGES = "images";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IMAGESIZE = "imagesize";
    public static final String COLUMN_URI = "uri";
    public static final String COLUMN_LASTTIME = "lasttime";

  private static final String DATABASE_NAME = "images.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table " + TABLE_IMAGES + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_IMAGESIZE + " text not null, " + COLUMN_URI + " text not null, " + COLUMN_LASTTIME + " text not null );";

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
      System.out.println(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGES);
    onCreate(db);
  }

} 