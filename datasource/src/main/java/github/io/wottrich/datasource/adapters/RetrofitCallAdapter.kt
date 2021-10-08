package github.io.wottrich.datasource.adapters

import wottrich.github.io.resource.Resource
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 25/08/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

class RetrofitCallAdapter<R>(
    private val responseType: Type
) : CallAdapter<R, Call<Resource<R>>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Call<Resource<R>> {
        return RetrofitCallAdapterResponse(call)
    }

}