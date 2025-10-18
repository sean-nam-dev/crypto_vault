package com.sean.cryptovault.f_base.presentation.view_model

import android.content.ClipData
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.sean.cryptovault.f_base.common.PasswordGenerator
import com.sean.cryptovault.f_base.presentation.ui_action.PasswordGeneratorUIAction
import com.sean.cryptovault.f_base.presentation.ui_state.PasswordGeneratorUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PasswordGeneratorViewModel: ViewModel() {
    private val _state = MutableStateFlow(PasswordGeneratorUIState())
    val state = _state.asStateFlow()

    fun onPasswordGeneratorAction(action: PasswordGeneratorUIAction) {
        when(action) {
            is PasswordGeneratorUIAction.OnSliderValueChange -> {
                _state.update { it.copy(passwordLength = action.value.toInt()) }
            }
            is PasswordGeneratorUIAction.OnIncludeButtonChange -> {
                _state.update {
                    when(action.index) {
                        0 -> it.copy(isDigitIncluded = !it.isDigitIncluded)
                        1 -> it.copy(isSignIncluded = !it.isSignIncluded)
                        else -> it.copy(isUppercaseIncluded = !it.isUppercaseIncluded)
                    }
                }
                _state.update {
                    it.copy(
                        password = PasswordGenerator.getPassword(
                            length = it.passwordLength,
                            isDigitIncluded = it.isDigitIncluded,
                            isSpecialSignIncluded = it.isSignIncluded,
                            isUppercaseLetterIncluded = it.isUppercaseIncluded
                        )
                    )
                }
            }
            is PasswordGeneratorUIAction.OnCopyClickAction -> {
                action.clipboardManager.setPrimaryClip(ClipData.newPlainText(action.label, _state.value.password))
                Toast.makeText(action.context, action.toastText,  Toast.LENGTH_SHORT).show()
            }
            is PasswordGeneratorUIAction.OnChangeClickAction -> {
                _state.update {
                    it.copy(
                        password = PasswordGenerator.getPassword(
                            length = it.passwordLength,
                            isDigitIncluded = it.isDigitIncluded,
                            isSpecialSignIncluded = it.isSignIncluded,
                            isUppercaseLetterIncluded = it.isUppercaseIncluded
                        )
                    )
                }
            }
        }
    }
}