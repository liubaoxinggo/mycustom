package com.lbx.mycustomtab.volley;

import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

public class VolleyBitmapHelper {

	private ImageLoader  loader;
	private static VolleyBitmapHelper mVolleyBitmapHelper;
	public VolleyBitmapHelper() {
		loader = new ImageLoader(VolleyHttpHelper.getInstance(com.lbx.mycustomtab.TApplication.nowApplication).getRequestQueue(), 
				new MImageCache());
	}
	 
	public static VolleyBitmapHelper getInstance(){
		if(mVolleyBitmapHelper == null){
			synchronized (VolleyBitmapHelper.class) {
				if(mVolleyBitmapHelper == null){
					mVolleyBitmapHelper = new VolleyBitmapHelper();
				}
			}
		}
		return mVolleyBitmapHelper;
	}
	
	public ImageLoader getLoader() {
		return loader;
	}

	@SuppressWarnings("static-access")
	public void display(String imageUrl,ImageView view,int maxWidth,int maxHeight,int defaultImageResId, int errorImageResId){
		loader.get(imageUrl, loader.getImageListener(view, defaultImageResId, errorImageResId) , maxWidth, maxHeight);
	}
	
	
	
	
	
	
	
	
	
}







