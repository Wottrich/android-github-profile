package wottrich.github.io.githubprofile.ui.repository.screen.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import github.io.wottrich.datasource.models.Profile
import github.io.wottrich.datasource.models.Repository
import github.io.wottrich.ui.state.State
import github.io.wottrich.ui.state.StateComponent
import github.io.wottrich.ui.widgets.CircularProgress
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import wottrich.github.io.githubprofile.ui.repository.screen.detail.components.RepositoryInformation
import wottrich.github.io.githubprofile.ui.repository.screen.detail.components.RepositoryOwner
import wottrich.github.io.githubprofile.ui.repository.screen.detail.components.RepositoryPullRequests
import wottrich.github.io.githubprofile.ui.repository.screen.detail.components.RepositoryStats

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 25/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

@Composable
fun RepositoryScreen(
    profileLogin: String,
    repositoryName: String,
    viewModel: RepositoryScreenViewModel = getViewModel {
        parametersOf(profileLogin, repositoryName)
    }
) {

    val repositoryState by viewModel.repositoryState.collectAsState()
    val scrollState = rememberScrollState()

    RepositoryStateComponent(repositoryState = repositoryState, scrollState = scrollState)
}

@Composable
private fun RepositoryStateComponent(
    repositoryState: State<Repository>,
    scrollState: ScrollState
) {
    StateComponent(
        state = repositoryState,
        initial = {
            CircularProgress()
        },
        failure = {

        },
        success = {
            Column(
                modifier = Modifier
                    .scrollable(
                        state = scrollState,
                        orientation = Orientation.Vertical
                    )
                    .padding(vertical = 16.dp)
            ) {

                RepositoryInformation(name = it.name, description = it.description)

                Divider()

                RepositoryOwner(owner = it.owner)

                Divider()

                RepositoryStats(repository = it)

                Divider()

                RepositoryPullRequests(pullRequestCount = it.openPullRequestCount)

                Divider()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RepositoryScreenPreview() {
    RepositoryStateComponent(
        State.success(
            Repository(
                "NetunoNavigationPod",
                "Wottrich/NetunoNavigationPod",
                "",
                "\uD83D\uDD31 NetunoNavigationPod \uD83D\uDD31 - To take navigation to the next level (MIT License)",
                "",
                "Kotlin",
                true,
                10,
                watchers = 1,
                openPullRequestCount = 1,
                owner = Profile(
                    "Wottrich",
                    "Lucas Cruz Wottrich",
                    "Android/iOS",
                    "",
                    10,
                    10
                )
            )
        ),
        ScrollState(0)
    )
}