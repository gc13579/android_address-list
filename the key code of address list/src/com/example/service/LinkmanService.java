package com.example.service;

import java.util.ArrayList;
import java.util.List;

import com.example.enity.Linkman;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LinkmanService {
	private DatabaseHelper databaseHelper;
	private DatabaseHelper databaseHelper1;
	public LinkmanService(Context context) {
		databaseHelper=new DatabaseHelper(context);
		databaseHelper1=new DatabaseHelper(context);
	}
	public void insert(Linkman linkman){
		SQLiteDatabase db=databaseHelper.getWritableDatabase();
		
		String sql=new StringBuffer()
			.append("insert into t_linkman ")
			.append("    (name,phone,QQ) ")
			.append("values ")
			.append("    (?,?,?) ")
			.toString();
		db.execSQL(sql,new Object[]{linkman.getName(),linkman.getPhone(),linkman.getQQ()});
	}	
	public void insert1(Linkman linkman){
		SQLiteDatabase db=databaseHelper1.getWritableDatabase();
		String sql=new StringBuffer()
			.append("insert into t_linkman1 ")
			.append("    (name,phone,QQ) ")
			.append("values ")
			.append("    (?,?,?) ")
			.toString();
		db.execSQL(sql,new Object[]{linkman.getName(),linkman.getPhone(),linkman.getQQ()});
		

	}	
	public List<Linkman> selectAll(){	
		List<Linkman> linkmen=new ArrayList<Linkman>();
		SQLiteDatabase db=databaseHelper.getWritableDatabase();
		String sql=new StringBuffer()
			.append("select * ")
			.append("from t_linkman ")
			.append("order by name ")
			.toString();		
		Cursor cursor=db.rawQuery(sql,null);		
		while(cursor.moveToNext()){
			Linkman linkman=new Linkman();
			linkman.setId(cursor.getInt(0));
			linkman.setName(cursor.getString(1));
			linkman.setPhone(cursor.getString(2));
			linkman.setQQ(cursor.getString(3));
			
			linkmen.add(linkman);
		}
		
		return linkmen;
	}
	public List<Linkman> selectAll1(){	
		List<Linkman> linkmen=new ArrayList<Linkman>();
		SQLiteDatabase db=databaseHelper1.getWritableDatabase();
		String sql=new StringBuffer()
			.append("select * ")
			.append("from t_linkman1 ")
			.append("order by id desc")
			.toString();		
		Cursor cursor=db.rawQuery(sql,null);		
		while(cursor.moveToNext()){
			Linkman linkman=new Linkman();
			linkman.setId(cursor.getInt(0));
			linkman.setName(cursor.getString(1));
			linkman.setPhone(cursor.getString(2));
			linkman.setQQ(cursor.getString(3));
			
			linkmen.add(linkman);
		}
		
		return linkmen;
	}
	
	
	public Linkman findById(Integer id){
		SQLiteDatabase db=databaseHelper.getWritableDatabase();
		String sql=new StringBuffer()
			.append("select * ")
			.append("from t_linkman ")
			.append("where id=? ")
			.toString();
		
		Cursor cursor=db.rawQuery(sql, new String[]{id+""});
		Linkman linkman=null;
		
		if(cursor.moveToNext()){
			linkman=new Linkman();
			linkman.setId(cursor.getInt(0));
			linkman.setName(cursor.getString(1));
			linkman.setPhone(cursor.getString(2));
			linkman.setQQ(cursor.getString(3));
		}
		
		return linkman;
	}
	public Linkman findById1(Integer id){
		SQLiteDatabase db=databaseHelper1.getWritableDatabase();
		String sql=new StringBuffer()
			.append("select * ")
			.append("from t_linkman1 ")
			.append("where id=? ")
			.toString();
		
		Cursor cursor=db.rawQuery(sql, new String[]{id+""});
		Linkman linkman=null;
		
		if(cursor.moveToNext()){
			linkman=new Linkman();
			linkman.setId(cursor.getInt(0));
			linkman.setName(cursor.getString(1));
			linkman.setPhone(cursor.getString(2));
			linkman.setQQ(cursor.getString(3));
		}
		
		return linkman;
	}
//	public Linkman findByPhone1(String phone){
//		SQLiteDatabase db=databaseHelper1.getWritableDatabase();
//		String sql=new StringBuffer()
//			.append("select * ")
//			.append("from t_linkman1 ")
//			.append("where phone=? ")
//			.toString();
//		
//		Cursor cursor=db.rawQuery(sql, new String[]{phone});
//		Linkman linkman=null;
//		
//		if(cursor.moveToNext()){
//			linkman=new Linkman();
//			linkman.setId(cursor.getInt(0));
//			linkman.setName(cursor.getString(1));
//			linkman.setPhone(cursor.getString(2));
//			linkman.setQQ(cursor.getString(3));
//		}
//		
//		return linkman;
//	}
	
	
	public void removeById(Integer id){
		SQLiteDatabase db=databaseHelper.getWritableDatabase();
		String sql=new StringBuffer()
			.append("delete from t_linkman ")
			.append("where id=? ")
			.toString();
		db.execSQL(sql, new Object[]{id});
	}
	public void removeById1(Integer id){
		SQLiteDatabase db=databaseHelper1.getWritableDatabase();
		String sql=new StringBuffer()
			.append("delete from t_linkman1 ")
			.append("where id=? ")
			.toString();
		db.execSQL(sql, new Object[]{id});
	}
	
	
	public void modify(Linkman linkman){
		SQLiteDatabase db=databaseHelper.getWritableDatabase();
		String sql=new StringBuffer()
			.append("update t_linkman ")
			.append("set name=?, ")
			.append("    phone=?, ")
			.append("    QQ=? ")
			.append("where id=? ")
			.toString();
		db.execSQL(sql,new Object[]{linkman.getName(),linkman.getPhone(),linkman.getQQ(),linkman.getId()});
	}
	
	
}
