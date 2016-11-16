package com.info_query.anlaiye;

import com.example.anlaiye.R;
import com.example.anlaiye.TeacherActivity;
import com.suiji.anlaiye.SuiJiActivity;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class InfoQueryActivity extends Activity {
private Button gradeinfo,stu_info,absense;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_query);
		setTitle("信息查询");
		init();
	}
	private void init() {
		gradeinfo=(Button) findViewById(R.id.gradeinfo);
		stu_info=(Button) findViewById(R.id.stu_info);
		absense=(Button) findViewById(R.id.absense);
		
		gradeinfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(InfoQueryActivity.this, GradeQuery.class);
				startActivity(intent);				
			}
		});
		
		stu_info.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent();
				intent.setClass(InfoQueryActivity.this, StuInfoQuery.class);
				startActivity(intent);	
			}
		});
		
		absense.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent();
				intent.setClass(InfoQueryActivity.this, AbsenceInfoQuery.class);
				startActivity(intent);
			}
		});
	}
	
	

}
