package com.food_dev.utils.base.viewmodel

import androidx.lifecycle.ViewModel
import com.food_dev.utils.ext.event.SingleLiveEvent

abstract class BaseViewModel: ViewModel() {
    val isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showToast: SingleLiveEvent<String> = SingleLiveEvent()
    val showSnack: SingleLiveEvent<String> = SingleLiveEvent()
}