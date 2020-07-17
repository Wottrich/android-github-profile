package wottrich.github.io.githubprofile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import wottrich.github.io.githubprofile.data.datasource.GithubDataSource
import wottrich.github.io.githubprofile.data.datasource.Services
import wottrich.github.io.githubprofile.model.ErrorWrapper
import wottrich.github.io.githubprofile.model.Profile
import wottrich.github.io.githubprofile.model.Repository

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */
 
class ProfileViewModel(
    private val service: GithubDataSource = GithubDataSource()
) : BaseViewModel() {

    //=======> Profile

    private var mLoadingProfile: MutableLiveData<Boolean> = MutableLiveData()
    private var mProfile: MutableLiveData<Profile> = MutableLiveData()
    private var mProfileError: MutableLiveData<String> = MutableLiveData()

    val loadingProfile: LiveData<Boolean>
        get() = mLoadingProfile

    val profile: LiveData<Profile>
        get() = mProfile

    val profileError: LiveData<String>
        get() = mProfileError

    //=======> Repositories

    private var mLoadingRepository: MutableLiveData<Boolean> = MutableLiveData()
    private var mRepositories: MutableLiveData<List<Repository>> = MutableLiveData()
    private var mRepositoriesError: MutableLiveData<String> = MutableLiveData()

    val loadingRepository: LiveData<Boolean>
        get() = mLoadingRepository

    val repositories: LiveData<List<Repository>>
        get() = mRepositories

    val repositoriesError: LiveData<String>
        get() = mRepositoriesError

    //=======> Variables

    private var profileLogin: String? = null

    //=======> Functions

    fun loadServices (profileLogin: String) {
        clear()
        this.profileLogin = profileLogin
        fetchProfile()
        fetchRepositories()
    }

    private fun fetchProfile () {
        profileLogin?.let { login ->
            mLoadingProfile.value = true
            service.loadProfile(login,
                onSuccess = { isSuccess, message, result ->
                    mLoadingProfile.value = false
                    if (isSuccess) {
                        mProfile.value = result
                    } else {
                        mProfileError.value = message
                    }
                },
                onFailure = { message ->
                    mLoadingProfile.value = false
                    mProfileError.value = message
                }
            )
        }
    }

    private fun fetchRepositories () {
        profileLogin?.let { login ->
            mLoadingRepository.value = true
            service.loadRepositories(login,
                onSuccess = { isSuccess, message, result ->
                    mLoadingRepository.value = false
                    if (isSuccess) {
                        mRepositories.value = result
                    } else {
                        mRepositoriesError.value = message
                    }
                },
                onFailure = { message ->
                    mLoadingRepository.value = false
                    mRepositoriesError.value = message
                }
            )
        }
    }

    private fun clear() {
        this.mProfile.value = null
        this.mRepositories.value = emptyList()
        this.mProfileError.value = null
        this.mRepositoriesError.value = null
    }

}