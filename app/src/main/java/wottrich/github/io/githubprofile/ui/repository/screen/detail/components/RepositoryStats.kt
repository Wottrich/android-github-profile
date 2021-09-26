package wottrich.github.io.githubprofile.ui.repository.screen.detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import github.io.wottrich.datasource.models.Repository
import github.io.wottrich.ui.values.Subtitle
import github.io.wottrich.ui.widgets.TextView
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.ui.shared.RepositoryLanguageContent
import wottrich.github.io.githubprofile.ui.shared.RepositoryStarsContent

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

@Composable
fun RepositoryStats(repository: Repository) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        LanguageCard(language = repository.language, languageColor = repository.languageColor)
        StarsCard(stargazersCount = repository.stargazersCount)
        WatchersCard(watchersCount = repository.watchers)
    }
}

@Composable
private fun RowScope.LanguageCard(language: String?, languageColor: String) {
    if (!language.isNullOrEmpty()) {
        RepositoryCard {
            RepositoryLanguageContent(
                textStyle = Subtitle.subtitleBold,
                language = language,
                languageColor = languageColor
            )
        }
    }
}

@Composable
private fun RowScope.StarsCard(stargazersCount: Int) {
    RepositoryCard {
        RepositoryStarsContent(
            textStyle = Subtitle.subtitleBold,
            stargazersCount = stargazersCount.toString()
        )
    }
}

@Composable
private fun RowScope.WatchersCard(watchersCount: Int) {
    RepositoryCard {
        Icon(
            painter = painterResource(id = R.drawable.ic_watch_eye),
            contentDescription = stringResource(id = R.string.repository_owner_label)
        )
        TextView(
            text = watchersCount.toString(),
            style = Subtitle.subtitleBold
        )
    }
}

@Composable
private fun RowScope.RepositoryCard(content: @Composable RowScope.() -> Unit) {
    val cardModifier =
        Modifier
            .fillMaxWidth()
            .height(32.dp)
            .weight(1f)
            .padding(horizontal = 16.dp)
    Card(modifier = cardModifier) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}