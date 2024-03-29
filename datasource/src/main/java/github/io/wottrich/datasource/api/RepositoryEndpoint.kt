package github.io.wottrich.datasource.api

import github.io.wottrich.datasource.models.Repository
import github.io.wottrich.datasource.models.RepositoryContent
import retrofit2.http.GET
import retrofit2.http.Path
import wottrich.github.io.resource.Resource

interface RepositoryEndpoint {
    @GET("users/{owner}/repos")
    suspend fun loadRepositories(@Path("owner") profileLogin: String): Resource<List<Repository>>

    @GET("repos/{owner}/{repo}")
    suspend fun loadRepositoryDetail(
        @Path("owner") profileLogin: String,
        @Path("repo") repositoryName: String
    ) : Resource<Repository>

    @GET("repos/{owner}/{repo}/contents")
    suspend fun loadRepositoryContents(
        @Path("owner") profileLogin: String,
        @Path("repo") repositoryName: String
    ) : Resource<List<RepositoryContent>>

    @GET("repos/{owner}/{repo}/contents/{path}")
    suspend fun loadRepositoryContentsPath(
        @Path("owner") profileLogin: String,
        @Path("repo") repositoryName: String,
        @Path("path") path: String
    ) : Resource<List<RepositoryContent>>
}