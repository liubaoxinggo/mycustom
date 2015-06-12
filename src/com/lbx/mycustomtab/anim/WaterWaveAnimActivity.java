package com.lbx.mycustomtab.anim;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.lbx.mycustomtab.R;

public class WaterWaveAnimActivity extends Activity {

	private static final int ANIMATIONEACHOFFSET = 600;
	
	private ImageView btn,wave1,wave2,wave3;
	
	private AnimationSet aniSet1,aniSet2,aniSet3;
	
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0x222:
				wave2.startAnimation(aniSet2);
				break;
			case 0x333:
				wave3.startAnimation(aniSet3);
				break;
			default:
				break;
			}
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim1);
		init();
		
	}
	private void init(){
		btn = (ImageView)findViewById(R.id.btn);
		wave1 = (ImageView)findViewById(R.id.wave1);
		wave2 = (ImageView)findViewById(R.id.wave2);
		wave3 = (ImageView)findViewById(R.id.wave3);
		aniSet1 = getNewAnimationSet();
		aniSet2 = getNewAnimationSet();
		aniSet3 = getNewAnimationSet();
		
		btn.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					showWaveAnimation();
					break;
				case MotionEvent.ACTION_UP:
					cancleWaveAnimation();
					break;
				case MotionEvent.ACTION_CANCEL:
					cancleWaveAnimation();
					break;

				default:
					break;
				}
				return false;
			}

		});
	}
	
	private void cancleWaveAnimation() {
		// TODO Auto-generated method stub
		wave1.clearAnimation();
		wave2.clearAnimation();
		wave3.clearAnimation();
	}
	
	private void showWaveAnimation() {
		// TODO Auto-generated method stub
		wave1.startAnimation(aniSet1);
		handler.sendEmptyMessageDelayed(0x222, ANIMATIONEACHOFFSET);
		handler.sendEmptyMessageDelayed(0x333, ANIMATIONEACHOFFSET * 2);
	}
	private AnimationSet getNewAnimationSet(){
		AnimationSet as = new AnimationSet(true);
		ScaleAnimation sa = new ScaleAnimation(1f, 2.3f, 1f, 2.3f,
				ScaleAnimation.RELATIVE_TO_SELF,0.5f,
				ScaleAnimation.RELATIVE_TO_SELF,0.5f);
		sa.setDuration(ANIMATIONEACHOFFSET * 3);
		sa.setRepeatCount(-1);
		AlphaAnimation aa = new AlphaAnimation(1f, 0.1f);
		aa.setDuration(ANIMATIONEACHOFFSET * 3);
		aa.setRepeatCount(-1);
		
		as.addAnimation(sa);
		as.addAnimation(aa);
		return as;
	}
}












