package wottrich.github.io.githubprofile.view.widgets

import android.graphics.Color
import androidx.compose.foundation.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope.weight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.model.Repository
import wottrich.github.io.githubprofile.ui.values.Description
import wottrich.github.io.githubprofile.ui.values.Title
import wottrich.github.io.githubprofile.ui.widgets.TextView

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 04/09/2020
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

@Composable
fun RowRepository (repository: Repository) {

    val cardModifier = Modifier
        .padding(all = 10.dp)
        .clip(shape = RoundedCornerShape(6.dp))
        .fillMaxWidth()

    val columnModifier = Modifier
        .padding(all = 10.dp)
        .fillMaxWidth()

    Card(
        modifier = cardModifier
    ) {
        Column(modifier = columnModifier) {

            //====> repository name
            TextView(text = repository.fullName ?: "", style = Title.titleBold)

            //====> fork
            TextView(
                text = ContextAmbient.current.getString(R.string.row_repository_branch_forked),
                isVisible = repository.fork,
                style = Description.descriptionLight
            )

            //====> description
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
private fun RepositoryLanguageAndStars (repository: Repository) {

    val rowModifier = Modifier.fillMaxWidth()

    Row(
        modifier = rowModifier,
        verticalGravity = Alignment.CenterVertically
    ) {

        //====> language
        val color = Color.parseColor(repository.languageColor)
        TextView(
            text = repository.language,
            color = ComposeColor(color),//ComposeColor is Color in compose
            style = Description.descriptionBold,
            isVisible = repository.language?.isNotEmpty() == true,
            modifier = Modifier.weight(1F)
        )

        //====> stars
        RepositoryStars(repository = repository)
    }
}

@Composable
private fun RepositoryStars (repository: Repository) {
    if (repository.stargazersCount != 0) {
        val image = vectorResource(id = R.drawable.ic_star_border_24)
        Row(
            modifier = Modifier.weight(1F),
            verticalGravity = Alignment.CenterVertically
        ) {
            Icon(asset = image)
            TextView(
                text = repository.stargazersCount.toString(),
                style = Description.descriptionBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultRowRepositoriesPreview () {
    Row {
        RowRepository(
            repository = Repository(
                "Wottrich/NetunoNavigationPod",
                "",
                "\uD83D\uDD31 NetunoNavigationPod \uD83D\uDD31 - To take navigation to the next level (MIT License)",
                "",
                "",
                "Kotlin",
                true,
                10
            )
        )
    }
}