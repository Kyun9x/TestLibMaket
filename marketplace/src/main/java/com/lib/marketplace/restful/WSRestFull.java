package com.lib.marketplace.restful;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.ipos.iposmanage.model.marrketplace.AddressModel;
import com.ipos.iposmanage.model.marrketplace.AddressModelData;
import com.ipos.iposmanage.model.marrketplace.ArticlesCountModelData;
import com.ipos.iposmanage.model.marrketplace.ArticlesImageModelData;
import com.ipos.iposmanage.model.marrketplace.ArticlesModel;
import com.ipos.iposmanage.model.marrketplace.ArticlesModelData;
import com.ipos.iposmanage.model.marrketplace.ArticlesModelDataObject;
import com.ipos.iposmanage.model.marrketplace.FeedbackModelData;
import com.ipos.iposmanage.model.marrketplace.GetPushModel;
import com.ipos.iposmanage.model.marrketplace.InBoxModelData;
import com.ipos.iposmanage.model.marrketplace.InBoxModelDataObject;
import com.ipos.iposmanage.model.marrketplace.InboxModel;
import com.ipos.iposmanage.model.marrketplace.LocationModelData;
import com.ipos.iposmanage.model.marrketplace.LocationModelDataObject;
import com.ipos.iposmanage.model.marrketplace.NvlBrandModelData;
import com.lib.marketplace.model.marketplace.NvlModelData;
import com.lib.marketplace.model.marketplace.NvlOnlineModelData;
import com.lib.marketplace.model.marketplace.NvlOnlineModelDataList;
import com.ipos.iposmanage.model.marrketplace.TagsModelData;
import com.lib.marketplace.app.Constants;
import com.lib.marketplace.model.orderonline.DmCallBackMoMo;
import com.lib.marketplace.paser.orderonline.RestAllDmBrand;
import com.lib.marketplace.paser.orderonline.RestAllDmCheckAutoPromotion;
import com.lib.marketplace.paser.orderonline.RestAllDmCheckVoucher;
import com.lib.marketplace.paser.orderonline.RestAllDmLocate;
import com.lib.marketplace.paser.orderonline.RestAllDmServiceListOrigin;
import com.lib.marketplace.paser.orderonline.RestAllDmStore;
import com.lib.marketplace.paser.orderonline.RestDmHistoryOrderOnline;
import com.lib.marketplace.paser.orderonline.RestDmOrderOnline;
import com.lib.marketplace.paser.orderonline.RestDmQRCode;
import com.lib.marketplace.util.SharedPref;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;


public class WSRestFull extends AbsRestful {

    public WSRestFull(Context context) {
        this.context = context;
        config = new SharedPref(context);
    }


