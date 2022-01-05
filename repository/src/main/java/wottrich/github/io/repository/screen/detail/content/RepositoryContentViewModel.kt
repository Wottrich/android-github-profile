package wottrich.github.io.repository.screen.detail.content

import github.io.wottrich.datasource.datasource.RepositoryDataSource
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import github.io.wottrich.datasource.models.RepositoryContent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import wottrich.github.io.base.extensions.toState
import wottrich.github.io.base.viewmodel.BaseViewModel
import wottrich.github.io.screenstate.ScreenState

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 15/11/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

class RepositoryContentViewModel(
    dispatchers: AppDispatchers,
    repositoryDataSource: RepositoryDataSource,
    profileLogin: String,
    repositoryName: String,
    path: String
) : BaseViewModel(dispatchers) {

    private val _contentState =
        MutableStateFlow<ScreenState<List<RepositoryContent>>>(ScreenState.initial())
    val contentState = _contentState.asStateFlow()

    init {
        launchIO {
            repositoryDataSource.loadRepositoryContentsPath(profileLogin, repositoryName, path)
                .collect {
                    _contentState.value = it.toState()
                }
        }
    }

}