package com.food_dev.merchant.notification

import android.content.Context
import com.food_dev.domain.repositories.LocalRepository
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FirebaseMessagingService: FirebaseMessagingService() {

    private val sharedPreferences = this.getSharedPreferences("foo_dev_prefs", Context.MODE_PRIVATE)

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val data  = remoteMessage.data

        if (data.isNotEmpty()){
            NotificationService().setNotification(this, data)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sharedPreferences.edit().putString("PREF_KEY_FIREBASE_TOKEN", token).apply()
    }

}