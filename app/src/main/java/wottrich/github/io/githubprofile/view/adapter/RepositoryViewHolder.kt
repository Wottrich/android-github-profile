package wottrich.github.io.githubprofile.view.adapter

import androidx.recyclerview.widget.RecyclerView
import wottrich.github.io.githubprofile.databinding.RowRepositoryBinding
import wottrich.github.io.githubprofile.model.Repository

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */
 
class RepositoryViewHolder(private val binding: RowRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repository: Repository) {
        binding.apply {
            this.repository = repository
        }
    }

}