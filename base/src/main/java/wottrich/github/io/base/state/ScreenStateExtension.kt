package wottrich.github.io.base.state

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import wottrich.github.io.screenstate.ScreenState
import wottrich.github.io.screenstate.ScreenStateCached
import wottrich.github.io.screenstate.ScreenStateFailure
import wottrich.github.io.screenstate.ScreenStateInitial

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 23/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

@Composable
inline fun <reified T> ScreenStateComponent(
    state: ScreenState<T>,
    initial: @Composable (ScreenStateInitial) -> Unit,
    failure: @Composable (ScreenStateFailure) -> Unit,
    cached: @Composable (T) -> Unit = {},
    success: @Composable (T) -> Unit
) {
    when (val value = state.value) {
        is ScreenStateInitial -> initial(value)
        is ScreenStateFailure -> failure(value)
        is ScreenStateCached<*> -> cached(value.data as T)
        is T -> success(value)
    }
}

inline fun <reified T> LazyListScope.screenStateListComponent(
    state: ScreenState<List<T>>,
    crossinline initial: @Composable (ScreenStateInitial) -> Unit,
    crossinline failure: @Composable (ScreenStateFailure) -> Unit,
    crossinline success: @Composable LazyItemScope.(T) -> Unit
) {
    when (val value = state.value) {
        is ScreenStateInitial -> item { initial(value) }
        is ScreenStateFailure -> item { failure(value) }
        is List<*> -> {
            items(value) {
                success(it as T)
            }
        }
    }
}