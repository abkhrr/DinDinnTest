package com.food_dev.splash.ui

import android.annotation.SuppressLint
import androidx.activity.viewModels
import com.food_dev.auth.activity.ui.AuthActivity
import com.food_dev.dashboard.activity.ui.MainActivity
import com.food_dev.splash.BR
import com.food_dev.splash.databinding.ActivitySplashBinding
import com.food_dev.splash.viewmodel.SplashViewModel
import com.food_dev.utils.base.BaseActivity
import com.food_dev.utils.ext.common.launchDelayedFunction
import com.food_dev.utils.ext.event.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override val binding: ActivitySplashBinding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    override val viewModel: SplashViewModel by viewModels()
    override val bindingVariable: Int = BR.viewModel

    override fun onInitViews() {
        decideNextDestination()
        initializeSplash()
    }

    private fun decideNextDestination(){
        viewModel.decideLoginSession()
    }

    private fun initializeSplash(){
        launchDelayedFunction {
            observeLoginValue()
        }
    }

    private fun observeLoginValue(){
        viewModel.isLoggedIn.observe(this, EventObserver{ hasSession ->
            if (!hasSession){
                AuthActivity.start(this)
            } else {
                MainActivity.start(this)
            }
        })
    }

}