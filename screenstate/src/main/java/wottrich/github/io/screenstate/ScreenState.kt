package wottrich.github.io.screenstate

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 22/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

@Suppress("unchecked_cast")
class ScreenState<out T> private constructor(val value: Any) {

    val initial: ScreenStateInitial
        get() {
            return value as ScreenStateInitial
        }

    val failure: ScreenStateFailure
        get() {
            return value as ScreenStateFailure
        }

    val success: T
        get() {
            return value as T
        }

    fun isSuccess(): Boolean = value !is ScreenStateInitial && value !is ScreenStateFailure

    fun isInitial(): Boolean = value is ScreenStateInitial

    fun isInitialNotLoading(): Boolean = value is ScreenStateInitial && !value.loading

    companion object {

        fun <T> initial(state: ScreenStateInitial = ScreenStateInitial()) = ScreenState<T>(state)

        fun <T> failure(state: ScreenStateFailure) = ScreenState<T>(state)

        fun <T> success(state: T) = ScreenState<T>(state as Any)

    }

}

data class ScreenStateFailure(val throwable: Throwable, val refreshing: Boolean = false)

data class ScreenStateInitial(val loading: Boolean = true)