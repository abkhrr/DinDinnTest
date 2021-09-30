package com.food_dev.data.repositoryimpl

import com.food_dev.domain.prefs.AppPreferences
import com.food_dev.domain.repositories.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val appPreferences: AppPreferences
): LocalRepository {

    override fun getCurrentLoginMode(): Int {
        TODO("Not yet implemented")
    }

}