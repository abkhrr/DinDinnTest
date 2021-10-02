package com.food_dev.data.repositoryimpl

import com.food_dev.data.remote.ApiService
import com.food_dev.domain.dto.api.AuthResponse
import com.food_dev.domain.dto.api.LoginRequest
import com.food_dev.domain.dto.api.RegisterRequest
import com.food_dev.domain.dto.api.WelcomeProfileRequest
import com.food_dev.domain.dto.local.model.ingredient.Ingredient
import com.food_dev.domain.dto.local.model.ingredient.showcase.IngredientShowcase
import com.food_dev.domain.dto.local.model.order.Order
import com.food_dev.domain.repositories.RemoteRepository
import com.food_dev.utils.base.api.ApiResponse
import com.food_dev.utils.base.api.BaseArrayResponse
import com.food_dev.utils.base.api.BaseObjectResponse
import com.food_dev.utils.ext.api.SocketError
import com.food_dev.utils.ext.api.TypeError
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val apiService: ApiService): RemoteRepository {

    override suspend fun login(loginRequest: LoginRequest): ApiResponse<BaseObjectResponse<AuthResponse>> {
        return try {
            val response = apiService.login(loginRequest)
            return ApiResponse.Success(response)
        } catch (e: Exception){
            getException(e)
        }
    }

    override suspend fun register(registerRequest: RegisterRequest): ApiResponse<BaseObjectResponse<AuthResponse>> {
        return try {
            val response = apiService.register(registerRequest)
            return ApiResponse.Success(response)
        } catch (e: Exception){
            getException(e)
        }
    }

    override suspend fun getMerchantIngredient(merchantId: String): ApiResponse<BaseArrayResponse<Ingredient>> {
        return try {
            val response = apiService.getMerchantIngredient(merchantId)
            return ApiResponse.Success(response)
        } catch (e: Exception){
            getException(e)
        }
    }

    override suspend fun getMerchantShowcase(merchantId: String): ApiResponse<BaseArrayResponse<IngredientShowcase>> {
        return try {
            val response = apiService.getMerchantShowcase(merchantId)
            return ApiResponse.Success(response)
        } catch (e: Exception){
            getException(e)
        }
    }

    override suspend fun getMerchantOrder(merchantId: String): ApiResponse<BaseArrayResponse<Order>> {
        return try {
            val response = apiService.getMerchantOrder(merchantId)
            return ApiResponse.Success(response)
        } catch (e: Exception){
            getException(e)
        }
    }

    override suspend fun confirmOrder(orderId: Int): ApiResponse<BaseObjectResponse<Order>> {
        return try {
            val response = apiService.confirmOrder(orderId)
            return ApiResponse.Success(response)
        } catch (e: Exception){
            getException(e)
        }
    }

    override suspend fun expiredOrder(orderId: Int): ApiResponse<BaseObjectResponse<Order>> {
        return try {
            val response = apiService.expiredOrder(orderId)
            return ApiResponse.Success(response)
        } catch (e: Exception){
            getException(e)
        }
    }

    override suspend fun prepareOrder(orderId: Int): ApiResponse<BaseObjectResponse<Order>> {
        return try {
            val response = apiService.prepareOrder(orderId)
            return ApiResponse.Success(response)
        } catch (e: Exception){
            getException(e)
        }
    }

    override suspend fun welcomeProfileUpdate(welcomeProfileRequest: WelcomeProfileRequest, merchantId: String): ApiResponse<BaseObjectResponse<AuthResponse>> {
        return try {
            val response = apiService.welcomeProfileUpdate(welcomeProfileRequest, merchantId)
            return ApiResponse.Success(response)
        } catch (e: Exception){
            getException(e)
        }
    }

    private fun getException(e: Exception): ApiResponse.Error {
        return when (e){
            is HttpException -> ApiResponse.Error(e.message(), e.code(), TypeError.ERROR_HTTP)
            is SocketTimeoutException -> ApiResponse.Error(e.localizedMessage, SocketError.SocketTimeOut.code, TypeError.ERROR_SOCKET_TIMEOUT)
            else                       -> ApiResponse.Error(e.localizedMessage, Int.MAX_VALUE, TypeError.NETWORK_ERROR)
        }
    }

}