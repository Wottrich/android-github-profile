package wottrich.github.io.profilerepositorysharedcomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import github.io.wottrich.ui.widgets.TextView


/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

@Composable
fun RepositoryLanguageContent(
    modifier: Modifier = Modifier,
    textStyle: TextStyle,
    language: String,
    languageColor: String
) {
    val color = android.graphics.Color.parseColor(languageColor)
    TextView(
        text = language,
        color = Color(color),
        style = textStyle,
        modifier = modifier
    )
}

@Composable
fun RepositoryStarsContent(
    textStyle: TextStyle,
    stargazersCount: String
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_star_border_24),
        contentDescription = stringResource(id = R.string.start_content_description)
    )
    TextView(
        text = stargazersCount,
        style = textStyle
    )
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun BuildProfileImage(avatarUrl: String?, imageSize: Dp) {
    if (!avatarUrl.isNullOrEmpty()) {
        Image(
            modifier = Modifier.size(imageSize),
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