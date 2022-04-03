package com.mohfahmi.core.domain.use_case.interactors

import com.mohfahmi.core.data.source.network.response.ApiResponse
import com.mohfahmi.core.domain.models.ArticleDomain
import com.mohfahmi.core.domain.repository.ActivityRepository
import com.mohfahmi.core.domain.repository.ArticleRepository
import com.mohfahmi.core.domain.use_case.ArticleUseCase
import com.mohfahmi.core.domain.utils.ResponseUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class ArticleInteractor(
    private val activityRepository: ActivityRepository,
    private val articleRepository: ArticleRepository
) : ArticleUseCase {

    private val _getSumData: Flow<Float> = flow {
        activityRepository.getSumAllData().collect {
            emit(it.toFloat())
        }
    }.flowOn(Dispatchers.IO)

    override fun getPercentageIncome(): Flow<Float> = flow {
        activityRepository.getSumIncomeData().filterNotNull().collect { income ->
            val getSumData = _getSumData.first()
            emit((income.toFloat() / getSumData) * 100f)
        }
    }.flowOn(Dispatchers.IO)

    override fun getPercentageExpense(): Flow<Float> = flow {
        activityRepository.getSumExpensesData().filterNotNull().collect { expense ->
            val getSumData = _getSumData.first()
            emit((expense.toFloat() / getSumData) * 100f)
        }
    }.flowOn(Dispatchers.IO)

    override fun getArticlesData(): Flow<ResponseUiState<List<ArticleDomain>>> = flow {
        articleRepository.getArticleData().onStart {
            emit(ResponseUiState.loading(null))
        }.collect { response ->
            when (response) {
                is ApiResponse.Success -> emit(ResponseUiState.success(response.data))
                is ApiResponse.Error -> emit(ResponseUiState.error(null, response.errorMessage))
                is ApiResponse.Empty -> emit(ResponseUiState.error(null, response.errorMessage))
            }
        }
    }.flowOn(Dispatchers.IO)
}