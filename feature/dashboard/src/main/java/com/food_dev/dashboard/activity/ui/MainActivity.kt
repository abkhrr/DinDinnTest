package com.food_dev.dashboard.activity.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import com.food_dev.dashboard.BR
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.food_dev.dashboard.activity.viewmodel.MainViewModel
import com.food_dev.dashboard.databinding.ActivityMainBinding
import com.food_dev.utils.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override val viewModel: MainViewModel by viewModels()
    override val bindingVariable: Int = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
    }

    override fun onInitViews(){}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(binding.dashboardNavHost))
        || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        findNavController(binding.dashboardNavHost).navigateUp()
        return true
    }

}