package com.lbx.mycustomtab.myview;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.view.DragEvent;
import android.view.View;
/**
 * 记录运动轨迹【匀速运动】
 * @author liubaoxing
 *
 */
@SuppressLint("DrawAllocation") public class MyView1 extends View implements Runnable {

	private boolean isStop = false;
	/**
	 * 初始位置
	 */
	private int x = 0,y = 0;
	/**
	 *  每次移动的距离
	 */
	private int xMove = 10,yMove = 20;
	/**
	 * 是否移动到了边缘
	 */
	private boolean isW,isH;
	
	/**
	 * 记录碰到边缘的点
	 */
	ArrayList<Point> points;
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == 0x101){
				MyView1.this.update();
				MyView1.this.invalidate();;
			}
		}
	};
	//构造方法
	public MyView1(Context context) {
		super(context);
		setFocusable(true);
		points = new ArrayList<Point>();
		points.add(new Point(x, y));
		setBackgroundColor(Color.GRAY);
		new Thread(this).start();
	}

	@Override
	public void run() {
		while(!isStop){
			handler.sendEmptyMessage(0x101);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint p = new Paint();
		p.setColor(Color.CYAN);
		canvas.drawCircle(x, y, 10, p);
		Paint pLine = new Paint();
		pLine.setColor(Color.BLACK);
		Point lastP = null ;
		for (int i = 0; i < points.size(); i++) {
			Point p1 = points.get(i);
			if((i + 1) < points.size()){
				Point p2 = points.get(i + 1);
				canvas.drawLine(p1.x, p1.y, p2.x, p2.y, pLine);  
			}else{
				lastP = p1;
			}
		}
		if(lastP != null){
			canvas.drawLine(lastP.x, lastP.y, x, y, pLine);  
		}
	}
	private void update(){
		int w = this.getWidth();
		int h = this.getHeight();
		if(points.size() > 40 ) points.clear();
		if(!isW){
			x += xMove;
			if(x >= w){
				isW = true;
				points.add(new Point(w, y));
			}
		}else{
			x -= xMove;
			if(x <= 0){
				isW = false;
				points.add(new Point(0, y));
			}
		}
		if(!isH){
			y += yMove;
			if(y >= h){
				isH = true;
				points.add(new Point(x, h));
			}
		}else{
			y -= yMove;
			if(y <= 0){
				isH = false;
				points.add(new Point(x, 0));
			}
		}
		System.out.println("x = "+x+" / y = "+y);
	}
	public void stop(){
		 isStop = true;
	}
	public void start(){
		isStop = false;
		new Thread(this).start();
	}
	public boolean isStop(){
		return isStop;
	}
	@Override
	public boolean onDragEvent(DragEvent event) {
		// TODO Auto-generated method stub
		return super.onDragEvent(event);
	}

}
