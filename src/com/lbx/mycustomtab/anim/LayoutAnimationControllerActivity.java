package com.lbx.mycustomtab.anim;

import java.util.ArrayList;
import java.util.HashMap;

import com.lbx.mycustomtab.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.LinearInterpolator;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class LayoutAnimationControllerActivity extends Activity {

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
		SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.item1, new String[]{"img","str"} , new int[]{R.id.iv,R.id.tv});
		gridView.setAdapter(adapter);
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.list_anim);
		LayoutAnimationController laController = new LayoutAnimationController(animation);
		//item 顺序出现
		laController.setOrder(LayoutAnimationController.ORDER_NORMAL);
		//item 随机出现
//		laController.setOrder(LayoutAnimationController.ORDER_RANDOM);
		//item 倒序出现
//		laController.setOrder(LayoutAnimationController.ORDER_REVERSE);
		//设置延时时间//注意这个地方是以秒为单位，是浮点型数据，所以要加f
		laController.setDelay(0.5f);
//		laController.setInterpolator(new CycleInterpolator(0.5f));
		//匀速
//		laController.setInterpolator(new LinearInterpolator());
		//先慢后快
		laController.setInterpolator(new DecelerateInterpolator());
		//先快后慢
//		laController.setInterpolator(new AccelerateInterpolator());
		gridView.setLayoutAnimation(laController);
	}
}
