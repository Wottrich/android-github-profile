package wottrich.github.io.githubprofile.viewModel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.data.datasource.GithubDataSourceInterface
import wottrich.github.io.githubprofile.data.resource.Status
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
    private val service: GithubDataSourceInterface,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private var currentGithubUser: String? = null

    //=======> Profile
    private val _headerStateFlow = MutableStateFlow<HeaderState?>(HeaderState())
    val headerStateFlow: StateFlow<HeaderState?> = _headerStateFlow

    //=======> Repositories
    private val _repositoriesStateFlow = MutableStateFlow<RepositoriesState?>(RepositoriesState())
    val repositoriesStateFlow: StateFlow<RepositoriesState?> = _repositoriesStateFlow

    //=======> Functions

    fun loadServices (githubUser: String) {
        if (currentGithubUser != githubUser) {
            currentGithubUser = githubUser
            viewModelScope.launch(dispatchers.io) {
                loadProfile()
            }
            viewModelScope.launch(dispatchers.io) {
                loadRepositories()
            }
        } else {
            mError.value = R.string.equal_login_error
        }
    }

    private suspend fun loadProfile() {
        val githubUser = currentGithubUser
        if (githubUser != null) {
            service.loadProfile(githubUser).collect { profileResource ->
                val newHeaderState = when (profileResource.status) {
                    Status.LOADING -> headerStateFlow.value?.copy(isInitialState = false, refreshing = true)
                    Status.SUCCESS -> headerStateFlow.value?.copy(
                        profile = profileResource.data,
                        refreshing = false,
                        errorState = false
                    )
                    Status.ERROR -> headerStateFlow.value?.copy(
                        isInitialState = false,
                        refreshing = false,
                        errorState = true
                    )
                }
                _headerStateFlow.value = newHeaderState
            }
        } else {
            _headerStateFlow.value = HeaderState(errorState = true)
        }
    }

    private suspend fun loadRepositories() {
        val githubUser = currentGithubUser
        if (githubUser != null) {
            service.loadRepositories(githubUser).collect { repositoriesResource ->
                val newRepositoriesState = when (repositoriesResource.status) {
                    Status.LOADING -> repositoriesStateFlow.value?.copy(isInitialState = false, refreshing = true)
                    Status.SUCCESS -> repositoriesStateFlow.value?.copy(
                        repositories = repositoriesResource.data.orEmpty(),
                        refreshing = false,
                        errorState = false
                    )
                    Status.ERROR -> repositoriesStateFlow.value?.copy(
                        isInitialState = false,
                        refreshing = false,
                        errorState = true
                    )
                }
                _repositoriesStateFlow.value = newRepositoriesState
            }
        } else {
            _repositoriesStateFlow.value = RepositoriesState(errorState = true)
        }
    }

}

data class HeaderState(
    val isInitialState: Boolean = true,
    val profile: Profile? = null,
    val refreshing: Boolean = false,
    val errorState: Boolean = false
)

data class RepositoriesState(
    val isInitialState: Boolean = true,
    val repositories: List<Repository> = listOf(),
    val refreshing: Boolean = false,
    val errorState: Boolean = false
)