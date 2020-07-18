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

    }

}