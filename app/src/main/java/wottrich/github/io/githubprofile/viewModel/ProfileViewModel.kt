package wottrich.github.io.githubprofile.viewModel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.archive.TransformationsUtils
import wottrich.github.io.githubprofile.data.datasource.GithubDataSource
import wottrich.github.io.githubprofile.data.resource.Resource
import wottrich.github.io.githubprofile.model.Profile
import wottrich.github.io.githubprofile.model.Repository
import kotlin.coroutines.CoroutineContext

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

class ProfileViewModel(
    private val service: GithubDataSource = GithubDataSource(),
    private val dispatcherMain: CoroutineContext = Dispatchers.Main,
    private val dispatcherIO: CoroutineContext = Dispatchers.IO
) : BaseViewModel() {

    //=======> Profile

    private var mProfileResource = MediatorLiveData<Resource<Profile>>()

    val profileResource: LiveData<Resource<Profile>>
        get() = mProfileResource

    val profile: LiveData<Profile>//data
        get() = Transformations.map(profileResource) {
            return@map it?.data
        }

    //=======> Repositories

    private var mRepositoriesResource = MediatorLiveData<Resource<List<Repository>>>()

    val repositoriesResource: LiveData<Resource<List<Repository>>>
        get() = mRepositoriesResource

    val repositories = MediatorLiveData<List<Repository>>().apply {
        addSource(repositoriesResource) {
            this.value = it?.data
        }
    }

    //=======> Service

    private var profileService: LiveData<Resource<Profile>> = MutableLiveData()
    private var repositoriesService: LiveData<Resource<List<Repository>>> = MutableLiveData()

    //=======> Variables

    private val mProfileLogin: MutableLiveData<String> = MutableLiveData()
    val profileLogin: LiveData<String>
        get() = mProfileLogin

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
            viewModelScope.launch(dispatcherMain) {

                mProfileResource.removeSource(profileService)

                withContext(dispatcherIO) {
                    profileService = service.loadProfile(login)
                }

                mProfileResource.addSource(profileService) {
                    mProfileResource.value = it
                }

            }
        }
    }

    private fun fetchRepositories () {
        mProfileLogin.value?.let { login ->
            viewModelScope.launch(dispatcherMain) {

                mRepositoriesResource.removeSource(repositoriesService)

                withContext(dispatcherIO) {
                    repositoriesService = service.loadRepositories(login)
                }

                mRepositoriesResource.addSource(repositoriesService) {
                    mRepositoriesResource.value = it
                }

            }
        }
    }

    private fun clear() {
        this.mProfileResource.value = null
        this.mRepositoriesResource.value = null
    }

}