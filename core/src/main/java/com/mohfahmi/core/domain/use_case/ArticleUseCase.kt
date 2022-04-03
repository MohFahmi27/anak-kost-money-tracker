package com.mohfahmi.core.domain.use_case

import com.mohfahmi.core.domain.models.ArticleDomain
import com.mohfahmi.core.domain.utils.ResponseUiState
import kotlinx.coroutines.flow.Flow

interface ArticleUseCase {
    fun getPercentageIncome(): Flow<Float>
    fun getPercentageExpense(): Flow<Float>
    fun getArticlesData(): Flow<ResponseUiState<List<ArticleDomain>>>
}