package com.bluetooth.anlaiye;
/**
 *  @author 高小黑
 *
 * 2016年5月16日下午8:29:56
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.anlaiye.DataBaseHelper;
import com.example.anlaiye.R;
import com.example.anlaiye.TeacherActivity;
import com.scoreinfo.anlaiye.ScoreInfoActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class BlueToothActivity extends Activity implements OnBluetoothListener{
	private DeviceListAdapter deviceListAdapter;
	private Builder tipBuilder;
	private ListView listview3;
	private final static String TAG = BlueToothActivity.class.getSimpleName();
	private BluetoothAdapter localBluetoothAdapter;
	private Handler handler;
	private ProgressDialog searchDialog;
	private BluetoothBroadcastReceiver bluetoothBroadcastReceiver;
	private ArrayList<Beacon> devices;
	ViewHolder viewholder ;
	private Button saveDB,queryDB,queryDB2;
	
	private Runnable connectRunnable=new Runnable() {
		
		@Override
		public void run() {
			// TODO 自动生成的方法存根
			if(localBluetoothAdapter!=null && localBluetoothAdapter.isEnabled()==true){
				connectBluetooth();
				System.out.println("判断蓝牙是否打开");
			}
		}

		private void connectBluetooth() {
			// TODO 自动生成的方法存根
			searchDialog=new ProgressDialog(BlueToothActivity.this);
			searchDialog.setMessage("正在搜索蓝牙...");
			searchDialog.setCancelable(false);
			searchDialog.show();
			System.out.println("正在搜索蓝牙");
			IntentFilter intentFilter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
			BluetoothBroadcastReceiver.onBluetoothListener=BlueToothActivity.this;
			bluetoothBroadcastReceiver=new BluetoothBroadcastReceiver();
			registerReceiver(bluetoothBroadcastReceiver, intentFilter);
			new Thread(){
				public void run(){
					LogUtil.i(TAG,"已经开始搜索...");
					System.out.println("已经开始搜索");
					localBluetoothAdapter.startDiscovery();
				}
			}.start();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blue_tooth);
		init();
		
		new Handler().post(connectRunnable);
		running();
	
	}

	

	private void running() {
		// TODO 自动生成的方法存根
		handler=new Handler();
		localBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
		System.out.println("蓝牙设备已开启");
		localBluetoothAdapter.enable();
		Toast.makeText(getApplicationContext(), "蓝牙设备已开启...", Toast.LENGTH_SHORT).show();
	}
	

	private void init() {
		// TODO 自动生成的方法存根
		listview3=(ListView) findViewById(R.id.listview3);
		deviceListAdapter=new DeviceListAdapter(this);
		listview3.setAdapter(deviceListAdapter);
		handler=new Handler();
	}

	@Override
	public void onSearch(BluetoothDevice device) {
		// TODO 自动生成的方法存根
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				if(searchDialog!=null){
					searchDialog.dismiss();
					searchDialog=null;
				}
				
				System.out.println("扫描到蓝牙设备");
			
			}
		});
		final Beacon beacon=new Beacon(device.getName(),device.getAddress());
		deviceListAdapter.addDevice(beacon);
		deviceListAdapter.notifyDataSetChanged();//更新listview界面
		
	}
   
	@Override
	public void onSearchOver() {
		// TODO 自动生成的方法存根
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				if(searchDialog!=null){
					searchDialog.dismiss();
					System.out.println("没有找到蓝牙");
					Toast.makeText(getApplicationContext(), "未找到蓝牙设备", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO 自动生成的方法存根
		unregisterReceiver(bluetoothBroadcastReceiver);//销毁蓝牙广播
		System.out.println("蓝牙广播销毁");
		super.onDestroy();
	}
	
	public void saveDB(View v) {
		
		DataBaseHelper dbHelper=new DataBaseHelper(this);
		SQLiteDatabase database=dbHelper.getWritableDatabase();	
		
		String[] columns = new  String[] { "deviceName" ,"deviceAddress" }; 
		Cursor  cursor =database.query("BluetoothInfo", null, null, null, null, null, null);
		
		for(int i=0;i<listview3.getCount();i++){
			LinearLayout linearLayout=(LinearLayout) listview3.getAdapter().getView(i, null, null);
			TextView deviceName=(TextView) linearLayout.getChildAt(0);
			TextView deviceAddress=(TextView) linearLayout.getChildAt(1);
			
			String name=deviceName.getText().toString();
			String address=deviceAddress.getText().toString();
			
			System.out.println("扫描蓝牙名称"+name);
			System.out.println("扫描蓝牙地址"+address);
		
		if(cursor.getCount()==0){
			System.out.println("数据库无蓝牙设备记录");
				
				ContentValues values=new ContentValues();
				
				values.put("deviceName", name);
				values.put("deviceAddress", address);
				database.insert("BluetoothInfo", null, values);
				System.out.println("搜索蓝牙数据已添加成功！");
				System.out.println("添加的蓝牙名称为："+name);
				System.out.println("添加的蓝牙地址为："+address );
				Toast.makeText(BlueToothActivity.this, "搜索蓝牙数据已添加成功！", 0).show();
		}else {
			
			System.out.println(cursor.getCount());
			
			while(cursor.moveToNext()){
				
			String QueryName=cursor.getString(1);
			String QueryAddress=cursor.getString(2);
			
			if(!address.equals(QueryAddress)){
			
			ContentValues values=new ContentValues();
			
			values.put("deviceName", QueryName);
			values.put("deviceAddress", QueryAddress);
			
			database.insert("AbsenceInfo", null, values);
			Toast.makeText(BlueToothActivity.this, "缺勤蓝牙数据已添加成功！", 0).show();
			}
			}
			}
		cursor.close();
		}
		database.close();
	} 
	public void queryDB(View v){
		
		DataBaseHelper dbHelper=new DataBaseHelper(this);
		SQLiteDatabase database=dbHelper.getWritableDatabase();	
		String[] columns = new  String[] { "deviceName" ,"deviceAddress" }; 
		Cursor  cursor =database.query("BluetoothInfo", columns, null, null, null, null, null);
		
		if(cursor.getCount()==0){
			Toast.makeText(BlueToothActivity.this, "学生数据库无记录", 0).show();
		}else{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		SimpleAdapter adapter = new SimpleAdapter(BlueToothActivity.this, list, R.layout.listitem_device,
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
	
	public void queryDB2(View v){
		
		DataBaseHelper dbHelper=new DataBaseHelper(this);
		SQLiteDatabase database=dbHelper.getWritableDatabase();	
		String[] columns = new  String[] { "deviceName" ,"deviceAddress" }; 
		Cursor  cursor =database.query("AbsenceInfo", columns, null, null, null, null, null);
		
		if(cursor.getCount()==0){
			Toast.makeText(BlueToothActivity.this, "缺勤数据库无记录", 0).show();
		}else{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			SimpleAdapter adapter = new SimpleAdapter(BlueToothActivity.this, list, R.layout.listitem_device,
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
