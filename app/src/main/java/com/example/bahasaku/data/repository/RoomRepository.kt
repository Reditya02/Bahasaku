package com.example.bahasaku.data.repository

import com.example.bahasaku.data.Populate
import com.example.bahasaku.data.room.BDao
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val dao: BDao
) {
    suspend fun getAllChapter() = dao.getAllChapter()

    suspend fun getAllWordById(chapterId: String) = dao.getAllWordById(chapterId)

    suspend fun getAllWord(query: String) = dao.getWordByQuery(query)

    suspend fun getWordById(id: String) = dao.getWordById(id)

    fun populateDatabase() {
        dao.apply {
            populateChapter(Populate.populateChapter)
        }
    }
}