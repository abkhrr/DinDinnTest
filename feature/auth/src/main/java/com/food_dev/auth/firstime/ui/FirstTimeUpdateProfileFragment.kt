package com.food_dev.auth.firstime.ui

import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import com.food_dev.auth.databinding.FragmentFirstTimeUpdateProfileBinding
import com.food_dev.auth.firstime.viewmodel.FirstTimeUpdateProfileViewModel
import com.food_dev.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class FirstTimeUpdateProfileFragment : BaseFragment<FragmentFirstTimeUpdateProfileBinding, FirstTimeUpdateProfileViewModel>() {

    @FragmentScoped
    override val viewModel: FirstTimeUpdateProfileViewModel by viewModels()
    override val binding: FragmentFirstTimeUpdateProfileBinding by lazy { FragmentFirstTimeUpdateProfileBinding.inflate(layoutInflater) }
    override val bindingVariable: Int = BR.viewModel

}