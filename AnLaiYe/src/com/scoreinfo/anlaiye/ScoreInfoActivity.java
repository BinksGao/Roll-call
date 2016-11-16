package com.scoreinfo.anlaiye;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.anlaiye.DataBaseHelper;
import com.example.anlaiye.R;
import com.student_info.anlaiye.StudengtInfoActivity;

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

public class ScoreInfoActivity extends Activity {

	private EditText ed_stu_name;
	private EditText ed_stu_number;
	private EditText ed_classname;
	private EditText ed_grades;
	private ListView listview;
	private Button creatgrades;
	private Button addgrades;
	private Button deletegrades;
	private Button updategrades;
	
	private Button queryDB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_info);
		setTitle("成绩信息");

		
		init();
	}
		
			public void init() {
				ed_stu_name=(EditText) findViewById(R.id.ed_stu_name);
				ed_stu_number=(EditText) findViewById(R.id.ed_stu_number);
				ed_classname=(EditText) findViewById(R.id.ed_classname);
				ed_grades=(EditText) findViewById(R.id.ed_grade);
			
				listview=(ListView) findViewById(R.id.listview1);
				ed_stu_name.setOnFocusChangeListener(new OnFocusChangeListener() {
					
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						if(hasFocus){
						}else{
							String name=ed_stu_name.getText().toString().trim();
							if(name.equals("")){
								Toast.makeText(ScoreInfoActivity.this, "学生姓名不能为空",0).show();						
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
								Toast.makeText(ScoreInfoActivity.this, "学号不能为空！", 0).show();
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
								Toast.makeText(ScoreInfoActivity.this, "课程名称不能为空！", 0).show();
							}
						}
					}
				});
				
				ed_grades.setOnFocusChangeListener(new OnFocusChangeListener() {
					
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						if(hasFocus){
						}else{
							String grade=ed_grades.getText().toString().trim();
							if(grade.equals("")){
								Toast.makeText(ScoreInfoActivity.this, "课程名称不能为空！", 0).show();
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
				String grades=ed_grades.getText().toString().trim();
				if(stu_name.length()==0||stu_number.length()==0||
						classname.length()==0||grades.length()==0){
					Toast.makeText(ScoreInfoActivity.this, "请输入完整的信息！", 0).show();
				}else{
					ContentValues values=new ContentValues();
					values.put("name", stu_name);
					values.put("stu_number", stu_number);
					values.put("classname", classname);
					values.put("grade", grades);
					database.insert("GradesInfo", null, values);
					database.close();
					Toast.makeText(ScoreInfoActivity.this, "添加成绩完成！", 0).show();
//					clearText();
				 
				
				}				
			}
			
			private void clearText() {
				ed_stu_name.setText("");
				ed_stu_number.setText("");
				ed_classname.setText("");
				ed_grades.setText("");
				
			}
			public void deleteDB(View v){
				String name=ed_stu_name.getText().toString().trim();
				String stu_number=ed_stu_number.getText().toString().trim();
				String classname=ed_classname.getText().toString().trim();
				String grade=ed_grades.getText().toString().trim();
				  //得到连接
		    	DataBaseHelper dbHelper=new DataBaseHelper(this);
				SQLiteDatabase database=dbHelper.getWritableDatabase();
				
				String sql="delete from GradesInfo where stu_number=? and name=? and classname=? " ;//根据学号、姓名、课程删除成绩
				Object args[]=new Object[]{stu_number,name,classname};
				
				database.execSQL(sql,args);
				database.close();
				Toast.makeText(ScoreInfoActivity.this, "删除完成！", 0).show();
				clearText();
			}
			public void updateDB(View v){
				  //得到连接
		    	DataBaseHelper dbHelper=new DataBaseHelper(this);
				SQLiteDatabase database=dbHelper.getWritableDatabase();
				String name=ed_stu_name.getText().toString().trim();
				String stu_number=ed_stu_number.getText().toString().trim();
				String classname=ed_classname.getText().toString().trim();
				String grade=ed_grades.getText().toString().trim();
				//更改成绩
				String sql="update GradesInfo set grade="+grade+" where stu_number=? and name=? and classname=?  ";//根据学号、姓名、课程更新成绩
				Object args[]=new Object[]{stu_number,name,classname};
				

				database.execSQL(sql,args);
				database.close();
				Toast.makeText(ScoreInfoActivity.this, "更新完成！", 0).show();
			}
			

			public void queryDB(View v){
			   	DataBaseHelper dbHelper=new DataBaseHelper(this);
				SQLiteDatabase database=dbHelper.getWritableDatabase();
				
				String[] columns = new  String[] { "stu_number" ,"name",  "classname","grade" }; 
				
				//String sql="select *from StudentInfo";
				Cursor  cursor =database.query("GradesInfo", columns, null, null, null, null, null);
				if(cursor.getCount()==0){
					Toast.makeText(ScoreInfoActivity.this, "数据库无记录", 0).show();
				}else{
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				SimpleAdapter adapter = new SimpleAdapter(ScoreInfoActivity.this, list, R.layout.stu_item,
        				new String[]{"stu_number", "stu_name", "classname", "grade"}, 
        				new int[]{R.id.stu_number, R.id.stu_name, R.id.classname, R.id.grade});
				
				for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
        			Map<String, Object> map = new HashMap<String, Object>();
        			
        			String number=cursor.getString(0);
        			
        			String name=cursor.getString(1);
        			
        			String classname=cursor.getString(2);
        			
        			String grade=cursor.getString(3);
        			
        			map.put( "stu_number", number );
        			map.put( "stu_name",name );
        			map.put( "classname", classname );
        			map.put( "grade", grade ); 
        			list.add(map);
        		}
				listview.setAdapter(adapter);
				}
				cursor.close();
				
				database.close();

			}  
}



