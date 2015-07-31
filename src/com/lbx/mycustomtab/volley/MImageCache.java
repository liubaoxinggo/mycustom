package com.lbx.mycustomtab.volley;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.lbx.mycustomtab.util.LogUtil;

public class MImageCache implements ImageCache {

	private LruCache<String, Bitmap> mCache;
	
	public MImageCache() {
		super();
		int maxSize = 10 * 1024 * 1024;//10M
		mCache = new LruCache<String, Bitmap>(maxSize){

			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				// TODO Auto-generated method stub
				return bitmap.getRowBytes() * bitmap.getHeight();
			}
			
		};
	}

	@Override
	public Bitmap getBitmap(String url) {
		// TODO Auto-generated method stub
		LogUtil.d("AAA", Thread.currentThread()+"MImageCache - getBitmap() : "+url);
		return mCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		// TODO Auto-generated method stub
		LogUtil.d("AAA", Thread.currentThread()+"MImageCache - putBitmap() : "+url+" / size = "+(bitmap.getRowBytes() * bitmap.getHeight()));
		mCache.put(url, bitmap);
	}

}















