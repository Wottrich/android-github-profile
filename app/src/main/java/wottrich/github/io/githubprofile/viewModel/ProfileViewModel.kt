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

    companion object {
        const val KEY_PROFILE_NAME = "KeyProfileName"
    }

    private var mLoadingProfile: MutableLiveData<Boolean> = MutableLiveData()
    val loadingProfile: LiveData<Boolean>
        get() = mLoadingProfile

    private var mProfile: MutableLiveData<Profile> = MutableLiveData()
    val profile: LiveData<Profile>
        get() = mProfile

    private var mLoadingRepository: MutableLiveData<Boolean> = MutableLiveData()
    val loadingRepository: LiveData<Boolean>
        get() = mLoadingRepository

    private var mRepositories: MutableLiveData<List<Repository>> = MutableLiveData()
    val repositories: LiveData<List<Repository>>
        get() = mRepositories

    private var profileLogin: String? = null

    fun loadServices (profileLogin: String) {
        this.profileLogin = profileLogin
        this.mRepositories.value = emptyList()
        fetchProfile()
        fetchRepositories()
    }

    fun reloadProfile() {
        fetchProfile()
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
                        mError.value = ErrorWrapper(message, Services.profile)
                    }
                },
                onFailure = { message ->
                    mLoadingProfile.value = false
                    mError.value = ErrorWrapper(message, Services.profile)
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
                        mError.value = ErrorWrapper(message, Services.repositories)
                    }
                },
                onFailure = { message ->
                    mLoadingRepository.value = false
                    mError.value = ErrorWrapper(message, Services.repositories)
                }
            )
        }
    }

}