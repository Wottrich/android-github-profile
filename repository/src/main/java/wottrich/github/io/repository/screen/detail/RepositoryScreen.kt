package wottrich.github.io.repository.screen.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import github.io.wottrich.datasource.models.Profile
import github.io.wottrich.datasource.models.Repository
import github.io.wottrich.datasource.models.RepositoryContent
import github.io.wottrich.datasource.models.RepositoryContentType
import github.io.wottrich.ui.widgets.CircularProgress
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import wottrich.github.io.base.state.ScreenStateComponent
import wottrich.github.io.repository.screen.detail.components.*
import wottrich.github.io.screenstate.ScreenState

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
    },
    onRepositoryContentClick: (String) -> Unit
) {

    val repositoryState by viewModel.repositoryState.collectAsState()
    val contentsState by viewModel.repositoryContentState.collectAsState()

    RepositoryStateComponent(
        repositoryState = repositoryState,
        contentsState = contentsState,
        onContentClick = onRepositoryContentClick
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RepositoryStateComponent(
    repositoryState: ScreenState<Repository>,
    contentsState: ScreenState<List<RepositoryContent>>,
    onContentClick: (String) -> Unit
) {
    ScreenStateComponent(
        state = repositoryState,
        initial = {
            CircularProgress()
        },
        failure = {

        },
        success = {
            LazyColumn {

                stickyHeader {
                    RepositoryInformation(
                        name = it.name,
                        description = it.description
                    )
                }

                item {
                    Divider()
                    RepositoryOwner(owner = it.owner)
                    Divider()
                    RepositoryStats(repository = it)
                    Divider()
                    RepositoryPullRequests(pullRequestCount = it.openPullRequestCount)
                    Divider()
                }

                repositoryContentsStickHeader(contentsState.isInitial())
                repositoryContents(
                    contentsState = contentsState,
                    onContentClick = onContentClick
                )

            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RepositoryScreenPreview() {
    RepositoryStateComponent(
        ScreenState.success(
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
        ScreenState.success(
            listOf(
                RepositoryContent(name = "dir", path = "dir", type = RepositoryContentType.DIR),
                RepositoryContent(name = "file", path = "file", type = RepositoryContentType.FILE)
            )
        )
    ) {}
}