package wottrich.github.io.profile

import github.io.wottrich.datasource.datasource.ProfileDataSource
import github.io.wottrich.datasource.datasource.RepositoryDataSource
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import github.io.wottrich.datasource.models.Profile
import github.io.wottrich.datasource.models.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import wottrich.github.io.base.viewmodel.BaseViewModel
import wottrich.github.io.githubprofile.archive.toState
import wottrich.github.io.screenstate.ScreenState
import wottrich.github.io.screenstate.ScreenStateInitial

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
    private val profileDataSource: ProfileDataSource,
    private val repositoryDataSource: RepositoryDataSource
) : BaseViewModel(dispatchers) {

    private var currentGithubUser: String? = null

    private val _profileState = MutableStateFlow(ProfileState())
    val profileState = _profileState.asStateFlow()

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
        profileDataSource.loadProfile(githubUser).collect { profileResource ->
            _profileState.value = profileState.value.copy(headerState = profileResource.toState())
        }
    }

    private suspend fun loadRepositories(githubUser: String) {
        repositoryDataSource.loadRepositories(githubUser).collect { repositoriesResource ->
            _profileState.value =
                profileState.value.copy(repositoriesState = repositoriesResource.toState())
        }
    }

}

data class ProfileState(
    val headerState: ScreenState<Profile> = ScreenState.initial(ScreenStateInitial(false)),
    val repositoriesState: ScreenState<List<Repository>> = ScreenState.initial(
        ScreenStateInitial(
            false
        )
    )
) {
    fun isInitial() =
        headerState.isInitialNotLoading() && repositoriesState.isInitialNotLoading()
}