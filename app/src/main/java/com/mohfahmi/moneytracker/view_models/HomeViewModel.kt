package com.mohfahmi.moneytracker.view_models

import androidx.lifecycle.*
import com.mohfahmi.core.domain.models.ActivityDomain
import com.mohfahmi.core.domain.use_case.HomeUseCase
import com.mohfahmi.moneytracker.utils.toCurrencyFormat
import kotlinx.coroutines.launch

class HomeViewModel(private val homeUseCase: HomeUseCase) : ViewModel() {
    val getUserName: LiveData<String> = homeUseCase.readUserName().asLiveData()

    private val _getAllActivityData = MutableLiveData<List<ActivityDomain>>()
    val getAllActivityData: LiveData<List<ActivityDomain>> = _getAllActivityData
    private fun getAllActivityData() {
        viewModelScope.launch {
            homeUseCase.getAllActivityData().collect {
                _getAllActivityData.value = it
            }
        }
    }

    private val _getIncome: LiveData<Long> = homeUseCase.readSumIncome().asLiveData()
    val getIncome: LiveData<String> = Transformations.map(_getIncome) {
        it.toCurrencyFormat()
    }
    private val _getExpenses: LiveData<Long> = homeUseCase.readSumExpenses().asLiveData()
    val getExpenses: LiveData<String> = Transformations.map(_getExpenses) {
        it.toCurrencyFormat()
    }

    init {
        getAllActivityData()
    }
}