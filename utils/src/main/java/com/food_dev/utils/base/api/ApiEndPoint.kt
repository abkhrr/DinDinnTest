package com.food_dev.utils.base.api

object ApiEndPoint {
    const val API_REGISTER_MERCHANT             = "/food-delivery/merchant/register"
    const val API_LOGIN_MERCHANT                = "/food-delivery/merchant/login"
    const val API_FIRST_TIME_PROFILE            = "/food-delivery/merchant/update/first-time/{merchantId}"
    const val API_GET_MERCHANT_INGREDIENT       = "/food-delivery/menu/get/merchant/indgridients/{merchantId}"
    const val API_GET_MERCHANT_SHOWCASE         = "/food-delivery/showcase/get/{merchantId}"
    const val API_GET_SHOWCASE_INGREDIENT       = "/food-delivery/showcase/get/showcase/ingredient/{showcaseId}"

    const val API_GET_MERCHANT_ORDER            = "/food-delivery/order/get/merchant/{merchantId}"
    const val API_MERCHANT_SET_ORDER_CONFIRMED  = "/food-delivery/order/put/status/confirmed/{orderId}"
    const val API_MERCHANT_SET_ORDER_EXPIRED    = "/food-delivery/order/put/status/prepared/{orderId}"
    const val API_MERCHANT_SET_ORDER_PREPARED   = "/food-delivery/order/put/status/expired/{orderId}"
}