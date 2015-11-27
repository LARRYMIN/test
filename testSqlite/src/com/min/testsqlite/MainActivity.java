package com.min.testsqlite;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.view.Menu;

public class MainActivity extends Activity {

	private final String mTableName="missed";
	private final String TABLE_CREATE = "create table if not exists "	+ mTableName+ " (id INTEGER PRIMARY KEY AUTOINCREMENT, phone_number NUMERIC, type NUMERIC, time TEXT)";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		String[] indexes = new String[0];
		DatabaseHelper dbHelper =   new DatabaseHelper(
				getBaseContext(), 
				mTableName,
				TABLE_CREATE, 
				indexes, 
				1);
		
		
		
		SQLiteDatabase sqlitedb = dbHelper.getReadableDatabase();
		sqlitedb = dbHelper.getReadableDatabase();
		
		//dbHelper.onUpgrade(sqlitedb, 1, 2);
		
		ContentValues args = new ContentValues();
		args.put("phone_number", 12433);
		args.put("type", 0);
		args.put("time", "2014-9-10");
		long l =sqlitedb.insert(mTableName, null, args);
		
		args.put("phone_number", 12435);
		args.put("type", 1);
		args.put("time", "2014/9/11");
		l =sqlitedb.insert(mTableName, null, args);
		
		args.put("phone_number", 12435);
		args.put("type", 0);
		args.put("time", "2014/9/12");
		l =sqlitedb.insert(mTableName, null, args);
		
		args.put("phone_number", 12435);
		args.put("type", 1);
		args.put("time", "2014/9/13");
		l =sqlitedb.insert(mTableName, null, args);
		
		
		Cursor cur = sqlitedb.query(
				mTableName, 
				new String[] { "phone_number", "time"}, 
				"phone_number=? and type = ?" ,
				new String[]{"12435","0"}, 
				null, 
				null, 
				null);
		
		cur.moveToFirst();
		int i =cur.getColumnIndex("time")	;
		int pn = cur.getInt(0);
		String time = cur.getString(i);
		cur.close();
			
		
		
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
