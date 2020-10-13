package com.lib.marketplace.adapter.marketplace

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipos.iposmanage.model.marrketplace.ArticlesModel
import com.ipos.iposmanage.util.DateTimeUtil.Companion.convertTimeStampToDate
import com.ipos.saler.extension.*
import com.lib.marketplace.R
import com.lib.marketplace.adapter.BaseRecycleAdapter
import com.lib.marketplace.app.Constants
import com.lib.marketplace.extension.initAvatarCompany
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.holder_my_articles.*

class MyArticlesListAdapter(
        private val context: Context,
        private val data: List<ArticlesModel>,
        val itemClickListener: (ArticlesModel) -> Unit,
) :
        RecyclerView.Adapter<MyArticlesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.holder_my_articles, parent, false)
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
                mLbTitle.text = StringExt.isTextEmpty(mTitle)
                mLbContent.text = StringExt.isTextEmpty(mContent)
                mLbPrice.text = StringExt.convertToMoney(mPrice)
                created_at?.let{
                    mTime.text = StringExt.isTextEmpty(convertTimeStampToDate(it, Constants.Date.Format.HH_MM_DD_MM_YYYY))
                }
                initAvatarCompany(mImage,mImage_urls[0]?.url_thumb.toString(),mContext.getDrawable(R.drawable.icon_default))

                convertView.setOnClickListener {
                    itemClickListener(this)
                }

            }
        }
    }
}