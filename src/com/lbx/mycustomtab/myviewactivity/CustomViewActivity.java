package com.lbx.mycustomtab.myviewactivity;

import android.app.Activity;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.lbx.mycustomtab.R;
import com.lbx.mycustomtab.util.LogUtil;

public class CustomViewActivity extends Activity {

	ImageView ivMove;
	float x,y,x1,y1;
	int w,h;
	RectF rect;
	int temp = 80;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_view);
		ivMove = (ImageView)findViewById(R.id.iv_move);
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		x = ivMove.getX();
		y = ivMove.getY();
		w = ivMove.getWidth();
		h = ivMove.getHeight();
		rect = new RectF(x, y, x + w, y + h);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		
		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			x1 = e.getX();
			y1 = e.getY() - temp;
			// ACTION_DOWN : x1 = 141.35406 ; y1 = 537.0565 --- x = 0.0 ; y = 324.0 --- w = 162 ; h = 162
			// ACTION_DOWN : x1 = 109.90813 ; y1 = 494.7698 --- x = 0.0 ; y = 324.0 --- w = 162 ; h = 162

			LogUtil.i("infos", "ACTION_DOWN : x1 = "+x1+" ; y1 = "+y1+" --- x = "+x+" ; y = "+y+" --- w = "+w+" ; h = "+h);
			break;
		case MotionEvent.ACTION_MOVE:
			if(rect.contains(x1, y1)){
				float x2 = e.getX();
				float y2 = e.getY() - temp;
				if(Math.abs(x2 - x1) > 10 || Math.abs(y2 - y1) > 10 ){
					ivMove.setX(x2);
					ivMove.setY(y2);
				}
				LogUtil.i("infos", "ACTION_MOVE : x2 = "+x2+" ; y2 = "+y2+" ----- x1 = "+x1+" ; y1 = "+y1);
			}
			break;
		case MotionEvent.ACTION_UP:
			if(rect.contains(x1, y1)){
				 x = e.getX();
				 y = e.getY() - temp;
				 rect = new RectF(x, y, x + w, y + h);
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(e);
	}
}
