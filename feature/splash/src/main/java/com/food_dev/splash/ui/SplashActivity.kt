package com.food_dev.splash.ui

import android.annotation.SuppressLint
import androidx.activity.viewModels
import com.food_dev.splash.BR
import com.food_dev.splash.databinding.ActivitySplashBinding
import com.food_dev.splash.viewmodel.SplashViewModel
import com.food_dev.utils.base.BaseActivity
import com.food_dev.utils.base.navigation.startAuthActivity
import com.food_dev.utils.base.navigation.startMainActivity
import com.food_dev.utils.ext.common.launchDelayedFunction
import com.food_dev.utils.ext.event.EventObserver
import com.food_dev.utils.ext.log.MyLog
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
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
        setupFirebaseToken()
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
                startAuthActivity(this)
            } else {
                startMainActivity(this)
            }
        })
    }

    private fun setupFirebaseToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                MyLog.d("MAIN_FCM_ERROR", "Fetching FCM registration token failed " + task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            viewModel.setupFirebaseToken(token)
        })
    }

}