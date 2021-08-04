package com.example.myopinion.repository

import com.example.myopinion.models.Opinion
import kotlinx.coroutines.flow.Flow


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