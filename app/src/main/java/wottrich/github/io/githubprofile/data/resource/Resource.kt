package wottrich.github.io.githubprofile.data.resource

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 14/08/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

sealed class Resource<out T>(open val data: T?) {

    data class Success<T>(override val data: T?) : Resource<T>(data)
    data class Failure<T>(val throwable: Throwable, override val data: T?) : Resource<T>(data)
    data class Loading<T>(override val data: T?) : Resource<T>(data)

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Success(data)
        }

        fun <T> failure(throwable: Throwable, data: T? = null): Resource<T> {
            return Failure(throwable, data)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Loading(data)
        }
    }
}