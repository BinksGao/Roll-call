package com.info_query.anlaiye;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluetooth.anlaiye.BlueToothActivity;
import com.example.anlaiye.DataBaseHelper;
import com.example.anlaiye.R;

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

public class AbsenceInfoQuery extends Activity {
	private Button QueryDB;
	private ListView listview3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_absence_info_query);
		setTitle("缺勤查询");
		listview3=(ListView) findViewById(R.id.listview3);
	}
	public void queryDB2(View v){
		
		DataBaseHelper dbHelper=new DataBaseHelper(this);
		SQLiteDatabase database=dbHelper.getWritableDatabase();	
		String[] columns = new  String[] { "deviceName" ,"deviceAddress" }; 
		Cursor  cursor =database.query("AbsenceInfo", columns, null, null, null, null, null);
		
		if(cursor.getCount()==0){
			Toast.makeText(AbsenceInfoQuery.this, "缺勤数据库无记录", 0).show();
		}else{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			SimpleAdapter adapter = new SimpleAdapter(AbsenceInfoQuery.this, list, R.layout.listitem_device,
					new String[]{"deviceName", "deviceAddress"}, 
					new int[]{R.id.device_name, R.id.device_address});
			
			for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
				Map<String, Object> map = new HashMap<String, Object>();
				
				String Name=cursor.getString(0);
				
				String Address=cursor.getString(1);
				
				
				map.put( "deviceName", Name );
				map.put( "deviceAddress",Address );
				list.add(map);
			}
			listview3.setAdapter(adapter);
		}
		cursor.close();
		
		database.close();
		
	}  
	
}
