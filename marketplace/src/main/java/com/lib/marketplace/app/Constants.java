package com.lib.marketplace.app;

import android.graphics.Color;

public class Constants {

    public static final int SPLASH_TIME = 1000;

    public static final String ROOT_FOLDER = ".foodbook";
    public static final String CACHE_FOLDER = "cache";
    public static final String THUMBNAIL_FOLDER = "thumbnail";
    public static final String DOWNLOAD_FOLDER = "download";
    public static final String MENU_CACHE = "menucache";
    public static final String MENU_CACHE_RADIO = "menuradiocache";

    //SCM
//    public static final String REST_SCM = "https://apiscmdev.ipos.vn/api/partners/v1/";// Release SCM
    public static final String REST_SCM = "https://apiscmdev.ipos.vn/api/partners/v1/";// Test SCM
    public static final String SCM_ACCESS_TOKEN = "4HPR9FGCBEITGBYW7EYPVZEAJCFGMYKDSRGC";
    public static final String SCM_SECRET_KEY = "42e78966590635e6e88ac9beec4e01ea";

    //Test order online
//    public static final String REST_BILLING = "https://posapi.ipos.vn/api/v3/pos-manager/";// that
    public static final String REST_BILLING = "https://apibilling.iposdev.com/billing/api/";// test
    public static final String BILLING_ACCESS_TOKEN = "GYIXZKY3UOQNF55S37IGUNWYLN5SPRFY";

    //Test Box
    public static final String REST_BOX = "https://apinotification-dev.ipos.vn/api/v1/";// test
    public static final String BOX_ACCESS_TOKEN = "YLGWVDY6Q8EKJEZQQRBQNWOD2P0Z4K1PGRMD";
    public static final String BOX_SECRET_KEY = "be5e8a68bb4d7a3029c8fa89f46d9a76";


    public static final String APP_ID_ONESIGNAL = "APPID";
    public static final String API_KEY_ONESIGNAL = "APIKEY";
    public static final String KEY_PUSH_TOKEN = "KEYPUSHTOKEN";

    public static final int ONE_SECOND = 1000;

    public static final boolean IS_LOG = true;
    public static final String GETIMEI = "GETIMEI";
    public static final String GETMAC = "GETMAC";
    public static final String KEY_USER = "KEY_USER";

    public static final String GCM_SENDER_ID = "1046584390823";
        public static final String POSPC = "POSPC";
    public static final String FABI = "FABI";


    public static final String KEY_REGDEVICE = "KEY_REGDEVICE";
    public static final String KEY_ANDROID_STETHO = "KEYANDROIDSTETHO";
    public static final long TIME_DELAY_RETRY = 1500;

    public static final String KEY_DATA = "KEY_DATA";
    public static final String KEY_INAP = "KEY_INAP";
    public static final String KEY_NOTIFTY = "KEY_NOTIFTY";

    public static final String KEY_REPORT_DETAIL = "KEY_REPORT_DETAIL";
    public static final String KEY_WORDSTATION_ID = "KEY_WORDSTATION_ID";
    public static final String KEY_LONG = "KEY_LONG";
    public static final String KEY_LAT = "KEY_LAT";
    public static final String ACTION_LOCATION_CHANGE = "ACTION_LOCATION_CHANGE";


    public static final String keylog = "ipos";


    public static CharSequence http = "http";


    public static final String URL_HOME = "http://ipos.vn/";

    public static final String URL_GMAP_IMG = "https://maps.googleapis.com/maps/api/staticmap?center=#position&zoom=15&size=250x330&maptype=roadmap&markers=color:blue%7Clabel:S%7C#name";
    public static final String URL_FIND_PATH = "https://maps.google.com/maps?output=dragdir&saddr=#mylocation&daddr=#destination";


    public static final String KEY_SUBJECT = "KEY_SUBJECT";

    public static final String KEY_URL = "KEY_URL";

    public static final String KEY_TITLE = "KEY_TITLE";

    public static final String OBJECT = "OBJECT";
    public static final String DATA = "DATA";


    public static final String KEY_TYPE = "TYPE";
    public static final String KEY_DELIVERY_STATUS = "DELIVERY_STATUS";
    public static final String KEY_AHAMOVE_CODE = "AHAMOVE_CODE";

    public static final long ON_DAY = 24 * 60 * 60 * 1000;

    public static final String HEIGHT_KEY = "HEIGHT_KEY";

    public static final String KEY_ORDER_DETAIL = "KEY_ORDER_DETAIL";


    public static final String KEY_PHONE = "KEY_PHONE";

    public static final String MAIL_FB = "cskh@foodbook.vn";

    public static final String IPOS_VN = "ipos.com.vn";

    public static final int[] chartColors = {
            Color.rgb(217, 80, 138)
            , Color.rgb(156, 202, 68)
            , Color.rgb(235, 50, 0)
            , Color.rgb(106, 59, 206)
            , Color.rgb(53, 194, 209)
            , Color.rgb(193, 37, 82)
            , Color.rgb(255, 102, 0)
            , Color.rgb(245, 199, 0)
            , Color.rgb(106, 150, 31)
            , Color.rgb(179, 100, 53)
    };

