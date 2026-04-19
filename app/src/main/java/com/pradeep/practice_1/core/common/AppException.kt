package com.pradeep.practice_1.core.common

sealed class AppException (
    msg: String? = null,
    cause: Throwable? = null
): Exception(msg, cause) {

    class Network(msg: String? = null, cause: Throwable? = null): AppException(msg, cause)

    class Sever(val code: Int, msg: String? = null): AppException(msg)

    class Unknown(cause: Throwable? = null) : AppException(cause?.message, cause)
}


fun AppException.toUserMessage(): String = when (this) {
     is AppException.Sever -> "Server respond with an error $code"
     is AppException.Network -> message?.takeIf { it.isNotBlank() } ?: "Check you connection and try again."
     is AppException.Unknown -> message?.takeIf { it.isNotBlank() } ?: "Something went wrong."
}