package com.mohfahmi.core.data.repository

import com.mohfahmi.core.data.source.network.INetworkDataSource
import com.mohfahmi.core.data.source.network.response.ApiResponse
import com.mohfahmi.core.domain.models.ArticleDomain
import com.mohfahmi.core.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow

class ArticleRepositoryImpl(private val networkDataSource: INetworkDataSource) : ArticleRepository {
    override fun getArticleData(): Flow<ApiResponse<List<ArticleDomain>>> = networkDataSource.getArticleData()
}