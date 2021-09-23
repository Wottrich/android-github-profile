package wottrich.github.io.githubprofile.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import wottrich.github.io.githubprofile.data.resource.Resource
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
 
interface INetworkAPI {

    @GET("users/{profileLogin}")
    suspend fun loadProfile(@Path("profileLogin") profileLogin: String) : Resource<Profile>

    @GET("users/{profileLogin}/repos")
    suspend fun loadRepositories(@Path("profileLogin") profileLogin: String) : Resource<List<Repository>>

}