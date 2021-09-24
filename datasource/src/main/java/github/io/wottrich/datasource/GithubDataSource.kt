package github.io.wottrich.datasource

import github.io.wottrich.datasource.api.INetworkAPI
import github.io.wottrich.datasource.models.Profile
import github.io.wottrich.datasource.models.Repository
import github.io.wottrich.datasource.resource.NetworkBoundResource
import github.io.wottrich.datasource.resource.Resource
import kotlinx.coroutines.flow.Flow

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