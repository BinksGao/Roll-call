package com.example.anlaiye;

import com.bluetooth.anlaiye.BlueToothActivity;
import com.button.anlaiye.ImageTextButton;
import com.courseinfo.anlaiye.CourseInfoActivity;
import com.info_query.anlaiye.InfoQueryActivity;
import com.scoreinfo.anlaiye.ScoreInfoActivity;
import com.student_info.anlaiye.StudengtInfoActivity;
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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.os.Build;

public class TeacherActivity extends Activity {

	private com.button.anlaiye.ImageTextButton teac_btn1;
	private com.button.anlaiye.ImageTextButton teac_btn2;
	private com.button.anlaiye.ImageTextButton teac_btn3;
	private com.button.anlaiye.ImageTextButton teac_btn4;
	private com.button.anlaiye.ImageTextButton teac_btn5;
	private com.button.anlaiye.ImageTextButton teac_btn6;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teacher);
		setTitle("教师使用");
		init();
	}
	private void init() {
		teac_btn1=(ImageTextButton) findViewById(R.id.teac_btn1);
		teac_btn2=(ImageTextButton) findViewById(R.id.teac_btn2);
		teac_btn3=(ImageTextButton) findViewById(R.id.teac_btn3);
		teac_btn4=(ImageTextButton) findViewById(R.id.teac_btn4);
		teac_btn5=(ImageTextButton) findViewById(R.id.teac_btn5);
		teac_btn6=(ImageTextButton) findViewById(R.id.teac_btn6);
		
		teac_btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(TeacherActivity.this, SuiJiActivity.class);
				startActivity(intent);
			}
			
			
		});
		
		teac_btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(TeacherActivity.this, StudengtInfoActivity.class);
				startActivity(intent);
			}
		});
		
		teac_btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(TeacherActivity.this, CourseInfoActivity.class);
				startActivity(intent);
			}
		});
		
		teac_btn4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(TeacherActivity.this, ScoreInfoActivity.class);
				startActivity(intent);
			}
		});
		
		teac_btn5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(TeacherActivity.this, BlueToothActivity.class);
				startActivity(intent);
			}
		});
		
		teac_btn6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(TeacherActivity.this, InfoQueryActivity.class);
				startActivity(intent);
			}
		});
	}
}
