package github.io.wottrich.datasource.api

import github.io.wottrich.datasource.models.Profile
import github.io.wottrich.datasource.models.Repository
import github.io.wottrich.datasource.resource.Resource
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

interface INetworkAPI {

    @GET("users/{profileLogin}")
    suspend fun loadProfile(@Path("profileLogin") profileLogin: String): Resource<Profile>

    @GET("users/{profileLogin}/repos")
    suspend fun loadRepositories(@Path("profileLogin") profileLogin: String): Resource<List<Repository>>

}