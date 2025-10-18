package com.sean.cryptovault.f_entrance.presentation.ui_action

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import java.util.concurrent.Executor

sealed class PINAuthorizationUIAction {
    data class OnPINChange(
        val pin: String,
        val warning: String,
        val context: Context,
        val navController: NavController
    ): PINAuthorizationUIAction()
    data class OnBackSpace(val action: Unit): PINAuthorizationUIAction()
    data class OnBiometricUse(
        val title: String,
        val negativeButtonText: String,
        val activity: FragmentActivity,
        val executor: Executor,
        val navController: NavController
    ): PINAuthorizationUIAction()
}