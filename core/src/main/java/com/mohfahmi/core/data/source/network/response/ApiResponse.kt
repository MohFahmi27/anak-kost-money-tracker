package com.mohfahmi.core.data.source.network.response

@Suppress("UNUSED_PARAMETER")
// sealed class to help organize api response whether success, error or empty
sealed class ApiResponse<T>(
    data: T? = null,
    errorMessage: String?
) {
    data class Success<T>(val data: T) : ApiResponse<T>(data, null)

    data class Error<T>(
        val errorMessage: String
    ) : ApiResponse<T>(null, errorMessage)

    data class Empty<T>(val errorMessage: String) : ApiResponse<T>(null, errorMessage)
}