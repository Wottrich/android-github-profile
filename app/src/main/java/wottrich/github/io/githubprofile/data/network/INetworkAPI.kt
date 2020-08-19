package wottrich.github.io.githubprofile.data.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import wottrich.github.io.githubprofile.BuildConfig
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
    fun loadProfile(@Path("profileLogin") profileLogin: String) : Call<Profile>

    @GET("users/{profileLogin}/repos")
    fun loadRepositories(@Path("profileLogin") profileLogin: String) : Call<List<Repository>>

}