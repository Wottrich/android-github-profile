package wottrich.github.io.githubprofile.ui.profile

import github.io.wottrich.datasource.GithubDataSourceInterface
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import github.io.wottrich.datasource.models.Profile
import github.io.wottrich.datasource.models.Repository
import github.io.wottrich.ui.state.ScreenState
import github.io.wottrich.ui.state.ScreenStateInitial
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.archive.toState
import wottrich.github.io.githubprofile.util.BaseViewModel

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

class ProfileViewModel(
    dispatchers: AppDispatchers,
    private val service: GithubDataSourceInterface
) : BaseViewModel(dispatchers) {

    private var currentGithubUser: String? = null

    private val _headerStateFlow =
        MutableStateFlow<ScreenState<Profile>>(ScreenState.initial(ScreenStateInitial(false)))
    val headerStateFlow: StateFlow<ScreenState<Profile>> = _headerStateFlow

    private val _repositoriesStateFlow = MutableStateFlow<ScreenState<List<Repository>>>(
        ScreenState.initial(ScreenStateInitial(false))
    )
    val repositoriesStateFlow: StateFlow<ScreenState<List<Repository>>> = _repositoriesStateFlow

    fun loadServices(githubUser: String) {
        if (currentGithubUser != githubUser) {
            currentGithubUser = githubUser
            launchIO {
                loadProfile(githubUser)
            }
            launchIO {
                loadRepositories(githubUser)
            }
        } else {
            _error.value = R.string.equal_login_error
        }
    }

    private suspend fun loadProfile(githubUser: String) {
        service.loadProfile(githubUser).collect { profileResource ->
            _headerStateFlow.value = profileResource.toState()
        }
    }

    private suspend fun loadRepositories(githubUser: String) {
        service.loadRepositories(githubUser).collect { repositoriesResource ->
            _repositoriesStateFlow.value = repositoriesResource.toState()
        }
    }

}