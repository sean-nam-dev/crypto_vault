package com.sean.cryptovault.f_base.presentation.ui_screen

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.f_base.presentation.ui.VaultCreationUI
import com.sean.cryptovault.f_base.presentation.ui_action.VaultCreationUIAction
import com.sean.cryptovault.f_base.presentation.view_model.VaultEditionViewModel
import com.sean.cryptovault.f_base.common.CustomTextFieldContent
import com.sean.cryptovault.core.common.PASSWORD_IS_VISIBLE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VaultEditionUIScreen(
    viewModel: VaultEditionViewModel,
    navController: NavController,
    passwordHideStatus: String
) {
    val vaultCreationUIState = viewModel.state.collectAsState()

    val toastText = stringResource(R.string.fill_required_fields)

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val vaultCreationLabelList = stringArrayResource(R.array.vault_creation_label_list)
    val vaultCreationIdentifierList = stringArrayResource(R.array.vault_creation_username_list)
    val switchContentList = stringArrayResource(R.array.switch_content_list)

    val switchContent: List<Triple<String, Boolean, (Boolean) -> Unit>> = listOf(
        Triple(
            switchContentList[0],
            vaultCreationUIState.value.isNumberActive
        ) { viewModel.onVaultEditionAction(VaultCreationUIAction.OnNumberSwitchClickAction(it)) },
        Triple(
            switchContentList[1],
            vaultCreationUIState.value.isSpecialSignActive
        ) { viewModel.onVaultEditionAction(VaultCreationUIAction.OnSpecialSignSwitchClickAction(it)) },
        Triple(
            switchContentList[2],
            vaultCreationUIState.value.isUppercaseLetterActive
        ) { viewModel.onVaultEditionAction(VaultCreationUIAction.OnUppercaseLetterSwitchClickAction(it)) }
    )

    val customTextFieldContent = listOf(
        CustomTextFieldContent(
            value = vaultCreationUIState.value.serviceText,
            label = vaultCreationLabelList[0],
            placeholder = vaultCreationIdentifierList[0],
            trailingIcon = R.drawable.ic_round_cancel,
            isError = vaultCreationUIState.value.isServiceError,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None,
            onValueChange = { viewModel.onVaultEditionAction(VaultCreationUIAction.OnServiceValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onVaultEditionAction(VaultCreationUIAction.OnServiceTrailingIconClickAction()) }
        ),
        CustomTextFieldContent(
            value = vaultCreationUIState.value.identifierText,
            label = vaultCreationLabelList[1],
            placeholder = vaultCreationIdentifierList[1],
            trailingIcon = R.drawable.ic_round_cancel,
            isError = vaultCreationUIState.value.isIdentifierError,
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None,
            onValueChange = { viewModel.onVaultEditionAction(VaultCreationUIAction.OnIdentifierValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onVaultEditionAction(VaultCreationUIAction.OnIdentifierTrailingIconClickAction()) }
        ),
        CustomTextFieldContent(
            value = vaultCreationUIState.value.linkText,
            label = vaultCreationLabelList[2],
            placeholder = vaultCreationIdentifierList[2],
            trailingIcon = R.drawable.ic_round_cancel,
            isError = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None,
            onValueChange = { viewModel.onVaultEditionAction(VaultCreationUIAction.OnLinkValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onVaultEditionAction(VaultCreationUIAction.OnLinkTrailingIconClickAction()) }
        ),
        CustomTextFieldContent(
            value = vaultCreationUIState.value.passwordText,
            label = vaultCreationLabelList[3],
            placeholder = vaultCreationIdentifierList[3],
            trailingIcon = R.drawable.ic_round_settings_suggest,
            isError = vaultCreationUIState.value.isPasswordError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Ascii,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (passwordHideStatus == PASSWORD_IS_VISIBLE) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = { viewModel.onVaultEditionAction(VaultCreationUIAction.OnPasswordValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onVaultEditionAction(VaultCreationUIAction.OnPasswordTrailingIconClickAction()) }
        )
    )

    VaultCreationUI(
        vaultCreationUIState = vaultCreationUIState.value,
        scrollBehavior = scrollBehavior,
        customTextFieldContent = customTextFieldContent,
        switchContent = switchContent,
        onNavAction = { viewModel.onVaultEditionAction(VaultCreationUIAction.OnNavAction(navController)) },
        onSaveAction = {
            viewModel.onVaultEditionAction(
                VaultCreationUIAction.OnSaveAction(
                    navController = navController,
                    context = context,
                    toastText = toastText
                )
            )
        },
        onSearchValueChange = { viewModel.onVaultEditionAction(VaultCreationUIAction.OnSearchValueChange(it)) },
        onSearchBlockVisualChange = { viewModel.onVaultEditionAction(VaultCreationUIAction.OnSearchBlockVisualChange()) },
        onSearchClickAction = { viewModel.onVaultEditionAction(VaultCreationUIAction.OnSearchClickAction(focusManager)) },
        onSearchBlockExpansionChange = { viewModel.onVaultEditionAction(VaultCreationUIAction.OnSearchBlockExpansionChange()) },
        onImageClickAction = { viewModel.onVaultEditionAction(VaultCreationUIAction.OnImageClickAction(it)) },
        onPasswordDialogDismissRequest = { viewModel.onVaultEditionAction(VaultCreationUIAction.OnPasswordDialogDismissRequest()) },
        onSliderValueChange = { viewModel.onVaultEditionAction(VaultCreationUIAction.OnSliderValueChange(it)) },
        onGenerateClickAction = { viewModel.onVaultEditionAction(VaultCreationUIAction.OnGenerateClickAction()) }
    )
}