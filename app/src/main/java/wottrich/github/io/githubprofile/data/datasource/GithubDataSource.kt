package wottrich.github.io.githubprofile.data.datasource

import kotlinx.coroutines.Deferred
import wottrich.github.io.githubprofile.data.network.*
import wottrich.github.io.githubprofile.data.wrapper.*
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

class GithubDataSource (
    private val api: INetworkAPI = INetworkAPI.api
) {

    suspend fun loadProfile (profileLogin: String) : Resource<Profile>? {
        return object : NetworkBoundResource<Profile, Profile>() {
            override fun processResponse(response: Profile): Profile {
                return response
            }

            override suspend fun createCallAsync(): Deferred<ApiResponse<Profile>> {
                return api.loadProfileAsync(profileLogin)
            }

        }.getResult()
    }

    suspend fun loadRepositories (profileLogin: String) : Resource<List<Repository>> {
        return object : NetworkBoundResource<List<Repository>, List<Repository>>() {
            override fun processResponse(response: List<Repository>): List<Repository> {
                return response
            }

            override suspend fun createCallAsync(): Deferred<ApiResponse<List<Repository>>> {
                return api.loadRepositoriesAsync(profileLogin)
            }

        }.getResult()
    }



}