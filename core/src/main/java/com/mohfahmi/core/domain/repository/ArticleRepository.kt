package com.mohfahmi.core.domain.repository

import com.mohfahmi.core.data.source.network.response.ApiResponse
import com.mohfahmi.core.domain.models.ArticleDomain
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getArticleData(): Flow<ApiResponse<List<ArticleDomain>>>
}