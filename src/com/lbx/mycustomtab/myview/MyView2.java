package com.lbx.mycustomtab.myview;

import com.lbx.mycustomtab.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.SweepGradient;
import android.view.View;
/**
 *  
 * @author liubaoxing
 *
 */
@SuppressLint("DrawAllocation") public class MyView2 extends View implements Runnable {

	public MyView2(Context context) {
		super(context);
		 
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//设置画布的颜色
		canvas.drawColor(Color.GRAY);
		//实例化Paint
		Paint p  = new Paint();
		//设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
		p.setAntiAlias(true);
		//设置画笔颜色
		p.setColor(Color.YELLOW);
		//设置画笔样式
		p.setStyle(Paint.Style.STROKE);//[画出的图形都是空心的]
		//设置笔画粗细
		p.setStrokeWidth(3);
		//画圆
		canvas.drawCircle(140, 140, 130, p);
		//画矩形
		canvas.drawRect(10, 290, 270, 550, p);
		canvas.drawRect(10, 570, 270, 800, p);
		//声明矩形
		RectF re = new RectF(10, 820, 270, 1000);
//		canvas.drawRect(re, p);
		//矩形 re 的内切椭圆
		canvas.drawOval(re, p);
		//实例化路径
		Path path = new Path();
		//移动到指定点
		path.moveTo(10, 1150);
		//画线
		path.lineTo(270, 1150);
		path.lineTo(140, 1020);
		//关闭路径【把起点和终点连接起来】
		path.close();
		//画路径
		canvas.drawPath(path, p);//画出的是三角形
		//==========================================================
		
		
		//设置画笔样式
		p.setStyle(Paint.Style.FILL);//[画出的图形都是实心的]
		//设置画笔颜色
		p.setColor(Color.BLUE);
		
		//画圆
		canvas.drawCircle(450, 140, 130, p);
		//画矩形
		canvas.drawRect(310, 290, 570, 550, p);
		canvas.drawRect(310, 570, 570, 800, p);
		//声明矩形
		RectF re1 = new RectF(310, 820, 570, 1000);
//		canvas.drawRect(re1, p);
		//矩形 re 的内切椭圆
		canvas.drawOval(re1, p);
		
		//实例化路径
		Path path2 = new Path();
		//移动到指定点
		path2.moveTo(310, 1150);
		//画线
		path2.lineTo(570, 1150);
		path2.lineTo(440, 1020);
		//关闭路径【把起点和终点连接起来】
		path2.close();
		//画路径
		canvas.drawPath(path2, p);//画出的是三角形
		
		//线性渲染
		Shader mShader = new LinearGradient(0, 0, 100, 100,
				new int[]{Color.RED,Color.DKGRAY,Color.GREEN}, null, Shader.TileMode.CLAMP);//Shader.TileMode.CLAMP边缘拉伸.
		//环形【光束，辐射】渲染 [环形渲染的原心最好与被渲染图形的圆心一致，随半径的变化会出现光晕效果或光晕叠加效果]
		Shader mShader0 = new RadialGradient(730, 140, 20, Color.YELLOW, Color.RED, TileMode.MIRROR);
		//为画笔设置线性渲染
		p.setColor(Color.BLACK);
		p.setShader(mShader0);
		
		//画圆
		canvas.drawCircle(730, 140, 130, p);
		
		//线性渲染
		Shader mShader1 = new LinearGradient(0, 0, 100, 100,
				new int[]{Color.BLUE,Color.YELLOW}, null, Shader.TileMode.MIRROR);//Shader.TileMode.MIRROR在水平方向和垂直方向交替景象, 两个相邻图像间没有缝隙.
		//为画笔设置线性渲染
		p.setShader(mShader1);
		//画矩形
		canvas.drawRect(600, 290, 860, 550, p);
		//线性渲染
		Shader mShader2 = new LinearGradient(0, 0, 100, 100,
				new int[]{Color.RED,Color.GREEN}, new float[]{0.4f,0.4f}, Shader.TileMode.REPEAT);//Shader.TileMode.REPEAT在水平方向和垂直方向重复摆放,两个相邻图像间有缝隙缝隙.
		//为画笔设置线性渲染
		p.setShader(mShader2);
		canvas.drawRect(600, 570, 860, 800, p);	
		//线性渲染
		Shader mShader3 = new LinearGradient(0, 0, 20, 20, Color.GREEN, Color.YELLOW, TileMode.REPEAT) ;
		//为画笔设置线性渲染
		p.setShader(mShader3);
		//声明矩形
		RectF re2 = new RectF(600, 820, 860, 1000);
//		canvas.drawRect(re1, p);
		//矩形 re 的内切椭圆
		canvas.drawOval(re2, p);
		//环形渲染 
		Shader mShader4 = new RadialGradient(0, 0, 30, Color.BLUE, Color.RED, TileMode.REPEAT);
		p.setShader(mShader4);
		//实例化路径
		Path path3 = new Path();
		//移动到指定点
		path3.moveTo(600, 1150);
		//画线
		path3.lineTo(860, 1150);
		path3.lineTo(730, 1020);
		//关闭路径【把起点和终点连接起来】
		path3.close();
		//画路径
		canvas.drawPath(path3, p);//画出的是三角形
		
		//扫描渐变   围绕一个中心点扫描渐变，类似于雷达扫描
		Shader sweepGradient = new SweepGradient(140, 1300, new int[]{Color.BLUE,Color.RED,Color.GREEN}, null);
		p.setShader(sweepGradient);
		canvas.drawCircle(140, 1300, 130, p);
		
		Shader linearGradient = new LinearGradient(0, 0, 20, 20, Color.GREEN, Color.YELLOW, TileMode.REPEAT) ;
		Shader radialGradient = new RadialGradient(420, 1300, 20, Color.YELLOW, Color.RED, TileMode.MIRROR);
		Shader sweepGradient1 = new SweepGradient(420, 1300, new int[]{Color.BLUE,Color.RED,Color.GREEN}, null);
	    Shader composeShader = new ComposeShader(linearGradient, radialGradient, PorterDuff.Mode.DARKEN);
	    p.setShader(composeShader);
	    canvas.drawCircle(420, 1300, 130, p);
	    Shader bitmapShader = new BitmapShader(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher), TileMode.MIRROR, TileMode.MIRROR);
	    p.setShader(bitmapShader);
	    canvas.drawRect(10, 1450, 600, 1880, p);
	    
	    
	}
	
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	} 
	
}


















