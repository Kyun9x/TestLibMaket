package com.lib.marketplace.activity.marketplace

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ipos.iposmanage.model.marrketplace.ArticlesModel
import com.ipos.saler.extension.StringExt
import com.lib.marketplace.R
import com.lib.marketplace.activity.BaseActivity
import com.lib.marketplace.adapter.marketplace.ImageSlideAdapter
import com.lib.marketplace.adapter.orderonline.RowItemAdapter
import com.lib.marketplace.app.Constants
import com.lib.marketplace.model.orderonline.RowItemModel
import com.lib.marketplace.util.Utilities
import com.soyagarden.android.common.extension.showStatusBar
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : BaseActivity() {

    private lateinit var mAdapter: RowItemAdapter
    private var mResult: ArrayList<RowItemModel> = ArrayList()
    private var dataModel: ArticlesModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        initAdapter()
        initExtraValue()
        initData()
        initClick()
    }

    private fun initAdapter() {
        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter = RowItemAdapter(context, mResult) {

            }
            adapter = mAdapter
        }
    }

    private fun initExtraValue() {
        dataModel = intent?.extras?.let {
            if (it.containsKey(Constants.OBJECT)) {
                it.getSerializable(Constants.OBJECT) as ArticlesModel?
            } else {
                null
            }
        }?.copy()

        dataModel?.run {
            mOrder.text = getString(R.string.lienhe_nguoiban) + " - " + mAuthor_phone
            mResult.add(RowItemModel(title = mTitle, isOnlyTitle = true))
            mResult.add(RowItemModel(title = getString(R.string.gia), content = mPrice.let { StringExt.convertToMoney(it) }, contentColor = R.color.mainColor, contentStyle = R.style.TextView_SemiBold))
            mResult.add(RowItemModel(title = getString(R.string.nguoi_ban), content = mAuthor_name))
            mResult.add(RowItemModel(title = getString(R.string.khu_vuc2), content = city?.city_name))
            mResult.add(RowItemModel(title = getString(R.string.description), content = mContent))
            initMenu()
        }
        mAdapter.notifyDataSetChanged()
    }

    private fun initMenu() {
        dataModel?.run {
            val imagesRes: ArrayList<String> = arrayListOf()
            mImage_urls.forEach {
                it.url?.run {
                    imagesRes.add(this)
                }
            }
            val fragments = ArrayList(imagesRes.map {
                IntroImageFragment.newInstance(it)
            })
            pagerMain.adapter = ImageSlideAdapter(supportFragmentManager, fragments)
            dots_indicator.setViewPager(pagerMain)
        }
}

    private fun initClick() {
        mClose.setOnClickListener { onBackPressed() }
        mOrder.setOnClickListener { Utilities.callPhone(this, dataModel?.mAuthor_phone) }
    }

    private fun initData() {
        showStatusBar(statusColor = true, color = R.color.white)
    }
}