package com.lbx.mycustomtab.anim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.lbx.mycustomtab.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class CustomItemAnimationActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_animation_contorller);
		init();
	}
	private void init(){
		GridView gridView = (GridView)findViewById(R.id.girdview);
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		for (int i = 0; i < 31; i++) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("img", R.drawable.p620010004+i);
			item.put("str", "index:"+(i));
			list.add(item);
		}
		Animation[] anims = new Animation[4]; 
		//right-->left
		anims[0] = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0f);
		//left -->right
		anims[1] = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0f);
		//bottom-->top
		anims[2] = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0f);
		//top -->bottom
		anims[3] = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0f);
		for (int i = 0; i < anims.length; i++) {
			anims[i].setDuration(1000);
		}
		MySimpleAdapter adapter = new MySimpleAdapter(this, anims, list);
		gridView.setAdapter(adapter);
	}
	private class MySimpleAdapter extends BaseAdapter{
		
		Context context;
		Animation[] anims;
		ArrayList<HashMap<String, Object>> list;
		LayoutInflater inflater;
		Random rand;
		
		public MySimpleAdapter(Context context, Animation[] anims,
				ArrayList<HashMap<String, Object>> list) {
			super();
			this.context = context;
			this.anims = anims;
			this.list = list;
			inflater = LayoutInflater.from(context);
			rand = new Random();
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}
		
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}
		
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if(convertView == null){
				convertView = inflater.inflate(R.layout.item1, null);
				holder = new ViewHolder();
				holder.iv = (ImageView)convertView.findViewById(R.id.iv);
				holder.tv = (TextView)convertView.findViewById(R.id.tv);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder)convertView.getTag();
			}
			HashMap<String, Object> map  = list.get(position);
			holder.iv.setImageResource((Integer)map.get("img"));
			holder.tv.setText((String)map.get("str"));
			convertView.startAnimation(anims[ rand.nextInt(4)]);
			return convertView;
		}
		
		class ViewHolder{
			ImageView iv;
			TextView tv;
		}
}
	 
}






