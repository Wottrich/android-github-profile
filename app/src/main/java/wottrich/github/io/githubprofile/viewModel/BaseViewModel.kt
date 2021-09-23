package wottrich.github.io.githubprofile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.util.AppDispatchers

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

abstract class BaseViewModel(
    dispatchers: AppDispatchers
) : ViewModel() {

    protected val coroutineContextIO = dispatchers.io
    protected val coroutineContextMain = dispatchers.main

    protected var _error: MutableLiveData<Int> = MutableLiveData()
    val error: LiveData<Int> = _error

    protected var _snackBarError = MutableStateFlow(R.string.unknown_error)
    val snackBarError = _snackBarError.asStateFlow()

    fun launchIO(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(context = coroutineContextIO) {
            block(this)
        }
    }

    fun launchMain(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(context = coroutineContextMain) {
            block(this)
        }
    }
}