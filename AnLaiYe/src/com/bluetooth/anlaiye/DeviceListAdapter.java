package com.bluetooth.anlaiye;

import java.util.ArrayList;

import com.example.anlaiye.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author 高小黑
 *
 * 2016年5月14日下午8:36:12
 */
public class DeviceListAdapter extends BaseAdapter{

	private ArrayList<Beacon> devices;
	private LayoutInflater layoutInflater;
	private Activity context;
	private ListView listview3;
	
	public DeviceListAdapter(Activity activity){
		super();
		context=activity;
		devices=new ArrayList<Beacon>();//Beacon 集合
		layoutInflater=context.getLayoutInflater();
				
	}
	
	public void addDevice(Beacon device){
		if(device==null)
			return;
			
			for(int i=0;i<devices.size();i++){
				String btAddress=devices.get(i).bluetoothAddress;
				System.out.println("判断是否有重复的蓝牙");
				if(btAddress.equals(device.bluetoothAddress)){
//					devices.add(i,device);
					devices.remove(i);//如果有重复的蓝牙，则只保留一个
				
			}
		}
			devices.add(device);
	}
	
	public Beacon getDevice(int position){
		return devices.get(position);
	}
	
	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return devices.size();
	}
	public void clear(){
		devices.clear();
	}

	@Override
	public Object getItem(int i) {
		// TODO 自动生成的方法存根
		return devices.get(i);
	}

	@Override
	public long getItemId(int i) {
		// TODO 自动生成的方法存根
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		// TODO 自动生成的方法存根
		
		ViewHolder viewholder;
		if(view==null){
			view=layoutInflater.inflate(R.layout.listitem_device, null
					);
			viewholder=new ViewHolder();
			viewholder.deviceAddress=(TextView) view.findViewById(R.id.device_address);
			viewholder.deviceName=(TextView) view.findViewById(R.id.device_name);
			view.setTag(viewholder);
		}else {
			viewholder=(ViewHolder) view.getTag();
		}
		Beacon device=devices.get(i);
		
		final String deviceName=device.name;
		final String deviceAddress=device.bluetoothAddress;
		
		
		if(deviceName!=null && deviceName.length()>0)
		viewholder.deviceName.setText(deviceName);//item中的device_name
		
		else 
			viewholder.deviceName.setText("Unknow service");
			
		viewholder.deviceAddress.setText(deviceAddress);
		
		
	//	System.out.println("蓝牙名称"+deviceName);
	//	System.out.println("蓝牙地址"+deviceAddress);
		return view;
		
	}
	

}
