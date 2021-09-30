package com.food_dev.dashboard.order.ui

import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import com.food_dev.dashboard.databinding.FragmentOrderBinding
import com.food_dev.dashboard.order.viewmodel.OrderViewModel
import com.food_dev.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class OrderFragment : BaseFragment<FragmentOrderBinding, OrderViewModel>() {

    @FragmentScoped
    override val viewModel: OrderViewModel by viewModels()
    override val binding: FragmentOrderBinding by lazy { FragmentOrderBinding.inflate(layoutInflater) }
    override val bindingVariable: Int = BR.viewModel

}