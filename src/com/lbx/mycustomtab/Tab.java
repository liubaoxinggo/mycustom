package com.lbx.mycustomtab;

import com.lbx.mycustomtab.R;
import com.lbx.mycustomtab.anim.WaterWaveAnimActivity;
import com.lbx.mycustomtab.myviewactivity.MyViewActivity1;
import com.lbx.mycustomtab.myviewactivity.MyViewActivity2;
import com.lbx.mycustomtab.myviewactivity.MyViewActivity3;
import com.lbx.mycustomtab.myviewactivity.SearchDevicesActivity;
import com.lbx.mycustomtab.tab1.MainActivity;
import com.lbx.mycustomtab.tab2.MainTabActivity;
import com.lbx.mycustomtab.tab3.MainActivity03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Tab extends Activity implements OnClickListener{

	Button btn1,btn2,btn3,btn4,btn5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		init();
	}

	void init(){
		btn1 = (Button)findViewById(R.id.btn1);
		btn1.setText("采用RadioGroup控件，tab间切换不会销毁以前的fragment");
		btn1.setOnClickListener(this);
		btn2 = (Button)findViewById(R.id.btn2);
		btn2.setText("采用FragmentTabHost ，tab间切换不会销毁以前的fragment，但是会重新onCreateView");
		btn2.setOnClickListener(this);
		btn3 = (Button)findViewById(R.id.btn3);
		btn3.setText("采用Viewpager和 RadioGroup控件，性能随Viewpager");
		btn3.setOnClickListener(this);
		btn4 = (Button)findViewById(R.id.btn4);
		btn4.setText("");
		btn4.setOnClickListener(this);
		btn5 = (Button)findViewById(R.id.btn5);
		btn5.setText("");
		btn5.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == btn1){
			startActivity(new Intent(this, MainActivity.class));
		}else if(v == btn2){
			startActivity(new Intent(this, MainTabActivity.class));
		}else if(v == btn3){
			startActivity(new Intent(this, MainActivity03.class));
		}else if(v == btn4){
//			startActivity(new Intent(this, AnimActivity.class));
		}else if(v == btn5){
//			startActivity(new Intent(this, SearchDevicesActivity.class));
		}
	}
}
