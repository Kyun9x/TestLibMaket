package com.lib.marketplace.activity.marketplace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lib.marketplace.R
import com.lib.marketplace.activity.BaseFragment
import com.lib.marketplace.app.ApplicationIpos
import kotlinx.android.synthetic.main.fragment_intro_slider.*

private const val IMAGE_RES = "imageRes"

class IntroImageFragment : BaseFragment() {

    private var imageRes: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_intro_slider, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getExtrasValue()
        initData()
    }

    private fun initData() {
        imageRes?.let {
            Glide.with(ApplicationIpos.getInstance()).load(it).apply(
                RequestOptions().placeholder(R.drawable.icon_default).override(100, 100)
            ).into(imgIntro)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(imageRes: String) = IntroImageFragment().apply {
            arguments = Bundle().apply {
                putString(IMAGE_RES, imageRes)
            }
        }
    }

    private fun getExtrasValue() {
        arguments?.let {
            imageRes = it.getString(IMAGE_RES)
        }
    }
}
