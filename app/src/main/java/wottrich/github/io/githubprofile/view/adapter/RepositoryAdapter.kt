package wottrich.github.io.githubprofile.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
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
 
class RepositoryAdapter(
    context: Context,
    private val repositories: LiveData<List<Repository>>,
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
): RecyclerView.Adapter<RepositoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            layoutInflater.inflate(R.layout.row_repository, parent, false)
        )
    }

    override fun getItemCount(): Int = repositories.value.orEmpty().size

    private fun getItem (position: Int) : Repository? {
        return repositories.value?.get(position)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

}