package wottrich.github.io.githubprofile.util

import androidx.lifecycle.LiveData

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 30/08/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

class AbsentLiveData<T : Any?> private constructor(): LiveData<T>() {
    init {
        // use post instead of set since this can be created on any thread
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}