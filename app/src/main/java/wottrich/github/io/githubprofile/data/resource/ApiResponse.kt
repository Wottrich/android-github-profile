package wottrich.github.io.githubprofile.data.resource

import retrofit2.Response
import wottrich.github.io.githubprofile.archive.getErrorMessage

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 14/08/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

object ApiGeneralKeys {
    const val errorKey = "message"
}

sealed class ApiResponse<T> {
    companion object {

        fun <T> create(throwable: Throwable): Resource<T> {
            return Resource.failure(throwable)
        }

        fun <T> create(response: Response<T>): Resource<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null && response.code() != 204) {
                    Resource.success(body)
                } else {
                    Resource.failure(EmptyDataException())
                }
            } else {
                Resource.failure(
                    Throwable(
                        response.errorBody()?.getErrorMessage(ApiGeneralKeys.errorKey)
                    )
                )
            }
        }
    }
}