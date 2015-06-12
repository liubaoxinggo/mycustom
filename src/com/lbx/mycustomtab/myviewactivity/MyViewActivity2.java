package com.lbx.mycustomtab.myviewactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.lbx.mycustomtab.myview.MyView1;
import com.lbx.mycustomtab.myview.MyView2;

public class MyViewActivity2 extends Activity {
	MyView2 view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		view = new MyView2(this);
		setContentView(view);
	}
	 
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
