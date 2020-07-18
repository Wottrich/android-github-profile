package wottrich.github.io.githubprofile.archive

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 12/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

object TransformationsUtils {

    fun isVisible (liveDataToObserve: LiveData<*>, condition: Boolean?) : LiveData<Int> {
        return Transformations.map(liveDataToObserve) {
            if(condition == true) View.VISIBLE else View.GONE
        }
    }

}