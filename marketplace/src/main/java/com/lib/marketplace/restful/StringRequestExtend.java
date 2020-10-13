package com.lib.marketplace.restful;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class StringRequestExtend extends StringRequest {

    public StringRequestExtend(int method, String url,
                               Listener<String> listener, ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        // TODO Auto-generated constructor stub
    }

    private Map<String, String> headers = new HashMap<String, String>();
    private Map<String, String> params = new HashMap<String, String>();

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        // Log.i("GsonRequest.getHeaders()", "get header");
        return headers;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {

        // Log.i("GsonRequest.getParams()", "get params ");
        return params;
    }

    public void setHeader(String title, Object content) {
        content = content == null ? "" : content;
        headers.put(title, content.toString());
    }

    public void setParams(String title, Object content) {
        content = content == null ? "" : content;
        params.put(title, content.toString());

    }

}
