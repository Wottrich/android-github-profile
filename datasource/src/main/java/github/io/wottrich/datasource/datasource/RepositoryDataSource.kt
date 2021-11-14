package github.io.wottrich.datasource.datasource

import github.io.wottrich.datasource.models.Repository
import github.io.wottrich.datasource.models.RepositoryContent
import kotlinx.coroutines.flow.Flow
import wottrich.github.io.resource.Resource

interface RepositoryDataSource {
    fun loadRepositories(profileLogin: String): Flow<Resource<List<Repository>>>
    fun loadRepository(profileLogin: String, repositoryName: String): Flow<Resource<Repository>>
    fun loadRepositoryContents(
        profileLogin: String,
        repositoryName: String
    ): Flow<Resource<List<RepositoryContent>>>
    fun loadRepositoryContentsPath(
        profileLogin: String,
        repositoryName: String,
        path: String
    ) : Flow<Resource<List<RepositoryContent>>>
}