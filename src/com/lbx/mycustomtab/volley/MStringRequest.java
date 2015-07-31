package com.lbx.mycustomtab.volley;

import java.io.UnsupportedEncodingException;

import com.android.volley.NetworkResponse;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

public class MStringRequest extends BaseRequest<String> {
	private final Listener<String> mListener;
	
	public MStringRequest(int method, String url,Listener<String> listener, ErrorListener errorlistener) {
		super(method, url, errorlistener);
		this.mListener = listener;
	}

	public MStringRequest(String url, Listener<String> mListener,ErrorListener listener) {
		this(Method.GET, url,mListener, listener);
	}
	@Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

}
