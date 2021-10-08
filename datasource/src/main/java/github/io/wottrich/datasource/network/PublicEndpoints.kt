package github.io.wottrich.datasource.network

import github.io.wottrich.datasource.api.ProfileEndpoint
import github.io.wottrich.datasource.api.RepositoryEndpoint

object PublicEndpoints {
    val profileEndpoint: ProfileEndpoint =
        Network.buildRetrofit().create(ProfileEndpoint::class.java)

    val repositoryEndpoint: RepositoryEndpoint =
        Network.buildRetrofit().create(RepositoryEndpoint::class.java)
}