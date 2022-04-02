package com.mohfahmi.moneytracker.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohfahmi.core.domain.use_case.EditNameUseCase

class EditNameViewModel(private val editNameUseCase: EditNameUseCase): ViewModel() {
    fun edtName(name: String) {
        editNameUseCase.editUsername(name, viewModelScope)
    }
}
