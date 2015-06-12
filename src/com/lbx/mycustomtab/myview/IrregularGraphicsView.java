package com.lbx.mycustomtab.myview;

import com.lbx.mycustomtab.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.AvoidXfermode.Mode;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.View;

@SuppressLint("DrawAllocation") public class IrregularGraphicsView extends View {

	Paint paint;
	public IrregularGraphicsView(Context context) {
		super(context);
		init(); 
	}

	private void init(){
		paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(3f);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeJoin(Paint.Join.ROUND);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
//		canvas.drawCircle(100, 100, 90, paint);
		testArc(canvas);
		testClock(canvas);
		//将位置移动画纸的坐标点
		canvas.save();
		canvas.translate(canvas.getWidth() / 2 + 150, 1100);
		paint.setColor(Color.BLACK);
//		canvas.drawLine(-500, 0, 500, 0, paint);
//		canvas.drawLine(0, -500, 0, 500, paint);
		paint.setTextSize(30);
//		canvas.drawText("0", 0, 0, paint);
//		canvas.drawText("300", 310, 30, paint);
//		canvas.drawText("300", 10, 330, paint);
		canvas.drawRect(-350, -350, 350, 350, paint);
		paint.setColor(Color.GRAY);
		canvas.drawCircle(0, 0, 300, paint);//绘制圆圈
		
//		for (int j = 0; j < 12; j++) {
//			canvas.rotate(30);
//			canvas.drawText(String.valueOf(j + 1), -10, -330, paint);
//		}
		for (int i = 0; i < 60; i++) {
			if(i % 5 == 0){
				canvas.drawLine(0, -280, 0, -300, paint);
			}else{
				canvas.drawLine(0, -290, 0, -300, paint);
			}
			canvas.rotate(6);
		}
		canvas.drawText("3", 310, 10, paint);
		canvas.drawText("6", -5, 330, paint);
		canvas.drawText("9", -330, 10, paint);
		canvas.drawText("12", -10, -310, paint);
		
	}

	private void testClock(Canvas canvas) {
		canvas.drawColor(0x6600f6f6);
		paint.setAntiAlias(true);
		paint.setStyle(Style.STROKE);
		paint.setColor(Color.GRAY);
		//将位置移动画纸的坐标点
		canvas.save();
		canvas.translate(canvas.getWidth() / 2 + 150, 400);
		canvas.drawCircle(0, 0, 300, paint);//绘制圆圈
		//绘制文字
//		canvas.translate(-75, -75); 
		Path path = new Path();
		path.addArc(new RectF(-250, -250, 250, 250), 220, 180);
		Paint citePaint = new Paint(paint);
		citePaint.setTextSize(34);
		citePaint.setStrokeWidth(2);;
		canvas.drawTextOnPath("http://www.fhit.com.cn", path, 28, 0, citePaint);
		
		Paint tmpPaint = new Paint(paint);
		tmpPaint.setStrokeWidth(1);
		
		float y = 300;
		int count = 60;
		for (int i = 0; i < count; i++) {
			if(i % 5 == 0){
				canvas.drawLine(0f, y, 0f, y + 12f, paint);
				canvas.drawText(String.valueOf(i / 5 + 1), -4f, y + 40f, citePaint);
			}else{
				canvas.drawLine(0f, y, 0f, y +5f, tmpPaint); 
			}
			canvas.rotate(360/count,0f,0f); //旋转画纸   
		}
		//绘制指针   
	    tmpPaint.setColor(Color.GRAY);   
	    tmpPaint.setStrokeWidth(4);   
	    canvas.drawCircle(0, 0, 17, tmpPaint);   
	    tmpPaint.setStyle(Style.FILL);   
	    tmpPaint.setColor(Color.YELLOW);   
	    canvas.drawCircle(0, 0, 5, tmpPaint);  
	    canvas.drawLine(0, 40, 0, -180, paint);   
	    canvas.restore();
	}

	private void testArc(Canvas canvas) {
		//绘制弧形，首先要确定一个矩形区域A,在A内画弧形，起始弧度0为水平向右，顺时针增加
		RectF rect1 = new RectF(0, 0, 300, 300);
		paint.setColor(Color.GREEN);
		canvas.drawRect(rect1, paint);
		paint.setColor(Color.GRAY);
		canvas.drawArc(rect1, 0, 30, false, paint);
		
		RectF rect2 = new RectF(0, 310, 300, 610);
		paint.setColor(Color.GREEN);
		canvas.drawRect(rect2, paint);
		paint.setColor(Color.GRAY);
		canvas.drawArc(rect2, 0, 60, true, paint);
		
		RectF rect3 = new RectF(0, 620, 300, 920);
		paint.setColor(Color.GREEN);
		canvas.drawRect(rect3, paint);
		paint.setColor(Color.GRAY);
		canvas.drawArc(rect3, 0,120, false, paint);
		
		RectF rect4 = new RectF(0, 930, 300, 1230);
		paint.setColor(Color.GREEN);
		canvas.drawRect(rect4, paint);
		paint.setColor(Color.GRAY);
		canvas.drawArc(rect4, 0, 270, false , paint);
	}
}















