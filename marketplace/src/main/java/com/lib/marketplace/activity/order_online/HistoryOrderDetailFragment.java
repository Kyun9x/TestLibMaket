package com.lib.marketplace.activity.order_online;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lib.marketplace.R;
import com.lib.marketplace.activity.BaseFragment;
import com.lib.marketplace.activity.CateActivity;
import com.lib.marketplace.adapter.orderonline.HistoryOrderOnlineRecyleAdapter;
import com.lib.marketplace.model.orderonline.DmHistoryOrderDetail;
import com.lib.marketplace.model.orderonline.DmOrderOnline;
import com.lib.marketplace.paser.orderonline.RestDmHistoryOrderOnline;
import com.lib.marketplace.restful.WSRestFull;
import com.lib.marketplace.util.ToastUtil;

import java.util.ArrayList;


public class HistoryOrderDetailFragment extends BaseFragment {

    private View v;
    private RecyclerView mRecycleView;
    private SwipeRefreshLayout refresh_layout;
    private ArrayList<DmOrderOnline> mDatas = new ArrayList<>();
    private HistoryOrderOnlineRecyleAdapter mAdapter;


    protected int getItemLayout() {
        return R.layout.fragment_history_order_detail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = super.onCreateView(inflater, container, savedInstanceState);
        mRecycleView = v.findViewById(R.id.list_order);
        refresh_layout = v.findViewById(R.id.refresh_layout);
        mRecycleView.setLayoutManager(new LinearLayoutManager(mActivity));
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initAdapter();
        initData();
        initClick();
    }

    private void initClick() {
        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getOrderOnline();
            }
        });
    }

    private void initData() {

    }

    private void initAdapter() {
        mAdapter = new HistoryOrderOnlineRecyleAdapter(mActivity, mDatas, dmOrderOnline -> {
            CateActivity.gotoOrderDetailFragment(mActivity, dmOrderOnline.getOrderCode(), OrderDetailFragment.TYPE_ORDER_HISTORY);
        });
        mRecycleView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void getOrderOnline() {
        refresh_layout.setRefreshing(false);
        showProgressHub(mActivity);
        new WSRestFull(getContext()).apiOrderHistory(new Response.Listener<RestDmHistoryOrderOnline>() {
            @Override
            public void onResponse(RestDmHistoryOrderOnline response) {
                onResponseOrderOnline(response.getData());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onResponseOrderOnline(null);
                error.printStackTrace();
                ToastUtil.makeText(mActivity, getString(R.string.error_network2));

            }
        });
    }

    private void onResponseOrderOnline(DmHistoryOrderDetail response) {
        dismissProgressHub();
        if (response != null) {
            ArrayList<DmOrderOnline> list = response.getmDatas();
            if (list != null) {
                mDatas.clear();
                mDatas.addAll(list);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getOrderOnline();
    }

    public static HistoryOrderDetailFragment newInstance() {
        HistoryOrderDetailFragment fragment = new HistoryOrderDetailFragment();
        return fragment;

    }
}
