package com.example.anlaiye;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class RegisterActivity extends Activity {


	private EditText user_name_edit;
	private EditText user_number_edit;
	private EditText password_edit;
	private EditText confirm_password_edit;
	private Button register_button;
	private Button back_login_button;
	private RadioGroup type_rgp;
	private RadioButton type1;
	private RadioButton type2;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
		setTitle("注册账号");
		
		 Intent intent=getIntent();
		user_name_edit=(EditText) findViewById(R.id.user_name_edit);
		user_number_edit=(EditText) findViewById(R.id.user_number_edit);
		password_edit=(EditText) findViewById(R.id.password_edit);
		confirm_password_edit=(EditText) findViewById(R.id.confirm_password_edit);
		register_button=(Button) findViewById(R.id.register_button);
		back_login_button=(Button) findViewById(R.id.back_login_button);
		type_rgp=(RadioGroup) findViewById(R.id.type_rgp);
		type1=(RadioButton) findViewById(R.id.type1);
		type2=(RadioButton) findViewById(R.id.type2);
		
		user_name_edit.setOnFocusChangeListener(new OnFocusChangeListener()
		{

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
				}else{
					String username=user_name_edit.getText().toString().trim();
					if(username.equals("")){
						Toast.makeText(RegisterActivity.this, "用户名不能为空",0).show();
					}
				}
			}
		});
		
		user_number_edit.setOnFocusChangeListener(new OnFocusChangeListener()
		{
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
				}else{
					String userenumber=user_number_edit.getText().toString().trim();
					if(userenumber.equals("")){
						Toast.makeText(RegisterActivity.this, "手机号码不能为空", 0).show();
					}else{
					if(userenumber.length()<11){
						Toast.makeText(RegisterActivity.this, "手机号码错误，请重新填写!", 0).show();
					}else{
							Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
								Matcher m = p.matcher(userenumber);
					}
					}
					
				}
			}
		});
		
		password_edit.setOnFocusChangeListener(new OnFocusChangeListener()
		{
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
				}else{
					String userpwd=password_edit.getText().toString().trim();
					if(userpwd.equals("")||userpwd.length()<6){
						Toast.makeText(RegisterActivity.this, "密码长度应大于6位数", 0).show();
					}
				}
			}
		});
		
		
	confirm_password_edit.setOnFocusChangeListener(new OnFocusChangeListener() {
		
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if(hasFocus){
				
			}else{
				String userpwd=password_edit.getText().toString().trim();
				String repwd=confirm_password_edit.getText().toString().trim();
				if(!userpwd.equals(repwd)){
					Toast.makeText(RegisterActivity.this, "两次的密码不一致！", 0).show();
				}
			}
		}
	});
		
		type_rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				String type=null;
				if(checkedId==type1.getId()){
					type=type1.getText().toString();
					Toast.makeText(RegisterActivity.this, "您的选择是"+type, 0).show();
				}else if(checkedId==type2.getId()){
					type=type2.getText().toString();
					Toast.makeText(RegisterActivity.this, "您的选择是"+type, 0).show();
				}
				
			}
			
		});
		
		back_login_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(RegisterActivity.this, LoginActivity.class);
				startActivity(intent);
			}
		});
	}
    
    public void InsertDB(View v){
 	    String registername = user_name_edit.getText().toString();
 	    String registernumber = user_number_edit.getText().toString();
 	    String registerpwd = password_edit.getText().toString();
 		String registerrepwd = confirm_password_edit.getText().toString();
 	  //得到连接
     	DataBaseHelper dbHelper=new DataBaseHelper(this);
 		SQLiteDatabase database=dbHelper.getWritableDatabase();
 		
         
 		ContentValues values=new ContentValues();
 		values.put("name", registername);
 		values.put("number", registernumber);
 		values.put("pwd", registerpwd);
 		values.put("repwd", registerrepwd);
 		
 		String registertype = null;
		if(type1.isChecked()){
			registertype=type1.getText().toString();
		}else if(type2.isChecked()){
			registertype=type2.getText().toString();
		}
 		
 		values.put("type", registertype);
 		long id=database.insert("UserInfo", null, values);
 		database.close();
 		 new AlertDialog.Builder(RegisterActivity.this)
         .setMessage("注册完成，请返回登录！").setPositiveButton("确定", null)
         .show();
     }

}
