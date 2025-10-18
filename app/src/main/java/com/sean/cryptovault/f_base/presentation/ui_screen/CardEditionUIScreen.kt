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
import com.sean.cryptovault.f_base.presentation.view_model.CardEditionViewModel
import com.sean.cryptovault.f_base.common.CustomTextFieldContent
import com.sean.cryptovault.f_base.common.VisualTransformationFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardEditionUIScreen(
    viewModel: CardEditionViewModel,
    navController: NavController
) {
    val cardEditionUIState = viewModel.state.collectAsState()

    val toastText = stringResource(R.string.fill_required_fields)

    val cardCreationLabelList = stringArrayResource(R.array.card_creation_label_list)
    val cardCreationPlaceholderList = stringArrayResource(R.array.card_creation_placeholder_list)

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val context = LocalContext.current

    val customTextFieldContent = listOf(
        CustomTextFieldContent(
            value = cardEditionUIState.value.bankText,
            label = cardCreationLabelList[0],
            placeholder = cardCreationPlaceholderList[0],
            trailingIcon = R.drawable.ic_round_cancel,
            isError = cardEditionUIState.value.isBankError,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None,
            onValueChange = { viewModel.onCardEditionAction(CardCreationUIAction.OnBankValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onCardEditionAction(CardCreationUIAction.OnBankTrailingIconClickAction()) }
        ),
        CustomTextFieldContent(
            value = cardEditionUIState.value.numberText,
            label = cardCreationLabelList[1],
            placeholder = cardCreationPlaceholderList[1],
            trailingIcon = R.drawable.ic_round_cancel,
            isError = cardEditionUIState.value.isNumberError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            visualTransformation = { VisualTransformationFilter.creditCardFilter(it) },
            onValueChange = { viewModel.onCardEditionAction(CardCreationUIAction.OnNumberValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onCardEditionAction(CardCreationUIAction.OnNumberTrailingIconClickAction()) }
        ),
        CustomTextFieldContent(
            value = cardEditionUIState.value.cvvText,
            label = cardCreationLabelList[2],
            placeholder = cardCreationPlaceholderList[2],
            trailingIcon = R.drawable.ic_round_cancel,
            isError = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None,
            onValueChange = { viewModel.onCardEditionAction(CardCreationUIAction.OnCVVValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onCardEditionAction(CardCreationUIAction.OnCVVTrailingIconClickAction()) }
        ),
        CustomTextFieldContent(
            value = cardEditionUIState.value.validThruText,
            label = cardCreationLabelList[3],
            placeholder = cardCreationPlaceholderList[3],
            trailingIcon = R.drawable.ic_round_cancel,
            isError = cardEditionUIState.value.isValidThruError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            visualTransformation = { VisualTransformationFilter.validThruFilter(it) },
            onValueChange = { viewModel.onCardEditionAction(CardCreationUIAction.OnValidThruValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onCardEditionAction(CardCreationUIAction.OnValidThruTrailingIconClickAction()) }
        ),
        CustomTextFieldContent(
            value = cardEditionUIState.value.fullNameText,
            label = cardCreationLabelList[4],
            placeholder = cardCreationPlaceholderList[4],
            trailingIcon = R.drawable.ic_round_cancel,
            isError = cardEditionUIState.value.isFullNameError,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            visualTransformation = VisualTransformation.None,
            onValueChange = { viewModel.onCardEditionAction(CardCreationUIAction.OnFullNameValueChange(it)) },
            onTrailingIconClickAction = { viewModel.onCardEditionAction(CardCreationUIAction.OnFullNameTrailingIconClickAction()) }
        )
    )

    CardCreationUI(
        scrollBehavior = scrollBehavior,
        customTextFieldContent = customTextFieldContent,
        onNavAction = { viewModel.onCardEditionAction(CardCreationUIAction.OnNavAction(navController)) },
        onSaveAction = {
            viewModel.onCardEditionAction(
                CardCreationUIAction.OnSaveAction(
                    navController = navController,
                    context = context,
                    toastText = toastText
                )
            )
        }
    )
}