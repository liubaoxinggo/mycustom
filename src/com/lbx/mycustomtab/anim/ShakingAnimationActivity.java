package com.lbx.mycustomtab.anim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lbx.mycustomtab.R;

public class ShakingAnimationActivity extends Activity {


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
		 
		Animation animation = new RotateAnimation(-5, 5, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		animation.setDuration(300);
		animation.setRepeatCount(Animation.INFINITE);
		animation.setRepeatMode(Animation.REVERSE);
		animation.setInterpolator(new LinearInterpolator());
		anims[0] = animation;
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
			convertView.startAnimation(anims[0]);
			return convertView;
		}
		
		class ViewHolder{
			ImageView iv;
			TextView tv;
		}
}
	 
}
