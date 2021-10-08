package wottrich.github.io.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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