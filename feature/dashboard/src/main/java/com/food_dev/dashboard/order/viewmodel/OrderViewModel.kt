package com.food_dev.dashboard.order.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.food_dev.domain.dto.local.model.order.Order
import com.food_dev.domain.repositories.LocalRepository
import com.food_dev.domain.repositories.RemoteRepository
import com.food_dev.utils.base.api.ApiResponse
import com.food_dev.utils.base.api.BaseArrayResponse
import com.food_dev.utils.base.api.BaseObjectResponse
import com.food_dev.utils.base.viewmodel.BaseViewModel
import com.food_dev.utils.ext.event.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
): BaseViewModel(){

    private var _orderData    = MutableLiveData<List<Order>>()
    private var _confirmOrderResult = MutableLiveData<Event<Order>>()

    val orderData: LiveData<List<Order>>
      get() = _orderData

    val confirmOrderResult: LiveData<Event<Order>>
        get() = _confirmOrderResult

    init {
        getMerchantOrder()
    }

    fun getMerchantOrder(){
        viewModelScope.launch {
            when(val result = remoteRepository.getMerchantOrder(localRepository.getMerchantId())){
                is ApiResponse.Success<BaseArrayResponse<Order>> -> {
                    val data         = result.data.data
                    val mappedOrder  = data.filter { !it.orderStatus.isOrderConfirmed }
                    _orderData.value = mappedOrder
                }
                is ApiResponse.Error -> {
                    showToast.value = "Error Cannot Get Order: ${result.message}"
                }
            }
        }
    }

    fun acceptOrder(orderId: Int){
        viewModelScope.launch {
            when(val result = remoteRepository.confirmOrder(orderId)){
                is ApiResponse.Success<BaseObjectResponse<Order>> -> {
                    _confirmOrderResult.value = Event(result.data.data)
                }
                is ApiResponse.Error -> {
                    showToast.value = "Error Cannot Get Order: ${result.message}"
                }
            }
        }
    }

}