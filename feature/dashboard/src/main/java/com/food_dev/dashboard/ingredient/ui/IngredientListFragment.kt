package com.food_dev.dashboard.ingredient.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.food_dev.dashboard.BR
import com.food_dev.dashboard.R
import com.food_dev.dashboard.databinding.FragmentIngredientListBinding
import com.food_dev.dashboard.ingredient.adapter.IngredientAdapter
import com.food_dev.dashboard.ingredient.viewmodel.IngredientListViewModel
import com.food_dev.domain.dto.local.model.ingredient.showcase.IngredientShowcase
import com.food_dev.utils.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class IngredientListFragment : BaseFragment<FragmentIngredientListBinding, IngredientListViewModel>(){

    @FragmentScoped
    override val viewModel: IngredientListViewModel by viewModels()
    override val binding: FragmentIngredientListBinding by lazy { FragmentIngredientListBinding.inflate(layoutInflater) }
    private val ingredientAdapter: IngredientAdapter by lazy { IngredientAdapter() }
    override val bindingVariable: Int = BR.viewModel
    private var isPortrait: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_search_menu, menu)
        val searchItemView = menu.findItem(R.id.action_search).actionView as LinearLayout
        val searchView     = (searchItemView[0] as CardView)[0] as SearchView
        searchView.setOnQueryTextListener(onSearchQueryTextListener)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup(){
        setupRefreshShowcase()
        setupShowcaseObserver()
        setupRvShowcase()
    }


    private fun setupRefreshShowcase() {
        binding.viewIngredientListSwipeRefresh.setOnRefreshListener {
            binding.viewIngredientListSwipeRefresh.isRefreshing = false
            viewModel.searchMerchantShowcase()
        }
    }

    private fun setupShowcaseObserver() {
        viewModel.searchMerchantShowcase()
        viewModel.showcaseData.observe(viewLifecycleOwner, { showcase ->
            val tabTitle = showcase.map { it.showcaseTitle }
            setupTabs(tabTitle)
            for(element in showcase){
                binding.viewTabLayoutIngredient.addOnTabSelectedListener(onTabSelectedListener(
                    element
                ))
            }
        })

        viewModel.showcaseIngredient.observe(viewLifecycleOwner, { ingredient ->
            ingredientAdapter.addList(ingredient)
        })
    }

    private fun setupRvShowcase() {
        isPortrait = requireContext().resources.configuration.orientation != Configuration.ORIENTATION_LANDSCAPE
        binding.viewCollectionRvIngredient.apply {
            layoutManager = if(isPortrait) {
                GridLayoutManager(this.context, 2, RecyclerView.VERTICAL, false)
            } else {
                GridLayoutManager(this.context, 4, RecyclerView.VERTICAL, false)
            }
            adapter      = ingredientAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun setupTabs(titles: List<String>) {
        binding.viewTabLayoutIngredient.removeAllTabs()
        titles.forEach { title ->
            binding.viewTabLayoutIngredient.addTab(binding.viewTabLayoutIngredient.newTab().setText(title))
        }
    }

    private lateinit var selectedTab: String
    private fun onTabSelectedListener(showcase: IngredientShowcase) = object: TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            selectedTab    = tab?.text.toString()
            val idSelected = selectedTab == showcase.showcaseTitle
            if (idSelected){
                viewModel.getShowcaseIngredient(showcase.id)
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }
    }

    private val onSearchQueryTextListener = object: SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }
        override fun onQueryTextChange(newText: String?): Boolean {
            ingredientAdapter.filter.filter(newText)
            return false
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        isPortrait = (newConfig.orientation != Configuration.ORIENTATION_LANDSCAPE)
        setupRvShowcase()
    }

}