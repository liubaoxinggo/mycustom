package com.lbx.mycustomtab.myviewactivity;

import android.app.Activity;
import android.os.Bundle;

import com.lbx.mycustomtab.myview.MySurfaceView;

public class SurfaceViewActivity extends Activity {

	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		MySurfaceView msv = new MySurfaceView(this);
		setContentView(msv);
	}
}
