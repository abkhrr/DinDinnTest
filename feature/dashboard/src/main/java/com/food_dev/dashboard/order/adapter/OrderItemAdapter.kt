package com.food_dev.dashboard.order.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.food_dev.dashboard.R
import com.food_dev.dashboard.databinding.ViewItemOrderIngredientBinding
import com.food_dev.domain.dto.local.model.order.OrderItems
import com.food_dev.utils.ext.recyclerview.DiffUtil
import kotlin.properties.Delegates

class OrderItemAdapter: RecyclerView.Adapter<OrderItemAdapter.ViewHolder>(), DiffUtil {

    var orderItems: List<OrderItems> by Delegates.observable(emptyList()) { _, old, new ->
        notifyTheAdapter(old, new) { oldItems, newItems -> oldItems.itemId == newItems.itemId }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ViewItemOrderIngredientBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = orderItems.size

    inner class ViewHolder(private val binding: ViewItemOrderIngredientBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            val items = orderItems[position]
            val title = binding.root.context.getString(R.string.menu_title, items.quantity, items.itemId.coreInfo.title)
            binding.viewIngredientTitle.text = title

            val adapterLayoutManager = LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
            adapterLayoutManager.initialPrefetchItemCount = items.addon.size

            val addonAdapter  = AddonAdapter()
            binding.viewCollectionRvOrderAddon.apply {
                adapter       = addonAdapter
                layoutManager = adapterLayoutManager
            }
            addonAdapter.addonItems = items.addon
            binding.executePendingBindings()
        }
    }
}