package com.lbx.mycustomtab.anim;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.lbx.mycustomtab.R;
import com.lbx.mycustomtab.util.LogUtil;
/**
 * 参考：http://www.360doc.com/content/13/0102/22/6541311_257754535.shtml
 * @author liubaoxing
 *
 */
public class AnimActivity extends Activity implements OnClickListener{

	boolean isAnim = false;
	ImageView iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9,iv10,iv11,iv12,iv13,iv14,iv15,iv16,iv17,iv18;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim_view);
		init();
//		Animator
		
	}

	private void init(){
		iv1 = (ImageView)findViewById(R.id.iv1);
		iv1.setOnClickListener(this);
		iv2 = (ImageView)findViewById(R.id.iv2);
		iv2.setOnClickListener(this);
		iv3 = (ImageView)findViewById(R.id.iv3);
		iv3.setOnClickListener(this);
		iv4 = (ImageView)findViewById(R.id.iv4);
		iv4.setOnClickListener(this);
		iv5 = (ImageView)findViewById(R.id.iv5);
		iv5.setOnClickListener(this);
		iv6 = (ImageView)findViewById(R.id.iv6);
		iv6.setOnClickListener(this);
		iv7 = (ImageView)findViewById(R.id.iv7);
		iv7.setOnClickListener(this);
		iv8 = (ImageView)findViewById(R.id.iv8);
		iv8.setOnClickListener(this);
		iv9 = (ImageView)findViewById(R.id.iv9);
		iv9.setOnClickListener(this);
		iv10 = (ImageView)findViewById(R.id.iv10);
		iv10.setOnClickListener(this);
		iv11 = (ImageView)findViewById(R.id.iv11);
		iv11.setOnClickListener(this);
		iv12 = (ImageView)findViewById(R.id.iv12);
		iv12.setOnClickListener(this);
		iv13 = (ImageView)findViewById(R.id.iv13);
		iv13.setOnClickListener(this);
		iv14 = (ImageView)findViewById(R.id.iv14);
		iv14.setOnClickListener(this);
		iv15 = (ImageView)findViewById(R.id.iv15);
		iv15.setOnClickListener(this);
		iv16 = (ImageView)findViewById(R.id.iv16);
		iv16.setOnClickListener(this);
		iv17 = (ImageView)findViewById(R.id.iv17);
		iv17.setOnClickListener(this);
		iv18 = (ImageView)findViewById(R.id.iv18);
		iv18.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == iv1){
			testRotate();
		}else if(v == iv2){
			testAlpha();
		}else if(v == iv3){
			testScale();
		}else if(v == iv4){
			testTranslate();
		}else if(v == iv5){
			testAnimationSet();
		}else if(v == iv6){
			testAlphaAnimator();
		}else if(v == iv7){
			testTranslationXAnimator();
		}else if(v == iv8){
			testTranslationYAnimator();
		}else if(v == iv9){
			testRotationXAnimator();
		}else if(v == iv10){
			testRotationYAnimator();
		}else if(v == iv11){
			testScaleXAnimator();
		}else if(v == iv12){
			testScaleYAnimator();
		}else if(v == iv13){
			testRotationZAnimator();
		}else if(v == iv14){
			if(!isAnim){
				testAnimatorSet1();
				isAnim = true;
			}else{
				testAnimatorSet2();
				isAnim = false;
			}
		}else if(v == iv15){
			testAnimatorSet3();
		}else if(v == iv16){
			testValueAnimator();
		}else if(v == iv17){
			testAnimatorSet3();
		}else if(v == iv18){
			testAnimatorSet3();
		}
		
	}
	
	private void testValueAnimator() {
		
		Keyframe kf0 = Keyframe.ofInt(0, 400);
		Keyframe kf1 = Keyframe.ofInt(0.25f, 200);
		Keyframe kf2 = Keyframe.ofInt(0.5f, 400);
		Keyframe kf3 = Keyframe.ofInt(0.75f, 100);
		Keyframe kf4 = Keyframe.ofInt(1f, 500);
		
		PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("width", kf0,kf1,kf2,kf3,kf4);
//		ObjectAnimator rotationAnim = ObjectAnimator.ofPropertyValuesHolder(iv16, pvhRotation);
		ObjectAnimator rotationAnim = ObjectAnimator.ofInt(iv16, "scaleX", 400,200,400,100,500);
		rotationAnim.setDuration(2000);
		rotationAnim.start();
//		iv16.animate().
	}

	private void testAnimatorSet1() {
		ObjectAnimator alpha = ObjectAnimator.ofFloat(iv14, "alpha", 1f,0.5f).setDuration(2000);
		ObjectAnimator scaleX = ObjectAnimator.ofFloat(iv14, "scaleX", 1f,0.5f).setDuration(2000);
		ObjectAnimator scaleY = ObjectAnimator.ofFloat(iv14, "scaleY", 1f,0.5f).setDuration(2000);
		ObjectAnimator rotation  = ObjectAnimator.ofFloat(iv14, "rotation", 0f,360f).setDuration(2000);
//		ObjectAnimator rotationY  = ObjectAnimator.ofFloat(iv14, "rotationY", 0f,180f,60f).setDuration(2000);
		AnimatorSet as = new AnimatorSet();
		//参考：http://blog.sina.com.cn/s/blog_6e519585010157zt.html
		//动画从开始到结束，变化率是一个加速的过程
//		as.setInterpolator(new AccelerateInterpolator());
		//动画从开始到结束，变化率是线性变化。
		as.setInterpolator(new LinearInterpolator());
		//动画从开始到结束，变化率是一个减速的过程。
//		as.setInterpolator(new DecelerateInterpolator());
		//动画从开始到结束，变化率是循环给定次数的正弦曲线。
//		as.setInterpolator(new CycleInterpolator(1.0f));
		as.setDuration(2000);
		as.setTarget(iv4);
		//动画顺序执行
//		as.playSequentially(alpha,translationX,translationY,rotationX,rotationY);
		//动画一起执行
		as.playTogether(alpha,scaleX,scaleY,rotation);
		as.start();
	}
	private void testAnimatorSet2() {
		ObjectAnimator alpha = ObjectAnimator.ofFloat(iv14, "alpha", 0.5f,1f).setDuration(2000);
		ObjectAnimator scaleX = ObjectAnimator.ofFloat(iv14, "scaleX", 0.5f,1f).setDuration(2000);
		ObjectAnimator scaleY = ObjectAnimator.ofFloat(iv14, "scaleY", 0.5f,1f).setDuration(2000);
		ObjectAnimator rotation  = ObjectAnimator.ofFloat(iv14, "rotation", 0f,-360f).setDuration(2000);
//		ObjectAnimator rotationY  = ObjectAnimator.ofFloat(iv14, "rotationY", 0f,180f,60f).setDuration(2000);
		AnimatorSet as = new AnimatorSet();
		//动画从开始到结束，LinearInterpolator
		as.setInterpolator(new LinearInterpolator());
		as.setDuration(2000);
		as.setTarget(iv4);
		//动画顺序执行
//		as.playSequentially(alpha,translationX,translationY,rotationX,rotationY);
		//动画一起执行
		as.playTogether(alpha,scaleX,scaleY,rotation);
		as.start();
	}
	private void testAnimatorSet3() {
		ObjectAnimator alpha = ObjectAnimator.ofFloat(iv15, "alpha", 1f,0.5f).setDuration(2000);
		ObjectAnimator scaleX = ObjectAnimator.ofFloat(iv15, "scaleX", 1f,0.5f).setDuration(2000);
		ObjectAnimator scaleY = ObjectAnimator.ofFloat(iv15, "scaleY", 1f,0.5f).setDuration(2000);
		ObjectAnimator rotation  = ObjectAnimator.ofFloat(iv15, "rotation", 0f,360f).setDuration(2000);
		ObjectAnimator translationX  = ObjectAnimator.ofFloat(iv15, "translationX", 0f,180f,60f).setDuration(2000);
		AnimatorSet as = new AnimatorSet();
		AnimatorSet as1 = new AnimatorSet();
		//动画从开始到结束，变化率是循环给定次数的正弦曲线。
		as.setInterpolator(new CycleInterpolator(0.5f));
		as.setDuration(2000);
		as1.playTogether(scaleX,scaleY);
		 
		//执行顺序：as1 --> alpha,rotation -->translationX
		as.play(alpha).after(as1).before(translationX).with(rotation);
		//
		/*as.play(translationX).before(as1);
		as.play(as1).with(alpha);
		as.play(as1).with(rotation);*/
		
		as.start();
	}

	private void testAlphaAnimator() {
		ObjectAnimator.ofFloat(iv6, "alpha", 1.0f,0.1f,01f,1f).setDuration(4000).start();
	}
	private void testTranslationXAnimator() {
		ObjectAnimator.ofFloat(iv7, "translationX", 0f,30f,0f).setDuration(2000).start();
	}
	private void testTranslationYAnimator() {
		ObjectAnimator.ofFloat(iv8, "translationY", 0f,30f,-30f).setDuration(2000).start();
	}
	private void testRotationXAnimator() {
		ObjectAnimator.ofFloat(iv9, "rotationX", 0f,180f,30f).setDuration(2000).start();
	}
	private void testRotationYAnimator() {
		ObjectAnimator.ofFloat(iv10, "rotationY", 0f,180f,60f).setDuration(2000).start();
	}
	private void testScaleXAnimator() {
		ObjectAnimator.ofFloat(iv11, "scaleX", 1.0f,0.2f,0.9f).setDuration(2000).start();
	}
	private void testScaleYAnimator() {
		ObjectAnimator.ofFloat(iv12, "scaleY", 0.5f,1f,3f,1f).setDuration(2000).start();
	}
	private void testRotationZAnimator() {
		ObjectAnimator.ofFloat(iv13, "rotation", 0f,180f,60f).setDuration(2000).start();
	}

	private void testAnimationSet() {
		AnimationSet as  = new AnimationSet(false);
		
		AlphaAnimation aa = new AlphaAnimation(1.0f, 0.1f);
		aa.setDuration(2000);
		aa.setRepeatCount(Animation.INFINITE);
		aa.setRepeatMode(Animation.REVERSE);
		aa.setInterpolator(new LinearInterpolator());
		 
		ScaleAnimation sa = new ScaleAnimation(1, //x轴的起始大小
					1f,//x轴缩放后的值
					1, //y轴的起始大小
					0.5f,//y轴缩放后的值
					Animation.RELATIVE_TO_SELF,//
					0f, //
					Animation.RELATIVE_TO_SELF,//
					0f);//
		sa.setDuration(2000);
		sa.setRepeatCount(-1);
		sa.setRepeatMode(Animation.REVERSE);
		
		as.addAnimation(aa);
		as.addAnimation(sa);
		as.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				
			}
		});
		iv5.startAnimation(as);
	}

	private void testTranslate() {
		
		 TranslateAnimation ta = new TranslateAnimation(
				 Animation.RELATIVE_TO_SELF, 0f, //X ---起始值
				 Animation.RELATIVE_TO_SELF, 0.5f, //X ---移动后的值
				 Animation.RELATIVE_TO_SELF, 0f, //Y ---起始值
				 Animation.RELATIVE_TO_SELF, 0.5f);//Y ---移动后的值
		 ta.setDuration(2000);
		 ta.setRepeatCount(-1);
		 ta.setRepeatMode(Animation.REVERSE);
		iv4.startAnimation(ta);
	}

	private void testScale() {
		ScaleAnimation sa = new ScaleAnimation(1, //x轴的起始大小
				1f,//x轴缩放后的值
				1, //y轴的起始大小
				0.5f,//y轴缩放后的值
				Animation.RELATIVE_TO_SELF,//
				0f, //
				Animation.RELATIVE_TO_SELF,//
				0f);//
		sa.setDuration(2000);
		sa.setRepeatCount(-1);
		sa.setRepeatMode(Animation.REVERSE);
		iv3.startAnimation(sa);
	}

	private void testRotate(){
		//第一个参数fromDegrees表示动画起始时的角度， 
		//第二个参数toDegrees表示动画结束时的角度
		//第三个参数为X方向的坐标类型：有RELATIVE_TO_SELF相对于自己；RELATIVE_TO_PARENT相对于父控件和ABSOLUTE绝对坐标
		//第四个参数为x轴的值，0.5f表示以自身这个控件的一半长度为x轴
		//第五个参数为Y方向的坐标类型：
		//第六个参数为x轴的值，0.5f表示以自身这个控件的一半长度为y轴
		RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		//设置执行周期
		ra.setDuration(3000);
		//设置循环次数：-1表示无限循环 ;
		ra.setRepeatCount(Animation.INFINITE);
		//设置循环模式：RESTART【重新开始】 or REVERSE【反向执行】
		ra.setRepeatMode(Animation.REVERSE);
		//设置速率器：LinearInterpolator动画以均匀的速率改变；
		//DecelerateInterpolator
		//CycleInterpolator
		//
		ra.setInterpolator(new LinearInterpolator());
		iv1.startAnimation(ra);
	}
	private void testAlpha(){
		//创建一个AlphaAnimation对象， 
		AlphaAnimation aa = new AlphaAnimation(1.0f, 0.1f);
		aa.setDuration(3000);
		aa.setRepeatCount(Animation.INFINITE);
		aa.setRepeatMode(Animation.REVERSE);
		aa.setInterpolator(new LinearInterpolator());
		iv2.startAnimation(aa);
	}
}












