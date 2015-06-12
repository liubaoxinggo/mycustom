package com.lbx.mycustomtab.myviewactivity;

import com.lbx.mycustomtab.myview.IrregularGraphicsView;

import android.app.Activity;
import android.os.Bundle;

public class IrregularGraphicsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		IrregularGraphicsView view = new IrregularGraphicsView(this);
		setContentView(view);
	}
}
