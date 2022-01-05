package wottrich.github.io.repository.screen.detail

import github.io.wottrich.datasource.datasource.RepositoryDataSource
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import github.io.wottrich.datasource.models.Repository
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
 * @since 25/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

class RepositoryScreenViewModel(
    dispatchers: AppDispatchers,
    private val repositoryDataSource: RepositoryDataSource,
    private val profileLogin: String,
    private val repositoryName: String
) : BaseViewModel(dispatchers) {

    private val _repositoryState = MutableStateFlow<ScreenState<Repository>>(ScreenState.initial())
    val repositoryState = _repositoryState.asStateFlow()

    private val _repositoryContentState =
        MutableStateFlow<ScreenState<List<RepositoryContent>>>(ScreenState.initial())
    val repositoryContentState = _repositoryContentState.asStateFlow()

    init {
        launchIO {
            repositoryDataSource.loadRepository(profileLogin, repositoryName).collect {
                _repositoryState.value = it.toState()
            }
        }
        launchIO {
            repositoryDataSource.loadRepositoryContents(profileLogin, repositoryName).collect {
                _repositoryContentState.value = it.toState()
            }
        }
    }

}