package com.food_dev.dashboard.order.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.food_dev.dashboard.R
import com.food_dev.dashboard.databinding.ViewItemAddonBinding
import com.food_dev.domain.dto.local.model.order.OrderAddon
import com.food_dev.utils.ext.recyclerview.DiffUtil
import kotlin.properties.Delegates

class AddonAdapter: RecyclerView.Adapter<AddonAdapter.ViewHolder>(), DiffUtil {

    var addonItems: List<OrderAddon> by Delegates.observable(emptyList()) { _, old, new ->
        notifyTheAdapter(old, new) { oldItems, newItems -> oldItems.addonId == newItems.addonId }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ViewItemAddonBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = addonItems.size

    inner class ViewHolder(private val binding: ViewItemAddonBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            val items = addonItems[position]
            val title = binding.root.context.getString(R.string.addon_title, items.quantity, items.addonOptionId.titleAnswer)
            binding.viewAddonTitleAndQuantity.text = title
            binding.executePendingBindings()
        }
    }
}