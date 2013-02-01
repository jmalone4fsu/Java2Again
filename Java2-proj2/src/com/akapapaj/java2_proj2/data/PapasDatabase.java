package com.akapapaj.java2_proj2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PapasDatabase extends SQLiteOpenHelper {
	private static final String DEBUG_TAG = "PapasDatabase";
	private static final int DB_VERSION = 3;
	private static final String DB_NAME = "akapapaj_data";

	// Database Schema Definitions //
	public static final String TABLE_ITEMS = "items";
	public static final String ID = "_id";
	// public static final String COL_ITEMID = "myitem_id";
	public static final String COL_ITEMNAME = "item_name";
	// public static final String COL_BRAND = "brand";
	// public static final String COL_CALORIES = "calories";
	public static final String COL_URL = "url";

	private static final String CREATE_TABLE_ITEMS = "CREATE TABLE "
			+ TABLE_ITEMS + " (" + ID + " integer PRIMARY KEY AUTOINCREMENT, "
			+ COL_ITEMNAME + " text NOT NULL, " + COL_URL
			+ " text UNIQUE NOT NULL);";
	private static final String DB_SCHEMA = CREATE_TABLE_ITEMS;

	public PapasDatabase(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create the database table //
		db.execSQL(DB_SCHEMA);
		seedData(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DEBUG_TAG, "Upgrading database. Existing content will explode. ["
				+ oldVersion + "]->[" + newVersion + "]");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
		onCreate(db);
	}

	/**
	 * Create sample data to use
	 * 
	 * @param db
	 *            The open database
	 */
	private void seedData(SQLiteDatabase db) {
		db.execSQL("insert into items (item_name, url) values ('Do Bulletproof Backups Require a Disasterproof Drive?', 'http://tidbits.com/article/13529?rss');");
		db.execSQL("insert into items (item_name, url) values ('Selling a Mac', 'http://tidbits.com/article/13498?rss');");

	}

}
