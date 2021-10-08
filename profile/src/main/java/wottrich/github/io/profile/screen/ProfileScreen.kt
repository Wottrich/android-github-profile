package wottrich.github.io.githubprofile.ui.profile.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import github.io.wottrich.datasource.models.Profile
import github.io.wottrich.datasource.models.Repository
import github.io.wottrich.ui.values.Subtitle
import github.io.wottrich.ui.values.Title
import github.io.wottrich.ui.values.backgroundColor
import github.io.wottrich.ui.values.colorPrimary
import github.io.wottrich.ui.widgets.TextView
import wottrich.github.io.base.state.ScreenStateComponent
import wottrich.github.io.base.state.screenStateListComponent
import wottrich.github.io.profile.HeaderPlaceholder
import wottrich.github.io.profile.HeaderProfile
import wottrich.github.io.profile.ProfileViewModel
import wottrich.github.io.profile.R
import wottrich.github.io.profile.RowRepository
import wottrich.github.io.screenstate.ScreenState

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
fun ProfileScreen(viewModel: ProfileViewModel, onRepositoryClick: (Repository) -> Unit) {
    val profileState by viewModel.profileState.collectAsState()
    val isInitialState = profileState.isInitial()

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
                buildHeaderItem(profileState.headerState)
                repositoriesContainer(profileState.repositoriesState, onRepositoryClick)
            }
        }
    }
}

fun LazyListScope.buildHeaderItem(headerState: ScreenState<Profile>) {
    item {
        ScreenStateComponent(
            state = headerState,
            initial = {
                HeaderPlaceholder()
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

fun LazyListScope.repositoriesContainer(
    repositoriesState: ScreenState<List<Repository>>,
    onRepositoryClick: (Repository) -> Unit
) {
    repositoriesStickyHeader(repositoriesState)
    screenStateListComponent(
        state = repositoriesState,
        initial = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = backgroundColor)
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.repositories_loading_label),
                    style = Title.titleBold
                )
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = colorPrimary
                )
            }
        },
        failure = {
            FindError(message = it.throwable.message)
        },
        success = {
            RowRepository(repository = it, onRepositoryClick = onRepositoryClick)
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

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.repositoriesStickyHeader(repositoriesState: ScreenState<List<Repository>>) {
    if (repositoriesState.isSuccess()) {
        stickyHeader {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = backgroundColor)
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(
                        id = R.string.repositories_count,
                        repositoriesState.success.size
                    ),
                    style = Title.titleBold
                )
            }
        }
    }
}