package com.lib.marketplace.app;

import android.app.Application;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.firebase.FirebaseApp;
import com.lib.marketplace.BuildConfig;
import com.lib.marketplace.bussiness.CartBussiness;
import com.lib.marketplace.bussiness.FontBussiness;
import com.lib.marketplace.bussiness.LocationBussiness;
import com.lib.marketplace.model.orderonline.DmBrand;
import com.lib.marketplace.model.orderonline.DmStore;
import com.lib.marketplace.restful.AbsRestful;
import com.lib.marketplace.restful.NukeSSLCerts;
import com.lib.marketplace.restful.OkHttpStack;
import com.lib.marketplace.util.FormatNumberUtil;
import com.lib.marketplace.util.SharedPref;

import java.util.ArrayList;

import ly.count.android.sdk.Countly;
import ly.count.android.sdk.CountlyConfig;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import vn.momo.momo_partner.AppMoMoLib;
import vn.zalopay.sdk.ZaloPaySDK;


public class ApplicationIpos extends Application {

    private RequestQueue mRequestQueue;

    private static ApplicationIpos sInstance;

    private FontBussiness mFontBussiness;
    private LocationBussiness locationBussiness;
    private CartBussiness cartBussiness;
    private ArrayList<DmBrand> listBrand = new ArrayList<>();
    private ArrayList<DmStore> listStore = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        FirebaseApp.getApps(this);

        sInstance = this;
        Log.d("APP", "ON APP");
        initBussiness();
        Log.i("APP", "ON APP Width/height ");
    }

    private void initBussiness() {
        mFontBussiness = new FontBussiness(this);
        locationBussiness = new LocationBussiness(this);
        cartBussiness = new CartBussiness();
        FormatNumberUtil.initInStance();

        Countly.applicationOnCreate();

        CountlyConfig config = (new CountlyConfig(this, "12ee977476a2b38f5a6423f336c8e9f1e2ec7529", "https://analytic.ipos.vn"));
        config.setLoggingEnabled(true);
        config.enableCrashReporting();
        config.setApplication(this);
        Countly.sharedInstance().init(config);
        ZaloPaySDK.getInstance().initWithAppId(684);


        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
//            AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT);
            AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.PRODUCTION);
        } else {
            AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.PRODUCTION);
            SharedPref config1 = new SharedPref(this);
            String sethoe = config1.getString(Constants.KEY_ANDROID_STETHO, "1");
            if ("1".equals(sethoe)) {
                Stetho.initializeWithDefaults(this);
            }
        }
    }


    public void initListBrand(ArrayList<DmBrand> item) {
        if (item != null && item.size() > 0) {
            listBrand.clear();
            listBrand.addAll(item);
        }
    }

    public void initListStore(ArrayList<DmStore> item) {
        if (item != null && item.size() > 0) {
            listStore.clear();
            listStore.addAll(item);
        }
    }

    public ArrayList<DmBrand> getListBrand() {
        return listBrand;
    }

    public ArrayList<DmStore> getListStore() {
        return listStore;
    }

    public void changeDeviceIdCounly(String deviceId) {
        Countly.sharedInstance().changeDeviceIdWithMerge(deviceId);
    }


    public static synchronized ApplicationIpos getInstance() {
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            //mRequestQueue = Volley.newRequestQueue(getApplicationContext());
            OkHttpClient.Builder builderOk = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new StethoInterceptor());
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)

                        .build();
                ConnectionSpec nothttps = new ConnectionSpec.Builder(ConnectionSpec.CLEARTEXT)

                        .build();
                ArrayList<ConnectionSpec> list = new ArrayList<>();
                list.add(spec);
                list.add(nothttps);
                builderOk.connectionSpecs(list);
                mRequestQueue = Volley.newRequestQueue(this, new OkHttpStack(builderOk.build()));
            } else {
                NukeSSLCerts.nuke();
                mRequestQueue = Volley.newRequestQueue(this);
            }

            VolleyLog.DEBUG = Constants.IS_LOG;
        }

        return mRequestQueue;
    }

    public static final String TAG = "VolleyPatternsFooD";

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        req.setRetryPolicy(AbsRestful.reTryPolicy());
        Log.i("App.addToRequestQueue() tag " + tag,
                "url restful " + req.getUrl());
        getRequestQueue().add(req);
    }


    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);
        req.setRetryPolicy(AbsRestful.reTryPolicy());
        Log.i("App.addToRequestQueue()", "url restful " + req.getUrl());
        getRequestQueue().add(req);
    }

    /**
     * Cancels all pending requests by the specified TAG, it is important to
     * specify a TAG so that the pending/ongoing requests can be cancelled.
     *
     * @param tag
     */
    public void cancelPendingRequests(Object tag) {
        Log.i("Cancel Request", "destroy request " + tag);
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public LocationBussiness getLocationBussiness() {
        return locationBussiness;
    }

    public void setLocationBussiness(LocationBussiness locationBussiness) {
        this.locationBussiness = locationBussiness;
    }

    public FontBussiness getFontBussiness() {

        return mFontBussiness;
    }

    public CartBussiness getCartBussiness() {
        return cartBussiness;
    }

    public void setCartBussiness(CartBussiness cartBussiness) {
        this.cartBussiness = cartBussiness;
    }
}
