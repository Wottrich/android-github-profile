package wottrich.github.io.githubprofile.view.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import github.io.wottrich.ui.state.State
import github.io.wottrich.ui.values.Subtitle
import github.io.wottrich.ui.values.Title
import github.io.wottrich.ui.widgets.ProgressBar
import github.io.wottrich.ui.widgets.TextView
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.archive.StateComponent
import wottrich.github.io.githubprofile.archive.stateListComponent
import wottrich.github.io.githubprofile.model.Profile
import wottrich.github.io.githubprofile.model.Repository
import wottrich.github.io.githubprofile.viewModel.ProfileViewModel

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

    val isInitialState =
        headerState.isInitialNotLoading() && repositoriesState.isInitialNotLoading()

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

fun LazyListScope.buildHeaderItem(headerState: State<Profile>) {
    item {
        StateComponent(
            state = headerState,
            initial = {
                ProgressBar()
            },
            failure = {
                FindError(message = it.throwable.message)
            },
            success = {
                HeaderProfile(profile = it)
            }
        )
    }
}

fun LazyListScope.repositoriesContainer(repositoriesState: State<List<Repository>>) {
    stateListComponent(
        state = repositoriesState,
        initial = {
            ProgressBar()
        },
        failure = {
            FindError(message = it.throwable.message)
        },
        success = {
            RowRepository(repository = it)
        }
    )
}

@Composable
fun FindError(message: String?) {

    val errorMessage = message ?: LocalContext.current.getString(R.string.unknown_error)

    TextView(
        text = errorMessage,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = Subtitle.subtitleBold
    )
}