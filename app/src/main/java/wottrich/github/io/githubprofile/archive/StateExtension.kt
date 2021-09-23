package wottrich.github.io.githubprofile.archive

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import github.io.wottrich.ui.state.State
import github.io.wottrich.ui.state.StateFailure
import github.io.wottrich.ui.state.StateInitial
import wottrich.github.io.githubprofile.data.resource.Resource

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

@Composable
inline fun <reified T> StateComponent(
    state: State<T>,
    initial: @Composable (StateInitial) -> Unit,
    failure: @Composable (StateFailure) -> Unit,
    success: @Composable (T) -> Unit
) {
    when (val value = state.value) {
        is StateInitial -> initial(value)
        is StateFailure -> failure(value)
        is T -> success(value)
    }
}

inline fun <reified T> LazyListScope.stateListComponent(
    state: State<List<T>>,
    crossinline initial: @Composable (StateInitial) -> Unit,
    crossinline failure: @Composable (StateFailure) -> Unit,
    crossinline success: @Composable LazyItemScope.(T) -> Unit
) {
    when (val value = state.value) {
        is StateInitial -> item {
            initial(value)
        }
        is StateFailure -> item { failure(value) }
        is List<*> -> {
            items(value) {
                success(it as T)
            }
        }
    }
}