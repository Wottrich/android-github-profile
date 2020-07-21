package wottrich.github.io.githubprofile.archive

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import wottrich.github.io.githubprofile.R

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 21/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl (view: ImageView, imageUrl: String?) {
    if(!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_person_32)
            .into(view)
    }
}