package wottrich.github.io.githubprofile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

abstract class BaseViewModel(
    final override val coroutineContext: CoroutineContext
) : ViewModel(), CoroutineScope {

    protected val scope = CoroutineScope(coroutineContext)

    protected var mError: MutableLiveData<Int> = MutableLiveData()
    val error: LiveData<Int>
        get() = mError

}