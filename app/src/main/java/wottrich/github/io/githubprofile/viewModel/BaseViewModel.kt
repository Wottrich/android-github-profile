package wottrich.github.io.githubprofile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import wottrich.github.io.githubprofile.model.ErrorWrapper

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

abstract class BaseViewModel() : ViewModel() {

    protected var mError: MutableLiveData<ErrorWrapper> = MutableLiveData()
    val error: LiveData<ErrorWrapper>
        get() = mError

    protected var mIsLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = mIsLoading

}