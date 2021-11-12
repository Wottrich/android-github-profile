package github.io.wottrich.datasource.injection

import github.io.wottrich.datasource.database.AppDatabase
import github.io.wottrich.datasource.database.ProfileDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/11/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

val databaseModules = module {

    single<ProfileDao> { AppDatabase.getInstance(androidContext()).profileDao() }

}