package wottrich.github.io.githubprofile.view.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.ui.values.Subtitle
import wottrich.github.io.githubprofile.ui.values.Title
import wottrich.github.io.githubprofile.ui.widgets.ProgressBar
import wottrich.github.io.githubprofile.ui.widgets.TextView
import wottrich.github.io.githubprofile.viewModel.HeaderState
import wottrich.github.io.githubprofile.viewModel.ProfileViewModel
import wottrich.github.io.githubprofile.viewModel.RepositoriesState

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 02/09/2020
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

@ExperimentalFoundationApi
@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {

    val headerState by viewModel.headerStateFlow.collectAsState()
    val repositoriesState by viewModel.repositoriesStateFlow.collectAsState()
    val isInitialHeaderState = headerState?.isInitialState == true
    val isInitialRepositoriesState = repositoriesState?.isInitialState == true
    val isInitialState = isInitialHeaderState && isInitialRepositoriesState

    Column(modifier = Modifier.fillMaxWidth()) {
        if (isInitialState) {
            TextView(
                text = LocalContext.current.getString(R.string.welcome_find_profile),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = Title.titleBold,
            )
        } else {
            LazyColumn {
                buildHeaderItem(headerState)
                repositoriesContainer(repositoriesState)
            }
        }
    }
}

fun LazyListScope.buildHeaderItem(headerState: HeaderState?) {
    item {
        when {
            headerState?.refreshing == true -> ProgressBar()
            headerState == null || headerState.errorState -> FindProfileError(message = "error")
            else -> HeaderProfile(profile = headerState.profile)
        }
    }
}

fun LazyListScope.repositoriesContainer(repositoriesState: RepositoriesState?) {
    when {
        repositoriesState?.refreshing == true -> {
            item {
                ProgressBar()
            }
        }
        repositoriesState == null || repositoriesState.errorState -> {
            item {
                FindProfileError(message = "error")
            }
        }
        else -> {
            items(repositoriesState.repositories) { repository ->
                RowRepository(repository = repository)
            }
        }
    }
}

@Composable
fun FindProfileError(message: String?) {

    val errorMessage = message ?: LocalContext.current.getString(R.string.unknown_error)

    TextView(
        text = errorMessage,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = Subtitle.subtitleBold
    )

}