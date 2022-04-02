package com.mohfahmi.moneytracker.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mohfahmi.core.domain.use_case.ProfileUseCase

class ProfileViewModel(private val profileUseCase: ProfileUseCase) : ViewModel() {
    val getUserName: LiveData<String> = profileUseCase.readNameUser().asLiveData()
    val getNotificationState: LiveData<Boolean> =
        profileUseCase.readNotificationState().asLiveData()

    fun setNotificationState(state: Boolean) {
        profileUseCase.saveNotification(state, viewModelScope)
    }
}