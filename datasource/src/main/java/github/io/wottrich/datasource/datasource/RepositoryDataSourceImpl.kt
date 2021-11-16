package github.io.wottrich.datasource.datasource

import github.io.wottrich.datasource.api.RepositoryEndpoint
import github.io.wottrich.datasource.models.Repository
import github.io.wottrich.datasource.models.RepositoryContent
import github.io.wottrich.datasource.resource.NetworkBoundResource
import kotlinx.coroutines.flow.Flow
import wottrich.github.io.resource.Resource

class RepositoryDataSourceImpl(
    private val repositoryEndpoint: RepositoryEndpoint
) : RepositoryDataSource {
    override fun loadRepositories(profileLogin: String): Flow<Resource<List<Repository>>> {
        return NetworkBoundResource(
            call = {
                repositoryEndpoint.loadRepositories(profileLogin)
            }
        ).build()
    }

    override fun loadRepository(
        profileLogin: String,
        repositoryName: String
    ): Flow<Resource<Repository>> {
        return NetworkBoundResource(
            call = {
                repositoryEndpoint.loadRepositoryDetail(profileLogin, repositoryName)
            }
        ).build()
    }

    override fun loadRepositoryContents(
        profileLogin: String,
        repositoryName: String
    ): Flow<Resource<List<RepositoryContent>>> {
        return NetworkBoundResource(
            call = {
                repositoryEndpoint.loadRepositoryContents(profileLogin, repositoryName)
            }
        ).build()
    }

    override fun loadRepositoryContentsPath(
        profileLogin: String,
        repositoryName: String,
        path: String
    ): Flow<Resource<List<RepositoryContent>>> {
        return NetworkBoundResource(
            call = {
                repositoryEndpoint.loadRepositoryContentsPath(profileLogin, repositoryName, path)
            }
        ).build()
    }
}