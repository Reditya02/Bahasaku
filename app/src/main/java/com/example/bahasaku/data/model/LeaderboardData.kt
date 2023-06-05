package com.example.bahasaku.data.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class LeaderboardData(
    @PrimaryKey
    var rank: Int,
    var photoUrl: String,
    var name: String,
    var score: String
) : Parcelable {
    companion object {
    }
}
