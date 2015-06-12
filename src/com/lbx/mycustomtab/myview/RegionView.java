package com.lbx.mycustomtab.myview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.view.View;
/**
 * �ο���http://blog.csdn.net/lonelyroamer/article/details/8349601
 *     http://blog.csdn.net/lonelyroamer/article/details/8349601
 * @author liubaoxing
 *
 */
@SuppressLint("DrawAllocation") public class RegionView extends View {

	Paint paint;
	Paint paint0;
	Paint textPaint;
	Path mPath;
	public RegionView(Context context) {
		super(context);
		init();
	}
	private void init(){
		paint = new Paint();
		//ָ���Ƿ�ʹ�ÿ���ݹ��ܣ����ʹ�ã���ʹ��ͼ�ٶȱ���  
		paint.setAntiAlias(true);
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(3f);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStyle(Style.FILL);
		
		paint0 = new Paint(paint);
		paint0.setStyle(Style.STROKE);
		
		//��ʼ��д���ֵĻ���
	    textPaint = new Paint(paint);
		textPaint.setColor(Color.DKGRAY);
		textPaint.setTextSize(24);
		//ֻ�Ὣˮƽ�������죬�߶Ȳ����  
		textPaint.setTextScaleX(2f);
		textPaint.setStrokeWidth(1);
		textPaint.setStyle(Style.FILL_AND_STROKE);
		
		mPath = new Path();
	}
	@SuppressLint("DrawAllocation") @Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawText("region����", 10, 25, textPaint);
		//�ü�
		testClip(canvas);
		//�����ѡȡ
		testRegion(canvas);
		
