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

    val cached: ScreenStateCached<T>
        get() {
            return value as ScreenStateCached<T>
        }

    val success: T
        get() {
            return value as T
        }

    fun isSuccess(): Boolean = !isInitial() && !isFailure() && !isCached()

    fun isInitial(): Boolean = value is ScreenStateInitial

    fun isInitialNotLoading(): Boolean = value is ScreenStateInitial && !value.loading

    fun isFailure(): Boolean = value is ScreenStateFailure

    fun isCached(): Boolean = value is ScreenStateCached<*>

    companion object {

        fun <T> initial(state: ScreenStateInitial = ScreenStateInitial()) = ScreenState<T>(state)

        fun <T> initialWithoutLoading(state: ScreenStateInitial = ScreenStateInitial(false)) =
            ScreenState<T>(state)

        fun <T> failure(state: ScreenStateFailure) = ScreenState<T>(state)

        fun <T> success(state: T) = ScreenState<T>(state as Any)

        fun <T> cached(state: ScreenStateCached<T>) = ScreenState<T>(state)

    }

}

data class ScreenStateCached<out T>(val data: T)

data class ScreenStateFailure(val throwable: Throwable, val refreshing: Boolean = false)

data class ScreenStateInitial(val loading: Boolean = true)