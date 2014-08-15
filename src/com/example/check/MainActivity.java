package com.example.check;

import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;  
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private CheckView mMyView = null;  
    private char[] res = new char[4];  //获取每次更新的验证码，可用于判断用户输入是否正确  
    private Button button;
    private TextView checkvalue;
    private CheckView checkView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	          
		  mMyView = (CheckView)findViewById(R.id.checkView);  
	        //初始化验证码  
	        res = mMyView.getValidataAndSetImage();  
	        mMyView.setOnClickListener(new OnClickListener() {  
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					 res = mMyView.getValidataAndSetImage();           
				}  
	        });  
	        button=(Button)findViewById(R.id.btn);
	        button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					putdialog();
				}
			});
	         
	}
	private void putdialog() {
		LayoutInflater inflater = LayoutInflater.from(this);
		View layout = inflater.inflate(R.layout.check_dialog, null);
		checkvalue = (EditText) layout.findViewById(R.id.captcha_value2);
		checkView = (CheckView) layout.findViewById(R.id.checkview2);
		res = checkView.getValidataAndSetImage();
		checkView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				res = checkView.getValidataAndSetImage();
			}
		});
		AlertDialog.Builder builder = new Builder(MainActivity.this);
		builder.setView(layout);
		builder.setTitle("请输入验证码");
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int arg1) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int arg1) {
				// TODO Auto-generated method stub
				String scheck = new String(res);
				String string = checkvalue.getText().toString();
				boolean b = string.equals(scheck);
				if (b) {
					Toast.makeText(MainActivity.this, "验证码输入正确", Toast.LENGTH_SHORT).show();
					dialog.dismiss();
				} else {
					Toast.makeText(MainActivity.this, "验证码输入有误，请重新输入", Toast.LENGTH_SHORT).show();
					putdialog();
				}

			}
		});
		builder.create().show();
		// dialog.show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
