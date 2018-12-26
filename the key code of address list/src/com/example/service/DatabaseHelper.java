package com.example.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static int version = 1;

	public DatabaseHelper(Context context) {
		super(context, "maillist.db", null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = new StringBuffer().append("create table t_linkman( ")
				.append("    id integer primary key autoincrement, ")
				.append("    name text, ").append("    phone text, ")
				.append("    QQ text ").append(") ").toString();
		db.execSQL(sql);
		String sql1 = new StringBuffer().append("create table t_linkman1( ")
				.append("    id integer primary key autoincrement, ")
				.append("    name text, ").append("    phone text, ")
				.append("    QQ text ").append(") ").toString();
		db.execSQL(sql1);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
