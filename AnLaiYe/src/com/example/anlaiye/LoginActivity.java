package com.example.anlaiye;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private Button btn_zhuce;
	private Button btn_cancer;
	private Button btn_zhaohui;
	private Button btn_denglu;
	private CheckBox check_pwd;
	private EditText login_name;
	private EditText login_pwd;
	private SharedPreferences sp;
	private RadioGroup logintype_rgp;
	private RadioButton logintype1;
	private RadioButton logintype2;

	private String FILE = "saveUserNamePwd";


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	
		
		initView();
		initweight2();
		sp=this.getSharedPreferences(FILE, Context.MODE_PRIVATE);
		String name=sp.getString("Loginname", "");
		String pwd=sp.getString("Loginpwd", "");
		if(name==null){
			check_pwd.setChecked(false);
		}else{
			check_pwd.setChecked(true);
			login_name.setText(name);
			login_pwd.setText(pwd);
			login_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
		}  
		
	}
	

		private void initView() {
			btn_zhuce=(Button) findViewById(R.id.btn_zhuce);
			btn_cancer=(Button) findViewById(R.id.btn_cancer);
			btn_zhaohui=(Button) findViewById(R.id.btn_zhaohui);
			btn_denglu=(Button) findViewById(R.id.btn_denglu);
			login_name=(EditText) findViewById(R.id.loginname_ed);
			login_pwd=(EditText) findViewById(R.id.loginpwd_ed);
			logintype_rgp=(RadioGroup) findViewById(R.id.logintype_rgp);
			logintype1=(RadioButton) findViewById(R.id.logintype1);
			logintype2=(RadioButton) findViewById(R.id.logintype2);
			check_pwd=(CheckBox) findViewById(R.id.check_pwd);
	}



		private void initweight2() {
		// TODO 自动生成的方法存根
			login_name.setOnFocusChangeListener(new OnFocusChangeListener()
			{
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if(hasFocus){
					}else{
						String loginname=login_name.getText().toString().trim();
						if(loginname.equals("")){
							Toast.makeText(LoginActivity.this, "用户名不能为空",0).show();
						}
					}
				}
			});
			
			login_pwd.setOnFocusChangeListener(new OnFocusChangeListener()
			{
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if(hasFocus){
					}else{
						String loginpwd=login_pwd.getText().toString().trim();
						if(loginpwd.equals("")){
							Toast.makeText(LoginActivity.this, "用户密码不能为空",0).show();
						}
					}
				}
			});
			
			logintype_rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					String type=null;
					if(checkedId==logintype1.getId()){
						type=logintype1.getText().toString();
						Toast.makeText(LoginActivity.this, "您的选择是"+type, 0).show();
					}else if(checkedId==logintype2.getId()){
						type=logintype2.getText().toString();
						Toast.makeText(LoginActivity.this, "您的选择是"+type, 0).show();
					}
					
				}
				
			});
			
			btn_zhuce.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Intent intent=new Intent();
					intent.setClass(LoginActivity.this, RegisterActivity.class);
					startActivity(intent);
				}
			});
			
			btn_cancer.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent=new Intent();
					intent.setAction(Intent.ACTION_MAIN);
					intent.addCategory(Intent.CATEGORY_HOME);
					startActivity(intent);
				}
			});
			
			btn_zhaohui.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Intent intent=new Intent();
					intent.setClass(LoginActivity.this, BackPWDActivity.class);
				startActivity(intent);
				}
			});
			
		}

		public  void QueryDB(View v){
		  //得到连接
		String loginname=login_name.getText().toString();
		String loginpwd=login_pwd.getText().toString();
		
		
		sp=this.getSharedPreferences(FILE, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor=sp.edit();
		
		logintype_rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					String type=null;
					if(checkedId==logintype1.getId()){
						type=logintype1.getText().toString();
						Toast.makeText(LoginActivity.this, "您的选择是"+type, 0).show();
					}else if(checkedId==logintype2.getId()){
						type=logintype2.getText().toString();
						Toast.makeText(LoginActivity.this, "您的选择是"+type, 0).show();
					}
				}
				
			});   
		
		String logintype = null;
		if(logintype1.isChecked()){
			logintype=logintype1.getText().toString();
		}else if(logintype2.isChecked()){
			logintype=logintype2.getText().toString();
		}
		
		DataBaseHelper dbHelper=new DataBaseHelper(this);
		SQLiteDatabase database=dbHelper.getWritableDatabase();
		String table="UserInfo";
		String[] columns = new  String[] { "name" ,"pwd",  "type" };  
		Cursor cursor = database.query(table, columns, null, null,null,null,null,null);   
		
		while(cursor.moveToNext()){
			String resultname=cursor.getString(0);
			
			String resultpwd=cursor.getString(1);
			
			String resulttype=cursor.getString(2);

			if((loginname.equals(resultname))&&(loginpwd.equals(resultpwd))&&(logintype.equals(resulttype))){
		          new AlertDialog.Builder(LoginActivity.this).setTitle("正确")
		          .setMessage("登录成功！").setPositiveButton("确定", null)
		          .show();
		          
		  	if(check_pwd.isChecked()){
					
					editor.putString("Loginname",loginname );
					editor.putString("Loginpwd", loginpwd);
					
					editor.commit();
					}else{
					editor.remove("Loginname");
					}   
		  	if(logintype1.isChecked()){
	    		Intent intent=new Intent();
				intent.setClass(LoginActivity.this, TeacherActivity.class);
				startActivity(intent);
			}else {
	    		Intent intent=new Intent();
				intent.setClass(LoginActivity.this, StudentActivity.class);
				startActivity(intent);
			} 
		          
		        }else{
		          new AlertDialog.Builder(LoginActivity.this).setTitle("正确")
		          .setMessage("账号或密码错误！").setPositiveButton("确定", null)
		          .show();  
		         
		        }  
		}
		
	}

}
