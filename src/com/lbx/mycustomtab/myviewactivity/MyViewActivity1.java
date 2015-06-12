package com.lbx.mycustomtab.myviewactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.lbx.mycustomtab.myview.MyView1;

public class MyViewActivity1 extends Activity {
	MyView1 view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		view = new MyView1(this);
		setContentView(view);
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(view.isStop()){
					view.start();
				}else{
					view.stop();
				}
			}
		});
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		view.stop();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		view.start();
	} 
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
