package com.mohfahmi.moneytracker.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mohfahmi.core.domain.models.ActivityDomain
import com.mohfahmi.core.domain.use_case.AddUseCase

class AddDataViewModel(private val addUseCase: AddUseCase) : ViewModel() {
    fun insert(activityDomain: ActivityDomain) {
        addUseCase.insertData(activityDomain, viewModelScope)
    }

    fun update(id: Long, name: String, amount: Long, date: String, isExpense: Boolean) {
        val activityDomain = ActivityDomain(id, name, amount, date, isExpense)
        addUseCase.updateData(activityDomain, viewModelScope)
    }

    fun delete(activityDomain: ActivityDomain) {
        addUseCase.deleteData(activityDomain, viewModelScope)
    }

    fun detailActivity(id: Long): LiveData<ActivityDomain> =
        addUseCase.getDetailData(id).asLiveData()
}