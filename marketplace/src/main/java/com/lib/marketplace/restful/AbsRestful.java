package com.lib.marketplace.restful;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.lib.marketplace.app.ApplicationIpos;
import com.lib.marketplace.app.Constants;
import com.lib.marketplace.util.SharedPref;


public class AbsRestful {
    protected String TAG = getClass().getSimpleName();
    protected Context context;
    protected SharedPref config;
    private static int time_out_retry = 20 * 1000;
    private static int mMaxNumRetries = 1;
    private static float mBackoffMultiplier = 1.0f;
    protected String REST_BILLING = Constants.REST_BILLING;
    protected String BASE_URL_SCM = Constants.REST_SCM;
    protected String BASE_URL_BOX = Constants.REST_BOX;
    protected String GET_NOTIFICATION = "https://onesignal.com/api/v1/notifications"; //API lay ds tang

    protected String ORDER_ONLINE_SERVICELIST = REST_BILLING + "partner/service/menu";
    protected String ORDER_CHECK_VOUCHER = REST_BILLING + "partner/check_voucher";
    protected String ORDER_CHECK_AUTO_PROMOTION = REST_BILLING + "partner/check_auto_promo";
    protected String ORDER_HISTORY = REST_BILLING + "partner/online_order/list";
    protected String ORDER_HISTORY_DETAIL = REST_BILLING + "partner/online_order/detail";
    protected String ORDER_ONLINE = REST_BILLING + "partner/online_order/create";
    protected String ORDER_CHECK_PAYMENT = REST_BILLING + "payment/check";
    protected String ORDER_CREATE_PAYMENT_ZALO = REST_BILLING + "payment/createOrder";
    protected String ORDER_PAYMENT_MOMO = REST_BILLING + "payment/momo/payapp";
    protected String ORDER_LOCATE = REST_BILLING + "common/locate";
    protected String ORDER_BRAND = REST_BILLING + "common/brand";
    protected String ORDER_STORE = REST_BILLING + "common/store";

    protected String SCM_BRANDS = BASE_URL_SCM + "brands";
    protected String SCM_PRODUCTS = BASE_URL_SCM + "products";
    protected String SCM_INVOICES = BASE_URL_SCM + "invoices";
    protected String SCM_ARTICLES = BASE_URL_SCM + "articles";
    protected String SCM_ARTICLES_COUNT = BASE_URL_SCM + "articles/count-status";
    protected String SCM_TAG = BASE_URL_SCM + "tags";
    protected String SCM_LOCATIONS = BASE_URL_SCM + "locations";
    protected String SCM_UPLOAD = BASE_URL_SCM + "upload/image";
    protected String SCM_CITIES = BASE_URL_SCM + "cities";
    protected String SCM_DISTRICTS = BASE_URL_SCM + "districts";
    protected String SCM_PRECINCTS = BASE_URL_SCM + "precincts";
    protected String SCM_FEEDBACK = BASE_URL_SCM + "feedback";

    protected String BOX_REGIS_FIREBASE = BASE_URL_BOX + "users/firebase-registration";
    protected String BOX_GET_NOTIFY = BASE_URL_BOX + "services/notifications";
    protected String BOX_GET_INBOX = BASE_URL_BOX + "services/notifications/box";
    protected String BOX_CHECK_INBOX = BASE_URL_BOX + "services/notification/checked-info";

    public static String TAG_REPORT = "TAG_RP";
    public static String TAG_LOGIN = "TAG_LOGIN";
    public static String TAG_CHANGE_PASS = "TAG_CHANGE_PASS";
    public static String TAG_GET_DAILY_REPORT = "TAG_GET_DAILY_REPORT";
    public static String TAG_GET_BILL_SIZE_ITEM_REPORT = "TAG_GET_BILL_SIZE_ITEM_REPORT";
    public static String TAG_GET_BILL_SIZE_AMOUNT_REPORT = "TAG_GET_BILL_SIZE_AMOUNT_REPORT";
    public static String TAG_GET_MONTH_REPORT = "TAG_GET_MONTH_REPORT";
    public static String TAG_GET_WORK_STATION = "TAG_GET_WORK_STATION";
    public static String TAG_GET_OBJECTBASE = "TAG_GET_OBJECTBASE";
    public static String TAG_GET_TIMING_AMOUNT = "TAG_GET_TIMING_AMOUNT";
    public static String TAG_GET_TIMING_BILL = "TAG_GET_TIMING_BILL";
    public static String TAG_GET_POWER_TABLE = "TAG_GET_POWER_TABLE";
    public static String TAG_GET_POWER_TABLE_BY_TIME = "TAG_GET_POWER_TABLE_BY_TIME";
    public static String TAG_GET_AMOUNT_CHART_BYMONTH = "TAG_GET_AMOUNT_CHART_BYMONTH";
    public static String TAG_GET_AMOUNT_CHART_BYYEAR = "TAG_GET_AMOUNT_CHART_BYYEAR";
    public static String TAG_GET_AMOUNT_CHART_BYITEMTYPE = "TAG_GET_AMOUNT_CHART_BYITEMTYPE";
    public static String TAG_GET_AMOUNT_CHART_BYITEMCLASS = "TAG_GET_AMOUNT_CHART_BYITEMCLASS";
    public static String TAG_GET_REPORT_GROUP = "TAG_GET_REPORT_GROUP";
    public static String TAG_GET_REPORT = "TAG_GET_REPORT";
    public static String TAG_GET_GENERATE_REPORT = "TAG_GET_GENERATE_REPORT";
    public static String TAG_GET_SET_OBJECT = "TAG_GET_SET_OBJECT";
    public static String TAG_GET_COMFIM = "TAG_GET_COMFIM";
    public static String TAG_CHANGE_PASSWORD = "TAG_CHANGE_PASSWORD";
    public static String TAG_GET_DELIVERY_REPORT = "TAG_GET_DELIVERY_REPORT";
    public static String TAG_GET_BOOKING_REPORT = "TAG_GET_BOOKING_REPORT";
    public static String TAG_GET_ITEM_TYPE = "TAG_GET_ITEM_TYPE";
    public static String TAG_GET_ITEM = "TAG_GET_ITEM";
    public static String TAG_AHAMOVE_GET_ORDER_DETAIL = "TAG_AHAMOVE_GET_ORDER_DETAIL";
    public static String TAG_GET_AREA = "TAG_GET_AREA";
    public static String TAG_GET_TABLE = "TAG_GET_TABLE";
    public static String TAG_GET_AVG_MONTHLY_AMOUNT = "TAG_GET_AVG_MONTHLY_AMOUNT";
    public static String TAG_NOTIFICATION = "TAG_NOTIFICATION";
    public static String TAG_CMS = "TAG_CMS";

    public AbsRestful() {

    }

    public AbsRestful(Context context) {
        config = new SharedPref(context);
    }


    protected <T> void addReq(Request<T> req, String TAG) {
        ApplicationIpos.getInstance().addToRequestQueue(req, TAG);
    }

    public static DefaultRetryPolicy reTryPolicy() {
        return new DefaultRetryPolicy(time_out_retry, mMaxNumRetries,
                mBackoffMultiplier);
    }

    public static final int TIME_OUT = 20 * 1000;


    protected String convertJsonValid(String response) {
        if (response != null && !response.equals("")) {
            response = response.replace("\\\"", "\"");
            response = response.replace("\\\\\"", "");

            response = response.substring(1, response.length() - 1);
        }
        return response;
    }
}