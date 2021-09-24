package wottrich.github.io.githubprofile.archive

import github.io.wottrich.datasource.resource.Resource
import github.io.wottrich.ui.state.State
import github.io.wottrich.ui.state.StateFailure

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 22/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

fun <T> Resource<T>.toState(): State<T> = when (this) {
    is Resource.Failure -> State.failure<T>(StateFailure(this.throwable))
    is Resource.Loading -> State.initial()
    is Resource.Success -> {
        val success = checkNotNull(this.data)
        State.success(success)
    }
}