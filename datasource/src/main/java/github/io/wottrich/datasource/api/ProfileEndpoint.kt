package github.io.wottrich.datasource.api

import github.io.wottrich.datasource.models.Profile
import retrofit2.http.GET
import retrofit2.http.Path
import wottrich.github.io.resource.Resource

interface ProfileEndpoint {

    @GET("users/{owner}")
    suspend fun loadProfile(@Path("owner") profileLogin: String): Resource<Profile>

}