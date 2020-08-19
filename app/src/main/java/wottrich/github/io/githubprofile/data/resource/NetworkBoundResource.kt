package wottrich.github.io.githubprofile.data.resource

import kotlinx.coroutines.Deferred

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 18/08/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

abstract class NetworkBoundResource<ResultType, RequestType> {

    suspend fun getResult(): Resource<ResultType> {
        return when (val result = createCallAsync().await()) {
            is ApiSuccessResponse -> {
                val process = processResponse(result.body)
                Resource.success(process)
            }
            is ApiEmptyResponse -> {
                Resource.success(null)
            }
            is ApiErrorResponse -> {
                Resource.error(result.error)
            }
        }
    }

    protected abstract fun processResponse(response: RequestType): ResultType

    protected abstract suspend fun createCallAsync(): Deferred<ApiResponse<RequestType>>

}