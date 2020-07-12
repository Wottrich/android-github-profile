package wottrich.github.io.githubprofile.view.adapter

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_repository.view.*
import wottrich.github.io.githubprofile.R
import wottrich.github.io.githubprofile.model.Repository

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */
 
class RepositoryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(repository: Repository) {
        view.apply {

            tvFullName.text = repository.fullName
            tvForked.isVisible = repository.fork ?: false

            tvDescription.text = repository.description
            tvDescription.isVisible = !repository.description.isNullOrEmpty()

            tvLanguage.text = repository.language
            tvLanguage.setTextColor(Color.parseColor(repository.languageColor))
            tvLanguage.isVisible = !repository.language.isNullOrEmpty()

            tvStars.text = String.format(context.getString(R.string.format_repositories_stars), repository.stargazersCount)
            tvStars.isVisible = repository.stargazersCount != 0

        }
    }

}