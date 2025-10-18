package com.sean.cryptovault.core.di.component

import com.sean.cryptovault.MainActivity
import com.sean.cryptovault.core.common.FileManager
import com.sean.cryptovault.core.di.dependencies.AppDependencies
import com.sean.cryptovault.core.di.module.FileManagerModule
import com.sean.cryptovault.core.di.module.ViewModelModule
import com.sean.cryptovault.core.di.annotation.qualifier.BiometricAuthenticationFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.DarkModeFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.LanguageFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.PinFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.SecretWordFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.SortFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.StartDestinationFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.TryCounterFMAnnotation
import com.sean.cryptovault.f_base.data.room.card.CardDao
import com.sean.cryptovault.f_base.data.room.vault.VaultDao
import com.sean.cryptovault.f_base.presentation.view_model.CardCreationViewModel
import com.sean.cryptovault.f_base.presentation.view_model.HomeViewModel
import com.sean.cryptovault.f_base.presentation.view_model.VaultCreationViewModel
import com.sean.cryptovault.f_entrance.di.DaoModule
import com.sean.cryptovault.f_entrance.di.RepositoryModule
import com.sean.cryptovault.f_entrance.presentation.view_model.PINAuthorizationViewModel
import com.sean.cryptovault.f_entrance.presentation.view_model.SecretWordCreationViewModel
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        RepositoryModule::class,
        DaoModule::class,
        ViewModelModule::class,
        FileManagerModule::class
    ],
    dependencies = [AppDependencies::class]
)
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun appDependencies(appDependencies: AppDependencies): Builder
        fun build(): AppComponent
    }

    fun getVaultDao(): VaultDao

    fun getCardDao(): CardDao

    fun inject(mainActivity: MainActivity)

    fun getSecretWordCreationViewModel(): SecretWordCreationViewModel

    fun getPINAuthorizationViewModel(): PINAuthorizationViewModel

    fun getHomeViewModel(): HomeViewModel

    fun getVaultCreationViewModel(): VaultCreationViewModel

    fun getCardCreationViewModel(): CardCreationViewModel

    @StartDestinationFMAnnotation
    fun getStartDestinationFM(): FileManager

    @DarkModeFMAnnotation
    fun getDarkModeFM(): FileManager

    @LanguageFMAnnotation
    fun getLanguageFM(): FileManager

    @PinFMAnnotation
    fun getPinFM(): FileManager

    @SecretWordFMAnnotation
    fun getSecretWordFM(): FileManager

    @BiometricAuthenticationFMAnnotation
    fun getBiometricAuthenticationFM(): FileManager

    @TryCounterFMAnnotation
    fun getTryCounterFM(): FileManager

    @SortFMAnnotation
    fun getSortFM(): FileManager
}