    public static class CounlyComponent {
        public static final String DASHBOARD_DAILY_REPORT = "DASHBOARD_DAILY_REPORT";
        public static final String DASHBOARD_MONTHLY_REPORT = "DASHBOARD_MONTHLY_REPORT";
        public static final String DASHBOARD_HOURLY_REPORT = "DASHBOARD_HOURLY_REPORT";
        public static final String DASHBOARD_RECEIPT_REPORT = "DASHBOARD_RECEIPT_REPORT";
        public static final String DASHBOARD_RESERVATION_TABLE_REPORT = "DASHBOARD_RESERVATION_TABLE_REPORT";
        public static final String DASHBOARD_DELIVERY_REPORT = "DASHBOARD_DELIVERY_REPORT";

        public static final String CHART_REPORT = "CHART_REPORT";

        public static final String OTHER_REPORT = "OTHER_REPORT";

        public static final String MENU = "MENU";
    }

    public static class CounlyFeature {
        public static final String DASHBOARD_DAILY_REPORT = "DASHBOARD_DAILY_REPORT";
        public static final String DASHBOARD_MONTHLY_REPORT = "DASHBOARD_MONTHLY_REPORT";
        public static final String DASHBOARD_HOURLY_REPORT = "DASHBOARD_HOURLY_REPORT";
        public static final String DASHBOARD_RECEIPT_REPORT = "DASHBOARD_RECEIPT_REPORT";
        public static final String DASHBOARD_RESERVATION_TABLE_REPORT = "DASHBOARD_RESERVATION_TABLE_REPORT";
        public static final String DASHBOARD_DELIVERY_REPORT = "DASHBOARD_DELIVERY_REPORT";

        public static final String CHART_COVERAGE_REPORT = "CHART_COVERAGE_REPORT";
        public static final String CHART_LIST_TABLE_REPORT = "CHART_LIST_TABLE_REPORT";
        public static final String CHART_MONTHLY_REVENUE_REPORT = "CHART_MONTHLY_REVENUE_REPORT";
        public static final String CHART_YEAR_REVENUE_REPORT = "CHART_YEAR_REVENUE_REPORT";
        public static final String CHART_REVENUE_BY_GROUP_ITEM = "CHART_REVENUE_BY_GROUP_ITEM";
        public static final String CHART_REVENUE_BY_ITEM_TYPE = "CHART_REVENUE_BY_ITEM_TYPE";
        public static final String CHART_AVERAGE_BILL = "CHART_AVERAGE_BILL";

        public static final String REPORT_ID = "REPORT_ID";

        public static final String MENU = "VIEW_MENU";
        public static final String EDIT_ITEM = "EDIT_ITEM";
    }

    public static class CounlyEvent {
        public static final String REPORT = "REPORT";
        public static final String FEATURE = "FEATURE";
    }

    public static class Event {
        public static final String Event = "Event";
        public static final String Notify = "Notify";
        public static final String Inbox = "Inbox";
    }

    public static class OrderType {
        public static final String OrderOnline = "OrderOnline";
        public static final String OrderNvl = "OrderNvl";
    }

    public static class OrderNvlStatus {
        public static final String COMPLETED = "COMPLETED";
        public static final String CANCELED = "CANCELED";
        public static final String PENDING = "PENDING";
        public static final String CONFIRMED = "CONFIRMED";
    }

    public static class ArticlesStatus {
        public static final String CONFIRMED = "CONFIRMED";
        public static final String PENDING = "PENDING";
        public static final String CANCELED = "CANCELED";
        public static final String SOLD = "SOLD";
        public static final String EXPIRED = "EXPIRED";
        public static final String expired = "expired";
        public static final String selling = "selling";
    }

    public static class NotifyStatus {
        public static final String SCM_ARTICLE = "SCM_ARTICLE";
        public static final String survey = "survey";
        public static final String campaign = "campaign";
    }

    public static class BROADCAST {
        public static final String EVENT = "BROAD_EVENT";
        public static final String BROAD_NOTIFY = "BROAD_NOTIFY";
        public static final String BROAD_BOX = "BROAD_BOX";
        public static final String BROAD_ARTICLES = "BROAD_ARTICLES";
        public static final String BROAD_NVL = "BROAD_NVL";
        public static final String REFRESH = "REFRESH";
    }

    public static class Date {
        public static class Format {
            public static final String DD_MM_YYYY = "dd/MM/yyyy";
            public static final String HH_MM_DD_MM_YYYY = "HH:mm dd/MM/yyyy";
            public static final String YYYY_MM_DD_HH_MM_SS_2 = "yyyy-MM-dd HH:mm:ss";
        }

        public static class Unit {
            public static final String SECOND = "second";
            public static final String MILLISECOND = "millisecond";
        }
    }
}
