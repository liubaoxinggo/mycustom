package com.lbx.mycustomtab.myviewactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.lbx.mycustomtab.R;

public class RadarActivity extends Activity {

	/**
	 * 扫描扇形
	 */
	private ImageView im_scan;
	/**
	 * 扫描点
	 */
	private ImageView im_dian;
	
	
	private ImageView  im_scan1,scanBg;//
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radar_layout);
		init();
	}
	private void init(){
		//初始化第一个
		im_scan = (ImageView) findViewById(R.id.im_scan);
		im_dian = (ImageView) findViewById(R.id.im_dian);
		im_scan.startAnimation(getRotateAnimation());
		im_dian.startAnimation(getNewAnimationSet());
		//初始化第二个
		im_scan1 = (ImageView) findViewById(R.id.im_scan_1);
		scanBg = (ImageView) findViewById(R.id.search_bg);
		
		/*int w = scanBg.getWidth();
		int h = scanBg.getHeight();
		LayoutParams params = new LayoutParams(w, h);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		im_scan1.setLayoutParams(params);*/
		im_scan1.startAnimation(getRotateAnimation1());
	}
	private  Animation getRotateAnimation1(){
		return AnimationUtils.loadAnimation(this, R.anim.rotate_animation);
	}
	private  RotateAnimation getRotateAnimation(){
		RotateAnimation ra = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		ra.setDuration(2000);
		ra.setInterpolator(new LinearInterpolator());//设置匀速，不会出现停顿现象
		ra.setRepeatCount(Animation.INFINITE);
		return ra;
	}
	private AnimationSet getNewAnimationSet(){
		AnimationSet as = new AnimationSet(true);
		RotateAnimation ra = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		ra.setDuration(3000);
		ra.setRepeatCount(-1);
		AlphaAnimation aa = new AlphaAnimation(0.0f, 1f);
		aa.setDuration(3000);
		aa.setRepeatCount(-1);
		
//		as.addAnimation(ra);
		as.addAnimation(aa);
		return as;
	}
	 
}























