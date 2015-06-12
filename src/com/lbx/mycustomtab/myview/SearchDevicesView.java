package com.lbx.mycustomtab.myview;

import com.lbx.mycustomtab.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class SearchDevicesView extends BaseReView {

	/**
	 * …®√Ëµƒ±≥æ∞Õº∆¨
	 */
	private Bitmap bitmap;
	/**
	 * …®√Ëµƒ÷––ƒ∞¥≈•
	 */
	private Bitmap bitmap1;
	/**
	 * …®√Ë…»–Œ
	 */
	private Bitmap bitmap2;
	/**
	 *  «∑Ò’˝‘⁄…®√Ë
	 */
	private boolean isSearching = false;
	
	private float offsetArgs = 0;
	
	public SearchDevicesView(Context context) {
		super(context);
		initBitmap(); 
	}

	public SearchDevicesView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initBitmap(); 
	}

	public SearchDevicesView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initBitmap(); 
	}
	
	private void initBitmap() {
		 if(bitmap == null){
			 bitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.gplus_search_bg));
		 }
		 if(bitmap1 == null){
			 bitmap1 = Bitmap.createBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.locus_round_click));
		 }
		 if(bitmap2 == null){
			 bitmap2 = Bitmap.createBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.gplus_search_args));
		 }
	}

	public boolean isSearching() {
		return isSearching;
	}

	public void setSearching(boolean isSearching) {
		this.isSearching = isSearching;
		offsetArgs = 0;
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		float left = getWidth() / 2 - bitmap.getWidth() / 2;
		float top = getHeight() / 2 - bitmap.getHeight() / 2;
		//ªÊ÷∆±≥æ∞
		canvas.drawBitmap(bitmap, left, top, null);
		
		if(isSearching){
			Rect rMoon = new Rect(getWidth() / 2 - bitmap2.getWidth(),
					getHeight() / 2	, getHeight() / 2,
					getHeight() / 2 + bitmap2.getHeight());
			canvas.rotate(offsetArgs, getWidth() / 2, getHeight() / 2);
			canvas.drawBitmap(bitmap2, null, rMoon, null);
			offsetArgs += 3;
		}else{
			canvas.drawBitmap(bitmap2, getWidth() / 2 - bitmap2.getWidth(), getHeight() / 2, null);
		}
		canvas.drawBitmap(bitmap1, getWidth() / 2 - bitmap1.getWidth() / 2, getHeight() / 2 - bitmap1.getHeight() / 2, null);
		if(isSearching){
			invalidate();
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			handleActionDownEvent(event);
			break;
		case MotionEvent.ACTION_UP:
			return true;
		case MotionEvent.ACTION_MOVE:
			return true;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	private void handleActionDownEvent(MotionEvent event) {
		RectF rectF = new RectF(getWidth() / 2 - bitmap1.getWidth() / 2, 
				getHeight() / 2 - bitmap1.getHeight() / 2, 
				getWidth() / 2 + bitmap1.getWidth() / 2, 
				getHeight() / 2 + bitmap1.getHeight() / 2);
		if(rectF.contains(event.getX(), event.getY())){
			if(!isSearching()) {
				setSearching(true);
			}else{
				setSearching(false);
			}
		}
	}
}














