package wottrich.github.io.githubprofile.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import wottrich.github.io.githubprofile.BuildConfig

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */
 
object Network {

    val clientHttp: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                builder.addLoggingInterceptor()
            }
            return builder.build()
        }

    val api: INetworkAPI
        get() = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RetrofitCallAdapterFactory())
            .client(clientHttp)
            .build()
            .create(INetworkAPI::class.java)

    private fun OkHttpClient.Builder.addLoggingInterceptor () : OkHttpClient.Builder {
        return addInterceptor (
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
    }

}