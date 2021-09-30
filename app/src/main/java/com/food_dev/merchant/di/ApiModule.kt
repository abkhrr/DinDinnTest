package com.food_dev.merchant.di

import com.food_dev.data.remote.ApiService
import com.food_dev.merchant.MainApplication
import com.food_dev.utils.base.config.WebApiProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideWebApiProvider(): WebApiProvider = WebApiProvider

    @Singleton
    @Provides
    fun provideRetrofit(
        webApiProvider: WebApiProvider,
        myApplication: MainApplication
    ): Retrofit = webApiProvider.getRetrofit(myApplication.getBaseUrl(), myApplication.applicationContext)

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(
        ApiService::class.java
    )
}