package github.io.wottrich.datasource.network

import github.io.wottrich.datasource.BuildConfig
import github.io.wottrich.datasource.adapters.RetrofitCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

object Network {

    fun buildRetrofit(
        baseUrl: String = BuildConfig.BASE_URL,
        client: OkHttpClient = defaultHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RetrofitCallAdapterFactory())
        .client(client)
        .build()

    private fun OkHttpClient.Builder.addDefaultLoggingInterceptor(): OkHttpClient.Builder {
        return addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
    }

    private val defaultHttpClient: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                builder.addDefaultLoggingInterceptor()
            }
            return builder.build()
        }

}