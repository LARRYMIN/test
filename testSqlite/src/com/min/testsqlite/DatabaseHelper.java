package com.min.testsqlite;

import java.util.Date;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper
{
	private String mCreateDB = "";
	private String[] mIndexes = null;
	private String mDBName = "";
	public DatabaseHelper (Context context, String dbName, String createDB, String[] indexes, int version)
	{
		super(context, dbName, null, version);
		mCreateDB=createDB;
		mDBName = dbName;
		mIndexes = indexes;
	}
	
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(mCreateDB);
		if(mIndexes!=null)
		{
			for(int i=0;i<mIndexes.length;i++)
			{
				db.execSQL(mIndexes[i]);
			}
		}
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) 
	{
		Log.w("DatabaseHelper", "Upgrading database from version " + oldVersion 
				+ " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS "+mDBName);
		onCreate(db);
	}
}
	
	