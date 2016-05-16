package com.example.favorite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "AddtoFav";
	private static final String TABLE_NAME = "Favorite";
	private static final String KEY_ID = "id";
	private static final String KEY_VID = "vid";
	private static final String KEY_VIDEO_ID = "videoid";
	private static final String KEY_VIDEO_NAME = "videoname";
	private static final String KEY_VIDEO_URL = "videourl";
	private static final String KEY_VIDEO_DURATION = "videoduration";
	private static final String KEY_VIDEO_CATEGORYNAME = "videocatname";
	private static final String KEY_VIDEO_CATEGORYID = "videocatid";
	private static final String KEY_VIDEO_DESCRIPTION = "videodesc";
	private static final String KEY_VIDEO_IMAGE_URL = "imageurl";
	private static final String KEY_VIDEO_TYPE = "videotype";

	public DatabaseHandler(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
			@Override
			public void onCreate(SQLiteDatabase db) 
			{
				String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
						+ KEY_ID + " INTEGER PRIMARY KEY," 
						+ KEY_VID + " TEXT,"
						+ KEY_VIDEO_ID + " TEXT," 
						+ KEY_VIDEO_NAME + " TEXT,"
						+ KEY_VIDEO_URL + " TEXT,"
						+ KEY_VIDEO_DURATION + " TEXT,"
						+ KEY_VIDEO_CATEGORYNAME + " TEXT,"
						+ KEY_VIDEO_CATEGORYID + " TEXT,"
						+ KEY_VIDEO_DESCRIPTION + " TEXT,"
						+ KEY_VIDEO_IMAGE_URL + " TEXT,"
						+ KEY_VIDEO_TYPE + " TEXT"
						+ ")";
				db.execSQL(CREATE_CONTACTS_TABLE);		
				
			}

			// Upgrading database
			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				// TODO Auto-generated method stub
				// Drop older table if existed
						db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

						// Create tables again
						onCreate(db);
			}
			
			//Adding Record in Database
			
			public	void AddtoFavorite(Pojo pj)
			{
				SQLiteDatabase db = this.getWritableDatabase();

				ContentValues values = new ContentValues();
				values.put(KEY_VID, pj.getVId());
				values.put(KEY_VIDEO_ID, pj.getVideoId());
				values.put(KEY_VIDEO_NAME, pj.getVideoName());
				values.put(KEY_VIDEO_URL, pj.getVideoUrl());
				values.put(KEY_VIDEO_DURATION, pj.getDuration());
				values.put(KEY_VIDEO_CATEGORYNAME, pj.getCategoryName());
				values.put(KEY_VIDEO_CATEGORYID, pj.getCategoryId());
				values.put(KEY_VIDEO_DESCRIPTION, pj.getDescription());
				values.put(KEY_VIDEO_IMAGE_URL, pj.getImageUrl());
				values.put(KEY_VIDEO_TYPE, pj.getVideoType());
				
				// Inserting Row
				db.insert(TABLE_NAME, null, values);
				db.close(); // Closing database connection
				
			}
			
			// Getting All Data
			public List<Pojo> getAllData() 
			{
				List<Pojo> dataList = new ArrayList<Pojo>();
				// Select All Query
				String selectQuery = "SELECT  * FROM " + TABLE_NAME;

				SQLiteDatabase db = this.getWritableDatabase();
				Cursor cursor = db.rawQuery(selectQuery, null);

				// looping through all rows and adding to list
				if (cursor.moveToFirst()) 
				{
					do {
						Pojo contact = new Pojo();
						contact.setId(Integer.parseInt(cursor.getString(0)));
						contact.setVId(cursor.getString(1));
						contact.setVideoId(cursor.getString(2));
						contact.setVideoName(cursor.getString(3));
						contact.setVideoUrl(cursor.getString(4));
						contact.setDuration(cursor.getString(5));
						contact.setCategoryName(cursor.getString(6));
						contact.setCategoryId(cursor.getString(7));
						contact.setDescription(cursor.getString(8));
						contact.setImageUrl(cursor.getString(9));
						contact.setVideoType(cursor.getString(10));
					 
						// Adding contact to list
						dataList.add(contact);
					} while (cursor.moveToNext());
				}

				// return contact list
				return dataList;
			}
			
		//getting single row
			
			public List<Pojo> getFavRow(String id) 
			{
				List<Pojo> dataList = new ArrayList<Pojo>();
				// Select All Query
				String selectQuery = "SELECT  * FROM " + TABLE_NAME +" WHERE vid="+id;

				SQLiteDatabase db = this.getWritableDatabase();
				Cursor cursor = db.rawQuery(selectQuery, null);

				// looping through all rows and adding to list
				if (cursor.moveToFirst()) 
				{
					do {
						Pojo contact = new Pojo();
						contact.setId(Integer.parseInt(cursor.getString(0)));
						contact.setVId(cursor.getString(1));
						contact.setVideoId(cursor.getString(2));
						contact.setVideoName(cursor.getString(3));
						contact.setVideoUrl(cursor.getString(4));
						contact.setDuration(cursor.getString(5));
						contact.setCategoryName(cursor.getString(6));
						contact.setCategoryId(cursor.getString(7));
						contact.setDescription(cursor.getString(8));
						contact.setImageUrl(cursor.getString(9));
						contact.setVideoType(cursor.getString(10));
						// Adding contact to list
						dataList.add(contact);
					} while (cursor.moveToNext());
				}

				// return contact list
				return dataList;
			}
			
		//for remove favorite
			
			public void RemoveFav(Pojo contact)
			{
			    SQLiteDatabase db = this.getWritableDatabase();
			    db.delete(TABLE_NAME, KEY_VID + " = ?",
			            new String[] { String.valueOf(contact.getVId()) });
			    db.close();
			}
			
			public enum DatabaseManager {
				INSTANCE;
				private SQLiteDatabase db;
				private boolean isDbClosed =true;
				DatabaseHandler dbHelper;
				public void init(Context context) {
					dbHelper = new DatabaseHandler(context);
				if(isDbClosed){
				isDbClosed =false;
				this.db = dbHelper.getWritableDatabase();
				}

				}


				public boolean isDatabaseClosed(){
				return isDbClosed;
				}

				public void closeDatabase(){
				if(!isDbClosed && db != null){
				isDbClosed =true;
				db.close();
				dbHelper.close();
				}
				}
			}
}
