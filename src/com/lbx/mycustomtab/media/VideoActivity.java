package com.lbx.mycustomtab.media;

import java.io.File;

import com.lbx.mycustomtab.R;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {

	private VideoView video;
	
	private MediaController ctrl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.video);
		String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/ucall/dsgw/pp.mp4";
		video = (VideoView)findViewById(R.id.videoview);
		File clip = new File(path);
		Log.i("infos", "????????"+clip.exists());
		if(clip.exists()){
			video.setVideoPath(clip.getAbsolutePath());
			ctrl = new MediaController(this);
			ctrl.setMediaPlayer(video);
			video.setMediaController(ctrl);
			video.requestFocus();
		}
	}
}
