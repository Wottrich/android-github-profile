package wottrich.github.io.githubprofile.ui.repository.screen.detail.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import github.io.wottrich.datasource.models.RepositoryContent
import github.io.wottrich.datasource.models.RepositoryContentType
import github.io.wottrich.ui.components.SubtitleRow
import github.io.wottrich.ui.components.TitleRow
import github.io.wottrich.ui.state.State
import github.io.wottrich.ui.state.stateListComponent
import github.io.wottrich.ui.values.Roboto
import github.io.wottrich.ui.values.backgroundColor
import github.io.wottrich.ui.values.colorPrimary
import github.io.wottrich.ui.values.onPrimary
import github.io.wottrich.ui.widgets.CircularProgress
import github.io.wottrich.ui.widgets.TextView
import wottrich.github.io.githubprofile.R

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.repositoryContents(contentsState: State<List<RepositoryContent>>) {
    stickyHeader {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = backgroundColor)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .background(color = colorPrimary)
                        .padding(4.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_file_code),
                        contentDescription = stringResource(id = R.string.pull_request_count),
                        tint = onPrimary
                    )
                }
                TitleRow(
                    text = stringResource(id = R.string.repository_archives),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            if (contentsState.isInitial()) {
                CircularProgress()
            }
        }
    }
    stateListComponent(
        state = contentsState,
        initial = {},
        failure = {
            Row(horizontalArrangement = Arrangement.Center) {
                SubtitleRow(text = stringResource(id = R.string.repository_archives_not_loaded))
            }
        },
        success = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                BuildContentIcon(
                    name = it.name,
                    repositoryContentType = it.type
                )
                TextView(
                    modifier = Modifier.padding(start = 8.dp),
                    text = it.name,
                    style = MaterialTheme.typography.subtitle1.copy(fontFamily = Roboto)
                )
            }
        }
    )
}

@Composable
private fun BuildContentIcon(name: String, repositoryContentType: RepositoryContentType) {
    when (repositoryContentType) {
        RepositoryContentType.FILE -> Icon(
            painter = painterResource(id = R.drawable.ic_file),
            contentDescription = stringResource(
                id = R.string.repository_file_icon_content_description,
                name
            ),
            tint = colorPrimary
        )
        RepositoryContentType.DIR -> Icon(
            painter = painterResource(id = R.drawable.ic_dir),
            contentDescription = stringResource(
                id = R.string.repository_dir_icon_content_description,
                name
            ),
            tint = colorPrimary
        )
    }

}

@Preview
@Composable
fun RepositoryContentsPreview() {
    LazyColumn {
        repositoryContents(
            State.success(
                listOf(
                    RepositoryContent(name = "dir", path = "dir", type = RepositoryContentType.DIR),
                    RepositoryContent(name = "file", path = "file", type = RepositoryContentType.FILE)
                )
            )
        )
    }
}
