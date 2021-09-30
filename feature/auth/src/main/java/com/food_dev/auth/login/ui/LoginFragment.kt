package com.food_dev.auth.login.ui

import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.food_dev.auth.databinding.FragmentLoginBinding
import com.food_dev.auth.login.viewmodel.LoginViewModel
import com.food_dev.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    @FragmentScoped
    override val viewModel: LoginViewModel by viewModels()
    override val binding: FragmentLoginBinding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    override val bindingVariable: Int = BR.viewModel

}