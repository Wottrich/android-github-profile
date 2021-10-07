package wottrich.github.io.githubprofile.ui.profile.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import github.io.wottrich.ui.values.colorAccent
import github.io.wottrich.ui.values.colorPrimary
import github.io.wottrich.ui.values.onPrimary

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 06/10/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

@Composable
fun HeaderPlaceholder() {
    Row(modifier = Modifier.height(106.dp).padding(all = 10.dp)) {
        Box(
            modifier = Modifier
                .size(86.dp)
                .clip(CircleShape)
                .background(color = colorAccent)
                .placeholder(
                    visible = true,
                    color = colorPrimary,
                    highlight = PlaceholderHighlight.shimmer(highlightColor = onPrimary)
                )

        )
        Column(
            modifier = Modifier.fillMaxHeight().padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.spacerPlaceholder(16.dp, 0.8f))
            Spacer(modifier = Modifier.spacerPlaceholder(14.dp, 0.6f))
            Spacer(modifier = Modifier.spacerPlaceholder(12.dp, 0.5f))
            Spacer(modifier = Modifier.spacerPlaceholder(14.dp, 0.5f))
            Spacer(modifier = Modifier.spacerPlaceholder(14.dp, 0.5f))
        }
    }
}

private fun Modifier.spacerPlaceholder(
    height: Dp,
    fraction: Float = 1f
) = height(height)
    .fillMaxWidth(fraction)
    .placeholder(
        visible = true,
        color = colorPrimary,
        highlight = PlaceholderHighlight.shimmer(highlightColor = onPrimary)
    )