package com.info_query.anlaiye;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.anlaiye.DataBaseHelper;
import com.example.anlaiye.R;
import com.scoreinfo.anlaiye.ScoreInfoActivity;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.os.Build;

public class GradeQuery extends Activity {
	private Button queryDB;
	private ListView listview1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grade_query);
		setTitle("成绩查询");
		listview1=(ListView) findViewById(R.id.listview1);
	}

	public void queryDB(View v){
	   	DataBaseHelper dbHelper=new DataBaseHelper(this);
		SQLiteDatabase database=dbHelper.getWritableDatabase();
		
		String[] columns = new  String[] { "stu_number" ,"name",  "classname","grade" }; 
		
		//String sql="select *from GradesInfo";
		Cursor  cursor =database.query("GradesInfo", columns, null, null, null, null, null);
		if(cursor.getCount()==0){
			Toast.makeText(GradeQuery.this, "数据库无记录", 0).show();
		}else{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		SimpleAdapter adapter = new SimpleAdapter(GradeQuery.this, list, R.layout.stu_item,
				new String[]{"stu_number", "stu_name", "classname", "grade"}, 
				new int[]{R.id.stu_number, R.id.stu_name, R.id.classname, R.id.grade});
		
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			String number=cursor.getString(0);
			
			String name=cursor.getString(1);
			
			String coures=cursor.getString(2);
			
			String grade=cursor.getString(3);
			
			map.put( "stu_number", number );
			map.put( "stu_name",name );
			map.put( "classname", coures );
			map.put( "grade", grade ); 
			list.add(map);
		}
		listview1.setAdapter(adapter);
		}
		cursor.close();
		
		database.close();

	}  
}
