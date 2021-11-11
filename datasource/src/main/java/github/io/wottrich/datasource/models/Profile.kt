package github.io.wottrich.datasource.models

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 10/07/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

@Entity(tableName = "profile")
@Keep
@Parcelize
data class Profile (
    @PrimaryKey
    val login: String,
    val name: String,
    val bio: String,
    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val followers: Int,
    val following: Int
) : Parcelable