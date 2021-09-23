package wottrich.github.io.githubprofile.view.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import github.io.wottrich.ui.values.Description
import github.io.wottrich.ui.values.Subtitle
import github.io.wottrich.ui.values.Title
import github.io.wottrich.ui.widgets.TextView
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.model.Profile

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 04/09/2020
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

/*
Collapsing layout example
@Composable
fun HeaderProfile(profile: Profile?, lazyListState: LazyListState?) {

    var scrolledY by remember {
        mutableStateOf(0f)
    }
    var previousOffset by remember {
        mutableStateOf(0)
    }

    Row(
        modifier = Modifier
            .padding(all = 10.dp)
            .graphicsLayer {
                val itemScrollOffset = lazyListState?.firstVisibleItemScrollOffset ?: 0
                scrolledY += itemScrollOffset - previousOffset
                translationY = scrolledY * 0.5f
                previousOffset = itemScrollOffset
            }
    ) {
        BuildProfileImage(avatarUrl = profile?.avatarUrl)
        BuildProfileInformation(profile = profile)
    }
}
 */

@Composable
fun HeaderProfile(profile: Profile?) {
    Row(modifier = Modifier.padding(all = 10.dp)) {
        BuildProfileImage(avatarUrl = profile?.avatarUrl)
        BuildProfileInformation(profile = profile)
    }
}

@Composable
private fun BuildProfileImage(avatarUrl: String?) {
    if (!avatarUrl.isNullOrEmpty()) {
        Image(
            modifier = Modifier.size(86.dp),
            painter = rememberImagePainter(
                data = avatarUrl,
                imageLoader = LocalImageLoader.current,
                builder = {
                    crossfade(true)
                    placeholder(
                        drawableResId = R.drawable.ic_person_32
                    )
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = "avatar image",
        )
    }
}

@Composable
private fun BuildProfileInformation(profile: Profile?) {
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

@Composable
fun FollowLink(@StringRes title: Int, countFollow: Int?) {

    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        TextView(text = LocalContext.current.getString(title), style = Subtitle.subtitleBold)
        TextView(
            modifier = Modifier.padding(start = 10.dp),
            text = countFollow.toString(),
            style = Subtitle.subtitleRegular
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderProfilePreview() {
    HeaderProfile(
        profile = Profile(
            "Wottrich",
            "Lucas Cruz Wottrich",
            "Android/iOS",
            "",
            10,
            10
        )
    )
}