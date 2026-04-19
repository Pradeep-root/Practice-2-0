package com.pradeep.practice_1.core.common

sealed class AppResult<out T> {

    data class Success<T>(val data: T): AppResult<T>()

    data class Error(val exception: AppException): AppResult<Nothing>()
}