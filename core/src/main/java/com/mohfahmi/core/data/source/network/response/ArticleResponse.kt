package com.mohfahmi.core.data.source.network.response

import com.squareup.moshi.Json

data class ArticleResponse(

	@Json(name="total")
	val total: Int? = null,

	@Json(name="code")
	val code: Int? = null,

	@Json(name="data")
	val data: List<DataItem?>? = null,

	@Json(name="messages")
	val messages: String? = null,

	@Json(name="status")
	val status: String? = null
)

data class DataItem(

	@Json(name="image")
	val image: Image? = null,

	@Json(name="link")
	val link: String? = null,

	@Json(name="isoDate")
	val isoDate: String? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="contentSnippet")
	val contentSnippet: String? = null
)

data class Image(

	@Json(name="small")
	val small: String? = null,

	@Json(name="large")
	val large: String? = null
)
