package github.io.wottrich.datasource.resource

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import wottrich.github.io.resource.Resource

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

private fun ResponseBody?.getErrorMessage(errorKey: String): String? {
    this?.charStream()?.readText()?.takeIf { it.contains(errorKey) }?.let {
        return JSONObject(it)[errorKey] as? String
    }
    return null
}