    public void apiOrderOnline_ServiceList(String productCode, Response.Listener<RestAllDmServiceListOrigin> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(ORDER_ONLINE_SERVICELIST);
        params.AddParam("productCode", productCode);

        GsonRequest<RestAllDmServiceListOrigin> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                RestAllDmServiceListOrigin.class, null, response, error);
        req.setHeader("accessToken", Constants.BILLING_ACCESS_TOKEN);
        addReq(req, TAG_CMS);
    }

    public void apiCheckVoucher(String json, Response.Listener<RestAllDmCheckVoucher> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(ORDER_CHECK_VOUCHER);

        GsonRequest<RestAllDmCheckVoucher> req = new GsonRequest<>(
                Request.Method.POST, params.toString(),
                RestAllDmCheckVoucher.class, json, response, error);
        req.setHeader("accessToken", Constants.BILLING_ACCESS_TOKEN);
        addReq(req, TAG_CMS);
    }

    public void apiCheckAutoPromotion(String json, Response.Listener<RestAllDmCheckAutoPromotion> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(ORDER_CHECK_AUTO_PROMOTION);

        GsonRequest<RestAllDmCheckAutoPromotion> req = new GsonRequest<>(
                Request.Method.POST, params.toString(),
                RestAllDmCheckAutoPromotion.class, json, response, error);
        req.setHeader("accessToken", Constants.BILLING_ACCESS_TOKEN);
        addReq(req, TAG_CMS);
    }

    public void apiOrderHistory(Response.Listener<RestDmHistoryOrderOnline> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(ORDER_HISTORY);

        GsonRequest<RestDmHistoryOrderOnline> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                RestDmHistoryOrderOnline.class, null, response, error);
        req.setHeader("accessToken", Constants.BILLING_ACCESS_TOKEN);
        addReq(req, TAG_CMS);
    }

    public void apiOrderHistory(String orderCode, Response.Listener<RestDmOrderOnline> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(ORDER_HISTORY_DETAIL);
        params.AddParam("orderCode", orderCode);

        GsonRequest<RestDmOrderOnline> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                RestDmOrderOnline.class, null, response, error);
        req.setHeader("accessToken", Constants.BILLING_ACCESS_TOKEN);
        addReq(req, TAG_CMS);
    }

    public void apiOrderOnline(String json, Response.Listener<RestDmOrderOnline> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(ORDER_ONLINE);

        GsonRequest<RestDmOrderOnline> req = new GsonRequest<>(
                Request.Method.POST, params.toString(),
                RestDmOrderOnline.class, json, response, error);
        req.setHeader("accessToken", Constants.BILLING_ACCESS_TOKEN);
        addReq(req, TAG_CMS);
    }

    public void apiOrderCheckPayment(String companyId, String storeId, String brandId, String paymentMethod, String tranId, Response.Listener<RestDmOrderOnline> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(ORDER_CHECK_PAYMENT);
        params.AddParam("companyId", companyId);
        params.AddParam("storeId", storeId);
        params.AddParam("brandId", brandId);
        params.AddParam("paymentMethod", paymentMethod);
        params.AddParam("tranId", tranId);

        GsonRequest<RestDmOrderOnline> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                RestDmOrderOnline.class, null, response, error);
        req.setHeader("accessToken", Constants.BILLING_ACCESS_TOKEN);
        addReq(req, TAG_CMS);
    }

    public void apiZaloPaymentCreate(String companyId, String storeId, String brandId, String paymentMethod, double amount, String tranId, String desc, Response.Listener<RestDmQRCode> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(ORDER_CREATE_PAYMENT_ZALO);
        params.AddParam("companyId", companyId);
        params.AddParam("storeId", storeId);
        params.AddParam("brandId", brandId);
        params.AddParam("paymentMethod", paymentMethod);
        params.AddParam("amount", amount);
        params.AddParam("tranId", tranId);
        params.AddParam("desc", desc);

        GsonRequest<RestDmQRCode> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                RestDmQRCode.class, null, response, error);
        req.setHeader("accessToken", Constants.BILLING_ACCESS_TOKEN);
        addReq(req, TAG_CMS);
    }

    public void apiPaymentMoMo(String json, Response.Listener<DmCallBackMoMo> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(ORDER_PAYMENT_MOMO);

        GsonRequest<DmCallBackMoMo> req = new GsonRequest<>(
                Request.Method.POST, params.toString(),
                DmCallBackMoMo.class, json, response, error);
        req.setHeader("accessToken", Constants.BILLING_ACCESS_TOKEN);
        addReq(req, TAG_CMS);
    }

    public void apiLocate(Response.Listener<RestAllDmLocate> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(ORDER_LOCATE);

        GsonRequest<RestAllDmLocate> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                RestAllDmLocate.class, null, response, error);
        req.setHeader("accessToken", Constants.BILLING_ACCESS_TOKEN);
        addReq(req, TAG_CMS);
    }

    public void apiBrand(String companyId, Response.Listener<RestAllDmBrand> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(ORDER_BRAND);
        if (companyId != null) {
            params.AddParam("companyId", companyId);
        }

        GsonRequest<RestAllDmBrand> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                RestAllDmBrand.class, null, response, error);
        req.setHeader("accessToken", Constants.BILLING_ACCESS_TOKEN);
        addReq(req, TAG_CMS);
    }

    public void apiStore(String companyId, Response.Listener<RestAllDmStore> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(ORDER_STORE);
        if (companyId != null) {
            params.AddParam("companyId", companyId);
        }

        GsonRequest<RestAllDmStore> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                RestAllDmStore.class, null, response, error);
        req.setHeader("accessToken", Constants.BILLING_ACCESS_TOKEN);
        addReq(req, TAG_CMS);
    }

    public void apiSCMProducts(String cityId, Response.Listener<NvlModelData> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_PRODUCTS);
        params.AddParam("active", 1);
        if (cityId != null) {
            params.AddParam("city_uid", cityId);
        }

        GsonRequest<NvlModelData> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                NvlModelData.class, null, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMBrands(Response.Listener<NvlBrandModelData> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_BRANDS);
        params.AddParam("active", 1);

        GsonRequest<NvlBrandModelData> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                NvlBrandModelData.class, null, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMTags(Response.Listener<TagsModelData> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_TAG);

        GsonRequest<TagsModelData> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                TagsModelData.class, null, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMLocation(Response.Listener<LocationModelData> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_LOCATIONS);

        GsonRequest<LocationModelData> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                LocationModelData.class, null, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMLocationCreate(String json, Response.Listener<LocationModelDataObject> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_LOCATIONS);

        GsonRequest<LocationModelDataObject> req = new GsonRequest<>(
                Request.Method.POST, params.toString(),
                LocationModelDataObject.class, json, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMLocationEdit(String json, Response.Listener<LocationModelDataObject> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_LOCATIONS);

        GsonRequest<LocationModelDataObject> req = new GsonRequest<>(
                Request.Method.PUT, params.toString(),
                LocationModelDataObject.class, json, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMCity(Response.Listener<AddressModelData> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_CITIES);
        params.AddParam("results_per_page", 1000);

        GsonRequest<AddressModelData> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                AddressModelData.class, null, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMDistricts(AddressModel addressModel, Response.Listener<AddressModelData> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_DISTRICTS);
        if (addressModel != null) {
            if (!TextUtils.isEmpty(addressModel.getCity_id())) {
                params.AddParam("city_uid", addressModel.getCity_id());
            }
        }

        GsonRequest<AddressModelData> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                AddressModelData.class, null, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMPrecincts(AddressModel addressModel, Response.Listener<AddressModelData> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_PRECINCTS);
        if (addressModel != null) {
            if (!TextUtils.isEmpty(addressModel.getCity_id())) {
                params.AddParam("city_uid", addressModel.getCity_id());
            }
            if (!TextUtils.isEmpty(addressModel.getDistrict_id())) {
                params.AddParam("district_uid", addressModel.getDistrict_id());
            }
        }

        GsonRequest<AddressModelData> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                AddressModelData.class, null, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMFeedback(String json, Response.Listener<FeedbackModelData> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_FEEDBACK);

        GsonRequest<FeedbackModelData> req = new GsonRequest<>(
                Request.Method.POST, params.toString(),
                FeedbackModelData.class, json, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMArticles(String cityId, Response.Listener<ArticlesModelData> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_ARTICLES);
        if (cityId != null) {
            params.AddParam("city_uid", cityId);
        }
        GsonRequest<ArticlesModelData> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                ArticlesModelData.class, null, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMArticlesDetail(String uId, Response.Listener<ArticlesModelDataObject> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_ARTICLES);
        if (uId != null) {
            params.AddParam("uid", uId);
        }
        GsonRequest<ArticlesModelDataObject> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                ArticlesModelDataObject.class, null, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMArticlesByStatus(ArticlesModel articlesModel, Response.Listener<ArticlesModelData> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_ARTICLES);
        if (articlesModel != null) {
            if(articlesModel.getStatus() != null){
                params.AddParam("status", articlesModel.getStatus());
            }
            if(articlesModel.getBrand_id() != null){
                params.AddParam("brand_id", articlesModel.getBrand_id());
            }
            if(articlesModel.getCompany_id() != null){
                params.AddParam("company_id", articlesModel.getCompany_id());
            }
            if(articlesModel.getAuthor_id() != null){
                params.AddParam("author_id", articlesModel.getAuthor_id());
            }
            if(articlesModel.getData_type() != null){
                params.AddParam("data_type", articlesModel.getData_type());
            }
                params.AddParam("type", "myArticle");
        }
        GsonRequest<ArticlesModelData> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                ArticlesModelData.class, null, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMUpload(File filePath, Response.Listener<ArticlesImageModelData> onresponse) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file",filePath.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"),filePath))
                .build();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(SCM_UPLOAD + "?thumb_size=100, 100")
                .method("POST", body)
                .addHeader("access-token", Constants.SCM_ACCESS_TOKEN)
                .addHeader("secret-key", Constants.SCM_SECRET_KEY)
                .build();

