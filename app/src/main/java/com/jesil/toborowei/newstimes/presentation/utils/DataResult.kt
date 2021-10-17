package com.jesil.toborowei.newstimes.presentation.utils

sealed class DataResult<out T>{
    data class Success<T>(val data : T?) : DataResult<T>()
    data class Error(val error: Throwable): DataResult<Nothing>()
    object Loading : DataResult<Nothing>()
}
