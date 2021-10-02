package com.food_dev.utils.socket

import com.food_dev.utils.ext.log.MyLog
import com.google.gson.Gson
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.net.URISyntaxException

class AppSocket(private val socket: Socket) {

    val gson : Gson = Gson()

    val isConnected: Boolean
        get() = socket.connected()

    fun connect() {
        try {
            socket.connect()
            MyLog.e("success", "suususus")
        } catch (e: URISyntaxException){
            MyLog.e("fail", "asu babi")
        }
    }

    fun subscribeToSocket(name: String, data: Any){
        socket.emit(name, data)
    }

    fun on(name: String, listener: Emitter.Listener){
        socket.on(name, listener)
    }

    fun emit(name: String, data: Any){
        socket.emit(name, data)
    }
}