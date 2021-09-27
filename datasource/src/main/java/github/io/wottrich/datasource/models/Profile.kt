package github.io.wottrich.datasource.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

@Keep
data class Profile (
    val login: String,
    val name: String,
    val bio: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val followers: Int,
    val following: Int
)