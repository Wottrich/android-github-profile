package wottrich.github.io.githubprofile.data.network

import kotlinx.coroutines.Deferred
import retrofit2.CallAdapter
import retrofit2.Retrofit
import wottrich.github.io.githubprofile.data.resource.ApiResponse
import java.lang.IllegalArgumentException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 18/08/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */
 
 class RetrofitCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        if (getRawType(returnType) == Deferred::class.java) {
            val apiResponseType = getParameterUpperBound(0, returnType as ParameterizedType)
            val apiResponseRaw = getRawType(apiResponseType)

            if (apiResponseRaw != ApiResponse::class.java) {
                throw IllegalArgumentException("Deferred raw type must be a ApiResponse")
            }

            if (apiResponseType !is ParameterizedType) {
                throw IllegalArgumentException("Deferred raw type must be a ApiResponse")
            }

            val bodyType = getParameterUpperBound(0, apiResponseType)
            return DeferredCallAdapter<Any>(bodyType)
        }

        return null
    }

}