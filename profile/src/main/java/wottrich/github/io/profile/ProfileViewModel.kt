package wottrich.github.io.profile

import github.io.wottrich.datasource.datasource.ProfileDataSource
import github.io.wottrich.datasource.datasource.RepositoryDataSource
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import github.io.wottrich.datasource.models.Profile
import github.io.wottrich.datasource.models.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import wottrich.github.io.base.SingleShotEventBus
import wottrich.github.io.base.extensions.toState
import wottrich.github.io.base.viewmodel.BaseViewModel
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
    private val repositoryDataSource: RepositoryDataSource,
) : BaseViewModel(dispatchers) {

    private var currentGithubUser: String? = null

    private val _effects = SingleShotEventBus<ProfileEffects>()
    val effects: Flow<ProfileEffects> = _effects.events

    private val _profileState = MutableStateFlow(ProfileState())
    val profileState = _profileState.asStateFlow()

    fun loadServices(githubUser: String) {
        if (currentGithubUser != githubUser) {
            currentGithubUser = githubUser
            loadProfile(githubUser)
            loadRepositories(githubUser)
        } else {
            emitEqualUserSnackbar()
        }
    }

    private fun emitEqualUserSnackbar() {
        launchIO {
            _effects.emit(ProfileEffects.EqualUser)
        }
    }

    private fun loadProfile(githubUser: String) {
        launchIO {
            profileDataSource.loadProfile(githubUser)
                .onEach { profileResource ->
                    val headerState = profileResource.toState()
                    _profileState.value = profileState.value.copy(headerState = headerState)
                }
                .catch { println("-----PROFILE----> $it") }
                .collect()
        }
    }

    private fun loadRepositories(githubUser: String) {
        launchIO {
            repositoryDataSource.loadRepositories(githubUser)
                .onEach { repositoriesResource ->
                    _profileState.value = profileState.value.copy(
                        repositoriesState = repositoriesResource.toState()
                    )
                }
                .catch { println("----REPOSITORIES-----> $it") }
                .collect()
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

    fun shouldShowFullError() =
        headerState.isFailure() && repositoriesState.isFailure()
}

sealed class ProfileEffects {
    object EqualUser : ProfileEffects()
}