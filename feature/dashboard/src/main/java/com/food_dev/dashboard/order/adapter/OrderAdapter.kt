package com.food_dev.dashboard.order.adapter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.food_dev.dashboard.R
import com.food_dev.dashboard.databinding.ViewItemOrderBinding
import com.food_dev.dashboard.order.listener.OrderClickListener
import com.food_dev.dashboard.order.listener.OrderItemViewListener
import com.food_dev.domain.dto.local.model.order.Order
import com.food_dev.utils.ext.constant.Const.ONE_SECOND
import com.food_dev.utils.ext.countdown.CountDownRunnable
import com.food_dev.utils.ext.date.DateUtils.pairOrderExpiredAndAlertedDate
import com.food_dev.utils.ext.date.DateUtils.toStringTime
import com.food_dev.utils.ext.recyclerview.DiffUtil
import kotlin.properties.Delegates


class OrderAdapter(val listener: OrderClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>(), DiffUtil {

    var orderItems: List<Order> by Delegates.observable(emptyList()) { _, old, new ->
        notifyTheAdapter(old, new) { oldItems, newItems -> oldItems.id == newItems.id }
    }

    private val recyclerHandler = Handler(Looper.getMainLooper())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ViewItemOrderBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bindView(position)
    }

    fun removeCallbacks() {
        recyclerHandler.removeCallbacksAndMessages(null)
    }

    override fun getItemCount(): Int = orderItems.size

    inner class ViewHolder(private val binding: ViewItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {

        private var countDownRunnable: CountDownRunnable = CountDownRunnable(itemView.context, recyclerHandler)

        fun bindView(position: Int) {
            val order                   = orderItems[position]
            binding.viewOrderId.text    = binding.root.context.resources.getString(R.string.order_id, order.id.toString())
            binding.viewTotalItems.text = binding.root.context.resources.getQuantityString(R.plurals.items_total, order.totalItems, order.totalItems)
            binding.viewTimeStart.text  = toStringTime(order.createdAt)

            val expiryTime  = order.expiredAt.time
            val alertedTime = order.alertedAt.time
            val datePair    = pairOrderExpiredAndAlertedDate(expiryTime, alertedTime)

            when {
                datePair.first > 0 -> {
                    binding.viewTimeCountTitle.visibility = View.VISIBLE
                    binding.viewTimeCount.visibility      = View.VISIBLE
                    binding.viewAcceptButton.text         = binding.root.context.getString(R.string.accept)
                    binding.viewAcceptButton.isEnabled    = true
                    binding.viewAcceptButton.backgroundTintList = ContextCompat.getColorStateList(binding.root.context,
                        R.color.blue_sapphire)

                    countDownRunnable.setViews(
                        binding.viewTimeCount,
                        binding.viewTimeCountTitle,
                        binding.viewAcceptButton,
                        binding.viewProgressCountDown
                    )
                    countDownRunnable.setElapsed(datePair.first, datePair.second)
                    recyclerHandler.postDelayed(countDownRunnable, ONE_SECOND)
                }
                else -> {
                    binding.viewTimeCountTitle.visibility = View.INVISIBLE
                    binding.viewTimeCount.visibility      = View.INVISIBLE
                    binding.viewAcceptButton.text         = binding.root.context.getString(R.string.expired)
                    binding.viewAcceptButton.isEnabled    = false
                    binding.viewAcceptButton.backgroundTintList = ContextCompat.getColorStateList(binding.root.context,
                        R.color.softer_gray)
                }
            }

            val adapterLayoutManager = LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
            adapterLayoutManager.initialPrefetchItemCount = order.orderItems.size

            val adapterOrderItems = OrderItemAdapter()
            binding.viewCollectionRvOrderIngredient.apply {
                adapter       = adapterOrderItems
                layoutManager = adapterLayoutManager
            }
            adapterOrderItems.orderItems = order.orderItems
            binding.item                = OrderItemViewListener {
                recyclerHandler.removeCallbacks(countDownRunnable)
                listener.onAcceptClicked(order)
            }
            binding.executePendingBindings()
        }
    }
}