package com.food_dev.dashboard.ingredient.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.food_dev.domain.dto.local.model.ingredient.showcase.IngredientShowcase
import com.food_dev.domain.repositories.LocalRepository
import com.food_dev.domain.repositories.RemoteRepository
import com.food_dev.utils.base.api.ApiResponse
import com.food_dev.utils.base.api.BaseArrayResponse
import com.food_dev.utils.base.api.BaseObjectResponse
import com.food_dev.utils.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientListViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
): BaseViewModel(){

    private var _showcaseData = MutableLiveData<List<IngredientShowcase>>()

    val showcaseData: LiveData<List<IngredientShowcase>>
      get() = _showcaseData

    fun searchMerchantShowcase(){
        val merchantId = localRepository.getMerchantId()
        viewModelScope.launch {
            when(val result = remoteRepository.getMerchantShowcase(merchantId)){
                is ApiResponse.Success<BaseArrayResponse<IngredientShowcase>> -> {
                    val data             = result.data.data
                    _showcaseData.value  = data
                }
                is ApiResponse.Error -> {
                    showToast.value = "Error Cannot Get Order: ${result.message}"
                }
            }
        }
    }

    init {
        searchMerchantShowcase()
    }

}