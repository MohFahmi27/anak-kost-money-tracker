package com.mohfahmi.core.data.source.network.api

import com.mohfahmi.core.data.source.network.response.ArticleResponse
import retrofit2.http.GET

interface ApiService {
    @GET("v1/suara/keuangan")
    suspend fun getArticlesFinance(): ArticleResponse
}