//            okhttp3.Response response = client.newCall(request).execute();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, okhttp3.Response response) throws IOException {
                    onresponse.onResponse(new Gson().fromJson(response.body().string(), ArticlesImageModelData.class));
                }
            });
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public void apiSCMArticlesCreate(String json, Response.Listener<ArticlesModelDataObject> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_ARTICLES);
//        params.AddParam("file", MultipartBody.Part );

        GsonRequest<ArticlesModelDataObject> req = new GsonRequest<>(
                Request.Method.POST, params.toString(),
                ArticlesModelDataObject.class, json, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMArticlesEdit(String json, Response.Listener<ArticlesModelDataObject> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_ARTICLES);
//        params.AddParam("file", MultipartBody.Part );

        GsonRequest<ArticlesModelDataObject> req = new GsonRequest<>(
                Request.Method.PUT, params.toString(),
                ArticlesModelDataObject.class, json, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMArticlesCount(ArticlesModel articlesModel, Response.Listener<ArticlesCountModelData> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_ARTICLES_COUNT);
        if (articlesModel != null) {
            if(articlesModel.getBrand_id() != null){
                params.AddParam("brand_id", articlesModel.getBrand_id());
            }
            if(articlesModel.getCompany_id() != null){
                params.AddParam("company_id", articlesModel.getCompany_id());
            }
        }

        GsonRequest<ArticlesCountModelData> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                ArticlesCountModelData.class, null, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMInvoices(String json, Response.Listener<NvlOnlineModelData> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_INVOICES);

        GsonRequest<NvlOnlineModelData> req = new GsonRequest<>(
                Request.Method.POST, params.toString(),
                NvlOnlineModelData.class, json, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMInvoicesHistory(String company_id, Response.Listener<NvlOnlineModelDataList> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_INVOICES);
        params.AddParam("company_id",company_id);

        GsonRequest<NvlOnlineModelDataList> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                NvlOnlineModelDataList.class, null, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiSCMInvoicesHistoryDetail(String company_id, String uId, Response.Listener<NvlOnlineModelData> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(SCM_INVOICES);
        params.AddParam("company_id",company_id);
        params.AddParam("uid",uId);

        GsonRequest<NvlOnlineModelData> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                NvlOnlineModelData.class, null, response, error);
        req.setHeader("access-token", Constants.SCM_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.SCM_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiBoxRegisFirebase(String json, Response.Listener<GetPushModel> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(BOX_REGIS_FIREBASE);

        GsonRequest<GetPushModel> req = new GsonRequest<>(
                Request.Method.POST, params.toString(),
                GetPushModel.class, json, response, error);
        req.setHeader("access-token", Constants.BOX_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.BOX_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiBoxRead(String uId, Response.Listener<InBoxModelDataObject> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(BOX_GET_INBOX);
        if(uId != null){
            params.AddParam("uid",uId);
        }

        GsonRequest<InBoxModelDataObject> req = new GsonRequest<>(
                Request.Method.PUT, params.toString(),
                InBoxModelDataObject.class, null, response, error);
        req.setHeader("access-token", Constants.BOX_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.BOX_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiBoxInbox(InboxModel inbox, Response.Listener<InBoxModelData> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(BOX_GET_INBOX);
        if (inbox != null) {
            if(inbox.getPage() != null){
                params.AddParam("page",inbox.getPage());
            }
            if(inbox.getResults_per_page() != null){
                params.AddParam("results_per_page",inbox.getResults_per_page());
            }
            if(inbox.getUser_id() != null){
                params.AddParam("user_id",inbox.getUser_id());
            }
            if(inbox.getBrand_id() != null){
                params.AddParam("brand_id",inbox.getBrand_id());
            }
            if(inbox.getCompany_id() != null){
                params.AddParam("company_id",inbox.getCompany_id());
            }
        }

        GsonRequest<InBoxModelData> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                InBoxModelData.class, null, response, error);
        req.setHeader("access-token", Constants.BOX_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.BOX_SECRET_KEY);
        addReq(req, TAG_CMS);
    }

    public void apiNotifyList(InboxModel inbox, Response.Listener<InBoxModelData> response, Response.ErrorListener error) {
        CreateResfulString params = new CreateResfulString(BOX_GET_NOTIFY);
        if (inbox != null) {
            if(inbox.getPage() != null){
                params.AddParam("page",inbox.getPage());
            }
            if(inbox.getResults_per_page() != null){
                params.AddParam("results_per_page",inbox.getResults_per_page());
            }
            if(inbox.getUser_id() != null){
                params.AddParam("user_id",inbox.getUser_id());
            }
            if(inbox.getBrand_id() != null){
                params.AddParam("brand_id",inbox.getBrand_id());
            }
            if(inbox.getCompany_id() != null){
                params.AddParam("company_id",inbox.getCompany_id());
            }
        }

        GsonRequest<InBoxModelData> req = new GsonRequest<>(
                Request.Method.GET, params.toString(),
                InBoxModelData.class, null, response, error);
        req.setHeader("access-token", Constants.BOX_ACCESS_TOKEN);
        req.setHeader("secret-key", Constants.BOX_SECRET_KEY);
        addReq(req, TAG_CMS);
    }
}