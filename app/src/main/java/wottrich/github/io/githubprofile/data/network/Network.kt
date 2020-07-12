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

    private val savedHeaders: HashMap<String, String> = hashMapOf()

    private fun OkHttpClient.Builder.addLoggingInterceptor () : OkHttpClient.Builder {
        return addInterceptor (
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
    }

    private fun OkHttpClient.Builder.receivedHeaderInterceptor () : OkHttpClient.Builder {
        return addInterceptor {
            it.proceed(it.request()).apply {
                savedHeaders.clear()
                headers.forEach { header ->
                    savedHeaders[header.first] = header.second
                }
            }
        }
    }

    private fun OkHttpClient.Builder.addHeaderInterceptor () : OkHttpClient.Builder {
        return addInterceptor {
            val original = it.request()
            val builder = original.newBuilder()

            //adding headers
            for ((key, value) in savedHeaders) {
                builder.addHeader(key, value)
            }

            val request = builder.build()
            it.proceed(request)
        }
    }

    val clientHttp: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                builder.addLoggingInterceptor()
            }
            builder.receivedHeaderInterceptor()
            builder.addHeaderInterceptor()
            return builder.build()
        }

}