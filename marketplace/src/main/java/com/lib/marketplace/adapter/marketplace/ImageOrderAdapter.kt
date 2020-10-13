package com.lib.marketplace.adapter.marketplace

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ipos.iposmanage.model.orderonline.ImageOrderModel
import com.ipos.saler.extension.ctx
import com.ipos.saler.extension.gone
import com.ipos.saler.extension.visible
import com.lib.marketplace.R

class ImageOrderAdapter(
        private val data: List<ImageOrderModel>,
        val itemClickListener: (ImageOrderModel, potision: Int) -> Unit,
        val deleteClickListener: (ImageOrderModel) -> Unit
) :
        RecyclerView.Adapter<ImageOrderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.holder_image_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (data.size > 6) {
            6
        } else {
            data.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (itemCount > 0) {
            val item = data[position]
            holder.bind(item)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mImage: ImageView = itemView.findViewById(R.id.mImage)
        private val mDelete: ImageView = itemView.findViewById(R.id.mDelete)

        fun bind(model: ImageOrderModel) {
            with(model) {
                Glide.with(itemView).load(img_url_thumb.toString()).apply(RequestOptions().error(R.drawable.icon_addimage)).into(mImage)

                if (img_url_thumb != null) {
//                    mImage.setPadding(0,0,0,0)
                    mDelete.visible()
                } else {
//                    mImage.setPadding(100,100,100,100)
                    mDelete.gone()
                }
                mImage.setOnClickListener {
                    itemClickListener(this, adapterPosition)
                }
                mDelete.setOnClickListener {
                    deleteClickListener(this)
                }
            }
        }
    }
}