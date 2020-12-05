package wottrich.github.io.githubprofile.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import kotlinx.coroutines.flow.flowOn
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.data.datasource.GithubDataSourceInterface
import wottrich.github.io.githubprofile.data.resource.Resource
import wottrich.github.io.githubprofile.model.Profile
import wottrich.github.io.githubprofile.model.Repository
import wottrich.github.io.githubprofile.util.AbsentLiveData
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

    //=======> Variables

    private val _profileLogin: MutableLiveData<String> = MutableLiveData()
    val profileLogin: LiveData<String>
        get() = _profileLogin

    //=======> Profile
    val profileResult: LiveData<Resource<Profile>> = _profileLogin.switchMap {
        return@switchMap if (it == null || it.isEmpty()) {
            AbsentLiveData.create()
        } else {
            service.loadProfile(it)
                .flowOn(dispatchers.io)
                .asLiveData()
        }
    }

    val profile: LiveData<Profile>
        get() = Transformations.map(profileResult) {
            return@map it?.data
        }

    //=======> Repositories

    val repositoriesResult: LiveData<Resource<List<Repository>>> = _profileLogin.switchMap {
        return@switchMap if (it == null || it.isEmpty()) {
            AbsentLiveData.create()
        } else {
            service.loadRepositories(it)
                .flowOn(dispatchers.io)
                .asLiveData()
        }
    }

    val repositories: LiveData<List<Repository>> = repositoriesResult.map {
        return@map it.data ?: mutableListOf()
    }

    //=======> Functions

    fun loadServices (profileLogin: String) {
        if (this._profileLogin.value != profileLogin) {
            this._profileLogin.value = profileLogin
        } else {
            mError.value = R.string.equal_login_error
        }
    }

}