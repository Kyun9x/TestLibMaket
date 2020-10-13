package com.lib.marketplace.adapter.marketplace

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ipos.iposmanage.fragment.marketplace.MyArticlesListFragment
import com.lib.marketplace.app.Constants

class MyArticlesAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val roleSale by lazy {
        arrayListOf(
                MyArticlesListFragment.newInstance(Constants.ArticlesStatus.PENDING),
                MyArticlesListFragment.newInstance(Constants.ArticlesStatus.CONFIRMED),
                MyArticlesListFragment.newInstance(Constants.ArticlesStatus.EXPIRED),
                MyArticlesListFragment.newInstance(Constants.ArticlesStatus.CANCELED),
                MyArticlesListFragment.newInstance(Constants.ArticlesStatus.SOLD)
        )
    }

    override fun getItemCount(): Int {
        return roleSale.size
    }

    override fun createFragment(position: Int): Fragment {
        return roleSale[position]
    }
}