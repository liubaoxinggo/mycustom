package com.lbx.mycustomtab.myviewactivity;

import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lbx.mycustomtab.R;
import com.lbx.mycustomtab.myview.BallView;
import com.nineoldandroids.animation.Keyframe;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.nineoldandroids.view.ViewHelper;
/**
 * 参考：http://blog.csdn.net/lmj623565791/article/details/38067475
 * @author liubaoxing
 *
 */
public class NineOldBallActivity extends Activity {

	BallView ball;
	RelativeLayout root;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ball_demo);
		init();
	}
	private void init(){
		ball = (BallView)findViewById(R.id.ball);
		root = (RelativeLayout)findViewById(R.id.container);
		((Button)findViewById(R.id.btn1)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				verticalRun();
			}
		});
		((Button)findViewById(R.id.btn11)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(animator != null){
					animator.reverse();
				}
			}
		});
		((Button)findViewById(R.id.btn2)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				paowuxian();
			}
		});
		((Button)findViewById(R.id.btn3)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				paowuxian2();
			}
		});
	}
	final float a = -1f / 75f;
	private void paowuxian2(){
		int count = 300;
		Keyframe[] keyframes = new Keyframe[count]; 
		float keyStep = 1f/(float)count;
		float key = keyStep;
		for (int i = 0; i < keyframes.length; i++) {
			keyframes[i] = Keyframe.ofFloat(key, i + 1);
			key += keyStep;
		}
		PropertyValuesHolder pvhX = PropertyValuesHolder.ofKeyframe("translationX", keyframes);
		key = keyStep;
		for (int i = 0; i < keyframes.length; i++) {
			keyframes[i] = Keyframe.ofFloat(key, getY(i + 1));
			key += keyStep;
		}
		PropertyValuesHolder pvhY = PropertyValuesHolder.ofKeyframe("translationY", keyframes);
		ObjectAnimator yxBouncer = ObjectAnimator.ofPropertyValuesHolder(ball,pvhX,pvhY).setDuration(2000);
		yxBouncer.setInterpolator(new BounceInterpolator());
		yxBouncer.start();
	}
	private float getY(float x){
		return a * x * x + 4 * x; 
	}
	ValueAnimator animator;
	private void verticalRun(){
		animator = ValueAnimator.ofFloat(0,root.getHeight() - ball.getHeight());
		animator.setTarget(ball);
		animator.setInterpolator(new BounceInterpolator());
		
		animator.setDuration(3000).start();
		animator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
//				ball.setTranslationY((Float)animation.getAnimatedValue());
				ViewHelper.setTranslationY(ball, (Float)animation.getAnimatedValue());
			}
		});
	}
	private void paowuxian(){
		final int t = 5;
		final int Vx = 200;//横向速度
		ValueAnimator valueAnimator = new ValueAnimator();
		valueAnimator.setDuration(t * 1000);
		valueAnimator.setObjectValues(new PointF(0, 0));
		valueAnimator.setInterpolator(new AccelerateInterpolator());
		valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {

			@Override
			public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
				// TODO Auto-generated method stub
				PointF point = new PointF();
				point.x = Vx * fraction * t;
				point.y = 0.5f * Vx * fraction * t * fraction * t;
				if(point.y <= root.getHeight() - ball.getHeight())
					return point;
				else 
					return null;
					
			}
		});
		valueAnimator.start();
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				 PointF p = (PointF)animation.getAnimatedValue();
				 if(p != null){
//					 ball.setTranslationY(p.y);
//					 ball.setTranslationX(p.x);//注意不要使用此方法，该方法不兼容低版本的【<11的版本】
					 ViewHelper.setTranslationX(ball, p.x);
					 ViewHelper.setTranslationY(ball, p.y);
				 }
			}
		});
		
	}
}


















