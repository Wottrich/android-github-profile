package wottrich.github.io.repository.screen.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import github.io.wottrich.ui.components.SubtitleRow
import github.io.wottrich.ui.components.TitleRow
import github.io.wottrich.ui.values.onPrimary
import wottrich.github.io.repository.R

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

private val pullRequestBoxColor = Color(0xFF589143)

@Composable
fun RepositoryPullRequests(pullRequestCount: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(color = pullRequestBoxColor)
                    .padding(4.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pull_request),
                    contentDescription = stringResource(id = R.string.pull_request_count),
                    tint = onPrimary
                )
            }
            TitleRow(
                text = stringResource(id = R.string.pull_request_label),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        SubtitleRow(text = pullRequestCount.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun RepositoryPullRequestCountPreview() {
    RepositoryPullRequests(pullRequestCount = 10)
}