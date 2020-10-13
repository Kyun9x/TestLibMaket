/**
 * Copyright 2013 Mani Selvaraj
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lib.marketplace.restful;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lib.marketplace.util.Log;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


/**
 * Custom implementation of Request<T> class which converts the HttpResponse
 * obtained to Java class objects. Uses GSON library, to parse the response
 * obtained. Ref - JsonRequest<T>
 *
 * @author Mani Selvaraj
 */

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class GsonRequest<T> extends Request<T> {

    private final Listener<T> mListener;
    private final String mRequestBody;

    private Gson mGson;
    private Class<T> mJavaClass;

    public GsonRequest(int method, String url, Class<T> cls,
                       String requestBody, Listener<T> listener,
                       ErrorListener errorListener) {
        super(method, url, errorListener);
        mGson = new Gson();
        mRequestBody = requestBody;
        mJavaClass = cls;
        mListener = listener;

    }

    @Override
    protected void deliverResponse(T response) {
        if (mListener != null)
            mListener.onResponse(response);
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

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    StandardCharsets.UTF_8);
            Log.i("GsonRequest.parseNetworkResponse()", "JSON  " + jsonString);
            T parsedGSON = mGson.fromJson(jsonString, mJavaClass);
            return Response.success(parsedGSON,
                    HttpHeaderParser.parseCacheHeaders(response));

        } catch (JsonSyntaxException je) {
            return Response.error(new ParseError(je));
        }

    }

    @Override
    public byte[] getBody() {
        try {
            return mRequestBody == null ? super.getBody() : mRequestBody
                    .getBytes(getParamsEncoding());
        } catch (UnsupportedEncodingException uee) {
            VolleyLog
                    .wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            mRequestBody, getParamsEncoding());
            Log.i("GsonRequest.getBody()", " get byte null");
            return null;
        } catch (AuthFailureError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getBodyContentType() {
        if (!TextUtils.isEmpty(mRequestBody) && (getMethod() == Method.POST || getMethod() == Method.PUT)) {
            return "application/json; charset=utf-8";
        }
        return super.getBodyContentType();
    }
}