package github.io.wottrich.datasource.datasource

import github.io.wottrich.datasource.api.ProfileEndpoint
import github.io.wottrich.datasource.models.Profile
import github.io.wottrich.datasource.resource.NetworkBoundResource
import kotlinx.coroutines.flow.Flow
import wottrich.github.io.resource.Resource

class ProfileDataSourceImpl(
    private val endpoint: ProfileEndpoint
) : ProfileDataSource {
    override fun loadProfile(profileLogin: String): Flow<Resource<Profile>> {
        return NetworkBoundResource(call = { endpoint.loadProfile(profileLogin) }).build()
    }
}