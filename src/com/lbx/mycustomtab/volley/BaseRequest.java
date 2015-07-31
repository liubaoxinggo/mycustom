package com.lbx.mycustomtab.volley;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.RetryPolicy;

public class BaseRequest<T> extends Request<T> {
 
	/**
	 * 连接超时时间
	 */
	private static final int SOCKET_TIMEOUT = 2500;
	/**
	 * 重连次数
	 */
	private static final int MAX_RETRIES = 2;
	
	public BaseRequest(int method, String url, ErrorListener listener) {
		super(method, url, listener);
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void deliverResponse(T response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		// TODO Auto-generated method stub
		Map<String, String> headers = new HashMap<String, String>();  
		headers.put("Charset", "UTF-8");  
		headers.put("Content-Type", "application/json");  
		headers.put("Accept-Encoding", "gzip,deflate");  
//		String Authorization = ConfigUtil.getInstance().getConfigString("authorization");
//		if(!"".equals("Authorization")){
//			headers.put("Authorization", Authorization);
//		}
		return headers;
	}

	@Override
	public RetryPolicy getRetryPolicy() {
		RetryPolicy rp = new DefaultRetryPolicy(SOCKET_TIMEOUT, MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
		return rp;
	}
	
}
















