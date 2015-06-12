package com.lbx.mycustomtab.tab2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lbx.mycustomtab.R;

public class FragmentPage2 extends Fragment{

	 	


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("life", "----FragmentPage2----onCreate()---");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("life", "----FragmentPage2----onCreateView()---");
		return inflater.inflate(R.layout.fragment_2, null);		
	}	

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("life", "----FragmentPage2----onStart()---");
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("life", "----FragmentPage2----onResume()---");
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("life", "----FragmentPage2----onPause()---");
	}
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("life", "----FragmentPage2----onStop()---");
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("life", "----FragmentPage2----onDestroy()---");
	}

}