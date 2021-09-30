package com.food_dev.merchant.di

import androidx.lifecycle.ViewModel
import com.food_dev.auth.activity.viewmodel.AuthViewModel
import com.food_dev.auth.login.viewmodel.LoginViewModel
import com.food_dev.auth.register.viewmodel.RegisterViewModel
import com.food_dev.dashboard.ingredient.detail.viewmodel.IngredientViewModel
import com.food_dev.dashboard.ingredient.list.viewmodel.IngredientListViewModel
import com.food_dev.dashboard.order.viewmodel.OrderViewModel
import com.food_dev.dashboard.other.viewmodel.OtherMenuViewModel
import com.food_dev.dashboard.promo.viewmodel.PromoViewModel
import com.food_dev.dashboard.transaction.viewmodel.TransactionViewModel
import com.food_dev.dashboard.activity.viewmodel.MainViewModel
import com.food_dev.splash.viewmodel.SplashViewModel
import com.food_dev.utils.base.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelScoped
    @ViewModelKey(SplashViewModel::class)
    abstract fun provideSplashViewModel(viewModel: SplashViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelScoped
    @ViewModelKey(AuthViewModel::class)
    abstract fun provideAuthViewModel(viewModel: AuthViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelScoped
    @ViewModelKey(LoginViewModel::class)
    abstract fun provideLoginViewModel(viewModel: LoginViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelScoped
    @ViewModelKey(RegisterViewModel::class)
    abstract fun provideRegisterViewModel(viewModel: RegisterViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelScoped
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(viewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelScoped
    @ViewModelKey(IngredientViewModel::class)
    abstract fun provideIngredientViewModel(viewModel: IngredientViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelScoped
    @ViewModelKey(IngredientViewModel::class)
    abstract fun provideIngredientListViewModel(viewModel: IngredientListViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelScoped
    @ViewModelKey(OrderViewModel::class)
    abstract fun provideOrderViewModel(viewModel: OrderViewModel) : ViewModel

}