package com.lib.marketplace.adapter.orderonline

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ipos.saler.extension.invisible
import com.ipos.saler.extension.visible
import com.lib.marketplace.R
import com.lib.marketplace.adapter.BaseRecycleAdapter
import com.lib.marketplace.model.orderonline.RowItemModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.holder_row_item.*

class RowItemAdapter(
    private val context: Context,
    private val data: ArrayList<RowItemModel>,
    val itemClickListener: (RowItemModel) -> Unit
) :
        RecyclerView.Adapter<RowItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.holder_row_item, parent, false)
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

        fun bind(model: RowItemModel) {
            with(model) {
                mOnlyTitle.text = title
                mTitle.text = title
                mContent.text = content

                isOnlyTitle?.run {
                    mOnlyTitle.visible()
                    mTitle.invisible()
                    mContent.invisible()
                } ?: run {
                    mOnlyTitle.invisible()
                    mTitle.visible()
                    mContent.visible()
                }
                contentStyle?.run {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        mContent.setTextAppearance(this)
                    } else {
                        mContent.setTextAppearance(context, this)
                    }
                }
                contentColor?.run {
                    mContent.setTextColor(ContextCompat.getColor(context, this))
                }

                itemView.setOnClickListener {
                    itemClickListener(model)
                }
            }
        }
    }
}