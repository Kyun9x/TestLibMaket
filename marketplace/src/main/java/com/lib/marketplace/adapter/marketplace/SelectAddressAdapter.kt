package com.ipos.iposmanage.adapter.marketplace

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import com.ipos.iposmanage.model.marrketplace.LocationModel
import com.ipos.saler.extension.StringExt
import com.ipos.saler.extension.ctx
import com.lib.marketplace.R
import com.lib.marketplace.adapter.BaseRecycleAdapter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.holder_select_address.*


class SelectAddressAdapter(
        private val context: Context,
        private val data: List<LocationModel>,
        val itemClickListener: (LocationModel) -> Unit,
        val itemEdit: (LocationModel) -> Unit,
        val itemDelete: (LocationModel) -> Unit,
        private val mListener: OnSwipeItem
) :
        RecyclerView.Adapter<SelectAddressAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.holder_select_address, parent, false)
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

        init {
            swipe.addDrag(SwipeLayout.DragEdge.Right, itemView.findViewById(R.id.bottom_wrapper))
//            swipe.addDrag(SwipeLayout.DragEdge.Left, itemView.findViewById(R.id.bottom_left))
        }

        fun close() {
            swipe.close()
        }

        fun bind(model: LocationModel) {
            with(model) {
                mTitle.text = StringExt.isTextEmpty(location_name)
                mTitle.isChecked = selected!!

                // Set swipe style
                swipe.showMode = SwipeLayout.ShowMode.PullOut

                // Set listener
                swipe.addSwipeListener(object : SwipeLayout.SwipeListener {
                    override fun onClose(layout: SwipeLayout) {}

                    override fun onUpdate(layout: SwipeLayout, leftOffset: Int, topOffset: Int) {}

                    override fun onStartOpen(layout: SwipeLayout) {}

                    override fun onOpen(layout: SwipeLayout) {}

                    override fun onStartClose(layout: SwipeLayout) {}

                    override fun onHandRelease(layout: SwipeLayout, xvel: Float, yvel: Float) {
                        val edge = layout.dragEdge.name
                        if (layout.openStatus.toString() !== "Close") {
                            when (edge) {
                                SwipeLayout.DragEdge.Right.name -> {
                                    // Drag RIGHT
                                    mListener.onSwipeRight(this@with)
                                }
                                SwipeLayout.DragEdge.Left.name -> {
                                    // Drag LEFT
                                    mListener.onSwipeLeft(this@with)
                                }
                                SwipeLayout.DragEdge.Top.name -> {
                                    // Drag TOP
                                    mListener.onSwipeTop(this@with)
                                }
                                SwipeLayout.DragEdge.Bottom.name -> {
                                    // Drag BOTTOM
                                    mListener.onSwipeBottom(this@with)
                                }
                            }
                        }
                    }
                })

                mTitle.setOnTouchListener(OnTouchListener { v, event ->
                    val DRAWABLE_LEFT = 0
                    val DRAWABLE_TOP = 1
                    val DRAWABLE_RIGHT = 2
                    val DRAWABLE_BOTTOM = 3
                    if (event.action == MotionEvent.ACTION_UP) {
                        if (event.rawX >= mTitle.getRight() - mTitle.getCompoundDrawables().get(DRAWABLE_RIGHT).getBounds().width()) {
                            swipe.open()
                            return@OnTouchListener true
                        }
                    }
                    false
                })
                mTitle.setOnClickListener {
                    itemClickListener(this)
                }
                mEdit.setOnClickListener {
                    itemEdit(this)
                }
                mDelete.setOnClickListener {
                    itemDelete(this)
                }
            }
        }
    }
    interface OnSwipeItem {
        fun onSwipeLeft(item: LocationModel)
        fun onSwipeRight(item: LocationModel)
        fun onSwipeTop(item: LocationModel)
        fun onSwipeBottom(item: LocationModel)
        fun onClickItem(item: LocationModel)
    }

}