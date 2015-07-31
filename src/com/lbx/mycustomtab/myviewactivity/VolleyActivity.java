package com.lbx.mycustomtab.myviewactivity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.lbx.mycustomtab.R;
import com.lbx.mycustomtab.adapter.MBaseAdapter;
import com.lbx.mycustomtab.util.LogUtil;
import com.lbx.mycustomtab.volley.VolleyBitmapHelper;
import com.lbx.mycustomtab.volley.VolleyHttpHelper;

public class VolleyActivity extends Activity {

	ImageView iv,iv1;
	NetworkImageView mNetworkImageView; 
	GridView gv;
	MAdapter adapter;
	List<String> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.volley);
		iv = (ImageView)findViewById(R.id.iv);
		iv1 = (ImageView)findViewById(R.id.iv1);
		gv = (GridView)findViewById(R.id.girdview);
		mNetworkImageView = (NetworkImageView)findViewById(R.id.network_image_view);
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

    /**
     * gridView 
     */
    private void setImage(){
    	String url = "http://image.baidu.com/search/index?ct=201326592&cl=2&st=-1&lm=-1&nc=1&ie=utf-8&tn=baiduimage&ipn=r&rps=1&pv=&fm=rs9&word=%E6%B5%B7%E8%B4%BC%E7%8E%8Bqq%E5%A4%B4%E5%83%8F&ofr=%E6%B5%B7%E8%B4%BC%E7%8E%8B#z=0&pn=&ic=0&st=-1&face=0&s=0&lm=-1";
//    	String url = "http://image.baidu.com/search/index?tn=baiduimage&ps=1&ct=201326592&lm=-1&cl=2&nc=1&ie=utf-8&word=%E6%B5%B7%E8%B4%BC%E7%8E%8B";
    	StringRequest request = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				list = getImgSrcList(response);
				adapter.setList(list);
				LogUtil.d("AAA", Thread.currentThread()+"StringRequest onResponse()"+list.size());
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
    /**
     * ImageLoader
     * @param v
     */
	public void test(View v){
//		String imageUrl = "http://img5.imgtn.bdimg.com/it/u=3510384511,555111286&fm=11&gp=0.jpg";
		String imageUrl = "http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg";
//		String imageUrl = "http://www.qq1234.org/uploads/allimg/150207/1543053945-11.jpg";
		VolleyBitmapHelper.getInstance().display(imageUrl, iv, 308, 308, R.drawable.p620010004, R.drawable.ic_launcher);
		setImage();
	}
	/**
	 * NetworkImageView
	 * @param v
	 */
	public void test1(View v){
//		String imageUrl = "http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg";
		String imageUrl = "http://img0.imgtn.bdimg.com/it/u=1416794918,621055276&fm=21&gp=0.jpg";
		mNetworkImageView.setDefaultImageResId(R.drawable.p620110006);
		mNetworkImageView.setErrorImageResId(R.drawable.ic_launcher);
		mNetworkImageView.setImageUrl(imageUrl, VolleyBitmapHelper.getInstance().getLoader());
	}
	/**
	 * ImageRequest
	 * @param v
	 */
	public void test2(View v){
		String imageUrl = "http://img4.imgtn.bdimg.com/it/u=2396921988,3988118924&fm=21&gp=0.jpg";
		ImageRequest mImageRequest = new ImageRequest(imageUrl, new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap response) {
				// TODO Auto-generated method stub
				iv1.setImageBitmap(response);
				LogUtil.d("AAA", Thread.currentThread()+"ImageRequest onResponse()");
			}
		}, 400, 400, Config.RGB_565, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				LogUtil.d("AAA", Thread.currentThread()+"ImageRequest onErrorResponse()");
			}
		});
		VolleyHttpHelper.getInstance(getBaseContext()).addToRequestQueue(mImageRequest);
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
		VolleyBitmapHelper.getInstance().display(list.get(position), holder.iv, 180, 180, R.drawable.ic_launcher, R.drawable.ic_launcher);
		return super.getView(position, convertView, parent);
	}
	class ViewHolder{
		ImageView iv;
		TextView tv;
	}
}



















