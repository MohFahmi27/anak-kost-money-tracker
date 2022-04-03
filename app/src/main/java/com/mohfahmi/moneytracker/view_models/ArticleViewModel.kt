package com.mohfahmi.moneytracker.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohfahmi.core.domain.models.ArticleDomain
import com.mohfahmi.core.domain.use_case.ArticleUseCase
import com.mohfahmi.core.domain.utils.ResponseUiState
import kotlinx.coroutines.launch

class ArticleViewModel(private val articleUseCase: ArticleUseCase) : ViewModel() {
    private val _getPercentageIncome = MutableLiveData<Float>()
    val getPercentageIncome: LiveData<Float> = _getPercentageIncome

    private fun getPercentageIncome() {
        viewModelScope.launch {
            articleUseCase.getPercentageIncome().collect {
                _getPercentageIncome.value = it
            }
        }
    }

    private val _getPercentageExpense = MutableLiveData<Float>()
    val getPercentageExpense: LiveData<Float> = _getPercentageExpense

    private fun getPercentageExpense() {
        viewModelScope.launch {
            articleUseCase.getPercentageExpense().collect {
                _getPercentageExpense.value = it
            }
        }
    }

    private val _articles = MutableLiveData<ResponseUiState<List<ArticleDomain>>>()
    val articles: LiveData<ResponseUiState<List<ArticleDomain>>> = _articles
    private fun getArticlesData() {
        viewModelScope.launch {
            articleUseCase.getArticlesData().collect {
                _articles.value = it
            }
        }
    }

    init {
        getPercentageIncome()
        getPercentageExpense()
        getArticlesData()
    }
}