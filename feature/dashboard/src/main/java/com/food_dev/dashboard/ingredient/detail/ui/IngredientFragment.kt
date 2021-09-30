package com.food_dev.dashboard.ingredient.detail.ui

import androidx.fragment.app.viewModels
import com.food_dev.dashboard.BR
import com.food_dev.dashboard.databinding.FragmentIngredientBinding
import com.food_dev.dashboard.ingredient.detail.viewmodel.IngredientViewModel
import com.food_dev.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class IngredientFragment : BaseFragment<FragmentIngredientBinding, IngredientViewModel>() {

    @FragmentScoped
    override val viewModel: IngredientViewModel by viewModels()
    override val binding: FragmentIngredientBinding by lazy { FragmentIngredientBinding.inflate(layoutInflater) }
    override val bindingVariable: Int = BR.viewModel


}