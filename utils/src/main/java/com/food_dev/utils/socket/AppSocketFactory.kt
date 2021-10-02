package com.food_dev.utils.socket

import io.socket.client.IO
import io.socket.client.Manager
import java.net.URI

object AppSocketFactory {

    private const val SERVER_URL = "https://io-idcoding.tech/food-delivery"
    fun foodDeliverySocket()     = AppSocket(IO.socket(SERVER_URL))
}