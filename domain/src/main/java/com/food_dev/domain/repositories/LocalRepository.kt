package com.food_dev.domain.repositories

import com.food_dev.utils.ext.constant.Const

interface LocalRepository{
    fun getCurrentLoginMode(): Int
    fun setCurrentLoginMode(mode: Const.LoggedInMode)
    fun setMerchantId(merchantId: String)
    fun getMerchantId(): String
    fun setFirebaseToken(firebaseToken: String?)
    fun getFirebaseToken(): String
}