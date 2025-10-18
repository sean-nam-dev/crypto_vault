package com.sean.cryptovault.core.common

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

fun setLanguage(context: Context, language: String) {
    context.resources.apply {
        val locale = Locale(language)
        val config = Configuration(configuration)

        context.createConfigurationContext(configuration)
        Locale.setDefault(locale)
        config.setLocale(locale)
        context.resources.updateConfiguration(config, displayMetrics)
    }
}