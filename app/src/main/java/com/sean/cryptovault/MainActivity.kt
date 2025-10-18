package com.sean.cryptovault

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringArrayResource
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.sean.cryptovault.core.common.FALSE
import com.sean.cryptovault.core.common.SEPARATOR
import com.sean.cryptovault.core.common.TRUE
import com.sean.cryptovault.core.common.setLanguage
import com.sean.cryptovault.core.component.NavigationBottomSheet
import com.sean.cryptovault.core.di.component.AppComponent
import com.sean.cryptovault.core.di.component.DaggerAppComponent
import com.sean.cryptovault.core.di.dependencies.AppDependencies
import com.sean.cryptovault.core.navigation.ScreenNavigation
import com.sean.cryptovault.f_base.presentation.ui_screen.CardCreationUIScreen
import com.sean.cryptovault.f_base.presentation.ui_screen.CardEditionUIScreen
import com.sean.cryptovault.f_base.presentation.ui_screen.HomeUIScreen
import com.sean.cryptovault.f_base.presentation.ui_screen.PasswordGeneratorUIScreen
import com.sean.cryptovault.f_base.presentation.ui_screen.StatisticsUIScreen
import com.sean.cryptovault.f_base.presentation.ui_screen.VaultCreationUIScreen
import com.sean.cryptovault.f_base.presentation.ui_screen.VaultEditionUIScreen
import com.sean.cryptovault.f_base.presentation.view_model.CardEditionViewModelFactory
import com.sean.cryptovault.f_base.presentation.view_model.PasswordGeneratorViewModel
import com.sean.cryptovault.f_base.presentation.view_model.VaultEditionViewModelFactory
import com.sean.cryptovault.f_entrance.presentation.ui_screen.PINApprovalUIScreen
import com.sean.cryptovault.f_entrance.presentation.ui_screen.PINAuthorizationUIScreen
import com.sean.cryptovault.f_entrance.presentation.ui_screen.PINCreationUIScreen
import com.sean.cryptovault.f_entrance.presentation.ui_screen.SecretWordAuthorizationUIScreen
import com.sean.cryptovault.f_entrance.presentation.ui_screen.SecretWordCreationUIScreen
import com.sean.cryptovault.f_entrance.presentation.view_model.PINApprovalViewModel
import com.sean.cryptovault.f_entrance.presentation.view_model.PINCreationViewModel
import com.sean.cryptovault.f_entrance.presentation.view_model.SecretWordAuthorizationViewModelFactory
import com.sean.cryptovault.f_settings.presentation.ui_screen.ContactUsUIScreen
import com.sean.cryptovault.f_settings.presentation.ui_screen.FAQUIScreen
import com.sean.cryptovault.f_settings.presentation.ui_screen.GeneralUIScreen
import com.sean.cryptovault.f_settings.presentation.ui_screen.LanguageUIScreen
import com.sean.cryptovault.f_settings.presentation.ui_screen.PINChangerUIScreen
import com.sean.cryptovault.f_settings.presentation.view_model.PINChangerViewModel
import com.sean.cryptovault.ui.theme.CryptoVaultTheme
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class MainApp: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appDependencies(AppDependenciesImpl())
            .build()
    }

    private inner class AppDependenciesImpl: AppDependencies {
        override val context: Context
            get() = this@MainApp
        override val configuration: Configuration
            get() = this@MainApp.resources.configuration
        override val filesDir: File
            get() = this@MainApp.filesDir

    }
}

