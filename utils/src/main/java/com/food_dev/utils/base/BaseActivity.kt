package com.food_dev.utils.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.food_dev.utils.base.viewmodel.BaseViewModel

abstract class BaseActivity<VB: ViewDataBinding, VM: BaseViewModel> : AppCompatActivity() {

    abstract val binding: VB
    abstract val viewModel: VM
    abstract val bindingVariable: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        onInitViews()
        setupAdapter()
        setupArguments()
        setupListener()
        setupListener()
        setupViewPager()
        setupObserver()
        initAPI()
    }

    private fun setupBinding(){
        setContentView(binding.root)
        binding.setVariable(bindingVariable, viewModel)
        binding.executePendingBindings()
    }

    abstract fun onInitViews()

    protected open fun setupArguments(){}
    protected open fun setupAdapter(){}
    protected open fun setupViewPager(){}

    protected open fun setupListener(){}
    protected open fun setupObserver(){}

    protected open fun initAPI(){}



}