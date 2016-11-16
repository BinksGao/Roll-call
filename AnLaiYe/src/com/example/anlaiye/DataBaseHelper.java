package com.example.anlaiye;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author 高小黑
 *数据库创建的帮助类
 * 2016年4月26日上午10:31:39
 */
public class DataBaseHelper extends SQLiteOpenHelper {
	public SQLiteDatabase db;
	//创建数据库
	public DataBaseHelper(Context context) {
		super(context, "DemoDB", null, 1);
	}
	//当数据库文件创建时调用一次
	//建表
	//插入初始化数据
	@Override
	public void onCreate(SQLiteDatabase db) {
		//创建数据表
		String sql1="create table UserInfo (_id integer primary key ,name varchar(20),number varchar(50),pwd varchar(50),repwd varchar(50),type varchar(50))";
		String sql2="create table GradesInfo (_id integer primary key ,stu_number varchar(20),name varchar(50),classname varchar(50),grade varchar(50))";
		String sql3="create table StudentInfo (_id integer primary key ,stu_number varchar(20),name varchar(50),classname varchar(50),sex varchar(50))";
		String sql4="create table CourseInfo (_id integer primary key ,coursename varchar(20),classname varchar(50),time varchar(50),address varchar(50),week varchar(50))";
		String sql5="create table BluetoothInfo (_id integer primary key ,deviceName varchar(20),deviceAddress varchar(50))";
		String sql6="create table AbsenceInfo (_id integer primary key ,deviceName varchar(20),deviceAddress varchar(50))";
		String sql7="create table SuiJiInfo (_id integer primary key ,StuInfo varchar(50),type varchar(20))";

		db.execSQL(sql1);
		
		db.execSQL(sql2);
		db.execSQL(sql3);
		db.execSQL(sql4);
		db.execSQL(sql5);
		db.execSQL(sql6);
		db.execSQL(sql7);
		
	}
	
	//版本更新时调用
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	

}
