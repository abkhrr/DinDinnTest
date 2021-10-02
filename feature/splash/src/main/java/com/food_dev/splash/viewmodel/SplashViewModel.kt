package com.food_dev.splash.viewmodel

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.food_dev.domain.repositories.LocalRepository
import com.food_dev.utils.base.viewmodel.BaseViewModel
import com.food_dev.utils.ext.constant.Const
import com.food_dev.utils.ext.event.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val localRepository: LocalRepository
): BaseViewModel() {

    private var _isLoggedIn = MutableLiveData<Event<Boolean>>()

    val isLoggedIn: LiveData<Event<Boolean>>
        get() = _isLoggedIn

    fun decideLoginSession(){
        val isMerchantLoggedIn = localRepository.getCurrentLoginMode()
        if (isMerchantLoggedIn == Const.LoggedInMode.NOT_LOGGED_IN.type){
            _isLoggedIn.value = Event(false)
        } else {
            _isLoggedIn.value = Event(true)
        }
    }

    fun setupFirebaseToken(token: String?){
        val existFirebaseToken = localRepository.getFirebaseToken()
        if(TextUtils.isEmpty(existFirebaseToken)){
            localRepository.setFirebaseToken(token)
        }
    }

}