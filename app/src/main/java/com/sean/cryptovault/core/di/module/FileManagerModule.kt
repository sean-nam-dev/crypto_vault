package com.sean.cryptovault.core.di.module

import android.content.res.Configuration
import com.sean.cryptovault.core.common.BIOMETRIC_AUTHENTICATION
import com.sean.cryptovault.core.common.DARK_MODE
import com.sean.cryptovault.core.common.ENTRANCE_START_DESTINATION
import com.sean.cryptovault.core.common.ENTRY_TRIES
import com.sean.cryptovault.core.common.FileManager
import com.sean.cryptovault.core.common.LOCALIZATION
import com.sean.cryptovault.core.common.Language
import com.sean.cryptovault.core.common.PIN
import com.sean.cryptovault.core.common.SECRET_WORD
import com.sean.cryptovault.core.common.SORT_BY_DATE
import com.sean.cryptovault.core.common.SORT_TYPE
import com.sean.cryptovault.core.common.TRY_COUNTER
import com.sean.cryptovault.core.di.annotation.qualifier.BiometricAuthenticationFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.DarkModeFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.LanguageFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.PinFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.SecretWordFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.SortFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.StartDestinationFMAnnotation
import com.sean.cryptovault.core.di.annotation.qualifier.TryCounterFMAnnotation
import com.sean.cryptovault.core.navigation.ScreenNavigation
import dagger.Module
import dagger.Provides
import dagger.Reusable
import java.io.File

@Module
class FileManagerModule {
    @Provides
    @Reusable
    @StartDestinationFMAnnotation
    fun provideStartDestinationFM(filesDir: File): FileManager =
        FileManager(
            filesDir,
            ENTRANCE_START_DESTINATION,
            ScreenNavigation.PINCodeCreation.route
        )

    @Provides
    @Reusable
    @DarkModeFMAnnotation
    fun provideDarkModeFM(filesDir: File, configuration: Configuration): FileManager =
        FileManager(
            filesDir,
            DARK_MODE,
            ((configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES).toString()
        )

    @Provides
    @Reusable
    @LanguageFMAnnotation
    fun provideLanguageFM(filesDir: File, configuration: Configuration): FileManager =
        FileManager(
            filesDir,
            LOCALIZATION,
            try {
                Language.valueOf(configuration.locales[0].language).name
            } catch (e: IllegalArgumentException) {
                Language.en.name
            }
        )

    @Provides
    @Reusable
    @PinFMAnnotation
    fun providePinFM(filesDir: File): FileManager =
        FileManager(
            filesDir,
            PIN,
            ""
        )

    @Provides
    @Reusable
    @SecretWordFMAnnotation
    fun provideSecretWordFM(filesDir: File): FileManager =
        FileManager(
            filesDir,
            SECRET_WORD,
            ""
        )

    @Provides
    @Reusable
    @BiometricAuthenticationFMAnnotation
    fun provideBiometricAuthenticationFM(filesDir: File): FileManager =
        FileManager(
            filesDir,
            BIOMETRIC_AUTHENTICATION,
            ""
        )

    @Provides
    @Reusable
    @TryCounterFMAnnotation
    fun provideTryCounterFM(filesDir: File): FileManager =
        FileManager(
            filesDir,
            TRY_COUNTER,
            ENTRY_TRIES
        )

    @Provides
    @Reusable
    @SortFMAnnotation
    fun provideSortFM(filesDir: File): FileManager =
        FileManager(
            filesDir,
            SORT_TYPE,
            SORT_BY_DATE.toString()
        )
}

