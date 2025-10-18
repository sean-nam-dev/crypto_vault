package com.sean.cryptovault.f_base.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sean.cryptovault.R
import com.sean.cryptovault.f_base.common.CustomTextFieldContent
import com.sean.cryptovault.core.common.getDp
import com.sean.cryptovault.core.component.CustomTextField
import com.sean.cryptovault.core.component.TopAppBarFrame

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCreationUI(
    scrollBehavior: TopAppBarScrollBehavior,
    customTextFieldContent: List<CustomTextFieldContent>,
    onNavAction: () -> Unit,
    onSaveAction: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarFrame(
                title = stringResource(R.string.new_card),
                navIconId = R.drawable.ic_round_arrow_back,
                onNavAction = onNavAction,
                scrollBehavior = scrollBehavior,
                R.drawable.ic_round_check to onSaveAction
            )
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(
                        topStart = getDp(R.dimen.vault_creation_ui_screen_lazy_column_padding),
                        topEnd = getDp(R.dimen.vault_creation_ui_screen_lazy_column_padding)
                    )
                )
                .padding(getDp(R.dimen.vault_creation_ui_screen_lazy_column_padding)),
            verticalArrangement = Arrangement.spacedBy(getDp(R.dimen.vault_creation_ui_screen_lazy_column_padding))
        ) {
            items(customTextFieldContent) {content ->
                CustomTextField(
                    value = content.value,
                    label = content.label,
                    placeholder = content.placeholder,
                    trailingIcon = content.trailingIcon,
                    isError = content.isError,
                    keyboardOptions = content.keyboardOptions,
                    visualTransformation = content.visualTransformation,
                    onValueChange = content.onValueChange,
                    onTrailingIconClickAction = content.onTrailingIconClickAction
                )
            }
        }
    }
}