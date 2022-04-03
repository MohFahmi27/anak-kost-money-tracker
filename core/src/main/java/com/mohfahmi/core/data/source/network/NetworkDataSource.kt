package com.mohfahmi.core.data.source.network

import com.mohfahmi.core.data.source.network.api.ApiService
import com.mohfahmi.core.data.source.network.response.ApiResponse
import com.mohfahmi.core.domain.models.ArticleDomain
import com.mohfahmi.core.domain.utils.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class NetworkDataSource(private val apiService: ApiService) : INetworkDataSource {
    override fun getArticleData(): Flow<ApiResponse<List<ArticleDomain>>> = flow {
        val response = apiService.getArticlesFinance()
        try {
            if (!response.data.isNullOrEmpty()) {
                emit(ApiResponse.Success(response.mapToDomain()))
            } else {
                emit(ApiResponse.Empty(response.messages!!))
            }
        } catch (e: HttpException) {
            emit(ApiResponse.Error("${response.code}: ${response.messages}"))
        } catch (e: IOException) {
            emit(ApiResponse.Error("${response.code}: ${response.messages}"))
        }
    }
}