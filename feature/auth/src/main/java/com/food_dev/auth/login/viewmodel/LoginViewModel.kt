package com.food_dev.auth.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.food_dev.domain.dto.api.AuthResponse
import com.food_dev.domain.dto.api.LoginRequest
import com.food_dev.domain.repositories.LocalRepository
import com.food_dev.domain.repositories.RemoteRepository
import com.food_dev.utils.base.api.ApiResponse
import com.food_dev.utils.base.api.BaseObjectResponse
import com.food_dev.utils.base.viewmodel.BaseViewModel
import com.food_dev.utils.ext.constant.Const
import com.food_dev.utils.ext.event.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
): BaseViewModel() {

    private var _loginDataValue = MutableLiveData<Event<Boolean>>()

    val loginDataValue: LiveData<Event<Boolean>>
      get() = _loginDataValue

    fun loginMerchant(email: String, password: String){
        val token        = localRepository.getFirebaseToken()
        val loginRequest = LoginRequest(email, password, token)
        viewModelScope.launch {
            when(val result = remoteRepository.login(loginRequest)){
                is ApiResponse.Success<BaseObjectResponse<AuthResponse>> -> {
                    localRepository.setCurrentLoginMode(Const.LoggedInMode.LOGGED_IN)
                    localRepository.setMerchantId(result.data.data.id)
                    _loginDataValue.value = Event(true)
                }
                is ApiResponse.Error -> {
                    showToast.value = "Error Cannot Login: ${result.message}"
                    _loginDataValue.value = Event(false)
                }
            }
        }
    }

}