package com.lbx.mycustomtab.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class BaseReView extends RelativeLayout {

	public Context context;
	public BaseReView(Context context) {
		super(context);
		this.context =  context; 
	}
	public BaseReView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context =  context; 
	}
	public BaseReView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context =  context; 
	}
}
