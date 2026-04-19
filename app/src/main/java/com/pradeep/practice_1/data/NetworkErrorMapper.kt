package com.pradeep.practice_1.data

import com.pradeep.practice_1.core.common.AppException
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class NetworkErrorMapper @Inject constructor() {

    fun map(throwable: Throwable): AppException = when (throwable) {
        is HttpException -> AppException.Sever(
            code = throwable.code(),
            msg = throwable.message()
        )

        is UnknownHostException,
        is SocketTimeoutException,
        is IOException -> AppException.Network(cause = throwable)
        is AppException -> throwable
        else -> AppException.Unknown(cause = throwable)
    }
}