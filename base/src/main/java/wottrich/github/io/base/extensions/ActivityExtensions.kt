package wottrich.github.io.base.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 25/09/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

inline fun <reified T: Activity> Activity?.startActivity(
    finishActualActivity: Boolean = false,
    options: Bundle? = null,
    setupIntent: Intent.() -> Unit = {}
) {
    val intent = Intent(this, T::class.java)
    setupIntent(intent)
    this?.startActivity(intent, options)
    if (finishActualActivity) {
        this?.finish()
    }
}