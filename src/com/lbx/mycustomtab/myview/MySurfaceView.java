package com.lbx.mycustomtab.myview;

import com.lbx.mycustomtab.util.LogUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements Callback{

	LoopThread thread;
	public MySurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public MySurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init(){
		LogUtil.i("AAA", "MySurfaceView init()");
		SurfaceHolder holder = getHolder();
        holder.addCallback(this); //设置Surface生命周期回调
        thread = new LoopThread(holder, getContext());
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		LogUtil.i("AAA", "MySurfaceView surfaceCreated()");
		thread.isRunning = true;
		thread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		LogUtil.i("AAA", "MySurfaceView surfaceChanged()");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		LogUtil.i("AAA", "MySurfaceView surfaceDestroyed()");
		thread.isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	class LoopThread extends Thread{
		SurfaceHolder surfaceHolder;
		Context context;
        boolean isRunning;
        float radius = 10f;
        Paint paint;
        public LoopThread(SurfaceHolder surfaceHolder,Context context){
        	 
            this.surfaceHolder = surfaceHolder;
            this.context = context;
            isRunning = false;
            
            paint = new Paint();
            paint.setColor(Color.YELLOW);
            paint.setStyle(Paint.Style.STROKE);
        }
        
        @Override
        public void run() {
        	// TODO Auto-generated method stub
        	super.run();

            Canvas c = null;
 
            while(isRunning){
                try{
                    synchronized (surfaceHolder) {
                        c = surfaceHolder.lockCanvas(null);
                        doDraw(c);
                        //通过它来控制帧数执行一次绘制后休息50ms
                        Thread.sleep(50);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
        public void doDraw(Canvas c){
            //这个很重要，清屏操作，清楚掉上次绘制的残留图像
            c.drawColor(Color.BLACK);
            c.translate(200, 200);
            c.drawCircle(0,0, radius++, paint);
 
            if(radius > 100){
                radius = 10f;
            }
        }
	}
}


















