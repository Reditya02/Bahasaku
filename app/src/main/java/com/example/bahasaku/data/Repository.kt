package com.example.bahasaku.data

import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.room.BDao
import javax.inject.Inject

class Repository @Inject constructor(
    private val dao: BDao
) {
    suspend fun getAllChapter() = dao.getAllChapter()

    suspend fun getAllCourse(chapterId: String) = dao.getAllCourse(chapterId)

    suspend fun getAllQuestion(courseId: String) = dao.getAllQuestion(courseId)

    suspend fun getAllWordById(chapterId: String) = dao.getAllWordById(chapterId)

    fun populateDatabase() {
        dao.apply {
            populateChapter(Populate.populateChapter)
            populateCourse(Populate.populateCourse)
        }
    }
}