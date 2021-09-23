package wottrich.github.io.githubprofile.viewModel

import github.io.wottrich.ui.state.State
import github.io.wottrich.ui.state.StateInitial
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.archive.toState
import wottrich.github.io.githubprofile.data.datasource.GithubDataSourceInterface
import wottrich.github.io.githubprofile.model.Profile
import wottrich.github.io.githubprofile.model.Repository
import wottrich.github.io.githubprofile.util.AppDispatchers

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
        MutableStateFlow<State<Profile>>(State.initial(StateInitial(false)))
    val headerStateFlow: StateFlow<State<Profile>> = _headerStateFlow

    private val _repositoriesStateFlow = MutableStateFlow<State<List<Repository>>>(
        State.initial(StateInitial(false))
    )
    val repositoriesStateFlow: StateFlow<State<List<Repository>>> = _repositoriesStateFlow

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