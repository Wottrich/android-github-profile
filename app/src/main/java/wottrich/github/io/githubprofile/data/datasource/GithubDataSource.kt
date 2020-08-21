package wottrich.github.io.githubprofile.data.datasource

import androidx.lifecycle.LiveData
import retrofit2.*
import wottrich.github.io.githubprofile.data.network.INetworkAPI
import wottrich.github.io.githubprofile.data.network.Network
import wottrich.github.io.githubprofile.data.resource.NetworkBoundResource
import wottrich.github.io.githubprofile.data.resource.Resource
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
    private val api: INetworkAPI = Network.api
) {

    suspend fun loadProfile (profileLogin: String) : LiveData<Resource<Profile>> {
        return NetworkBoundResource<Profile, Profile>(
            processResponse = { it },
            createCallAsync = api.loadProfile(profileLogin)
        ).build().asLiveData()
    }

    suspend fun loadRepositories (profileLogin: String) : LiveData<Resource<List<Repository>>> {
        return NetworkBoundResource<List<Repository>, List<Repository>>(
            processResponse = { it },
            createCallAsync = api.loadRepositories(profileLogin)
        ).build().asLiveData()
    }

}