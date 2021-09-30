package com.food_dev.merchant.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    //Singleton
    //Provides
    //un provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
    //   Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.APPLICATION_ID)
    //       .build()

    //@Singleton
    //@Provides
    //fun provideExampleDao(appDatabase: AppDatabase) = appDatabase.exampleDao()
}