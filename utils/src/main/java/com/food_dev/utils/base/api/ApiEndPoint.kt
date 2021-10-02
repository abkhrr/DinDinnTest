package com.food_dev.utils.base.api

object ApiEndPoint {
    const val API_REGISTER_MERCHANT             = "/merchant/register"
    const val API_LOGIN_MERCHANT                = "/merchant/login"
    const val API_FIRST_TIME_PROFILE            = "/merchant/update/first-time/{merchantId}"
    const val API_GET_MERCHANT_INGREDIENT       = "/menu/get/merchant/indgridients/{merchantId}"
    const val API_GET_MERCHANT_SHOWCASE         = "/showcase/get/{merchantId}"

    const val API_GET_MERCHANT_ORDER            = "/order/get/merchant/{merchantId}"
    const val API_MERCHANT_SET_ORDER_CONFIRMED  = "/order/confirmed/{orderId}"
    const val API_MERCHANT_SET_ORDER_EXPIRED    = "/order/prepared/{orderId}"
    const val API_MERCHANT_SET_ORDER_PREPARED   = "/order/expired/{orderId}"
}