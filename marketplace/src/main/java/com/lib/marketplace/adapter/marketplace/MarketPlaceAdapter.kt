package com.lib.marketplace.adapter.marketplace

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lib.marketplace.activity.marketplace.ArticleFragment
import com.lib.marketplace.activity.marketplace.NvlFragment
import com.lib.marketplace.activity.order_online.PurchaseFragment

class MarketPlaceAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val roleSale by lazy {
        arrayListOf(
                PurchaseFragment.newInstance(),
                NvlFragment.newInstance(),
                ArticleFragment.newInstance()
        )
    }

    override fun getItemCount(): Int {
        return roleSale.size
    }

    override fun createFragment(position: Int): Fragment {
        return roleSale[position]
    }
}