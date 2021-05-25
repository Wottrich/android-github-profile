package wottrich.github.io.githubprofile.view.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import wottrich.github.io.githubprofile.data.resource.Status
import wottrich.github.io.githubprofile.ui.widgets.ProgressBar
import wottrich.github.io.githubprofile.viewModel.ProfileViewModel
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.data.resource.Resource
import wottrich.github.io.githubprofile.model.Profile
import wottrich.github.io.githubprofile.model.Repository
import wottrich.github.io.githubprofile.ui.values.Subtitle
import wottrich.github.io.githubprofile.ui.values.Title
import wottrich.github.io.githubprofile.ui.widgets.TextView

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 02/09/2020
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {

    val profileState = viewModel.profileResult.observeAsState(null)
    val repositoriesState = viewModel.repositoriesResult.observeAsState(null)

    Column(modifier = Modifier.fillMaxWidth()) {

        TextView(
            text = LocalContext.current.getString(R.string.welcome_find_profile),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = Title.titleBold,
            isVisible = profileState.value == null || repositoriesState.value == null
        )

        if (profileState.value != null) {
            ProfileContainer(profileState = profileState)
            Divider()
        }

        if(repositoriesState.value != null) {
            RepositoriesContainer(repositoriesState = repositoriesState)
        }

    }

}

@Composable
fun ProfileContainer (profileState: State<Resource<Profile>?>) {
    val profileResult = profileState.value
    when (profileResult?.status) {
        Status.SUCCESS -> {
            val profile = profileResult.data
            HeaderProfile(profile = profile)
        }
        Status.LOADING -> ProgressBar()
        Status.ERROR -> FindProfileError(message = profileResult.message)
    }
}

@Composable
fun RepositoriesContainer(repositoriesState: State<Resource<List<Repository>>?>) {
    val repositoriesResult = repositoriesState.value
    when (repositoriesResult?.status) {
        Status.SUCCESS -> {
            val repositories = repositoriesResult.data ?: mutableListOf()
            LazyColumn {
                items(repositories) {
                    RowRepository(repository = it)
                }
            }
        }
        Status.LOADING -> ProgressBar()
        Status.ERROR -> FindProfileError(message = repositoriesResult.message)
    }
}

@Composable
fun FindProfileError (message: String?) {

    val errorMessage = message ?: LocalContext.current.getString(R.string.unknown_error)

    TextView(
        text = errorMessage,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = Subtitle.subtitleBold
    )

}