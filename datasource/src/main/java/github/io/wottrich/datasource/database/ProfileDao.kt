package github.io.wottrich.datasource.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import github.io.wottrich.datasource.models.Profile

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 11/11/2021
 *
 * Copyright © 2021 GithubProfile. All rights reserved.
 *
 */

@Dao
interface ProfileDao {

    @Transaction
    @Query("SELECT * FROM profile WHERE login LIKE '%' || :profileLogin || '%'")
    suspend fun getAllProfilesSavedByQuery(profileLogin: String): List<Profile>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profile: Profile): Long?

    @Delete
    suspend fun delete(profile: Profile)

}