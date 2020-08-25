package wottrich.github.io.githubprofile.data.network

import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import wottrich.github.io.githubprofile.BuildConfig
import wottrich.github.io.githubprofile.data.wrapper.ApiResponse
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

    companion object {
        val api: INetworkAPI
            get() = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RetrofitCallAdapterFactory())
                .client(Network.clientHttp)
                .build()
                .create(INetworkAPI::class.java)
    }

    @GET("users/{profileLogin}")
    suspend fun loadProfileAsync(@Path("profileLogin") profileLogin: String) : ApiResponse<Profile>

    @GET("users/{profileLogin}/repos")
    suspend fun loadRepositoriesAsync(@Path("profileLogin") profileLogin: String) : ApiResponse<List<Repository>>

}