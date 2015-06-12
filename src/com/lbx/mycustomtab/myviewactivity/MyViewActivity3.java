package com.lbx.mycustomtab.myviewactivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.lbx.mycustomtab.myview.WaterWaveView;
import com.lbx.mycustomtab.util.LogUtil;

public class MyViewActivity3 extends Activity {
//	MyView3 view;
	WaterWaveView view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		view = new MyView3(this);
		view = new WaterWaveView(this);
		LogUtil.i("infos", "--------onCreate -----a------");
		view.setFillWaveSourceShapeRadius(120f);
//		view.setWaveInfo(60f, 2f, 2f, 15f, Color.BLUE);
		view.setWaveInfo(60f, 4f, 2f, 40f, 0xff00f0f0);
		setContentView(view);
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view.setFillAllView(true);
			}
		});
	}
	 
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
