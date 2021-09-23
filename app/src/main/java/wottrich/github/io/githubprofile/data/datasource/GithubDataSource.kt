package wottrich.github.io.githubprofile.data.datasource

import kotlinx.coroutines.flow.Flow
import wottrich.github.io.githubprofile.data.api.INetworkAPI
import wottrich.github.io.githubprofile.data.resource.NetworkBoundResource
import wottrich.github.io.githubprofile.data.resource.Resource
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

interface GithubDataSourceInterface {
    fun loadProfile (profileLogin: String) : Flow<Resource<Profile>>
    fun loadRepositories (profileLogin: String) : Flow<Resource<List<Repository>>>
}

class GithubDataSource (
    private val api: INetworkAPI
) : GithubDataSourceInterface {

    override fun loadProfile (profileLogin: String) : Flow<Resource<Profile>> {
        return NetworkBoundResource(call = { api.loadProfile(profileLogin) }).build()
    }

    override fun loadRepositories (profileLogin: String) : Flow<Resource<List<Repository>>> {
        return NetworkBoundResource(call = { api.loadRepositories(profileLogin) }).build()
    }
}