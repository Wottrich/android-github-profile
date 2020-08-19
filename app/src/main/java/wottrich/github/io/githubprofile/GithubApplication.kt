package wottrich.github.io.githubprofile

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import wottrich.github.io.githubprofile.di.appModule

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 19/08/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */
 
class GithubApplication : Application () {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@GithubApplication)
            modules(appModule)
        }
    }

}