package wottrich.github.io.githubprofile.view.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luca992.compose.image.CoilImage
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.model.Profile
import wottrich.github.io.githubprofile.ui.values.Description
import wottrich.github.io.githubprofile.ui.values.Subtitle
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
fun HeaderProfile (profile: Profile?) {
    Row(modifier = Modifier.padding(all = 10.dp)) {
        CoilImage(
            model = if (profile?.avatarUrl?.isNotEmpty() == true)  profile.avatarUrl else R.drawable.ic_person_32,
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
fun FollowLink (@StringRes title: Int, countFollow: Int?) {

    Row (horizontalArrangement = Arrangement.SpaceEvenly ) {
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
fun HeaderProfilePreview () {
    HeaderProfile(profile = Profile("Wottrich", "Lucas Cruz Wottrich", "Android/iOS", "", 10, 10))
}