package github.io.wottrich.ui.state

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 22/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

@Suppress("unchecked_cast")
class State<out T> private constructor(val value: Any) {

    val initial: StateInitial
        get() {
            return value as StateInitial
        }

    val failure: StateFailure
        get() {
            return value as StateFailure
        }

    val success: T
        get() {
            return value as T
        }

    fun isSuccess(): Boolean = value !is StateInitial && value !is StateFailure

    fun isInitial(): Boolean = value is StateInitial

    fun isInitialNotLoading(): Boolean = value is StateInitial && !value.loading

    companion object {

        fun <T> initial(state: StateInitial = StateInitial()) = State<T>(state)

        fun <T> failure(state: StateFailure) = State<T>(state)

        fun <T> success(state: T) = State<T>(state as Any)

    }

}

data class StateFailure(val throwable: Throwable, val refreshing: Boolean = false)

data class StateInitial(val loading: Boolean = true)