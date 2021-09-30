package com.food_dev.auth.register.ui

import androidx.fragment.app.viewModels
import com.food_dev.auth.BR
import com.food_dev.auth.databinding.FragmentRegisterBinding
import com.food_dev.auth.register.viewmodel.RegisterViewModel
import com.food_dev.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {

    @FragmentScoped
    override val viewModel: RegisterViewModel by viewModels()
    override val binding: FragmentRegisterBinding by lazy { FragmentRegisterBinding.inflate(layoutInflater) }
    override val bindingVariable: Int = BR.viewModel


}