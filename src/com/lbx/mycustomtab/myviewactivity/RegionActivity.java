package com.lbx.mycustomtab.myviewactivity;

import android.app.Activity;
import android.os.Bundle;

import com.lbx.mycustomtab.myview.RegionView;

public class RegionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		RegionView view = new RegionView(this);
		setContentView(view);
	}
}
