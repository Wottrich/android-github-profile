package wottrich.github.io.githubprofile.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

    private fun OkHttpClient.Builder.addLoggingInterceptor () : OkHttpClient.Builder {
        return addInterceptor (
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
    }

    val clientHttp: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                builder.addLoggingInterceptor()
            }
            return builder.build()
        }

}