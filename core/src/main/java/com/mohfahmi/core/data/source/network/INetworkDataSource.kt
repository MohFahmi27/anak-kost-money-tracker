package com.mohfahmi.core.data.source.network

import com.mohfahmi.core.data.source.network.response.ApiResponse
import com.mohfahmi.core.domain.models.ArticleDomain
import kotlinx.coroutines.flow.Flow

interface INetworkDataSource {
    fun getArticleData(): Flow<ApiResponse<List<ArticleDomain>>>
}