package com.lbx.mycustomtab.myviewactivity;

import com.lbx.mycustomtab.myview.SearchDevicesView;

import android.app.Activity;
import android.os.Bundle;

public class SearchDevicesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SearchDevicesView sdv = new SearchDevicesView(this);
		sdv.setWillNotDraw(false);//û����仭�������
		setContentView(sdv);
	}
}