		getAnyView(canvas,Color.GREEN, 250, 1290, Region.Op.INTERSECT);		
//		getAnyView(canvas,Color.GREEN, 600, 1290, Region.Op.XOR);		
		getAnyView(canvas,Color.GRAY, 250, 1290);		
		getAnyView(canvas,Color.GREEN, 600, 1290, Region.Op.DIFFERENCE,Region.Op.DIFFERENCE,Region.Op.DIFFERENCE);		
		getAnyView(canvas,Color.GRAY, 600, 1290);		
		/*canvas.save();
		Rect rect = new Rect(10, 30, 200, 200);
		Rect clipRect = new Rect(10, 30, 100, 100);
		//�����о�����Ҫ����Ҫ���ľ����ཻ��ֻ��ʾ�ཻ������  
//		canvas.clipRect(clipRect);
		//�����о�����Ҫ����Ҫ���ľ����ཻ������ʾ�ཻ������  
		canvas.clipRect(clipRect, Region.Op.XOR);
		canvas.drawRect(rect, paint);
		canvas.restore();*/
	}
	private void getAnyView(Canvas canvas,int color,int x,int y) {
		canvas.save();
		canvas.translate(x, y);
		
		Path path1 = new Path();
		
		path1.addArc(new RectF(0, 0, 300, 300), 0, 60);
		path1.arcTo(new RectF(150, 0, 450, 300), 120, 60);
		path1.arcTo(new RectF(75, 150, 375, 450), 240, 60);
		 
		path1.close();
		paint0.setColor(color);
		canvas.drawPath(path1, paint0);
		canvas.restore(); 
	}
	private void getAnyView(Canvas canvas,int color,int x,int y,Region.Op op) {
		canvas.save();
		canvas.translate(x, y);
		
		Path path1 = new Path();
		path1.addCircle(150, 150, 150, Direction.CCW);
		Path path2 = new Path();
		path2.addCircle(300, 150, 150, Direction.CCW);
		Path path3 = new Path();
		path3.addCircle(225, 300, 150, Direction.CCW);
		canvas.restore();
		
		paint.setColor(color);
		
		canvas.save();
		canvas.translate(x, y);
		canvas.clipPath(path1, op);
		canvas.drawPath(path2, paint);
		canvas.restore();
		
		canvas.save();
		canvas.translate(x, y);
		canvas.clipPath(path2, op);
		canvas.drawPath(path3, paint);
		canvas.restore();
		
		canvas.save();
		canvas.translate(x, y);
		canvas.clipPath(path3, op);
		canvas.drawPath(path1, paint);
		canvas.restore();
	}
	private void getAnyView(Canvas canvas,int color,int x,int y,Region.Op op1,Region.Op op2,Region.Op op3) {
		canvas.save();
		canvas.translate(x, y);
		
		Path path1 = new Path();
		path1.addCircle(150, 150, 150, Direction.CCW);
		Path path2 = new Path();
		path2.addCircle(300, 150, 150, Direction.CCW);
		Path path3 = new Path();
		path3.addCircle(225, 300, 150, Direction.CCW);
		canvas.restore();
		
		paint.setColor(color);
		
		canvas.save();
		canvas.translate(x, y);
		canvas.clipPath(path1, op1);
		canvas.drawPath(path2, paint);
		canvas.restore();
		
		canvas.save();
		canvas.translate(x, y);
		canvas.clipPath(path2, op2);
		canvas.drawPath(path3, paint);
		canvas.restore();
		
		canvas.save();
		canvas.translate(x, y);
		canvas.clipPath(path3, op3);
		canvas.drawPath(path1, paint);
		canvas.restore();
	}
	
	 
	private void testClip(Canvas canvas) {
		canvas.save();
		canvas.translate(10, 30);
		drawScene(canvas);
		canvas.restore();
		canvas.drawText("ԭͼ", 60, 60, textPaint);
		
		canvas.save();
		canvas.translate(320, 30);
		canvas.clipRect(20, 20, 180, 180);
		//����ͼ��A��ü�����B��ͬ�Ĳ��֣�����������Ϊȥ��A��B����������
		canvas.clipRect(60, 60, 140, 140, Region.Op.DIFFERENCE);
		drawScene(canvas);
		canvas.restore();
		canvas.drawText("DIFFERENCE", 320, 60, textPaint);
		
		canvas.save();
		canvas.translate(520, 30);
		canvas.clipRect(20, 20, 180, 180);
		canvas.clipRect(60, 60, 140, 140, Region.Op.INTERSECT);
		drawScene(canvas);
		canvas.restore();
		canvas.drawText("INTERSECT", 520, 90, textPaint);
		
		canvas.save();
		canvas.translate(720, 30);
		canvas.clipRect(20, 20, 180, 180);
		canvas.clipRect(60, 60, 140, 140, Region.Op.XOR);
		drawScene(canvas);
		canvas.restore();
		canvas.drawText("XOR", 720, 60, textPaint);
		//-----------------�ڶ���-------------------------
		canvas.save();
		canvas.translate(10, 320);
		mPath.reset();
		mPath.addCircle(100, 100, 100, Direction.CCW);
		canvas.clipPath(mPath, Region.Op.REPLACE);
		drawScene(canvas);
		canvas.restore();
		canvas.drawText("REPLACE", 10, 320, textPaint);
		
		canvas.save();
		canvas.translate(240, 320);
		mPath.reset();
		mPath.addCircle(100, 100, 100, Direction.CCW);
		canvas.clipPath(mPath, Region.Op.DIFFERENCE);
		drawScene(canvas);
		canvas.restore();
		canvas.drawText("DIFFERENCE", 240, 320, textPaint);
		
		canvas.save();
		canvas.translate(480, 320);
		mPath.reset();
		mPath.addCircle(100, 100, 100, Direction.CCW);
		canvas.clipPath(mPath, Region.Op.XOR);
		drawScene(canvas);
		canvas.restore();
		canvas.drawText("XOR", 540, 320, textPaint);
		
		canvas.save();
		canvas.translate(700, 320);
		mPath.reset();
		mPath.addCircle(100, 100, 100, Direction.CCW);
		canvas.clipPath(mPath, Region.Op.UNION);
		drawScene(canvas);
		canvas.restore();
		canvas.drawText("UNION", 700, 320, textPaint);
	}
	private void testRegion(Canvas canvas) {
		canvas.save();
		canvas.translate(10, 560);
		drawO(canvas, "ԭͼ");
		canvas.restore();
		
		//-------region1Ϊ����������region2Ϊ��������-----------//
		
		// ��������Ϊregion1 �� region2�ཻ������  [����]
		canvas.save();
		canvas.translate(320, 560);
		drawRgn(canvas, Color.BLUE, "INTERSECT", Region.Op.INTERSECT);
		canvas.restore();
		
		////��������Ϊregion1 �� region2��ͬ������  
		canvas.save();
		canvas.translate(640, 560);
		drawRgn(canvas, Color.RED, "DIFFERENCE", Region.Op.DIFFERENCE);
		canvas.restore();
		
		
		//��������Ϊregion1 �� region2���һ�������  
		canvas.save();
		canvas.translate(10, 970);
		drawRgn(canvas, Color.CYAN, "UNION", Region.Op.UNION);
		canvas.restore();
		
		//��������Ϊregion1 �� region2�ཻ֮�������
		canvas.save();
		canvas.translate(320, 970);
		drawRgn(canvas, Color.MAGENTA, "XOR", Region.Op.XOR);
		canvas.restore();
		
		//��������Ϊregion2 �� region1��ͬ������  
		canvas.save();
		canvas.translate(640, 970);
		drawRgn(canvas, Color.YELLOW, "REVERSE_DIFFERENCE", Region.Op.REVERSE_DIFFERENCE);
		canvas.restore();
		
		//��������ΪΪregion2������ 
		canvas.save();
		canvas.translate(10, 1290);
		drawRgn(canvas, Color.GREEN, "REPLACE", Region.Op.REPLACE);
		canvas.restore();
	}
	private void drawO(Canvas canvas,String msg){
		if(msg != null){
			canvas.drawText(msg, 10, 30, textPaint);
		}
		Rect rect1 = new Rect(0, 100, 300, 200);
		Rect rect2 = new Rect(100, 0, 200, 300);
		paint.setColor(Color.LTGRAY);
		canvas.drawRect(rect1, paint);
		canvas.drawRect(rect2, paint);
		Rect rect3 = new Rect(0, 0, 300, 300);
		canvas.drawRect(rect3, paint0);
	}
	private void drawRgn(Canvas canvas,int color,String msg,Region.Op op){
		Region rgn = new Region();
		Rect rect1 = new Rect(0, 100, 300, 200);
		Rect rect2 = new Rect(100, 0, 200, 300);
		rgn.set(rect1);
		rgn.op(rect2, op);
		paint.setColor(color);
		//RegionIterator����Region�е����о�����е���������ʹ�ø��࣬���ĳ��Region�����о���
		int count = drawRegion(canvas, rgn);
		Rect rect3 = new Rect(0, 0, 300, 300);
		canvas.drawRect(rect3, paint0);
		if(msg != null){
			canvas.drawText(msg, 10, 30, textPaint);
		}
		canvas.drawText("count:"+count, 10, 60, textPaint);
	} 
	/**
	 * ��������
	 * @param canvas
	 * @param rgn
	 * @return  ���������ڵľ�����Ŀ
	 */
	private int drawRegion(Canvas canvas,Region rgn){
		RegionIterator iter = new RegionIterator(rgn);
		Rect r = new Rect();
		int count = 0;
		while (iter.next(r)) {
			 canvas.drawRect(r, paint);
			 count++;
		}
		return count;
	}
	
	private void drawScene(Canvas canvas) {
		//�ü���������
		canvas.clipRect(new Rect(0, 0, 200, 200));
		//���ü��ľ��������ű���ɫ
		canvas.drawColor(Color.WHITE);
		
		paint.setColor(Color.RED);
		//����������ĶԽ���
		canvas.drawLine(0, 0, 200, 200, paint);
		
		paint.setColor(Color.GREEN);
		//��Բ
		canvas.drawCircle(60, 140, 60, paint);
	}
}





































