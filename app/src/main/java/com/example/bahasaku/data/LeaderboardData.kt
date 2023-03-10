package com.example.bahasaku.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LeaderboardData(
    var rank: Int,
    var photoUrl: String,
    var name: String,
    var score: String
) : Parcelable {
    companion object {
        val getListDummy: List<LeaderboardData>
            get() {
                val data = mutableListOf<LeaderboardData>()
                for (i in 0..10) {
                    data.add(
                        LeaderboardData(
                            rank = i+1,
                            photoUrl = "https://loremflickr.com/320/240",
                            name = "John Doe $i",
                            score = "${100 - i}"
                        )
                    )
                }
                return data
            }
    }
}