@Suppress("RecursivePropertyAccessor")
val Context.appComponent: AppComponent
    get() = when(this) {
        is MainApp -> appComponent
        else -> this.applicationContext.appComponent
    }

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : FragmentActivity() {

    @Inject
    lateinit var secretWordAuthorizationViewModelFactory: SecretWordAuthorizationViewModelFactory

    @Inject
    lateinit var vaultEditionViewModelFactory: VaultEditionViewModelFactory

    @Inject
    lateinit var cardEditionViewModelFactory: CardEditionViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        setContent {
//            val systemUiController = rememberSystemUiController()
//            systemUiController.setSystemBarsColor(MaterialTheme.colorScheme.surfaceTint)
//            systemUiController.setStatusBarColor(MaterialTheme.colorScheme.surface)

            val startDestinationFM = appComponent.getStartDestinationFM()
            val darkModeFM = appComponent.getDarkModeFM()
            val languageFM = appComponent.getLanguageFM()
            val pinFM = appComponent.getPinFM()
            val secretWordFM = appComponent.getSecretWordFM()

            val navController = rememberNavController()

            var isDarkTheme by remember { mutableStateOf(darkModeFM.read() == TRUE) }
            val onDarkThemeChange: () -> Unit = {
                isDarkTheme = !isDarkTheme
                darkModeFM.assign(isDarkTheme.toString())
            }

            var language by remember { mutableStateOf(languageFM.read()) }
            val onLanguageChange: (String) -> Unit = {
                language = it
                languageFM.assign(it)
            }

            setLanguage(this, language)

            CryptoVaultTheme(darkTheme = isDarkTheme) {
                NavHost(
                    navController = navController,
                    startDestination = ScreenNavigation.Entrance.route
                ) {
                    navigation(
                        startDestination = startDestinationFM.read(),
                        route = ScreenNavigation.Entrance.route
                    ) {
                        composable(ScreenNavigation.PINCodeCreation.route) {
                            PINCreationUIScreen(
                                viewModel = PINCreationViewModel(),
                                navController = navController
                            )
                        }
                        composable(
                            ScreenNavigation.PINCodeApproval.getArgs("original_pin"),
                            listOf(navArgument("original_pin") { type = NavType.StringType})
                        ) {
                            PINApprovalUIScreen(
                                viewModel = PINApprovalViewModel(),
                                navController = navController,
                                originalPIN = it.arguments?.getString("original_pin")!!
                            )
                        }
                        composable(
                            ScreenNavigation.SecretWordCreation.getArgs("confirmed_pin"),
                            listOf(navArgument("confirmed_pin") { type = NavType.StringType })
                        ) {
                            SecretWordCreationUIScreen(
                                viewModel = appComponent.getSecretWordCreationViewModel(),
                                navController = navController,
                                confirmedPIN = it.arguments?.getString("confirmed_pin")!!,
                                questionList = stringArrayResource(R.array.security_question_list)
                            )
                        }
                        composable(ScreenNavigation.PINCodeAuthorization.route) {
                            PINAuthorizationUIScreen(
                                viewModel = appComponent.getPINAuthorizationViewModel(),
                                navController = navController
                            )
                        }
                        composable(ScreenNavigation.SecretWordAuthorization.route) {
                            SecretWordAuthorizationUIScreen(
                                viewModel = secretWordAuthorizationViewModelFactory
                                    .create(stringArrayResource(R.array.security_question_list)[secretWordFM.read().split(SEPARATOR).first().toInt()]),
                                navController = navController
                            )
                        }
                    }
                    navigation(
                        startDestination = ScreenNavigation.Home.route,
                        route = ScreenNavigation.Base.route
                    ) {
                        val vaultDao = appComponent.getVaultDao()
                        val cardDao = appComponent.getCardDao()

                        composable(ScreenNavigation.Home.route) {
                            val sheetState = rememberModalBottomSheetState()
                            val scope = rememberCoroutineScope()
                            var isSheetShown by remember { mutableStateOf(false) }

                            val onShowBottomSheet: () -> Unit = { isSheetShown = true }
                            val onHideBottomSheet: () -> Unit = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        isSheetShown = false
                                    }
                                }
                            }
                            val onBottomSheetDR: () -> Unit = {
                                isSheetShown = false
                            }

                            NavigationBottomSheet(
                                isSheetShown = isSheetShown,
                                sheetState = sheetState,
                                navController = navController,
                                onDismissRequest = onBottomSheetDR,
                                onHideBottomSheet = onHideBottomSheet
                            ) {
                                HomeUIScreen(
                                    viewModel = appComponent.getHomeViewModel(),
                                    navController = navController,
                                    vaultDao = vaultDao,
                                    cardDao = cardDao,
                                    onBurgerTopBarNavAction = onShowBottomSheet
                                )
                            }
                        }
                        composable(ScreenNavigation.Statistics.route) {
                            val sheetState = rememberModalBottomSheetState()
                            val scope = rememberCoroutineScope()
                            var isSheetShown by remember { mutableStateOf(false) }

                            val onShowBottomSheet: () -> Unit = { isSheetShown = true }
                            val onHideBottomSheet: () -> Unit = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        isSheetShown = false
                                    }
                                }
                            }
                            val onBottomSheetDR: () -> Unit = {
                                isSheetShown = false
                            }

                            NavigationBottomSheet(
                                isSheetShown = isSheetShown,
                                sheetState = sheetState,
                                navController = navController,
                                onDismissRequest = onBottomSheetDR,
                                onHideBottomSheet = onHideBottomSheet
                            ) {
                                StatisticsUIScreen(
                                    navController = navController,
                                    vaultDao = vaultDao,
                                    cardDao = cardDao,
                                    onBurgerTopBarNavAction = onShowBottomSheet
                                )
                            }
                        }
                        composable(ScreenNavigation.PasswordGenerator.route) {
                            val sheetState = rememberModalBottomSheetState()
                            val scope = rememberCoroutineScope()
                            var isSheetShown by remember { mutableStateOf(false) }

                            val onShowBottomSheet: () -> Unit = { isSheetShown = true }
                            val onHideBottomSheet: () -> Unit = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        isSheetShown = false
                                    }
                                }
                            }
                            val onBottomSheetDR: () -> Unit = {
                                isSheetShown = false
                            }

                            NavigationBottomSheet(
                                isSheetShown = isSheetShown,
                                sheetState = sheetState,
                                navController = navController,
                                onDismissRequest = onBottomSheetDR,
                                onHideBottomSheet = onHideBottomSheet
                            ) {
                                PasswordGeneratorUIScreen(
                                    viewModel = PasswordGeneratorViewModel(),
                                    onBurgerTopBarNavAction = onShowBottomSheet
                                )
                            }
                        }
                        composable(ScreenNavigation.VaultCreation.route) {
                            VaultCreationUIScreen(
                                viewModel = appComponent.getVaultCreationViewModel(),
                                navController = navController,
                                passwordHideStatus = FALSE
                            )
                        }
                        composable(
                            ScreenNavigation.VaultEdition.getArgs(
                                "id", "image_id", "service", "username",
                                "password", "link", "is_favorite"
                            )
                        ) {
                            VaultEditionUIScreen(
                                viewModel = vaultEditionViewModelFactory.create(
                                    it.arguments?.getString("id")!!.toLong(),
                                    it.arguments?.getString("image_id")!!.toInt(),
                                    it.arguments?.getString("service")!!,
                                    it.arguments?.getString("username")!!,
                                    it.arguments?.getString("password")!!,
                                    it.arguments?.getString("link")!!.ifBlank { "" }.replace('|', '/'),
                                    it.arguments?.getString("is_favorite") == TRUE
                                ),
                                navController = navController,
                                passwordHideStatus = FALSE
                            )
                        }
                        composable(ScreenNavigation.CardCreation.route) {
                            CardCreationUIScreen(
                                viewModel = appComponent.getCardCreationViewModel(),
                                navController = navController
                            )
                        }
                        composable(
                            ScreenNavigation.CardEdition.getArgs(
                                "id", "bank", "number", "valid_thru",
                                "cvv", "full_name", "is_favorite"
                            )
                        ) {
                            CardEditionUIScreen(
                                viewModel = cardEditionViewModelFactory.create(
                                    it.arguments?.getString("id")!!.toLong(),
                                    it.arguments?.getString("bank")!!,
                                    it.arguments?.getString("number")!!,
                                    it.arguments?.getString("valid_thru")!!,
                                    it.arguments?.getString("cvv")!!.ifBlank { "" },
                                    it.arguments?.getString("full_name")!!,
                                    it.arguments?.getString("is_favorite")!! == TRUE
                                ),
                                navController = navController
                            )
                        }
                    }
                    navigation(
                        startDestination = ScreenNavigation.General.route,
                        route = ScreenNavigation.Settings.route
                    ) {
                        composable(ScreenNavigation.General.route) {
                            val sheetState = rememberModalBottomSheetState()
                            val scope = rememberCoroutineScope()
                            var isSheetShown by remember { mutableStateOf(false) }

                            val onShowBottomSheet: () -> Unit = { isSheetShown = true }
                            val onHideBottomSheet: () -> Unit = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        isSheetShown = false
                                    }
                                }
                            }
                            val onBottomSheetDR: () -> Unit = {
                                isSheetShown = false
                            }

                            NavigationBottomSheet(
                                isSheetShown = isSheetShown,
                                sheetState = sheetState,
                                navController = navController,
                                onDismissRequest = onBottomSheetDR,
                                onHideBottomSheet = onHideBottomSheet
                            ) {
                                GeneralUIScreen(
                                    navController = navController,
                                    isDarkTheme = isDarkTheme,
                                    onBurgerTopBarNavAction = onShowBottomSheet,
                                    onDarkThemeChange = onDarkThemeChange
                                )
                            }
                        }
                        composable(ScreenNavigation.Language.route) {
                            LanguageUIScreen(
                                navController = navController,
                                language = language,
                                onLanguageChange = onLanguageChange
                            )
                        }
                        composable(ScreenNavigation.PINChanger.route) {
                            PINChangerUIScreen(
                                viewModel = PINChangerViewModel(),
                                navController = navController,
                                pinFM = pinFM,
                                secretWordFM = secretWordFM,
                                startDestinationFM = startDestinationFM
                            )
                        }
                        composable(ScreenNavigation.ContactUs.route) {
                            ContactUsUIScreen(navController)
                        }
                        composable(ScreenNavigation.FAQ.route) {
                            FAQUIScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
