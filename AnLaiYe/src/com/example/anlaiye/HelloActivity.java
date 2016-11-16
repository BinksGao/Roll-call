package com.example.anlaiye;

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
import android.widget.Toast;
import android.os.Build;

public class HelloActivity extends Activity {
	private Button btn_hello;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hello);
		setTitle("欢迎使用");
		btn_hello=(Button) findViewById(R.id.btn_hello);
		btn_hello.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent();
				intent.setClass(HelloActivity.this, LoginActivity.class);
				startActivity(intent);
			}
		});
		Toast.makeText(this, "系统初始化中,请稍后", 0).show();
		
	}

}
