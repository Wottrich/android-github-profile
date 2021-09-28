package wottrich.github.io.githubprofile.archive

import github.io.wottrich.datasource.resource.Resource
import github.io.wottrich.ui.state.ScreenState
import github.io.wottrich.ui.state.ScreenStateFailure

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 22/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

fun <T> Resource<T>.toState(): ScreenState<T> = when (this) {
    is Resource.Failure -> ScreenState.failure<T>(ScreenStateFailure(this.throwable))
    is Resource.Loading -> ScreenState.initial()
    is Resource.Success -> {
        val success = checkNotNull(this.data)
        ScreenState.success(success)
    }
}