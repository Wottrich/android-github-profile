package wottrich.github.io.githubprofile.data.datasource

import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import wottrich.github.io.githubprofile.data.network.INetworkAPI
import wottrich.github.io.githubprofile.model.Profile
import wottrich.github.io.githubprofile.model.Repository

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

    fun loadProfile (profileLogin: String, onSuccess: OnSuccess<Profile>, onFailure: OnFailure) {
        api.loadProfile(profileLogin).enqueue(object : Callback<Profile> {
            override fun onFailure(call: Call<Profile>, t: Throwable) {
                onFailure(t.message)
            }

            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                val result = response.body()
                val statusCode = response.code()

                if (statusCode in 200..299) {
                    if (result != null) {
                        onSuccess(true, response.message(), result)
                    } else {
                        onSuccess(false, response.message(), null)
                    }
                } else {
                    onFailure(response.message())
                }
            }

        })

    }

    fun loadRepositories (profileLogin: String, onSuccess: OnSuccess<List<Repository>>, onFailure: OnFailure) {

        api.loadRepositories(profileLogin).enqueue(object : Callback<List<Repository>> {
            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                onFailure(t.message)
            }

            override fun onResponse(
                call: Call<List<Repository>>,
                response: Response<List<Repository>>
            ) {
                val result = response.body()
                val statusCode = response.code()

                if (statusCode in 200..299) {
                    if (result != null) {
                        onSuccess(true, response.message(), result)
                    } else {
                        onSuccess(false, response.message(), null)
                    }
                } else {
                    onFailure(response.message())
                }

            }

        })

    }

}