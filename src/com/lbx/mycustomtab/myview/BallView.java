package com.lbx.mycustomtab.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class BallView extends View {

	Paint paint;
	int radius;
	public BallView(Context context) {
		super(context);
		init();
	}

	public BallView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public BallView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int w = this.getWidth();
		int h = this.getHeight();
		radius = w < h ? w / 2 : h / 2;
	}
	private void init(){
		paint = new Paint();
		paint.setStyle(Style.FILL);
		paint.setColor(Color.GREEN);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawCircle(radius, radius, radius, paint);
	}
}

























