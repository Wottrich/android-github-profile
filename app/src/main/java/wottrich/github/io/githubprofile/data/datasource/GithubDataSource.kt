package wottrich.github.io.githubprofile.data.datasource

import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.*
import wottrich.github.io.githubprofile.data.network.INetworkAPI
import wottrich.github.io.githubprofile.model.Profile
import wottrich.github.io.githubprofile.model.Repository
import java.lang.RuntimeException

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

typealias OnSuccess<T> = (isSuccess: Boolean, message: String?, result: T?) -> Unit
typealias OnFailure = (message: String?) -> Unit

class GithubDataSource (
    private val api: INetworkAPI = INetworkAPI.api
) {

    suspend fun loadProfile (profileLogin: String) : Profile {

        val response = api.loadProfile(profileLogin).awaitResponse()

        val result = response.body()
        val statusCode = response.code()

        return if (result != null && statusCode in 200..299) {
            result
        } else {
            throw RuntimeException(response.message())
        }

//        api.loadProfile(profileLogin).enqueue(object : Callback<Profile> {
//            override fun onFailure(call: Call<Profile>, t: Throwable) {
//                onFailure(t.message)
//            }
//
//            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
//                val result = response.body()
//                val statusCode = response.code()
//
//                if (statusCode in 200..299) {
//                    if (result != null) {
//                        onSuccess(true, response.message(), result)
//                    } else {
//                        onSuccess(false, response.message(), null)
//                    }
//                } else {
//                    onFailure(response.message())
//                }
//            }
//
//        })

    }

    @Throws(Exception::class)
    suspend fun loadRepositories (profileLogin: String) : List<Repository> {

        val response = api.loadRepositories(profileLogin).awaitResponse()

        val result = response.body()
        val statusCode = response.code()

        return if (result != null && statusCode in 200..299) {
            result
        } else {
            throw RuntimeException(response.message())
        }

//        repositories.enqueue(object : Callback<List<Repository>> {
//            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
//                onFailure(t.message)
//            }
//
//            override fun onResponse(
//                call: Call<List<Repository>>,
//                response: Response<List<Repository>>
//            ) {
//                val result = response.body()
//                val statusCode = response.code()
//
//                if (statusCode in 200..299) {
//                    if (result != null) {
//                        onSuccess(true, response.message(), result)
//                    } else {
//                        onSuccess(false, response.message(), null)
//                    }
//                } else {
//                    onFailure(response.message())
//                }
//
//            }
//
//        })

    }

}