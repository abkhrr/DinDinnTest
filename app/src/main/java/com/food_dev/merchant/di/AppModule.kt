package com.food_dev.merchant.di

import android.content.Context
import android.net.ConnectivityManager
import com.food_dev.data.local.prefs.AppPreferencesImpl
import com.food_dev.domain.prefs.AppPreferences
import com.food_dev.merchant.MainApplication
import com.food_dev.utils.ext.constant.Const
import com.food_dev.utils.ext.qualifiers.PreferenceInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MainApplication {
        return app as MainApplication
    }

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: MainApplication): Context {
        return context
    }

    @Provides
    @PreferenceInfo
    fun providePrefName(): String = Const.PREFS_NAME

    @Provides
    @Singleton
    fun provideAppPreferencesImpl(appPreferences: AppPreferencesImpl): AppPreferences {
        return appPreferences
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(context: MainApplication): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

}