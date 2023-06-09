package com.example.bahasaku.model.repository

import com.example.bahasaku.model.Populate
import com.example.bahasaku.model.room.BDao
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