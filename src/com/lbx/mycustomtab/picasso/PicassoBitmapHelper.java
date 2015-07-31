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
		//ͼƬ�����Ͻǻؼ������Ǳ�ʾ����ɫ��ʾ��Memory����ͼƬ����ɫ��ʾ��Disk����ͼƬ����ɫ��ʾ��Network����ͼƬ
		mPicasso.setIndicatorsEnabled(true);
		//�Ƿ������־
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
