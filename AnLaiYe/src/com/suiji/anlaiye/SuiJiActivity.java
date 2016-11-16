package com.suiji.anlaiye;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.anlaiye.R;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SuiJiActivity extends Activity {
    /** Called when the activity is first created. */
	
	ArrayList<HashMap<String,String>> al;
	ListView lv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sui_ji);
		setTitle("随机点名");
        lv=(ListView) findViewById(R.id.listview1);
        al=new ArrayList<HashMap<String,String>>();//数组集合
        InputStream is=null;
        File file=new File(Environment.getExternalStorageDirectory().toString()+
				/*File.separator+"Test"+*/File.separator+"data.xls");//创建文件对象  sd卡下的文件
        
        try {
        	FileInputStream in=new FileInputStream(file);
			Workbook wb=Workbook.getWorkbook(in);//得到文件对
			Sheet sheet=wb.getSheet(0);//getSheet(i)只能读取一页
			int row=sheet.getRows();//得到excel表的行数
			HashMap<String,String> hm;
			for(int i=0;i<row;++i)//遍历excel表
			{
				Cell cellnumber=sheet.getCell(0, i); //第i行的第0列 
				Cell cellname=sheet.getCell(1, i);//第i行的第1列 
				System.out.println(cellnumber.getContents()+":"+cellname.getContents());//取得单元格内容
				hm=new HashMap<String,String>();
				hm.put("Number", cellnumber.getContents());  
				hm.put("Name", cellname.getContents());
				al.add(hm);
			}
			SimpleAdapter sa=new SimpleAdapter(this,al,R.layout.lv_item,new String[]{"Number","Name"},new int[]{R.id.tv_number,R.id.tv_name});
			lv.setAdapter(sa);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
  public void DianMing(View v){
	  Intent intent=new Intent();
		intent.setClass(SuiJiActivity.this, DianMingActivity.class);
		startActivity(intent);
  }
}