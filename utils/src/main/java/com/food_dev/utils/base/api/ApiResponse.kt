package com.food_dev.utils.base.api

import com.food_dev.utils.ext.api.TypeError

sealed class ApiResponse<out T: Any> {
    data class Success<out T: Any>(val data: T) : ApiResponse<T>()
    data class Error(val message: String?, val statusCode: Int?, val typeError: TypeError) : ApiResponse<Nothing>()
}