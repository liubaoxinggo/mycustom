package com.lbx.mycustomtab.volley;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RequestQueue.RequestFilter;
import com.android.volley.toolbox.Volley;

public class VolleyHttpHelper {

	/**
	 * 默认的tag
	 */
	private static final String DEFAULT_TAG = "ycall";
	
	private Context mContext;
	/**
	 * 只需要一个实例
	 * Global request queue for Volley 
	 */
	private RequestQueue mRequestQueue;
	/**
	 * 只需要一个实例
	 */
	private static VolleyHttpHelper mVolleyHttpHelper;
	
	public VolleyHttpHelper(Context mContext) {
		this.mContext = mContext;
	}
	
	public static VolleyHttpHelper getInstance(Context mContext){
		if(mVolleyHttpHelper == null){
			synchronized (VolleyHttpHelper.class) {
				if(mVolleyHttpHelper == null){
					mVolleyHttpHelper = new VolleyHttpHelper(mContext);
				}
			}
		}
		return mVolleyHttpHelper;
	}

	public RequestQueue getRequestQueue(){
		if(mRequestQueue == null){
			synchronized (VolleyHttpHelper.class) {
				if(mRequestQueue == null){
					mRequestQueue = Volley.newRequestQueue(mContext);
				}
			}
		}
		mRequestQueue.start();
		return mRequestQueue;
	}
	
	/**
	 * Adds the specified request to the global queue using the Default TAG. 
	 * @param request
	 * @throws AuthFailureError 
	 */
	public <T> void addToRequestQueue(Request<T> request){
		request.setTag(DEFAULT_TAG);
		getRequestQueue().add(request);
	}
	/**
	 * Adds the specified request to the global queue, if tag is specified then 
	 * it is used else Default TAG is used. 
	 * @param request
	 * @param tag
	 * @throws AuthFailureError 
	 */
	public <T> void addToRequestQueue(Request<T> request,String tag){
		request.setTag(TextUtils.isEmpty(tag) ? DEFAULT_TAG : tag);
		getRequestQueue().add(request);
	}
	/**
	 * Cancels all pending requests by the specified TAG, it is important to 
	 * specify a TAG so that the pending/ongoing requests can be cancelled. 
	 * @param tag
	 */
	public void canclePendingRequest(Object tag){
		if(mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}
	public void canclePendingRequest(RequestFilter filter){
		if(mRequestQueue != null) {
			mRequestQueue.cancelAll(filter);
		}
	}
	
}




















