package com.mm.domain.model


/**
 * Generic class for holding success response, error response and loading status
 */
data class OutputResource<out T>(
    val status: Status,
    val data: T?,
    val error: ApiError?,
    val message: String?
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): OutputResource<T> {
            return OutputResource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(message: String, error: ApiError?): OutputResource<T> {
            return OutputResource(Status.ERROR, null, error, message)
        }

        fun <T> loading(data: T? = null): OutputResource<T> {
            return OutputResource(Status.LOADING, data, null, null)
        }
    }

    override fun toString(): String {
        return "Result(status=$status, data=$data, error=$error, message=$message)"
    }
}
