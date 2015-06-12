package com.lbx.mycustomtab.myviewactivity;

import com.lbx.mycustomtab.myview.PathView;

import android.app.Activity;
import android.os.Bundle;

public class PathActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		PathView view = new PathView(this);
		setContentView(view);
	}
}
