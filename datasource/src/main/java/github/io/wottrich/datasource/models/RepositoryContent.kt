package github.io.wottrich.datasource.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 26/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

@Keep
data class RepositoryContent(
    val name: String,
    val path: String,
    val type: RepositoryContentType
)

enum class RepositoryContentType {
    @SerializedName("file")
    FILE,
    @SerializedName("dir")
    DIR
}