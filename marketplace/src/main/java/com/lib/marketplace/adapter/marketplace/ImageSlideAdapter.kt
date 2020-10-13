package com.lib.marketplace.adapter.marketplace

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.lib.marketplace.activity.marketplace.IntroImageFragment

class ImageSlideAdapter(fragmentManager: FragmentManager, private val pages: List<IntroImageFragment>)
    : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

}
