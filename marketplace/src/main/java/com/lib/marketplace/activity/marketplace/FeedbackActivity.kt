package com.lib.marketplace.activity.marketplace

import android.os.Bundle
import android.widget.RatingBar
import com.android.volley.Response
import com.ipos.iposmanage.model.marrketplace.FeedbackModel
import com.lib.marketplace.extension.initIcon
import com.ipos.saler.extension.visible
import com.lib.marketplace.R
import com.lib.marketplace.activity.BaseActivity
import com.lib.marketplace.app.ApplicationIpos
import com.lib.marketplace.restful.WSRestFull
import com.lib.marketplace.util.ToastUtil
import com.soyagarden.android.common.extension.showStatusBar
import kotlinx.android.synthetic.main.activity_feedback.*
import kotlinx.android.synthetic.main.include_header2.*

class FeedbackActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        initData()
        initClick()
    }

    private fun initClick() {
        mIconLeft.setOnClickListener { onBackPressed() }
        mOrder.setOnClickListener { feedback()}
        success.setOnClickListener { onBackPressed() }
    }

    private fun feedback() {
        var feedbackModel = FeedbackModel(feedback_title = mLb.text.toString(), feedback_content = mNote.text.toString(), customer_name = mCartBussiness.userId,customer_phone = "")
        WSRestFull(this).apiSCMFeedback(feedbackModel.toJson(), Response.Listener {
            it?.run{
                data?.run{
                    layout_success.visible()
                }
            }
        }, Response.ErrorListener {
            it.printStackTrace()
            ToastUtil.makeText(this, getString(R.string.error_network2))
        })
    }

    private fun initData() {
        showStatusBar(statusColor = true, color = R.color.white)
        mlbTitle.text = getString(R.string.feedback)
        mRating.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { p0, p1, p2 ->
                when(p1){
                    1.0.toFloat()  ->{
                        initIcon(imageView4,R.drawable.icon_fb_1)
                        mLb.text = getString(R.string.rate_lb_1)
                    }
                    2.0.toFloat()  ->{

                        initIcon(imageView4,R.drawable.icon_fb_2)
                        mLb.text = getString(R.string.rate_lb_2)
                    }
                    3.0.toFloat()  ->{

                        initIcon(imageView4,R.drawable.icon_fb_3)
                        mLb.text = getString(R.string.rate_lb_3)
                    }
                    4.0.toFloat()  ->{

                        initIcon(imageView4,R.drawable.icon_fb_4)
                        mLb.text = getString(R.string.rate_lb_4)
                    }
                    5.0.toFloat()  ->{
                        initIcon(imageView4,R.drawable.icon_fb_5)
                        mLb.text = getString(R.string.rate_lb_5)
                    }
                }
            }

    }

}