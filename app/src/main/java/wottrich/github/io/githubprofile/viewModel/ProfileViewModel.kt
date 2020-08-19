package wottrich.github.io.githubprofile.viewModel

import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.data.datasource.GithubDataSourceInterface
import wottrich.github.io.githubprofile.data.wrapper.Resource
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

    //=======> Profile
    private var _profileResult: MutableLiveData<Resource<Profile>> = MutableLiveData()
    val profileResult: LiveData<Resource<Profile>>
        get() = _profileResult

    val profile: LiveData<Profile>
        get() = Transformations.map(profileResult) {
            return@map it?.data
        }

    //=======> Repositories

    private var _repositoriesResult: MutableLiveData<Resource<List<Repository>>> = MutableLiveData()

    val repositoriesResult: LiveData<Resource<List<Repository>>>
        get() = _repositoriesResult

    val repositories = MediatorLiveData<List<Repository>>().apply {
        addSource(_repositoriesResult) {
            this.value = it?.data
        }
    } //data

    //=======> Variables

    private val mProfileLogin: MutableLiveData<String> = MutableLiveData()
    val profileLogin: LiveData<String>
        get() = mProfileLogin

    //=======> Init

    init {
        repositories.observeForever {  }
    }

    //=======> Functions

    fun loadServices (profileLogin: String) {
        if (this.mProfileLogin.value != profileLogin) {
            clear()
            this.mProfileLogin.value = profileLogin
            fetchProfile()
            fetchRepositories()
        } else {
            mError.value = R.string.equal_login_error
        }
    }

    private fun fetchProfile () {
        mProfileLogin.value?.let { login ->
            viewModelScope.launch(dispatchers.io) {
                service.loadProfile(login).collect {
                    _profileResult.postValue(it)
                }
            }
        }
    }

    private fun fetchRepositories () {
        mProfileLogin.value?.let { login ->
            viewModelScope.launch(dispatchers.io) {
                service.loadRepositories(login).collect {
                    _repositoriesResult.postValue(it)
                }
            }
        }
    }

    private fun clear() {
        this._profileResult.value = null
        this._repositoriesResult.value = null
    }

}