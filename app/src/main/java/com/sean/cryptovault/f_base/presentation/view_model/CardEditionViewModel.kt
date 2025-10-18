package com.sean.cryptovault.f_base.presentation.view_model

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sean.cryptovault.f_base.data.room.card.Card
import com.sean.cryptovault.f_base.data.room.card.CardDao
import com.sean.cryptovault.f_base.presentation.ui_action.CardCreationUIAction
import com.sean.cryptovault.f_base.presentation.ui_state.CardCreationUIState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CardEditionViewModel @AssistedInject constructor(
    @Assisted("id") val id: Long,
    @Assisted("bank") bank: String,
    @Assisted("number") number: String,
    @Assisted("validThru") validThru: String,
    @Assisted("cvv") cvv: String,
    @Assisted("fullName") fullName: String,
    @Assisted("isFavorite") val isFavorite: Boolean,
    private val cardDao: CardDao
): ViewModel() {
    private val _state = MutableStateFlow(
        CardCreationUIState(
            bankText = bank,
            numberText = number,
            validThruText = validThru,
            cvvText = cvv,
            fullNameText = fullName
        )
    )
    val state = _state.asStateFlow()

    fun onCardEditionAction(action: CardCreationUIAction) {
        when(action) {
            is CardCreationUIAction.OnNavAction -> {
                action.navController.popBackStack()
            }
            is CardCreationUIAction.OnSaveAction -> {
                _state.update {
                    it.copy(
                        isBankError = it.bankText.isBlank(),
                        isNumberError = it.numberText.isBlank() || it.numberText.length < 16,
                        isValidThruError = it.validThruText.isBlank() || it.validThruText.length < 4,
                        isFullNameError = it.fullNameText.isBlank()
                    )
                }
                if (!_state.value.isBankError &&
                    !_state.value.isNumberError &&
                    !_state.value.isValidThruError &&
                    !_state.value.isFullNameError) {
                    viewModelScope.launch {
                        cardDao.update(
                            Card(
                                id = id,
                                bank = _state.value.bankText,
                                number = _state.value.numberText,
                                validThru = _state.value.validThruText,
                                cvv = _state.value.cvvText,
                                fullName = _state.value.fullNameText,
                                isFavorite = isFavorite
                            )
                        )
                    }
                    action.navController.popBackStack()
                } else {
                    Toast.makeText(action.context, action.toastText, Toast.LENGTH_SHORT).show()
                }
            }
            is CardCreationUIAction.OnBankValueChange -> {
                _state.update { it.copy(bankText = action.text) }
            }
            is CardCreationUIAction.OnNumberValueChange -> {
                _state.update { it.copy(numberText = action.text.take(16)) }
            }
            is CardCreationUIAction.OnCVVValueChange -> {
                _state.update { it.copy(cvvText = action.text.take(3)) }
            }
            is CardCreationUIAction.OnValidThruValueChange -> {
                _state.update { it.copy(validThruText = action.text.take(4)) }
            }
            is CardCreationUIAction.OnFullNameValueChange -> {
                _state.update { it.copy(fullNameText = action.text) }
            }
            is CardCreationUIAction.OnBankTrailingIconClickAction -> {
                _state.update { it.copy(bankText = "") }
            }
            is CardCreationUIAction.OnNumberTrailingIconClickAction -> {
                _state.update { it.copy(numberText = "") }
            }
            is CardCreationUIAction.OnCVVTrailingIconClickAction -> {
                _state.update { it.copy(cvvText = "") }
            }
            is CardCreationUIAction.OnValidThruTrailingIconClickAction -> {
                _state.update { it.copy(validThruText = "") }
            }
            is CardCreationUIAction.OnFullNameTrailingIconClickAction -> {
                _state.update { it.copy(fullNameText = "") }
            }
        }
    }
}

@AssistedFactory
interface CardEditionViewModelFactory {
    fun create(
        @Assisted("id") id: Long,
        @Assisted("bank") bank: String,
        @Assisted("number") number: String,
        @Assisted("validThru") validThru: String,
        @Assisted("cvv") cvv: String,
        @Assisted("fullName") fullName: String,
        @Assisted("isFavorite") isFavorite: Boolean,
    ): CardEditionViewModel
}