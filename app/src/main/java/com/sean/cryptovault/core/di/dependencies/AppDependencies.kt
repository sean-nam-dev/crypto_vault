package com.sean.cryptovault.core.di.dependencies

import android.content.Context
import android.content.res.Configuration
import java.io.File

interface AppDependencies {
    val context: Context
    val configuration: Configuration
    val filesDir: File
}