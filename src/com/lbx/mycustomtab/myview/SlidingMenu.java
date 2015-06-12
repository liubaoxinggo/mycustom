package com.lbx.mycustomtab.myview;

import com.lbx.mycustomtab.R;
import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SlidingMenu extends ViewGroup {

	
	private Context context;
	/**
	 * 菜单
	 */
	private LinearLayout menu;
	/**
	 * 内容
	 */
	private LinearLayout content;
	/**
	 * 菜单是否打开
	 */
	private boolean isOpen = false;
	/**
	 * 整体宽度
	 */
	private int w;
	/**
	 * 按下时的x坐标
	 */
	private int x;
	public SlidingMenu(Context context) {
		super(context);
		init(context);
	}

	
	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}


	public SlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context){
		this.context = context;
		View v = LayoutInflater.from(context).inflate(R.layout.sliding_menu, null);
		content = (LinearLayout)v.findViewById(R.id.content);
		menu = (LinearLayout)v.findViewById(R.id.menu);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		w = this.getWidth();
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			x = (int) e.getX();
			if(!isOpen){//关闭时   <--->
				
			}else{
				
			}
			break;
		case MotionEvent.ACTION_MOVE:
			float translationX = e.getX() - x;
			ViewHelper.setTranslationX(content, translationX);
			break;
		case MotionEvent.ACTION_UP:
			int temp = (int) (e.getX() - x);
			if(temp >= w /3){
				ViewHelper.setTranslationX(content, w / 3);
			}else{
				ViewHelper.setTranslationX(content, 0);
			}
			break;
		default:
			break;
		}
		return super.onTouchEvent(e);
	}
	
}























