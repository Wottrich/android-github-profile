package wottrich.github.io.base.extensions

import wottrich.github.io.resource.Resource
import wottrich.github.io.resource.Resource.Cached
import wottrich.github.io.resource.Resource.Failure
import wottrich.github.io.resource.Resource.Loading
import wottrich.github.io.resource.Resource.Success
import wottrich.github.io.screenstate.ScreenState
import wottrich.github.io.screenstate.ScreenStateCached
import wottrich.github.io.screenstate.ScreenStateFailure

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 22/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

fun <T> Resource<T>.toState(): ScreenState<T> = when (this) {
    is Failure -> ScreenState.failure<T>(
        ScreenStateFailure(
            this.throwable
        )
    )
    is Loading -> ScreenState.initial()
    is Success -> {
        val success = checkNotNull(this.data)
        ScreenState.success(success)
    }
    is Cached -> {
        val cached = checkNotNull(this.data)
        ScreenState.cached(ScreenStateCached<T>(cached))
    }
}