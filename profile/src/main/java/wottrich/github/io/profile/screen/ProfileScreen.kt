package wottrich.github.io.profile.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.OutlinedButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import wottrich.github.io.base.state.ScreenStateComponent
import wottrich.github.io.base.state.screenStateListComponent
import wottrich.github.io.profile.HeaderPlaceholder
import wottrich.github.io.profile.HeaderProfile
import wottrich.github.io.profile.ProfileEffects
import wottrich.github.io.profile.ProfileEffects.EqualUser
import wottrich.github.io.profile.ProfileViewModel
import wottrich.github.io.profile.R
import wottrich.github.io.profile.R.string
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
fun ProfileScreen(
    scaffoldState: ScaffoldState,
    viewModel: ProfileViewModel,
    onRepositoryClick: (Repository) -> Unit,
    onTryAgain: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val profileState by viewModel.profileState.collectAsState()
    val effects = viewModel.effects
    val isInitialState = profileState.isInitial()
    val shouldShowFullError = profileState.shouldShowFullError()

    Effects(effects, coroutineScope, scaffoldState)

    Column(modifier = Modifier.fillMaxWidth()) {
        if (isInitialState) {
            TextView(
                text = LocalContext.current.getString(R.string.welcome_find_profile),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = Title.titleBold,
            )
        } else {
            if (shouldShowFullError) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = string.profile_screen_profile_not_found),
                        style = Title.titleBold
                    )
                    OutlinedButton(
                        onClick = onTryAgain,
                        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = backgroundColor)
                    ) {
                        Text(
                            text = stringResource(
                                id = string.profile_screen_profile_not_found_button_try_again
                            )
                        )
                    }
                }
            } else {
                LazyColumn {
                    buildHeaderItem(profileState.headerState)
                    repositoriesContainer(profileState.repositoriesState, onRepositoryClick)
                }
            }
        }
    }
}

@Composable
private fun Effects(
    effects: Flow<ProfileEffects>,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    val equalUserErrorMessage = stringResource(id = string.equal_login_error)
    LaunchedEffect(key1 = effects) {
        effects.collect {
            when (it) {
                is EqualUser -> coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(equalUserErrorMessage)
                }
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
            cached = {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Data cached, trying fetch from network")
                    }
                    HeaderProfile(profile = it)
                }
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