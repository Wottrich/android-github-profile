package wottrich.github.io.githubprofile.data.resource

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 14/08/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

data class Resource <out T> (val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success (data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }
        fun <T> error (message: String?, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }
        fun <T> loading (data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}