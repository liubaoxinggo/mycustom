package com.lbx.mycustomtab.myviewactivity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lbx.mycustomtab.R;
import com.lbx.mycustomtab.myview.assist.ShapeHolder;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorInflater;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

public class NineOldAndroidAnimationLoading extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nine_old_animation);
		init();
	}
	void init(){
		LinearLayout container = (LinearLayout) findViewById(R.id.container);
		final MyAnimationView animview = new MyAnimationView(this);
		container.addView(animview);
		Button btn1 = (Button) findViewById(R.id.btn1);
		btn1.setText("Run");
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				animview.startAnimator();
			}
		});
	}
	public class MyAnimationView extends View implements ValueAnimator.AnimatorUpdateListener{
		private static final float BALL_SIZE = 100f;
		public final ArrayList<ShapeHolder> balls = new ArrayList<ShapeHolder>();
		
		Animator animation = null;
		
		public MyAnimationView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			addBall(50, 50);
			addBall(200, 50);
			addBall(350, 50);
			addBall(500, 50,Color.GREEN);
			
		}

		@Override
		public void onAnimationUpdate(ValueAnimator animation) {
			// TODO Auto-generated method stub
			invalidate();
			ShapeHolder ball = balls.get(0);
			ball.setY((Float)animation.getAnimatedValue());
		}
		private void createAnimation(){
			Context appContext = NineOldAndroidAnimationLoading.this;
			if(animation == null){
				ObjectAnimator anim = (ObjectAnimator)AnimatorInflater.loadAnimator(appContext, R.anim.object_animator);
				anim.addUpdateListener(this);
				anim.setTarget(balls.get(0));
				
				ValueAnimator fader = (ValueAnimator)AnimatorInflater.loadAnimator(appContext,R.anim.animator);
				fader.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						balls.get(1).setAlpha((Float)animation.getAnimatedValue());
					}
				});
				AnimatorSet seq = (AnimatorSet)AnimatorInflater.loadAnimator(appContext, R.anim.animator_set);
				seq.setTarget(balls.get(2));
				
				ObjectAnimator colorizer = (ObjectAnimator)AnimatorInflater.loadAnimator(appContext, R.anim.color_animator);
				colorizer.setTarget(balls.get(3));
				
				animation = new AnimatorSet();
				((AnimatorSet)animation).playTogether(anim,fader,seq,colorizer);
				
			}
		}
		public void startAnimator(){
			createAnimation();
			animation.start();
		}
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			for (ShapeHolder ball : balls) {
				canvas.translate(ball.getX(), ball.getY());
				ball.getShape().draw(canvas);
				canvas.translate(-ball.getX(), -ball.getY());
				
			}
		}
		private void addBall(float x,float y,int color){
			ShapeHolder shapeHolder = createBall(x, y);
			shapeHolder.setColor(color);
			balls.add(shapeHolder);
		}
		private void addBall(float x,float y){
			ShapeHolder shapeHolder = createBall(x, y);
			int red = (int)(100 + Math.random() * 155);
			int green = (int)(100 + Math.random() * 155);
			int blue = (int)(100 + Math.random() * 155);
			int color = 0xff000000 | red << 16 | green << 8 | blue;
			int darkColor = 0xff000000 | red/4 << 16 | green/4 << 8 | blue/4;
			Paint paint = shapeHolder.getShape().getPaint();
			RadialGradient gradient = new RadialGradient(37.5f, 12.5f, 50f, color, darkColor, Shader.TileMode.CLAMP);
			paint.setShader(gradient);
			balls.add(shapeHolder);
		}
		private ShapeHolder createBall(float x,float y){
			OvalShape circle = new OvalShape();
			circle.resize(BALL_SIZE, BALL_SIZE);
			ShapeDrawable drawable = new ShapeDrawable(circle);
			ShapeHolder shapeHolder = new ShapeHolder(drawable);
			shapeHolder.setX(x);
			shapeHolder.setY(y);
			return shapeHolder;
		}
		
		
	}
	
}
