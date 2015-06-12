package com.lbx.mycustomtab.tab2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lbx.mycustomtab.R;

public class FragmentPage1 extends Fragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("life", "----FragmentPage1----onCreate()---");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("life", "----FragmentPage1----onCreateView()---");
		return inflater.inflate(R.layout.fragment_1, null);		
	}	

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("life", "----FragmentPage1----onStart()---");
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("life", "----FragmentPage1----onResume()---");
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("life", "----FragmentPage1----onPause()---");
	}
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("life", "----FragmentPage1----onStop()---");
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("life", "----FragmentPage1----onDestroy()---");
	}
}
