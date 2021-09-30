package com.food_dev.merchant.di

import com.food_dev.data.repositoryimpl.LocalRepositoryImpl
import com.food_dev.data.repositoryimpl.RemoteRepositoryImpl
import com.food_dev.domain.repositories.LocalRepository
import com.food_dev.domain.repositories.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository {
        return localRepositoryImpl
    }

    @Provides
    @ViewModelScoped
    fun provideRemoteRepository(remoteRepositoryImpl: RemoteRepositoryImpl): RemoteRepository {
        return remoteRepositoryImpl
    }

}