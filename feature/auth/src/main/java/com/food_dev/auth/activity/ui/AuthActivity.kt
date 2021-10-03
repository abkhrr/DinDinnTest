package com.food_dev.auth.activity.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.library.baseAdapters.BR
import com.food_dev.auth.activity.viewmodel.AuthViewModel
import com.food_dev.auth.databinding.ActivityAuthBinding
import com.food_dev.utils.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding, AuthViewModel>() {

    override val binding: ActivityAuthBinding by lazy { ActivityAuthBinding.inflate(layoutInflater) }
    override val viewModel: AuthViewModel by viewModels()
    override val bindingVariable: Int = BR.viewModel

    override fun onInitViews() {}

}