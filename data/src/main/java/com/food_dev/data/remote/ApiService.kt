package com.food_dev.data.remote

import com.food_dev.domain.dto.api.*
import com.food_dev.domain.dto.local.model.ingredient.Ingredient
import com.food_dev.domain.dto.local.model.ingredient.showcase.IngredientShowcase
import com.food_dev.domain.dto.local.model.order.Order
import com.food_dev.utils.base.api.ApiEndPoint
import com.food_dev.utils.base.api.BaseArrayResponse
import com.food_dev.utils.base.api.BaseObjectResponse
import retrofit2.http.*

interface ApiService {

    @POST(ApiEndPoint.API_REGISTER_MERCHANT)
    suspend fun register(@Body registerRequest: RegisterRequest): BaseObjectResponse<AuthResponse>

    @POST(ApiEndPoint.API_LOGIN_MERCHANT)
    suspend fun login(@Body loginRequest: LoginRequest): BaseObjectResponse<AuthResponse>

    @GET(ApiEndPoint.API_GET_MERCHANT_INGREDIENT)
    suspend fun getMerchantIngredient(@Path("merchantId") merchantId: String): BaseArrayResponse<Ingredient>

    @GET(ApiEndPoint.API_GET_MERCHANT_SHOWCASE)
    suspend fun getMerchantShowcase(@Path("merchantId") merchantId: String): BaseArrayResponse<IngredientShowcase>

    @GET(ApiEndPoint.API_GET_SHOWCASE_INGREDIENT)
    suspend fun getShowcaseIngredient(@Path("showcaseId") showcaseId: Int): BaseObjectResponse<ShowcaseIngredientResponse>

    @GET(ApiEndPoint.API_GET_MERCHANT_ORDER)
    suspend fun getMerchantOrder(@Path("merchantId") merchantId: String): BaseArrayResponse<Order>

    @PUT(ApiEndPoint.API_MERCHANT_SET_ORDER_CONFIRMED)
    suspend fun confirmOrder(@Path("orderId") orderId: Int): BaseObjectResponse<Order>

    @PUT(ApiEndPoint.API_MERCHANT_SET_ORDER_EXPIRED)
    suspend fun expiredOrder(@Path("orderId") orderId: Int): BaseObjectResponse<Order>

    @PUT(ApiEndPoint.API_MERCHANT_SET_ORDER_PREPARED)
    suspend fun prepareOrder(@Path("orderId") orderId: Int): BaseObjectResponse<Order>

    @PUT(ApiEndPoint.API_FIRST_TIME_PROFILE)
    suspend fun welcomeProfileUpdate(
        @Body welcomeProfileRequest: WelcomeProfileRequest,
        @Path("merchantId") merchantId: String
    ): BaseObjectResponse<AuthResponse>

}