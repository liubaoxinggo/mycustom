package com.lbx.mycustomtab;

import android.app.Application;
import android.content.Context;

public class TApplication extends Application {

	public static Context nowApplication;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		nowApplication = this;
	}
}
