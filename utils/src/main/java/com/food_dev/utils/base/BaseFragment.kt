package com.food_dev.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.food_dev.utils.base.navigation.NavigationCommand
import com.food_dev.utils.base.viewmodel.BaseViewModel

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    abstract val viewModel: VM
    abstract val binding: VB
    abstract val bindingVariable: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onStart() {
        super.onStart()
        viewModel.showToast.observe(this, { Toast.makeText(activity, it, Toast.LENGTH_LONG).show() })
        viewModel.showSnack.observe(this, { Snackbar.make(this.requireView(), it, Snackbar.LENGTH_LONG).show() })
    }

    fun navigate(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.To     -> findNavController().navigate(command.directions)
            is NavigationCommand.Back   -> findNavController().popBackStack()
            is NavigationCommand.BackTo -> findNavController().popBackStack(command.destinationId, false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        performDataBinding()
        setupAdapter()
        setupArguments()
        setupListener()
        setupListener()
        setupViewPager()
        initAPI()
        setupRv()
        setupObserver()
        initOnClick()
    }

    private fun performDataBinding(){
        binding.setVariable(bindingVariable, viewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    protected open fun setupOnCreate(){}
    protected open fun setupArguments(){}
    protected open fun setupAdapter(){}
    protected open fun setupViewPager(){}
    protected open fun setupListener(){}
    protected open fun initAPI(){}
    protected open fun setupRv(){}
    protected open fun setupObserver(){}
    protected open fun initOnClick(){}
    protected open fun initLogout(){}

}