package com.sean.cryptovault.f_base.presentation.ui_screen

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import com.sean.cryptovault.R
import com.sean.cryptovault.f_base.presentation.ui.CardCreationUI
import com.sean.cryptovault.f_base.presentation.ui_action.CardCreationUIAction
import com.sean.cryptovault.f_base.presentation.view_model.CardCreationViewModel
import com.sean.cryptovault.f_base.common.CustomTextFieldContent
import com.sean.cryptovault.f_base.common.VisualTransformationFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCreationUIScreen(
    viewModel: CardCreationViewModel,
    navController: NavController
) {
    val cardCreationUIState = viewModel.state.collectAsState()

    val toastText = stringResource(R.string.fill_required_fields)

    val cardCreationLabelList = stringArrayResource(R.array.card_creation_label_list)
    val cardCreationPlaceholderList = stringArrayResource(R.array.card_creation_placeholder_list)

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val context = LocalContext.current

    val customTextFieldContent = listOf(
        CustomTextFieldContent(
            value = cardCreationUIState.value.bankText,
            label = cardCreationLabelList[0],
            placeholder = cardCreationPlaceholderList[0],
            trailingIcon = R.drawable.ic_round_cancel,
            isError = cardCreationUIState.value.isBankError,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None,
            onValueChange = { viewModel.onCardCreationAction(CardCreationUIAction.OnBankValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onCardCreationAction(CardCreationUIAction.OnBankTrailingIconClickAction()) }
        ),
        CustomTextFieldContent(
            value = cardCreationUIState.value.numberText,
            label = cardCreationLabelList[1],
            placeholder = cardCreationPlaceholderList[1],
            trailingIcon = R.drawable.ic_round_cancel,
            isError = cardCreationUIState.value.isNumberError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            visualTransformation = { VisualTransformationFilter.creditCardFilter(it) },
            onValueChange = { viewModel.onCardCreationAction(CardCreationUIAction.OnNumberValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onCardCreationAction(CardCreationUIAction.OnNumberTrailingIconClickAction()) }
        ),
        CustomTextFieldContent(
            value = cardCreationUIState.value.cvvText,
            label = cardCreationLabelList[2],
            placeholder = cardCreationPlaceholderList[2],
            trailingIcon = R.drawable.ic_round_cancel,
            isError = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None,
            onValueChange = { viewModel.onCardCreationAction(CardCreationUIAction.OnCVVValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onCardCreationAction(CardCreationUIAction.OnCVVTrailingIconClickAction()) }
        ),
        CustomTextFieldContent(
            value = cardCreationUIState.value.validThruText,
            label = cardCreationLabelList[3],
            placeholder = cardCreationPlaceholderList[3],
            trailingIcon = R.drawable.ic_round_cancel,
            isError = cardCreationUIState.value.isValidThruError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            visualTransformation = { VisualTransformationFilter.validThruFilter(it) },
            onValueChange = { viewModel.onCardCreationAction(CardCreationUIAction.OnValidThruValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onCardCreationAction(CardCreationUIAction.OnValidThruTrailingIconClickAction()) }
        ),
        CustomTextFieldContent(
            value = cardCreationUIState.value.fullNameText,
            label = cardCreationLabelList[4],
            placeholder = cardCreationPlaceholderList[4],
            trailingIcon = R.drawable.ic_round_cancel,
            isError = cardCreationUIState.value.isFullNameError,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            visualTransformation = VisualTransformation.None,
            onValueChange = { viewModel.onCardCreationAction(CardCreationUIAction.OnFullNameValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onCardCreationAction(CardCreationUIAction.OnFullNameTrailingIconClickAction()) }
        )
    )

    CardCreationUI(
        scrollBehavior = scrollBehavior,
        customTextFieldContent = customTextFieldContent,
        onNavAction = { viewModel.onCardCreationAction(CardCreationUIAction.OnNavAction(navController)) },
        onSaveAction = {
            viewModel.onCardCreationAction(
                CardCreationUIAction.OnSaveAction(
                    navController = navController,
                    context = context,
                    toastText = toastText
                )
            )
        }
    )
}







