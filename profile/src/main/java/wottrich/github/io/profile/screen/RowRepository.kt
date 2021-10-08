package wottrich.github.io.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import github.io.wottrich.datasource.models.Profile
import github.io.wottrich.datasource.models.Repository
import github.io.wottrich.ui.values.Description
import github.io.wottrich.ui.values.Title
import github.io.wottrich.ui.widgets.TextView
import wottrich.github.io.profilerepositorysharedcomponents.RepositoryLanguageContent
import wottrich.github.io.profilerepositorysharedcomponents.RepositoryStarsContent

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 04/09/2020
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

@Composable
fun RowRepository(repository: Repository, onRepositoryClick: (Repository) -> Unit) {

    val cardModifier = Modifier
        .padding(all = 10.dp)
        .fillMaxWidth()

    val columnModifier = Modifier
        .fillMaxWidth()
        .clickable { onRepositoryClick(repository) }
        .padding(all = 10.dp)


    Card(
        modifier = cardModifier
    ) {
        Column(modifier = columnModifier) {

            TextView(text = repository.fullName ?: "", style = Title.titleBold)

            TextView(
                text = LocalContext.current.getString(R.string.row_repository_branch_forked),
                isVisible = repository.fork == true,
                style = Description.descriptionLight
            )

            TextView(
                text = repository.description,
                style = Description.descriptionBold,
                isVisible = repository.description?.isNotEmpty() == true,
                modifier = Modifier.padding(top = 5.dp)
            )

            RepositoryLanguageAndStars(repository = repository)

        }
    }

}

@Composable
private fun RepositoryLanguageAndStars(repository: Repository) {

    val rowModifier = Modifier.fillMaxWidth()

    Row(
        modifier = rowModifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BuildLanguageContent(repository = repository)
        RepositoryStars(repository = repository)
    }
}

@Composable
private fun RowScope.BuildLanguageContent(repository: Repository) {
    val language = repository.language
    if (!language.isNullOrEmpty()) {
        RepositoryLanguageContent(
            modifier = Modifier.weight(1F),
            textStyle = Description.descriptionBold,
            language = language,
            languageColor = repository.languageColor
        )
    }
}

@Composable
private fun RowScope.RepositoryStars(repository: Repository) {
    if (repository.stargazersCount != 0) {
        Row(
            modifier = Modifier.weight(1F),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RepositoryStarsContent(
                textStyle = Description.descriptionBold,
                stargazersCount = repository.stargazersCount.toString()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultRowRepositoriesPreview() {
    Row {
        RowRepository(
            repository = Repository(
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
        ) {

        }
    }
}