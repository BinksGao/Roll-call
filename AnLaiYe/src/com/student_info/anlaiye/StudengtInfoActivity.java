package com.student_info.anlaiye;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.anlaiye.DataBaseHelper;
import com.example.anlaiye.R;

import android.animation.AnimatorSet.Builder;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.DeniedByServerException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class StudengtInfoActivity extends Activity {

	private EditText ed_stu_name;
	private EditText ed_stu_number;
	private EditText ed_classname;
	private EditText ed_sex;
	private ListView listview;
	
	private Button queryDB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_studengt_info);
		setTitle("学生信息");

		
		init();
	}
		
			public void init() {
				ed_stu_name=(EditText) findViewById(R.id.ed_stu_name);
				ed_stu_number=(EditText) findViewById(R.id.ed_stu_number);
				ed_classname=(EditText) findViewById(R.id.ed_classname);
				ed_sex=(EditText) findViewById(R.id.ed_sex);
				listview=(ListView) findViewById(R.id.listview1);
				ed_stu_name.setOnFocusChangeListener(new OnFocusChangeListener() {
					
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						if(hasFocus){
						}else{
							String name=ed_stu_name.getText().toString().trim();
							if(name.equals("")){
								Toast.makeText(StudengtInfoActivity.this, "学生姓名不能为空",0).show();						
							}
						}
					}	
				});
				
				ed_stu_number.setOnFocusChangeListener(new OnFocusChangeListener() {
					
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						if(hasFocus){
						}else{
							String stu_number=ed_stu_number.getText().toString().trim();
							if(stu_number.equals("")){
								Toast.makeText(StudengtInfoActivity.this, "学号不能为空！", 0).show();
							}
						}
						
					}
				});
				
				ed_classname.setOnFocusChangeListener(new OnFocusChangeListener() {
					
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						if(hasFocus){
						}else{
							String classname=ed_classname.getText().toString().trim();
							if(classname.equals("")){
								Toast.makeText(StudengtInfoActivity.this, "班级名称不能为空！", 0).show();
							}
						}
					}
				});
				
			ed_sex.setOnFocusChangeListener(new OnFocusChangeListener() {
					
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						if(hasFocus){
						}else{
							String grade=ed_sex.getText().toString().trim();
							if(grade.equals("")){
								Toast.makeText(StudengtInfoActivity.this, "性别不能为空！", 0).show();
							}
						}
					}
				});  
				
		   
				
			}
			public void CreatDB(View v){
				  //得到连接
		    	DataBaseHelper dbHelper=new DataBaseHelper(this);
				SQLiteDatabase database=dbHelper.getWritableDatabase();	
			}
			public void insertDB(View v) {
				  //得到连接
		    	DataBaseHelper dbHelper=new DataBaseHelper(this);
				SQLiteDatabase database=dbHelper.getWritableDatabase();	
				
				String stu_name=ed_stu_name.getText().toString().trim();
				String stu_number=ed_stu_number.getText().toString().trim();
				String classname=ed_classname.getText().toString().trim();
				String sex=ed_sex.getText().toString().trim();
				if(stu_name.length()==0||stu_number.length()==0||
						classname.length()==0||sex.length()==0){
					Toast.makeText(StudengtInfoActivity.this, "请输入完整的信息！", 0).show();
				}else{
					ContentValues values=new ContentValues();
					values.put("name", stu_name);
					values.put("stu_number", stu_number);
					values.put("classname", classname);
					values.put("sex", sex);
					database.insert("StudentInfo", null, values);
					database.close();
					Toast.makeText(StudengtInfoActivity.this, "添加信息完成！", 0).show();
				 
				
				}				
			}
			

			public void deleteDB(View v){
				String name=ed_stu_name.getText().toString().trim();
				String stu_number=ed_stu_number.getText().toString().trim();
				String classname=ed_classname.getText().toString().trim();
				String sex=ed_sex.getText().toString().trim();
				  //得到连接
		    	DataBaseHelper dbHelper=new DataBaseHelper(this);
				SQLiteDatabase database=dbHelper.getWritableDatabase();
				
				String sql="delete from StudentInfo where stu_number=? and name=?  " ;//根据学号和姓名删除
				Object args[]=new Object[]{stu_number,name};
				
				database.execSQL(sql,args);
				database.close();
				Toast.makeText(StudengtInfoActivity.this, "删除完成！", 0).show();
				//clearText();
			}
			public void updateDB(View v){
				  //得到连接
		    	DataBaseHelper dbHelper=new DataBaseHelper(this);
				SQLiteDatabase database=dbHelper.getWritableDatabase();
				String name=ed_stu_name.getText().toString().trim();
				String stu_number=ed_stu_number.getText().toString().trim();
			//	String classname=ed_classname.getText().toString().trim();
				String sex=ed_sex.getText().toString().trim();
				String sql="update StudentInfo set sex=? where stu_number=? ";//根据学号进行更新
				Object args[]=new Object[]{sex,stu_number};

				database.execSQL(sql,args);
				database.close();
				Toast.makeText(StudengtInfoActivity.this, "更新完成！", 0).show();
			}
			
		
			public void queryDB(View v){
			   	DataBaseHelper dbHelper=new DataBaseHelper(this);
				SQLiteDatabase database=dbHelper.getWritableDatabase();
				
				String[] columns = new  String[] { "stu_number" ,"name",  "classname","sex" }; 
				
				//String sql="select *from StudentInfo";
				Cursor  cursor =database.query("StudentInfo", columns, null, null, null, null, null);
				if(cursor.getCount()==0){
					Toast.makeText(StudengtInfoActivity.this, "数据库无记录", 0).show();
				}else{
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				SimpleAdapter adapter = new SimpleAdapter(StudengtInfoActivity.this, list, R.layout.stu_info_item,
        				new String[]{"stu_number", "stu_name", "classname", "sex"}, 
        				new int[]{R.id.stu_number, R.id.stu_name, R.id.classname, R.id.sex});
				
				for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
        			Map<String, Object> map = new HashMap<String, Object>();
        			
        			String number=cursor.getString(0);
        			
        			String name=cursor.getString(1);
        			
        			String coures=cursor.getString(2);
        			
        			String sex=cursor.getString(3);
        			
        			map.put( "stu_number", number );
        			map.put( "stu_name",name );
        			map.put( "classname", coures );
        			map.put( "sex", sex ); 
        			list.add(map);
        		}
				listview.setAdapter(adapter);
				}
				cursor.close();
				
				database.close();

			}  

		
}

 

