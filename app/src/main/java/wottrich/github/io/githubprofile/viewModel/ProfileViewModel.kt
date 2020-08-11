package wottrich.github.io.githubprofile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.archive.TransformationsUtils
import wottrich.github.io.githubprofile.data.datasource.GithubDataSource
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

    //TODO parametro usando no layout disponivel no arquivo "Main with databinding.kt"
    //TODO parameter used into layout available in "Main with databinding.kt" file
    val profileErrorVisibility: LiveData<Int>//error visibility
        get() = TransformationsUtils.isVisible(mProfileError, mProfileError.value != null)

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

    //TODO parametro usando no layout disponivel no arquivo "Main with databinding.kt"
    //TODO parameter used into layout available in "Main with databinding.kt" file
    val repositoriesErrorVisibility: LiveData<Int> //error visibility
        get() = TransformationsUtils.isVisible(mRepositoriesError, mRepositoriesError.value != null)

    val repositoriesError: LiveData<String>
        get() = mRepositoriesError

    //=======> Variables

    private val mProfileLogin: MutableLiveData<String> = MutableLiveData()
    //TODO parametro usando no layout disponivel no arquivo "Main with databinding.kt"
    //TODO parameter used into layout available in "Main with databinding.kt" file
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
        mProfileLogin.value?.let { login ->
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