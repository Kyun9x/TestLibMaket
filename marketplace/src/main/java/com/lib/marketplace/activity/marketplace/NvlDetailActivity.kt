package com.lib.marketplace.activity.marketplace

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lib.marketplace.model.orderonline.DmServiceListOrigin
import com.ipos.saler.extension.StringExt
import com.lib.marketplace.R
import com.lib.marketplace.activity.BaseActivity
import com.lib.marketplace.adapter.marketplace.ImageSlideAdapter
import com.lib.marketplace.adapter.orderonline.RowItemAdapter
import com.lib.marketplace.app.Constants
import com.lib.marketplace.model.orderonline.RowItemModel
import com.soyagarden.android.common.extension.showStatusBar
import kotlinx.android.synthetic.main.activity_nvl_detail.*

class NvlDetailActivity : BaseActivity() {

    private lateinit var mAdapter: RowItemAdapter
    private var mResult: ArrayList<RowItemModel> = ArrayList()
    private var nvlModel: DmServiceListOrigin? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nvl_detail)
        initAdapter()
        initExtraValue()
        initData()
        initClick()
    }

    private fun initExtraValue() {
        nvlModel = intent?.extras?.let {
            if (it.containsKey(Constants.OBJECT)) {
                it.getSerializable(Constants.OBJECT) as DmServiceListOrigin?
            } else {
                null
            }
        }?.copy()

        nvlModel?.run {
//            initAvatarCompany(mImg,image_thumb,getDrawable(R.drawable.icon_default))
            if(quantity == 0.0){
                mQuantity.text = "1"
                nvlModel?.quantity = 1.0
            }else{
                mQuantity.text = "" + StringExt.convertNumberToString(quantity)
            }
            mResult.add(RowItemModel(title = name, isOnlyTitle = true))
            mResult.add(RowItemModel(title = getString(R.string.gia), content = unitPrice.let { StringExt.convertToMoney(it) } + "/ " + unitName, contentColor = R.color.mainColor, contentStyle = R.style.TextView_SemiBold))
            mResult.add(RowItemModel(title = getString(R.string.khu_vuc2), content = supplier_address))
            mResult.add(RowItemModel(title = getString(R.string.brand), content = brand_name))
            mResult.add(RowItemModel(title = getString(R.string.nha_cung_cap), content = supplier_name))
            mResult.add(RowItemModel(title = getString(R.string.description), content = desc))
            initMenu()
        }
        mAdapter.notifyDataSetChanged()
    }

    private fun initMenu() {
        nvlModel?.run {
            val imagesRes: ArrayList<String> = arrayListOf()
            imageUrls?.forEach {
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

    private fun initAdapter() {
        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter = RowItemAdapter(context, mResult) {

            }
            adapter = mAdapter
        }
    }


    private fun initClick() {
        mClose.setOnClickListener { onBackPressed() }
        mOrder.setOnClickListener { order() }
        mMinus.setOnClickListener { minus() }
        mPlus.setOnClickListener { plus() }
    }

    private fun order() {
        Intent().apply {
            putExtra(Constants.OBJECT, nvlModel)
            setResult(Activity.RESULT_OK, this)
            onBackPressed()
        }
    }

    private fun plus() {
        var quantity = mQuantity.text.toString().toInt()
        mQuantity.text = "" + quantity.plus(1)
        nvlModel?.quantity = mQuantity.text.toString().toDouble()
    }

    private fun minus() {
        var quantity = mQuantity.text.toString().toInt()
        if (quantity > 0) {
            mQuantity.text = "" + quantity.minus(1)
        }
        nvlModel?.quantity = mQuantity.text.toString().toDouble()
    }

    private fun initData() {
        showStatusBar(statusColor = true, color = R.color.white)
    }
}