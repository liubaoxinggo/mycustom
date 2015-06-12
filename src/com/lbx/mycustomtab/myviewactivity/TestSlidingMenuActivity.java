package com.lbx.mycustomtab.myviewactivity;

import android.app.Activity;
import android.os.Bundle;

import com.lbx.mycustomtab.myview.SlidingMenu;

public class TestSlidingMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SlidingMenu view = new SlidingMenu(this);
		setContentView(view);
	}
}
