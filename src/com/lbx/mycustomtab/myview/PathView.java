package com.lbx.mycustomtab.myview;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
/**
 * 参考：http://blog.csdn.net/harvic880925/article/details/38926877
 *     http://kevinems.com/151.html
 *     //画布的使用讲解
 *     http://blog.csdn.net/harvic880925/article/details/39080931
 * @author liubaoxing
 *
 */
public class PathView extends View {

	Paint paint;
	Paint textPaint;
	float phase = 0f;
	boolean isRun = false;
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0x001:
				init();
				phase++;
				invalidate();
				break;
			case 0x002:
				
				break;

			default:
				break;
			}
		}
		
	};
	public PathView(Context context) {
		super(context);
		init();
	}
	private void init(){
		paint = new Paint();
		//指定是否使用抗锯齿功能，如果使用，会使绘图速度变慢  
		paint.setAntiAlias(true);
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(3f);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStyle(Style.STROKE);
		
		//初始化写文字的画笔
	    textPaint = new Paint(paint);
		textPaint.setColor(Color.DKGRAY);
		textPaint.setTextSize(24);
		//只会将水平方向拉伸，高度不会变  
		textPaint.setTextScaleX(2f);
		textPaint.setStrokeWidth(1);
		textPaint.setStyle(Style.FILL_AND_STROKE);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(0xffaaaaaa);
		test1(canvas);
		test2(canvas);
		testWH(canvas);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(isRun){
			isRun = false;
		}else{
			isRun = true;
			testThread();
		}
		return super.onTouchEvent(event);
	}
	private void testThread(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (isRun) {
					try {
						handler.sendEmptyMessage(0x001);
						Thread.sleep(100);
					} catch (InterruptedException e) {
						handler.sendEmptyMessage(0x002);
					}
				}
			}
		}).start();
	}
	private void testWH(Canvas canvas) {
		int canvasW = canvas.getWidth();
		int canvasH = canvas.getHeight();
		int w = this.getWidth();
		int h = this.getHeight();
		StringBuilder sb = new StringBuilder();
		sb.append("canvasW = ").append(canvasW).append(",canvasH = "+canvasH).append("\n");
		canvas.drawText(sb.toString(), 260, 720, textPaint);
		StringBuilder sb1 = new StringBuilder();
		sb1.append("this.W = ").append(w).append(",this.H = "+h);	
		canvas.drawText(sb1.toString(), 260, 750, textPaint);
		textPaint.setColor(0xff00f0f0);
		textPaint.setTextSize(44);
		canvas.drawText("点击试试", 260, 850, textPaint);
	}
	private void test2(Canvas canvas) {
		Path path0 = new Path();
		path0.moveTo(220, 100);
		//从上一个点添加一个三次贝塞尔曲线，经过控制点(x1, y1), (x2, y2)，在(x3, y3) 结束
		path0.cubicTo(250, 30, 320, 150, 400, 10);
		//从上一个点添加一个二次贝塞尔曲线，经过控制点(x1, y1)，在(x2, y2) 结束
		path0.quadTo(390,80, 500, 150); 
		canvas.drawPath(path0, paint);
		canvas.drawTextOnPath("A1234567890987654321B", path0, 10f, 0f, textPaint);
		canvas.save();
		
		Path path1 = new Path();
		path1.moveTo(250, 150);
		Path path2 = new Path();
		path2.moveTo(250, 150);
		path2.lineTo(950, 150);
		for (int i = 0; i < 15; i++) {
			path1.lineTo(i * 20 + 250, (float)Math.random() * 60 + 150);
		}
		int[] colors = {Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW,Color.BLACK,Color.MAGENTA,Color.CYAN};
		PathEffect[] effects  = new PathEffect[7];
		//不使用路径效果
		effects[0] = null;
		paint.setPathEffect(effects[0]);
		paint.setColor(colors[0]);
		canvas.drawPath(path1, paint);
		canvas.drawTextOnPath("没有使用路径效果[0]", path2, 5, 5, textPaint);
		canvas.translate(0, 80);
		//CornerPathEffect 可以使用圆角来代替尖锐的角从而对基本图形的形状尖锐的边角进行平滑
		effects[1] = new CornerPathEffect(10);
		paint.setPathEffect(effects[1]);
		paint.setColor(colors[1]);
		canvas.drawPath(path1, paint);
		canvas.drawTextOnPath("CornerPathEffect[1]", path2, 5, 5, textPaint);
		canvas.translate(0, 80);
		
		//DashPathEffect  可以使用DashPathEffect来创建一个虚线的轮廓(短横线/小圆点)，而不是使用实线。你还可以指定任意的虚/实线段的重复模式
		//可以使paint画出类似虚线的样子,并且可以任意指定虚实的排列方式.代码中的float数组,必须是偶数长度,且>=2,指定了多少长度的实线之后再画多少长度的空白.
		//如本代码中,绘制长度2的实线,再绘制长度4的空白,再绘制长度8的实线,再绘制长度6的空白,依次重复.phase是偏移量,可以不用理会.
		effects[2] = new DashPathEffect(new float[]{2,4,8,6}, phase);
		paint.setPathEffect(effects[2]);
		paint.setColor(colors[2]);
		canvas.drawPath(path1, paint);
		canvas.drawTextOnPath("DashPathEffect[2]", path2, 5, 5, textPaint);
		canvas.translate(0, 80);
		//DiscretePathEffect  与DashPathEffect相似，但是添加了随机性。当绘制它的时候，需要指定每一段的长度和与原始路径的偏离度。
		effects[3] = new DiscretePathEffect(3.0f, 5.0f);
		paint.setPathEffect(effects[3]);
		paint.setColor(colors[3]);
		canvas.drawPath(path1, paint);
		canvas.drawTextOnPath("DiscretePathEffect[3]", path2, 5, 5, textPaint);
		canvas.translate(0, 80);
		
		Path path3 = new Path();
		path3.addRect(0, 0, 8, 8, Direction.CCW);
		// PathDashPathEffect  这种效果可以定义一个新的形状(路径)并将其用作原始路径的轮廓标记。
		effects[4] = new PathDashPathEffect(path3, 12f, phase,PathDashPathEffect.Style.ROTATE);
		paint.setPathEffect(effects[4]);
		paint.setColor(colors[4]);
		canvas.drawPath(path1, paint);
		canvas.drawTextOnPath("PathDashPathEffect[4]", path2, 5, 5, textPaint);
		canvas.translate(0, 80);
		
		//顺序地在一条路径中添加两种效果，这样每一种效果都可以应用到原始路径中，而且两种结果可以结合起来。
		effects[5] = new SumPathEffect(effects[1], effects[2]);
		paint.setPathEffect(effects[5]);
		paint.setColor(colors[5]);
		canvas.drawPath(path1, paint);
		canvas.drawTextOnPath("SumPathEffect[5]=[1,2]", path2, 5, 5, textPaint);
		canvas.translate(0, 80);
		//将两种效果组合起来应用，先使用第一种效果，然后在这种效果的基础上应用第二种效果。
        //对象形状的PathEffect的改变会影响到形状的区域。这就能够保证应用到相同形状的填充效果将会绘制到新的边界中。
		effects[6] = new ComposePathEffect(effects[1], effects[2]);
		paint.setPathEffect(effects[6]);
		paint.setColor(colors[6]);
		canvas.drawPath(path1, paint);
		canvas.drawTextOnPath("ComposePathEffect[6]=[1,2]", path2, 5, 5, textPaint);
		canvas.translate(0, 80);
		canvas.restore();
	}
	
	private void test1(Canvas canvas) {
		Path path = new Path();
		//设置起点
		path.moveTo(10, 10);//
		//第一条直线的终点，也是第二条的起点
		path.lineTo(10, 100);
		//第二条直线
		path.lineTo(200, 100);
		//闭环
		path.close();
		canvas.drawPath(path, paint);
		//------- //矩形路径//----------//
		Path path1 = new Path();
		RectF rect1 = new RectF(10, 120, 210, 320);
		//顺时针和逆时针方向主要影响文字的排版走向
		path1.addRect(rect1, Direction.CCW);//逆时针 方向
		canvas.drawPath(path1, paint);
		canvas.drawTextOnPath("1234567890987654321", path1, 10f, 5f, textPaint);
		
		Path path2 = new Path();
		RectF rect2 = new RectF(10, 340, 210, 540);
		//顺时针和逆时针方向主要影响文字的排版走向
		path2.addRect(rect2, Direction.CW);//顺时针方向
		canvas.drawPath(path2, paint);
		canvas.drawTextOnPath("1234567890987654321", path2, 10f, 5f, textPaint);
		
		//------- //圆角矩形路径,四个圆角可以分别定义大小//----------//
		Path path3 = new Path();
		RectF rect3 = new RectF(10, 560, 210, 760);
		//只能构建统一圆角大小,float rx：所产生圆角的椭圆的横轴半径；float ry：所产生圆角的椭圆的纵轴半径；
		path3.addRoundRect(rect3, 20, 20, Direction.CCW); 
		canvas.drawPath(path3, paint);
		canvas.drawTextOnPath("1234567890987654321", path3, 10f, 5f, textPaint);
		
		Path path4 = new Path();
		RectF rect4 = new RectF(10, 780, 210, 980);
		//float[] radii：必须传入8个数值，分四组，分别对应每个角所使用的椭圆的横轴半径和纵轴半径
		//如｛x1,y1,x2,y2,x3,y3,x4,y4｝，其中，x1,y1对应第一个角的（左上角）用来产生圆角的椭圆的横轴半径和纵轴半径，
		//其它类推……
		float[] radii = {10f,10f,20f,20f,30f,40f,50f,60f}; 
		path4.addRoundRect(rect4, radii, Direction.CW);
		canvas.drawPath(path4, paint);
		canvas.drawTextOnPath("1234567890987654321", path4, 10f, 5f, textPaint);
		
		//------- //圆形路径//----------//
		Path path5 = new Path();
		path5.addCircle(110, 1100, 100, Direction.CW);
		canvas.drawPath(path5, paint);
		canvas.drawTextOnPath("A1234567890987654321B", path5, 0f, 5f, textPaint);
		
		Path path6 = new Path();
		path6.addCircle(110, 1320, 100, Direction.CCW);
		canvas.drawPath(path6, paint);
		canvas.drawTextOnPath("A1234567890987654321B", path6, 0f, 5f, textPaint);
		
		//------- //椭圆形路径//----------//
		Path path7 = new Path();
		//椭圆所对的矩形
		RectF rect7 = new RectF(10, 1440, 210, 1540);
		path7.addOval(rect7, Direction.CW);
		canvas.drawPath(path7, paint);
		canvas.drawTextOnPath("A1234567890987654321B", path7, 0f, 5f, textPaint);
		
		//------- //弧形路径//----------//
		Path path8 = new Path();
		//弧形所对应的矩形 
		RectF rect8 = new RectF(10, 1560, 210, 1760);
		//startAngle ：开始的角度，X轴正方向为0度,sweepAngel：持续的度数；
		path8.addArc(rect8, 0, 190);
		canvas.drawRect(rect8, paint);
		canvas.drawPath(path8, paint);
		canvas.drawTextOnPath("A1234567890987654321B", path8, 0f, 5f, textPaint);
	}
	
}


























