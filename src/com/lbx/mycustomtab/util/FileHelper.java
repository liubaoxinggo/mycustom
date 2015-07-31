package com.lbx.mycustomtab.util;

import java.io.File;

import android.os.Environment;

public class FileHelper {

	/**
	 * 
	 * @param cacheDir eg. /ucall/image
	 * @return
	 */
	public static File getDiskCacheFile(String diskCachePath){
		String cachePath =  Environment.getExternalStorageDirectory().getAbsolutePath()+diskCachePath;
        System.out.println("diskCachePath = "+cachePath);
        File file = new File(cachePath);
        if(!file.exists()){
        	file.mkdirs();
        }
        return file;
	}
}
