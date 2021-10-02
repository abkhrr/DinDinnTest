package com.food_dev.dashboard.ingredient.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.food_dev.dashboard.databinding.ViewItemIngredientBinding
import com.food_dev.domain.dto.local.model.ingredient.Ingredient
import com.food_dev.utils.R
import com.food_dev.utils.ext.recyclerview.DiffUtil
import java.util.*
import kotlin.properties.Delegates

class IngredientAdapter: RecyclerView.Adapter<IngredientAdapter.ViewHolder>(), DiffUtil, Filterable {

    var ingredientItem: List<Ingredient> by Delegates.observable(emptyList()) { _, old, new ->
        notifyTheAdapter(old, new) { oldItems, newItems -> oldItems.id == newItems.id }
    }

    private var filteredList = ingredientItem

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

            Glide.with(binding.root.context)
                .load(items.images[0])
                .error(R.drawable.placeholder)
                .into(binding.viewItemImage)

            binding.executePendingBindings()
        }
    }

    override fun getFilter(): Filter = object: Filter() {

        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val searchedText = charSequence.trim().toString().lowercase(Locale.getDefault())
            val filteredList = when {
                searchedText.isBlank() -> ingredientItem
                else -> {
                    ingredientItem.filter {
                        it.coreInfo.title.lowercase(Locale.getDefault()).contains(searchedText)
                    }
                }
            }
            val filterResults    = FilterResults()
            filterResults.values = filteredList
            return filterResults
        }

        override fun publishResults(text: CharSequence, results: FilterResults) {
            @Suppress("UNCHECKED_CAST")
            filteredList = results.values as List<Ingredient>
        }

    }
}