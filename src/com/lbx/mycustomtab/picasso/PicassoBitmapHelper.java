package com.lbx.mycustomtab.picasso;

import java.io.File;

import android.os.Environment;

import com.lbx.mycustomtab.TApplication;
import com.lbx.mycustomtab.util.FileHelper;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

public class PicassoBitmapHelper {

	private static PicassoBitmapHelper mPicassoBitmapHelper;
	private Picasso mPicasso;
	
	public PicassoBitmapHelper() {
		super();
		// TODO Auto-generated constructor stub
		File cacheDir = FileHelper.getDiskCacheFile("/ucall/picasso");
//		mPicasso = new Picasso.Builder(TApplication.nowApplication).downloader(new OkHttpDownloader(cacheDir)).build();
		mPicasso = Picasso.with(TApplication.nowApplication);
		//图片的左上角回家上三角表示：绿色表示从Memory加载图片；蓝色表示从Disk加载图片；红色表示从Network加载图片
		mPicasso.setIndicatorsEnabled(true);
		//是否输出日志
		mPicasso.setLoggingEnabled(true);
	}

	public static  PicassoBitmapHelper getInstance(){
		if(mPicassoBitmapHelper == null){
			synchronized (PicassoBitmapHelper.class) {
				if(mPicassoBitmapHelper == null){
					mPicassoBitmapHelper = new PicassoBitmapHelper();
				}
			}
		}
		return mPicassoBitmapHelper;
	}

	public Picasso getPicasso() {
		return mPicasso;
	}
	
}
