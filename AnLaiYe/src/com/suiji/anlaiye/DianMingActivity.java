
package com.suiji.anlaiye;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import com.example.anlaiye.DataBaseHelper;
import com.example.anlaiye.R;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
public class DianMingActivity extends Activity {
	private List<String> stuList = null;
//	private Resources r = null;
	private TextView tvName = null;
	private boolean stopped = false;
	Handler handler = null;
	private RadioGroup type_rgp;
	private RadioButton RB1;
	private RadioButton RB2;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_ming);
        tvName = (TextView)findViewById(R.id.tvName);
        setTitle("随机点名");
        type_rgp=(RadioGroup) findViewById(R.id.type_rgp);
		RB1=(RadioButton) findViewById(R.id.RB1);
		RB2=(RadioButton) findViewById(R.id.RB2);
        
type_rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				String type=null;
				if(checkedId==RB1.getId()){
					type=RB1.getText().toString();
				}else if(checkedId==RB2.getId()){
					type=RB2.getText().toString();
				}
			}
		});
		
        tvName.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopped = !stopped;
				if(!stopped)
					handler.postAtTime(new MyThread(), 3000);
			}
		});
        File file=new File(Environment.getExternalStorageDirectory().toString()+
				/*File.separator+"Test"+*/File.separator+"namelist.txt");
        
       FileInputStream stream = null;//文件输入
	try {
		stream = new FileInputStream(file);
	} catch (FileNotFoundException e1) {
		// TODO 自动生成的 catch 块
		e1.printStackTrace();
	}
        try {
			stuList = readInfo(stream);
			final int stuCount = stuList.size();
			if(stuCount == 0) return;
			final Random rand = new Random();//产生一个随机数
			handler = new Handler(){ //通过Handler类，可以提交和处理一个Runnable对象
				@Override         
				public void handleMessage(Message msg) {
				
					if(stopped) return;
					if(msg.what == 0x1234){
						tvName.setText(stuList.get(rand.nextInt(stuCount)));
					}
				}
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
		handler.postAtTime(new MyThread(), 5000);////在指定时间后创建新线程执行Runnable对象  
    }
    
	class MyThread implements Runnable {//通过实现接口的方式创建新线程
		@Override
		public void run() {
			Message msg = new Message();//
			msg.what = 0x1234;//what：消息的编码；
			handler.sendMessage(msg); //Handler 在发送消息的时候，需要发送一个新的对象
			handler.post(new MyThread());////立即执行Runnable对象  
			if(stopped)
				handler.removeCallbacks(new MyThread());//取消获取消息被处理时要执行的回调对象
		}
	}
    
    private List<String> readInfo(InputStream stream) throws IOException{
    	List<String> lst = new ArrayList<String>();
    	BufferedReader br = null;
    	try{
    		br = new BufferedReader(new InputStreamReader(stream,"GBK"));
    		String str = null;
    		while((str = br.readLine()) != null){//读取文本行
    			lst.add(str);
    		}
        	return lst;
    	}finally{
    		if(br!= null) br.close();
    	}
    }
    
    public void  SaveDB(View v){
    	
    	DataBaseHelper dbHelper=new DataBaseHelper(this);
 		SQLiteDatabase database=dbHelper.getWritableDatabase();
 		
 		String stuInfo = tvName.getText().toString();
         
 		ContentValues values=new ContentValues();
 		values.put("StuInfo", stuInfo);
    	 String type = null;
 		if(RB1.isChecked()){
 			type=RB1.getText().toString();
 		}else if(RB2.isChecked()){
 			type=RB2.getText().toString();
 		}
 		values.put("type", type);
 		
 		long id=database.insert("SuiJiInfo", null, values);
 		database.close();
 		
 		System.out.println(stuInfo);
 		System.out.println(type);
    }
    
    public void QueryDB(View v){
    	Intent intent=new Intent();
		intent.setClass(DianMingActivity.this, QueryActivity.class);
		
		startActivity(intent);
    }
}