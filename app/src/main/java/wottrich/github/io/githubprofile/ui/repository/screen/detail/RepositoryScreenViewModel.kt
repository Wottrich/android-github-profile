package wottrich.github.io.githubprofile.ui.repository.screen.detail

import github.io.wottrich.datasource.GithubDataSourceInterface
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import github.io.wottrich.datasource.models.Repository
import github.io.wottrich.datasource.models.RepositoryContent
import github.io.wottrich.ui.state.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import wottrich.github.io.githubprofile.archive.toState
import wottrich.github.io.githubprofile.util.BaseViewModel

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
    private val service: GithubDataSourceInterface,
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
            service.loadRepository(profileLogin, repositoryName).collect {
                _repositoryState.value = it.toState()
            }
        }
        launchIO {
            service.loadRepositoryContents(profileLogin, repositoryName).collect {
                _repositoryContentState.value = it.toState()
            }
        }
    }

}