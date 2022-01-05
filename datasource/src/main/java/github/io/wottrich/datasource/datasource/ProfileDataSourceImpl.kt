package github.io.wottrich.datasource.datasource

import github.io.wottrich.datasource.api.ProfileEndpoint
import github.io.wottrich.datasource.database.ProfileDao
import github.io.wottrich.datasource.models.Profile
import github.io.wottrich.datasource.resource.NetworkBoundResource
import kotlinx.coroutines.flow.Flow
import wottrich.github.io.resource.Resource

class ProfileDataSourceImpl(
    private val profileDao: ProfileDao,
    private val endpoint: ProfileEndpoint
) : ProfileDataSource {
    override fun loadProfile(profileLogin: String): Flow<Resource<Profile>> {
        return NetworkBoundResource(
            getFromDatabase = { profileDao.getAllProfilesSavedByQuery(profileLogin).firstOrNull() },
            saveCallResults = { profile -> profileDao.insert(profile) },
            call = { endpoint.loadProfile(profileLogin) }
        ).build()
    }
}