package wottrich.github.io.githubprofile.data.network

import kotlinx.coroutines.Deferred
import retrofit2.CallAdapter
import retrofit2.Retrofit
import wottrich.github.io.githubprofile.data.wrapper.ApiResponse
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 14/08/20
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
            val enclosingType =  getParameterUpperBound(0, returnType as ParameterizedType)
            val rawType = getRawType(enclosingType)

            if (rawType != ApiResponse::class.java) {
                throw IllegalArgumentException("type must be a ApiResponse")
            }

            if (enclosingType !is ParameterizedType) {
                throw IllegalArgumentException("resource must be parameterized")
            }

            val bodyType = getParameterUpperBound(0, enclosingType)
            return DeferredCallAdapter<Any>(bodyType)
        }
        return null
    }

}