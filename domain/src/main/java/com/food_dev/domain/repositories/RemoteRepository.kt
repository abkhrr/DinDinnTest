package com.food_dev.domain.repositories

import com.food_dev.domain.dto.api.*
import com.food_dev.domain.dto.local.model.ingredient.Ingredient
import com.food_dev.domain.dto.local.model.ingredient.showcase.IngredientShowcase
import com.food_dev.domain.dto.local.model.order.Order
import com.food_dev.utils.base.api.ApiEndPoint
import com.food_dev.utils.base.api.ApiResponse
import com.food_dev.utils.base.api.BaseArrayResponse
import com.food_dev.utils.base.api.BaseObjectResponse
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface RemoteRepository {

    suspend fun login(
        loginRequest: LoginRequest
    ): ApiResponse<BaseObjectResponse<AuthResponse>>

    suspend fun register(
        registerRequest: RegisterRequest
    ): ApiResponse<BaseObjectResponse<AuthResponse>>

    suspend fun welcomeProfileUpdate(
        welcomeProfileRequest: WelcomeProfileRequest,
        merchantId: String
    ): ApiResponse<BaseObjectResponse<AuthResponse>>

    suspend fun getMerchantIngredient(merchantId: String): ApiResponse<BaseArrayResponse<Ingredient>>
    suspend fun getMerchantShowcase(merchantId: String): ApiResponse<BaseArrayResponse<IngredientShowcase>>
    suspend fun getShowcaseIngredient(showcaseId: Int): ApiResponse<BaseObjectResponse<ShowcaseIngredientResponse>>
    suspend fun getMerchantOrder(merchantId: String): ApiResponse<BaseArrayResponse<Order>>
    suspend fun confirmOrder(orderId: Int): ApiResponse<BaseObjectResponse<Order>>
    suspend fun expiredOrder(orderId: Int): ApiResponse<BaseObjectResponse<Order>>
    suspend fun prepareOrder(orderId: Int): ApiResponse<BaseObjectResponse<Order>>

}