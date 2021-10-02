package com.food_dev.auth.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.food_dev.domain.dto.api.AuthResponse
import com.food_dev.domain.dto.api.RegisterRequest
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
class RegisterViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
): BaseViewModel() {

    private var _registerDataValue = MutableLiveData<Event<Boolean>>()

    val registerDataValue: LiveData<Event<Boolean>>
        get() = _registerDataValue

    fun registerUser(email: String, storeName: String, merchantPIC: String, password: String){
        val registerRequest = RegisterRequest(storeName, merchantPIC, email, password)
        viewModelScope.launch {
            when(val result = remoteRepository.register(registerRequest)){
                is ApiResponse.Success<BaseObjectResponse<AuthResponse>> -> {
                    localRepository.setCurrentLoginMode(Const.LoggedInMode.LOGGED_IN)
                    _registerDataValue.value = Event(true)
                }
                is ApiResponse.Error -> {
                    showToast.value = "Error Cannot Register: ${result.message}"
                    _registerDataValue.value = Event(false)
                }
            }
        }
    }

}