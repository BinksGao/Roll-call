package com.courseinfo.anlaiye;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.anlaiye.DataBaseHelper;
import com.example.anlaiye.R;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.os.Build;

public class CourseInfoActivity extends Activity {
	private EditText ed_coursename;
	private EditText ed_classname;
	private EditText ed_time;
	private EditText ed_address;
	private EditText ed_week;
	private ListView listview4;
	private Button creatgrades;
	private Button addgrades;
	private Button deletegrades;
	private Button updategrades;
	private Button queryDB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_info);
		setTitle("课程信息");
		
		init();
	}
	private void init() {
		ed_coursename=(EditText) findViewById(R.id.ed_coursename);
		ed_classname=(EditText) findViewById(R.id.ed_classname);
		ed_time=(EditText) findViewById(R.id.ed_time);
		ed_week=(EditText) findViewById(R.id.ed_week);
		ed_address=(EditText) findViewById(R.id.ed_address);
		
		listview4=(ListView) findViewById(R.id.listview4);
		
		ed_coursename.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
				}else{
					String name=ed_coursename.getText().toString().trim();
					if(name.equals("")){
						Toast.makeText(CourseInfoActivity.this, "课程名不能为空",0).show();						
					}
				}
			}	
		});
		
		ed_classname.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
				}else{
					String stu_number=ed_classname.getText().toString().trim();
					if(stu_number.equals("")){
						Toast.makeText(CourseInfoActivity.this, "班级不能为空！", 0).show();
					}
				}
				
			}
		});
		
		ed_time.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
				}else{
					String classname=ed_time.getText().toString().trim();
					if(classname.equals("")){
						Toast.makeText(CourseInfoActivity.this, "上课时间不能为空！", 0).show();
					}
				}
			}
		});
		
		ed_week.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
				}else{
					String week=ed_week.getText().toString().trim();
					if(week.equals("")){
						Toast.makeText(CourseInfoActivity.this, "周次不能为空！", 0).show();
					}
				}
			}
		});
		
		ed_address.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
				}else{
					String grade=ed_address.getText().toString().trim();
					if(grade.equals("")){
						Toast.makeText(CourseInfoActivity.this, "上课教室不能为空！", 0).show();
					}
				}
			}
		});
		
	}
	
	public void insertDB(View v) {
		  //得到连接
  	DataBaseHelper dbHelper=new DataBaseHelper(this);
		SQLiteDatabase database=dbHelper.getWritableDatabase();	
		
		String coursename=ed_coursename.getText().toString().trim();
		String time=ed_time.getText().toString().trim();
		String classname=ed_classname.getText().toString().trim();
		String address=ed_address.getText().toString().trim();
		String week=ed_week.getText().toString().trim();
		if(coursename.length()==0||time.length()==0||
				classname.length()==0||address.length()==0){
			Toast.makeText(CourseInfoActivity.this, "请输入完整的信息！", 0).show();
		}else{
			ContentValues values=new ContentValues();
			values.put("coursename", coursename);
			values.put("classname", classname);
			values.put("time", time);
			values.put("week", week);
			values.put("address", address);
			database.insert("CourseInfo", null, values);
			database.close();
			Toast.makeText(CourseInfoActivity.this, "添加课程完成！", 0).show();
//			clearText();
		}				
	}
	private void clearText() {
		ed_coursename.setText("");
		ed_time.setText("");
		ed_classname.setText("");
		ed_address.setText("");
		ed_week.setText("");
		
	}
	public void deleteDB(View v){
		String coursename=ed_coursename.getText().toString().trim();
		String time=ed_time.getText().toString().trim();
		String classname=ed_classname.getText().toString().trim();
		String address=ed_address.getText().toString().trim();
		  //得到连接
    	DataBaseHelper dbHelper=new DataBaseHelper(this);
		SQLiteDatabase database=dbHelper.getWritableDatabase();
		
		String sql="delete from CourseInfo where coursename=? " ;//根据课程名删除课程
		Object args[]=new Object[]{coursename};
		
		database.execSQL(sql,args);
		database.close();
		Toast.makeText(CourseInfoActivity.this, "删除完成！", 0).show();
		clearText();
	}
	public void queryDB(View v){
	   	DataBaseHelper dbHelper=new DataBaseHelper(this);
		SQLiteDatabase database=dbHelper.getWritableDatabase();
		
		String[] columns = new  String[] { "coursename" ,"classname",  "time","address" ,"week"}; 
		
		//String sql="select *from GradesInfo";
		Cursor  cursor =database.query("CourseInfo", columns, null, null, null, null, null);
		if(cursor.getCount()==0){
			Toast.makeText(CourseInfoActivity.this, "数据库无记录", 0).show();
		}else{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		SimpleAdapter adapter = new SimpleAdapter(CourseInfoActivity.this, list, R.layout.courseinfo,
				new String[]{"coursename" ,"classname",  "time","address" ,"week"}, 
				new int[]{R.id.coursename, R.id.classname, R.id.time, R.id.address,R.id.week});
		
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			String coursename=cursor.getString(0);//cursor 游标 保存查询结果
			
			String classname=cursor.getString(1);
			
			String time=cursor.getString(2);
			
			String address=cursor.getString(3);
			
			String week=cursor.getString(4);
			
			map.put( "coursename", coursename );
			map.put( "classname",classname );
			map.put( "time", time );
			map.put( "address", address ); 
			map.put( "week", week ); 
			list.add(map);
		}
		listview4.setAdapter(adapter);
		}
		cursor.close();
		
		database.close();

	}  

}
