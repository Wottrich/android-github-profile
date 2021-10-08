package github.io.wottrich.datasource.datasource

import github.io.wottrich.datasource.models.Profile
import kotlinx.coroutines.flow.Flow
import wottrich.github.io.resource.Resource

interface ProfileDataSource {
    fun loadProfile(profileLogin: String): Flow<Resource<Profile>>
}