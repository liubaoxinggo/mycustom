package com.lbx.mycustomtab.picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.lbx.mycustomtab.R;
import com.lbx.mycustomtab.adapter.MBaseAdapter;
import com.lbx.mycustomtab.util.ConfigUtil;
import com.lbx.mycustomtab.util.LogUtil;
import com.lbx.mycustomtab.volley.VolleyHttpHelper;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

public class PicassoActivity extends Activity {

	ImageView iv,iv1;
	GridView gv;
	List<String> list;
	MAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picasso);
		iv = (ImageView)findViewById(R.id.iv);
		iv1 = (ImageView)findViewById(R.id.iv1);
		gv = (GridView)findViewById(R.id.girdview);
		list = new ArrayList<String>();
		adapter = new MAdapter(this, list);
		gv.setAdapter(adapter);
	}
	/**
     * 得到网页中图片的地址
     */
    private List<String> getImgSrcList(String htmlStr) {
        List<String> pics = new ArrayList<String>();

        String regEx_img0 = "thumbURL\":\"http://(.*?).jpg\""; // 图片链接地址
        String regEx_img1 = "<img.*?src=\"http://(.*?).jpg\""; // 图片链接地址
        Pattern p_image = Pattern.compile(regEx_img0, Pattern.CASE_INSENSITIVE);
        Matcher m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            String src = m_image.group(1);
            if (src.length() < 150) {
                pics.add("http://" + src + ".jpg");
            }
        }
        return pics;
    }
    private void setImage(){
    	String[] urls = {"http://image.baidu.com/search/index?ct=201326592&cl=2&st=-1&lm=-1&nc=1&ie=utf-8&tn=baiduimage&ipn=r&rps=1&pv=&fm=rs10&word=QQ%E5%A4%B4%E5%83%8F&ofr=%E5%9B%BE%E7%89%87#z=0&pn=&ic=&st=-1&face=0&s=0&lm="
    			,"http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1438326263979_R&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&word=%E6%B5%B7%E8%B4%BC%E7%8E%8B+%E5%A4%B4%E5%83%8F#z=0&pn=&ic=0&st=-1&face=0&s=0&lm=-1"
    			,"http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1438326407168_R&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&itg=0&ie=utf-8&word=%E4%B8%AA%E6%80%A7+%E5%A4%B4%E5%83%8F#z=0&pn=&ic=0&st=-1&face=0&s=0&lm=-1"
    			,"http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1438326339620_R&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&itg=0&ie=utf-8&word=%E7%BE%8E%E5%A5%B3+%E5%A4%B4%E5%83%8F#z=0&pn=&ic=0&st=-1&face=0&s=0&lm=-1"
    			,"http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1438326687328_R&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&itg=0&ie=utf-8&word=%E9%A3%8E%E6%99%AF+%E5%A4%B4%E5%83%8F#z=0&pn=&ic=0&st=-1&face=0&s=0&lm=-1"
    			,"http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1438326744777_R&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&itg=0&ie=utf-8&word=%E5%8A%A8%E7%89%A9+%E5%A4%B4%E5%83%8F#z=0&pn=&ic=0&st=-1&face=0&s=0&lm=-1"
    			,"http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1438326785376_R&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&itg=0&ie=utf-8&word=%E6%A4%8D%E7%89%A9%E5%A4%B4%E5%83%8F"
    			,"http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1438326816124_R&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&word=%E6%A4%8D%E7%89%A9+%E5%A4%B4%E5%83%8F"
    	};
    	for (int i = 0; i < urls.length; i++) {
    		StringRequest request = new StringRequest(urls[i], new Listener<String>() {
    			
    			@Override
    			public void onResponse(String response) {
    				// TODO Auto-generated method stub
    				list.addAll(list.size(), getImgSrcList(response));
    				adapter.setList(list);
    				saveList(list); 
    				LogUtil.d("AAA", Thread.currentThread()+"StringRequest onResponse():"+list.size());
    			}
    		}, new ErrorListener() {
    			
    			@Override
    			public void onErrorResponse(VolleyError error) {
    				// TODO Auto-generated method stub
    				LogUtil.d("AAA", Thread.currentThread()+"StringRequest onErrorResponse()");
    			}
    		});
    		VolleyHttpHelper.getInstance(this).addToRequestQueue(request);
		}
    }
    void saveList(List<String> list){
    	synchronized (PicassoActivity.class) {
    		ConfigUtil.getInstance().setConfigString("list", new Gson().toJson(list));
		}
    }
	public void test1(View v){
		String imageUrl = "http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg";
		 Picasso.with(this).load(imageUrl).fit().resize(200, 200).centerCrop()
		.placeholder(R.drawable.ic_launcher).error(R.drawable.po)
		.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
		.into(iv1,new Callback(){

			@Override
			public void onError() {
				// TODO Auto-generated method stub
				LogUtil.d("AAA", Thread.currentThread()+"Callback onError()");
			}

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				LogUtil.d("AAA", Thread.currentThread()+"Callback onSuccess()");
			}
			
		});
	}
	public void test2(View v){
		String listData = ConfigUtil.getInstance().getConfigString("list");
		if(TextUtils.isEmpty(listData)){
			setImage();
		}else{
			adapter.setList(new Gson().fromJson(listData, String[].class));
		}
		
	}
}
class MAdapter extends MBaseAdapter<String>{

	public MAdapter(Context context, List<String> list) {
		super(context, list);
		// TODO Auto-generated constructor stub
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.image_item_x, null);
			holder = new ViewHolder();
			holder.iv = (ImageView)convertView.findViewById(R.id.iv);
			holder.tv = (TextView)convertView.findViewById(R.id.tv);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		holder.tv.setText("index : "+position);
//		VolleyBitmapHelper.getInstance().display(list.get(position), holder.iv, 180, 180, R.drawable.ic_launcher, R.drawable.ic_launcher);
		PicassoBitmapHelper.getInstance().getPicasso().load(list.get(position)).centerInside().config(Config.RGB_565).error(R.drawable.ic_launcher)
//		.networkPolicy(NetworkPolicy.NO_STORE, NetworkPolicy.NO_CACHE)
//		.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
		.placeholder(R.drawable.po).resize(200, 200).into(holder.iv);
		return super.getView(position, convertView, parent);
	}
	class ViewHolder{
		ImageView iv;
		TextView tv;
	}
}