package com.example.android.bluetoothchat;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class ImagesDataSource {

  public final String SDStorage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();

  // Database fields
  private SQLiteDatabase database;
  private MySQLiteHelper dbHelper;
  private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_IMAGESIZE, MySQLiteHelper.COLUMN_URI, MySQLiteHelper.COLUMN_LASTTIME};

  public ImagesDataSource(Context context) {
    dbHelper = new MySQLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public ReceivedImage addImage(String imageSize, String Uri, String lastTime) {
    ContentValues values = new ContentValues();
    values.put(MySQLiteHelper.COLUMN_IMAGESIZE, imageSize);
    values.put(MySQLiteHelper.COLUMN_URI, Uri);
    values.put(MySQLiteHelper.COLUMN_LASTTIME, lastTime);
    System.out.println(imageSize+Uri+lastTime);
    long insertId = database.insert(MySQLiteHelper.TABLE_IMAGES, null,
        values);
    System.out.println(insertId);
    System.out.println("Values: " +values);
    Cursor cursor = database.query(MySQLiteHelper.TABLE_IMAGES,
        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    cursor.moveToFirst();
    ReceivedImage newReceivedImage = cursorToImage(cursor);
    cursor.close();

    System.out.println("ReceivedImage add with id: " + newReceivedImage.getId() + "imagesize:" + newReceivedImage.getImageSize() + "imageURI:" + newReceivedImage.getUri() + "imageLastTime:" + newReceivedImage.getLastUpdatedTime());
    return newReceivedImage;
  }

  public void deleteImage(ReceivedImage receivedImage) {
    long id = receivedImage.getId();
    System.out.println("ReceivedImage deleted with id: " + id + "imagesize:" + receivedImage.getImageSize() + "imageURI:" + receivedImage.getUri() + "imageLastTime:" + receivedImage.getLastUpdatedTime());
    database.delete(MySQLiteHelper.TABLE_IMAGES, MySQLiteHelper.COLUMN_ID
            + " = " + id, null);
  }

  public ReceivedImage updateImage(ReceivedImage receivedImage, String imageSize){
    long	id	=	receivedImage.getId();
    ContentValues values	=	new	ContentValues();
    values.put(MySQLiteHelper.COLUMN_URI,	SDStorage + "/" + String.valueOf(id) + ".png");
    values.put(MySQLiteHelper.COLUMN_IMAGESIZE,	imageSize);
    database.update(MySQLiteHelper.TABLE_IMAGES,	values,
            MySQLiteHelper.COLUMN_ID+	"	=	"	+	id,
            null);
    System.out.println("ReceivedImage	update	with	id:	"	+	id);
    Cursor	cursor	=	database.query(MySQLiteHelper.TABLE_IMAGES,
            allColumns,	MySQLiteHelper.COLUMN_ID +	"	=	"	+	id,	null,
            null,	null,	null);
    cursor.moveToFirst();
    ReceivedImage newReceivedImage = cursorToImage(cursor);
    cursor.close();
    System.out.println("ReceivedImage update with id: " + newReceivedImage.getId() + "imagesize:" + newReceivedImage.getImageSize() + "imageURI:" + newReceivedImage.getUri() + "imageLastTime:" + newReceivedImage.getLastUpdatedTime());
    return newReceivedImage;
  }


  public List<ReceivedImage> getAllComments() {
    List<ReceivedImage> receivedImages = new ArrayList<ReceivedImage>();

    Cursor cursor = database.query(MySQLiteHelper.TABLE_IMAGES,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      ReceivedImage receivedImage = cursorToImage(cursor);
      receivedImages.add(receivedImage);
      cursor.moveToNext();
    }
    // make sure to close the cursor
    cursor.close();
    return receivedImages;
  }

  private ReceivedImage cursorToImage(Cursor cursor) {
    ReceivedImage receivedImage = new ReceivedImage();
    receivedImage.setId(cursor.getLong(0));
    receivedImage.setImageSize(cursor.getString(1));
    receivedImage.setUri(cursor.getString(2));
    receivedImage.setLastUpdatedTime(cursor.getString(3));
    //receivedImage.setComment(cursor.getString(1));
    return receivedImage;
  }
} 
