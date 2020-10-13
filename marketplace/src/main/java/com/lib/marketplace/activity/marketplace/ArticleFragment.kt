package com.lib.marketplace.activity.marketplace

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.ipos.iposmanage.model.marrketplace.ArticlesModel
import com.ipos.saler.extension.openActivity
import com.lib.marketplace.R
import com.lib.marketplace.activity.BaseFragment
import com.lib.marketplace.adapter.marketplace.ArtilesAdapter
import com.lib.marketplace.app.Constants
import com.lib.marketplace.restful.WSRestFull
import com.lib.marketplace.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_article.*
import java.util.*

class ArticleFragment : BaseFragment() {
    private var mAdapter: ArtilesAdapter? = null
    private val mDatas = ArrayList<ArticlesModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let {
            LocalBroadcastManager.getInstance(it)
                    .registerReceiver(onNotice, IntentFilter(Constants.BROADCAST.BROAD_ARTICLES))
        }
        initAdapter()
        initData()
        initClick()
    }

    private fun initClick() {
        mCreate.setOnClickListener { view: View? ->
            openActivity(ArticleCreateActivity::class.java)
        }
    }

    private fun initData() {
        callServiceList()
    }

    private fun initAdapter() {
        listRecyle?.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            mAdapter = ArtilesAdapter(context, mDatas) {
                val bundle = Bundle()
                bundle.putSerializable(Constants.OBJECT, it)
                openActivity(
                        ArticleDetailActivity::class.java,
                        bundle = bundle
                )
            }
            adapter = mAdapter
        }
    }

    private fun callServiceList() {
        showProgressHub(mActivity)
        WSRestFull(context).apiSCMArticles(mCartBussiness.getCartLocate().locateId,{ (data) -> onResponseServiceList(data) }) { error: VolleyError ->
            onResponseServiceList(null)
            error.printStackTrace()
            ToastUtil.makeText(mActivity, getString(R.string.error_network2))
        }
    }

    private fun onResponseServiceList(data: ArrayList<ArticlesModel>?) {
        dismissProgressHub()
        mDatas.clear()
        if (data != null) {
            mDatas.addAll(data)
        }
        mAdapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(onNotice)
        }
    }

    var onNotice: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            callServiceList()
        }
    }

    companion object {
        fun newInstance(): ArticleFragment {
            return ArticleFragment()
        }
    }
}