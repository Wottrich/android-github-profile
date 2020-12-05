package wottrich.github.io.githubprofile.view.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.style.TextAlign
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.data.resource.Resource
import wottrich.github.io.githubprofile.data.resource.Status
import wottrich.github.io.githubprofile.model.Profile
import wottrich.github.io.githubprofile.model.Repository
import wottrich.github.io.githubprofile.ui.values.Subtitle
import wottrich.github.io.githubprofile.ui.values.Title
import wottrich.github.io.githubprofile.ui.widgets.ProgressBar
import wottrich.github.io.githubprofile.ui.widgets.TextView
import wottrich.github.io.githubprofile.viewModel.ProfileViewModel
import kotlin.random.Random

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 02/09/2020
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

@ExperimentalMaterialApi
@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {

    val profileState: Resource<Profile>? by viewModel.profileResult.observeAsState(null)
    val repositoriesState: Resource<List<Repository>>? by viewModel.repositoriesResult.observeAsState(
        null
    )

    Column(modifier = Modifier.fillMaxWidth()) {

        TextView(
            text = AmbientContext.current.getString(R.string.welcome_find_profile),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = Title.titleBold,
            color = Color(android.graphics.Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))),
            isVisible = profileState == null || repositoriesState == null
        )

        if (profileState != null) {
            ProfileContainer(profileState = profileState)
            Divider()
        }

        if (repositoriesState != null) {
            RepositoriesContainer(repositoriesState = repositoriesState)
        }

    }

}

@Composable
fun ProfileContainer(profileState: Resource<Profile>?) {
    when (profileState?.status) {
        Status.SUCCESS -> {
            val profile = profileState.data
            HeaderProfile(profile = profile)
        }
        Status.LOADING -> ProgressBar()
        Status.ERROR -> FindProfileError(message = profileState.message)
    }
}

@Composable
fun RepositoriesContainer(repositoriesState: Resource<List<Repository>>?) {
    when (repositoriesState?.status) {
        Status.SUCCESS -> {
            val repositories = repositoriesState.data ?: mutableListOf()
            LazyColumnFor(items = repositories) { repository ->
                RowRepository(repository = repository)
            }
        }
        Status.LOADING -> ProgressBar()
        Status.ERROR -> FindProfileError(message = repositoriesState.message)
    }
}

@Composable
fun FindProfileError(message: String?) {

    val errorMessage = message ?: ContextAmbient.current.getString(R.string.unknown_error)

    TextView(
        text = errorMessage,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = Subtitle.subtitleBold
    )

}