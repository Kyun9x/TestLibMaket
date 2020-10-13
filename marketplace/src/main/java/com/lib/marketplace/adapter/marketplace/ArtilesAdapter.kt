package com.lib.marketplace.adapter.marketplace

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ipos.iposmanage.model.marrketplace.ArticlesModel
import com.ipos.saler.extension.gone
import com.lib.marketplace.R
import com.lib.marketplace.adapter.BaseRecycleAdapter
import com.lib.marketplace.app.ApplicationIpos
import com.lib.marketplace.util.FormatNumberUtil
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_service_list.*
import kotlinx.android.synthetic.main.fragment_intro_slider.*

class ArtilesAdapter(
        private val context: Context,
        private val data: ArrayList<ArticlesModel>,
        val itemClickListener: (ArticlesModel) -> Unit
) :
        RecyclerView.Adapter<ArtilesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.adapter_service_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (itemCount > 0) {
            val item = data[position]
            holder.bind(item)
        }
    }

    inner class ViewHolder(itemView: View) : BaseRecycleAdapter(context, itemView), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bind(model: ArticlesModel) {
            with(model) {

                Glide.with(ApplicationIpos.instance).load(mImage_urls[0].url_thumb).apply(
                    RequestOptions().placeholder(R.drawable.icon_default).override(100, 100)
                ).into(image)
                total_price.text = FormatNumberUtil.formatCurrency(mPrice)
                title.text = mTitle
                adress.text = mContent
                plus.gone()

                itemView.setOnClickListener {
                    itemClickListener(model)
                }
            }
        }
    }
}