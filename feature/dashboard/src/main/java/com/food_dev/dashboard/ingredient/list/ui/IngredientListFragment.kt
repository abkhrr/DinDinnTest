package com.food_dev.dashboard.ingredient.list.ui

import androidx.fragment.app.viewModels
import com.food_dev.dashboard.BR
import com.food_dev.dashboard.databinding.FragmentIngredientBinding
import com.food_dev.dashboard.ingredient.list.viewmodel.IngredientListViewModel
import com.food_dev.utils.base.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class IngredientListFragment : BaseFragment<FragmentIngredientBinding, IngredientListViewModel>(){

    @FragmentScoped
    override val viewModel: IngredientListViewModel by viewModels()
    override val binding: FragmentIngredientBinding by lazy { FragmentIngredientBinding.inflate(layoutInflater) }
    override val bindingVariable: Int = BR.viewModel
}