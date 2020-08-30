package wottrich.github.io.githubprofile.data.resource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 16/08/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */
 
class NetworkBoundResource<ResultType, RequestType>(
    private val saveCallResults: (suspend (item: RequestType) -> Unit)? = null,
    private val processResponse: (response: RequestType) -> ResultType,
    private val call: suspend () -> ApiResponse<RequestType>
) {

    fun build(): Flow<Resource<ResultType>> {
        return flow {
            emit(Resource.loading())
            fetchFromNetwork()
        }
    }

    private suspend fun FlowCollector<Resource<ResultType>>.fetchFromNetwork() {
        return when (val result = call()) {
            is ApiSuccessResponse -> {
                val process = processResponse(result.body)
                saveCallResults?.invoke(result.body)
                emit(Resource.success(process))
            }
            is ApiEmptyResponse -> {
                emit(Resource.success(null))
            }
            is ApiErrorResponse -> {
                emit(Resource.error(result.error))
            }
        }
    }

}