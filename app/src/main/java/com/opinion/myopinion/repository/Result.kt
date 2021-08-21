package com.opinion.myopinion.repository


class Result<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Result<T> {
            return Result(Status.SUCCESS, data, null)
        }

        fun <T> error(data: T?, message: String?): Result<T> {
            return Result(Status.SUCCESS, data, message)
        }


    }
}