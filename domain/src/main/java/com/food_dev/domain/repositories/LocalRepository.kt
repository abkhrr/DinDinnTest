package com.food_dev.domain.repositories

import javax.inject.Inject

interface LocalRepository{
    fun getCurrentLoginMode(): Int
}