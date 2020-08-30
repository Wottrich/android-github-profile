package wottrich.github.io.githubprofile.util.callAdapter

import retrofit2.*
import wottrich.github.io.githubprofile.data.resource.ApiResponse
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
) : CallAdapter<R, Call<ApiResponse<R>>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Call<ApiResponse<R>> {
        return RetrofitCallAdapterResponse(call)
    }

}