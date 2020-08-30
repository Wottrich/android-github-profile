package wottrich.github.io.githubprofile.data.datasource

import kotlinx.coroutines.flow.Flow
import wottrich.github.io.githubprofile.data.api.INetworkAPI
import wottrich.github.io.githubprofile.data.resource.*
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
        return NetworkBoundResource(
            processResponse = { it },
            call = { api.loadProfile(profileLogin) }
        ).build()


//        flow {
//            emit(Resource.loading())
//            delay(2000)
//            val profile = Profile(
//                "Wottrich",
//                "Wottrich",
//                "test",
//                "https://avatars0.githubusercontent.com/u/24254062?v=4",
//                10,
//                10
//            )
//            emit(Resource.success(profile))
//        }


//        NetworkBoundResource<Profile, Profile>(
//            processResponse = { it },
//            call = { api.loadProfileAsync(profileLogin) }
//        ).build()
    }

    override fun loadRepositories (profileLogin: String) : Flow<Resource<List<Repository>>> {
        return NetworkBoundResource(
            processResponse = { it },
            call = { api.loadRepositories(profileLogin) }
        ).build()
    }
}