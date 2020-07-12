package wottrich.github.io.githubprofile.model

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.archive.colorLanguages

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */
 
data class Repository(
    @SerializedName("full_name") val fullName: String?,
    @SerializedName("html_url") val htmlUrl: String?,
    val description: String?,
    val url: String?,//Details
    @SerializedName("updated_at") val updatedAt: String?,
    val language: String?,
    val fork: Boolean?,
    @SerializedName("stargazers_count") val stargazersCount: Int
) {

    //val img: Drawable = this.resources.getDrawable(R.drawable.ic_circular_24, theme)

    val languageColor: String
        get() {
            val color = "#a3a3a3"
            return if(colorLanguages?.containsKey(language) == true) {
                colorLanguages[language] ?: color
            } else {
                color
            }
        }

}