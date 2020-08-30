package wottrich.github.io.githubprofile.archive

import okhttp3.ResponseBody
import org.json.JSONObject

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 14/08/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

fun ResponseBody?.getErrorMessage(errorKey: String): String? {
    this?.charStream()?.readText()?.takeIf { it.contains(errorKey) }?.let {
        return JSONObject(it)[errorKey] as? String
    }
    return null
}