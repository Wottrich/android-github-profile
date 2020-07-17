package wottrich.github.io.githubprofile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.archive.TransformationsUtils
import wottrich.github.io.githubprofile.data.datasource.GithubDataSource
import wottrich.github.io.githubprofile.model.Profile
import wottrich.github.io.githubprofile.model.Repository
import java.lang.Exception
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
    context: CoroutineContext = IO
) : BaseViewModel(context) {

    //=======> Profile

    private var mLoadingProfile: MutableLiveData<Boolean> = MutableLiveData()
    private var mProfile: MutableLiveData<Profile> = MutableLiveData()
    private var mProfileError: MutableLiveData<String> = MutableLiveData()

    val loadingProfile: LiveData<Int> //loading
        get() = TransformationsUtils.isVisible(mLoadingProfile, mLoadingProfile.value)

    val profileError: LiveData<String>//error
        get() = mProfileError

    val profileErrorVisibility: LiveData<Int>//error visibility
        get() = TransformationsUtils.isVisible(mProfileError, mProfileError.value != null)

    val profile: LiveData<Profile>//data
        get() = mProfile

    //=======> Repositories

    private var mLoadingRepositories: MutableLiveData<Boolean> = MutableLiveData()
    private var mRepositories: MutableLiveData<List<Repository>> = MutableLiveData()
    private var mRepositoriesError: MutableLiveData<String> = MutableLiveData()

    val loadingRepositories: LiveData<Int> //loading
        get() = TransformationsUtils.isVisible(mLoadingRepositories, mLoadingRepositories.value)

    val repositoriesError: LiveData<String> //error
        get() = mRepositoriesError

    val repositoriesErrorVisibility: LiveData<Int> //error visibility
        get() = TransformationsUtils.isVisible(mRepositoriesError, mRepositoriesError.value != null)

    val repositories: LiveData<List<Repository>> //data
        get() = mRepositories

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
            mLoadingProfile.value = true
            scope.launch {
                try {
                    val result = service.loadProfile(login)
                    mProfile.postValue(result)
                } catch (e: Exception) {
                    mProfileError.postValue(e.message)
                } finally {
                    mLoadingProfile.postValue(false)
                }
            }
        }
    }

    private fun fetchRepositories () {
        mProfileLogin.value?.let { login ->
            mLoadingRepositories.value = true
            scope.launch {
                try {
                    val result = service.loadRepositories(login)
                    mRepositories.postValue(result)
                } catch (e: Exception) {
                    mRepositoriesError.postValue(e.message)
                } finally {
                    mLoadingRepositories.postValue(false)
                }

            }
        }
    }

    private fun clear() {
        this.mProfile.value = null
        this.mRepositories.value = emptyList()
        this.mProfileError.value = null
        this.mRepositoriesError.value = null
    }

}