package com.food_dev.data.repositoryimpl

import com.food_dev.domain.prefs.AppPreferences
import com.food_dev.domain.repositories.LocalRepository
import com.food_dev.utils.ext.constant.Const
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val appPreferences: AppPreferences
): LocalRepository {

    override fun getCurrentLoginMode(): Int {
        return appPreferences.getCurrentLoginMode()
    }

    override fun setCurrentLoginMode(mode: Const.LoggedInMode) {
        return appPreferences.setCurrentLoginMode(mode)
    }

    override fun setMerchantId(merchantId: String) {
        return appPreferences.setMerchantId(merchantId)
    }

    override fun getMerchantId(): String {
        return appPreferences.getMerchantId()
    }

    override fun setFirebaseToken(firebaseToken: String?) {
        return appPreferences.setFirebaseToken(firebaseToken)
    }

    override fun getFirebaseToken(): String {
        return appPreferences.getFirebaseToken()
    }

}