package wottrich.github.io.profile

import github.io.wottrich.datasource.database.ProfileDao
import github.io.wottrich.datasource.dispatchers.AppDispatchers
import github.io.wottrich.datasource.models.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import wottrich.github.io.base.viewmodel.BaseViewModel
import wottrich.github.io.screenstate.ScreenState

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/11/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

class ProfileSearchViewModel(
    dispatchers: AppDispatchers,
    private val profileDao: ProfileDao
) : BaseViewModel(dispatchers) {

    private var currentList: List<Profile> = listOf()

    private val _screenState =
        MutableStateFlow<ScreenState<List<Profile>>>(ScreenState.initialWithoutLoading())
    val screenState = _screenState.asStateFlow()

    fun onQueryChange(query: String) {
        launchIO {
            val profileList = profileDao.getAllProfilesSavedByQuery(query)
            currentList = profileList
            _screenState.value = ScreenState.success(profileList)
        }
    }

    fun onDelete(profile: Profile) {
        launchIO {
            profileDao.delete(profile)
            currentList = currentList.toMutableList().apply {
                remove(profile)
            }.toList()
            _screenState.value = ScreenState.success(currentList)
        }
    }

}