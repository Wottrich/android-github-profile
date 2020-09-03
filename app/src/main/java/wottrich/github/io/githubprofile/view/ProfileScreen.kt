package wottrich.github.io.githubprofile.view

import android.graphics.Color
import androidx.annotation.StringRes
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.luca992.compose.image.CoilImage
import wottrich.github.io.githubprofile.data.resource.Status
import wottrich.github.io.githubprofile.model.Profile
import wottrich.github.io.githubprofile.ui.widgets.ProgressBar
import wottrich.github.io.githubprofile.viewModel.ProfileViewModel
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.model.Repository
import wottrich.github.io.githubprofile.ui.values.*
import wottrich.github.io.githubprofile.ui.widgets.TextView

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 02/09/2020
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {

    val profileState = viewModel.profileResult.observeAsState(null)
    val repositoriesState = viewModel.repositoriesResult.observeAsState(null)

    Column(modifier = Modifier.fillMaxWidth()) {

        if (profileState.value == null || repositoriesState.value == null) {
            TextView(
                text = ContextAmbient.current.getString(R.string.welcome_find_profile),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        when (profileState.value?.status) {
            Status.SUCCESS -> {
                val profile = profileState.value?.data
                HeaderProfile(profile = profile)
            }
            Status.LOADING -> ProgressBar()
            Status.ERROR -> Unit
        }

        val repositoriesResult = repositoriesState.value
        when (repositoriesResult?.status) {
            Status.SUCCESS -> {
                LazyColumnFor(
                    items = repositoriesResult.data ?: mutableListOf()
                ) { repository ->
                    RowRepository(repository = repository)
                }
            }
            Status.LOADING -> ProgressBar()
            Status.ERROR -> Unit
        }

    }

}

@Composable
fun HeaderProfile (profile: Profile?) {
    Row(modifier = Modifier.padding(all = 10.dp)) {
        CoilImage(
            model = profile?.avatarUrl ?: "",
            modifier = Modifier.height(86.dp).width(86.dp)
        )
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            TextView(text = profile?.name, style = Title.titleBold)
            TextView(text = profile?.bio, style = Subtitle.subtitleBold)
            TextView(text = profile?.login, style = Description.descriptionRegular)
            FollowLink(
                title = R.string.profile_followers,
                countFollow = profile?.followers
            )
            FollowLink(
                title = R.string.profile_following,
                countFollow = profile?.following
            )
        }
    }
}

@Composable
fun RowRepository (repository: Repository) {

    val cardModifier = Modifier
        .padding(all = 10.dp)
        .clip(shape = RoundedCornerShape(6.dp))
        .fillMaxWidth()

    val columnModifier = Modifier
        .padding(all = 10.dp)
        .fillMaxWidth()

    val rowModifier = Modifier.fillMaxWidth()

    Card(
        modifier = cardModifier
    ) {
        Column(modifier = columnModifier) {
            TextView(text = repository.fullName ?: "", style = Title.titleBold)

            TextView(
                text = ContextAmbient.current.getString(R.string.row_repository_branch_forked),
                isVisible = repository.fork,
                style = Description.descriptionLight
            )

            TextView(
                text = repository.description,
                style = Description.descriptionBold,
                isVisible = repository.description?.isNotEmpty() == true,
                modifier = Modifier.padding(top = 5.dp)
            )


            Row(
                modifier = rowModifier,
                verticalGravity = Alignment.CenterVertically
            ) {
                val color = Color.parseColor(repository.languageColor)
                TextView(
                    text = repository.language,
                    color = ComposeColor(color),
                    style = Description.descriptionBold,
                    isVisible = repository.language?.isNotEmpty() == true,
                    modifier = Modifier.weight(1F)
                )//ComposeColor is Color in compose

                if (repository.stargazersCount != 0) {
                    val image = vectorResource(id = R.drawable.ic_star_border_24)
                    Row(
                        modifier = Modifier.weight(1F),
                        verticalGravity = Alignment.CenterVertically
                    ) {
                        //Image(asset = image)
                        Icon(asset = image)
                        TextView(
                            text = repository.stargazersCount.toString(),
                            style = Description.descriptionBold
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun FollowLink (@StringRes title: Int, countFollow: Int?) {

    Row (horizontalArrangement = Arrangement.SpaceEvenly ) {
        TextView(text = ContextAmbient.current.getString(title), style = Subtitle.subtitleBold)
        TextView(
            modifier = Modifier.padding(start = 10.dp),
            text = countFollow.toString(),
            style = Subtitle.subtitleRegular
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview () {
    Row {
        //HeaderProfile(profile = Profile("Wottrich", "Lucas Cruz Wottrich", "Android/iOS", "https://avatars0.githubusercontent.com/u/24254062?v=4", 10, 10))
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