package com.food_dev.dashboard.ingredient.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.food_dev.dashboard.R
import com.food_dev.dashboard.databinding.ViewItemIngredientBinding
import com.food_dev.domain.dto.local.model.ingredient.Ingredient
import com.food_dev.utils.ext.recyclerview.DiffUtil
import java.util.*
import kotlin.properties.Delegates

class IngredientAdapter: RecyclerView.Adapter<IngredientAdapter.ViewHolder>(), DiffUtil, Filterable {

    var ingredientItem: List<Ingredient> by Delegates.observable(emptyList()) { _, old, new ->
        notifyTheAdapter(old, new) { oldItems, newItems -> oldItems.id == newItems.id }
    }

    var originalList: List<Ingredient> = listOf()

    fun addList(list: List<Ingredient>){
        ingredientItem = list
        originalList   = ingredientItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ViewItemIngredientBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = ingredientItem.size
    inner class ViewHolder(val binding: ViewItemIngredientBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            val items     = ingredientItem[position]
            binding.items = items

            if (items.images.isNotEmpty()){
                Glide.with(binding.root.context)
                    .load(items.images[0].imageUrl)
                    .error(R.drawable.placeholder)
                    .into(binding.viewItemImage)
            }

            if(items.coreInfo.availableQuantity < 10){
                binding.viewAvailableQuantity.backgroundTintList = ContextCompat.getColorStateList(binding.root.context, android.R.color.darker_gray)
                binding.viewLayoutAvailableQty.setStrokeColor(ContextCompat.getColorStateList(binding.root.context, android.R.color.darker_gray))
                binding.viewAvailableQuantityText.setTextColor(ContextCompat.getColorStateList(binding.root.context, android.R.color.darker_gray))
            } else {
                binding.viewAvailableQuantity.backgroundTintList = ContextCompat.getColorStateList(binding.root.context, R.color.red_quantity)
                binding.viewLayoutAvailableQty.setStrokeColor(ContextCompat.getColorStateList(binding.root.context, R.color.red_quantity))
                binding.viewAvailableQuantityText.setTextColor(ContextCompat.getColorStateList(binding.root.context, R.color.red_quantity))
            }

            binding.executePendingBindings()
        }
    }

    override fun getFilter(): Filter = object: Filter() {

        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val searchedText = charSequence.trim().toString().lowercase(Locale.getDefault())
            val filters = when {
                searchedText.isBlank() -> originalList
                else -> {
                    ingredientItem.filter { it.coreInfo.title.lowercase(Locale.getDefault()).contains(searchedText) }
                }
            }
            val filterResults      = FilterResults()
            filterResults.values   = filters
            return filterResults
        }

        override fun publishResults(text: CharSequence, results: FilterResults) {
            @Suppress("UNCHECKED_CAST")
            ingredientItem = results.values as List<Ingredient>
        }

    }
}