package com.suiji.anlaiye;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.anlaiye.DataBaseHelper;
import com.example.anlaiye.R;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;



public class QueryActivity extends Activity {
	private ListView listview1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query);
		setTitle("信息查询");
		listview1=(ListView) findViewById(R.id.listView1);
	}
	public void queryDB2(View v){
		
		DataBaseHelper dbHelper=new DataBaseHelper(this);
		SQLiteDatabase database=dbHelper.getWritableDatabase();	
		
		String[] columns = new  String[] { "StuInfo" ,"type" }; 
		Cursor  cursor =database.query("SuiJiInfo", columns, null, null, null, null, null);
		
		if(cursor.getCount()==0){
			Toast.makeText(QueryActivity.this, "缺勤数据库无记录", 0).show();
		}else{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			SimpleAdapter adapter = new SimpleAdapter(QueryActivity.this, list, R.layout.stulist,
					new String[]{ "StuInfo" ,"type"}, 
					new int[]{R.id.StuInfo, R.id.type});
			
			for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
				Map<String, Object> map = new HashMap<String, Object>();
				
				String Stu=cursor.getString(0);
				
				String Type=cursor.getString(1);
				
				
				map.put( "StuInfo", Stu );
				map.put( "type",Type );
				list.add(map);
			}
			listview1.setAdapter(adapter);
		}
		cursor.close();
		
//		database.close();
		
	}  
	
}
