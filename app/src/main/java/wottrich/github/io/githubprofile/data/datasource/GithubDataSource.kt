package wottrich.github.io.githubprofile.data.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import wottrich.github.io.githubprofile.data.network.*
import wottrich.github.io.githubprofile.data.wrapper.*
import wottrich.github.io.githubprofile.model.Profile
import wottrich.github.io.githubprofile.model.Repository
import kotlin.coroutines.coroutineContext

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

interface GithubDataSourceInterface {
    suspend fun loadProfile (profileLogin: String) : Flow<Resource<Profile>>
    suspend fun loadRepositories (profileLogin: String) : Flow<Resource<List<Repository>>>
}

class GithubDataSource (
    private val api: INetworkAPI = INetworkAPI.api
) : GithubDataSourceInterface {

    override suspend fun loadProfile (profileLogin: String) : Flow<Resource<Profile>> {
        return flow {
            NetworkBoundResource(
                collector = this,
                processResponse = { it },
                call = { api.loadProfileAsync(profileLogin) }
            ).build()
        }
    }

    override suspend fun loadRepositories (profileLogin: String) : Flow<Resource<List<Repository>>> {
        return flow {
            NetworkBoundResource(
                collector = this,
                processResponse = { it },
                call = { api.loadRepositoriesAsync(profileLogin) }
            ).build()
        }
    }
}