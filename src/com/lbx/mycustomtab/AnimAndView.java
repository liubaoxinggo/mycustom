package com.lbx.mycustomtab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lbx.mycustomtab.R;
import com.lbx.mycustomtab.anim.AnimActivity;
import com.lbx.mycustomtab.anim.WaterWaveAnimActivity;
import com.lbx.mycustomtab.myviewactivity.CustomViewActivity;
import com.lbx.mycustomtab.myviewactivity.IrregularGraphicsActivity;
import com.lbx.mycustomtab.myviewactivity.MyViewActivity1;
import com.lbx.mycustomtab.myviewactivity.MyViewActivity2;
import com.lbx.mycustomtab.myviewactivity.MyViewActivity3;
import com.lbx.mycustomtab.myviewactivity.PathActivity;
import com.lbx.mycustomtab.myviewactivity.RadarActivity;
import com.lbx.mycustomtab.myviewactivity.RegionActivity;
import com.lbx.mycustomtab.myviewactivity.SearchDevicesActivity;

public class AnimAndView extends Activity  implements OnClickListener{

	Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		init();
	}
	void init(){
		btn1 = (Button)findViewById(R.id.btn1);
		btn1.setText("�˶��ĵ�");
		btn1.setOnClickListener(this);
		btn2 = (Button)findViewById(R.id.btn2);
		btn2.setText("����ͼ�λ���");
		btn2.setOnClickListener(this);
		btn3 = (Button)findViewById(R.id.btn3);
		btn3.setText("�Զ���view�Ĳ���");
		btn3.setOnClickListener(this);
		btn4 = (Button)findViewById(R.id.btn4);
		btn4.setText("����ʵ�ֵĲ���");
		btn4.setOnClickListener(this);
		btn5 = (Button)findViewById(R.id.btn5);
		btn5.setText("�״�ɨ��1 �Զ���viewʵ��");
		btn5.setOnClickListener(this);
		btn6 = (Button)findViewById(R.id.btn6);
		btn6.setText("�״�ɨ��2 ͨ������ʵ��");
		btn6.setOnClickListener(this);
		btn7 = (Button)findViewById(R.id.btn7);
		btn7.setText("���Ʋ�����ͼ��IrregularGraphics");
		btn7.setOnClickListener(this);
		btn8 = (Button)findViewById(R.id.btn8);
		btn8.setText("path�Ĳ���");
		btn8.setOnClickListener(this);
		btn9 = (Button)findViewById(R.id.btn9);
		btn9.setText("����region���Ĳ���");
		btn9.setOnClickListener(this);
		btn10 = (Button)findViewById(R.id.btn10);
		btn10.setText("�Զ���view");
		btn10.setOnClickListener(this);
		btn11 = (Button)findViewById(R.id.btn11);
		btn11.setText("view����");
		btn11.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == btn1){
			startActivity(new Intent(this, MyViewActivity1.class));
		}else if(v == btn2){
			startActivity(new Intent(this, MyViewActivity2.class));
		}else if(v == btn3){
			startActivity(new Intent(this, MyViewActivity3.class));
		}else if(v == btn4){
			startActivity(new Intent(this, WaterWaveAnimActivity.class));
		}else if(v == btn5){
			startActivity(new Intent(this, SearchDevicesActivity.class));
		}else if(v == btn6){
			startActivity(new Intent(this, RadarActivity.class));
		}else if(v == btn7){//Irregular graphics
			startActivity(new Intent(this, IrregularGraphicsActivity.class));
		}else if(v == btn8){// 
			startActivity(new Intent(this, PathActivity.class));
		}else if(v == btn9){// 
			startActivity(new Intent(this, RegionActivity.class));
		}else if(v == btn10){// 
			startActivity(new Intent(this, CustomViewActivity.class));
		}else if(v == btn11){// 
			startActivity(new Intent(this, AnimActivity.class));
		}
	}
}














