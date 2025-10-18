package com.sean.cryptovault.core.navigation

sealed class ScreenNavigation(val route: String) {
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
    fun getArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }

    data object Entrance: ScreenNavigation("entrance")

    data object PINCodeCreation: ScreenNavigation("pin_code_creation")
    data object PINCodeApproval: ScreenNavigation("pin_code_approval")
    data object PINCodeAuthorization: ScreenNavigation("pin_code_authorization")
    data object SecretWordCreation: ScreenNavigation("secret_word_creation")
    data object SecretWordAuthorization: ScreenNavigation("secret_word_authorization")


    data object Base: ScreenNavigation("base")

    data object Home: ScreenNavigation("home")
    data object Statistics: ScreenNavigation("statistics")
    data object PasswordGenerator: ScreenNavigation("generator")

    data object VaultCreation: ScreenNavigation("vault_creation")
    data object VaultEdition: ScreenNavigation("vault_edition")

    data object CardCreation: ScreenNavigation("card_creation")
    data object CardEdition: ScreenNavigation("card_edition")


    data object Settings: ScreenNavigation("settings")

    data object General: ScreenNavigation("general")
    data object Language: ScreenNavigation("language")
    data object PINChanger: ScreenNavigation("pin_changer")
    data object ContactUs: ScreenNavigation("contact_us")
    data object FAQ: ScreenNavigation("faq")
}
