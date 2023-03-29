package com.example.bahasaku.data

import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.room.BDao
import javax.inject.Inject

class Repository @Inject constructor(
    private val dao: BDao
) {
    suspend fun getAllChapter() = dao.getAllChapter()

    suspend fun getAllCourse(chapterId: String) = dao.getAllCourse(chapterId)
}