package com.example.bahasaku.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.Course
import com.example.bahasaku.data.model.Question

@Dao
interface BDao {
    //Chapter Section
    @Query("select * from chapter")
    suspend fun getAllChapter(): List<Chapter>

    @Query("update chapter set progress = :progress where id like :chapterId")
    suspend fun updateChapterProgress(chapterId: String, progress: Float)

    @Query("update chapter set isAvailable = 1 where id like :chapterId")
    suspend fun makeChapterAvailable(chapterId: String)

    //Course Section
    @Query("select * from course where chapterId like :chapterId")
    suspend fun getAllCourse(chapterId: String): List<Course>

    @Query("update course set score = :score where id like :courseId")
    suspend fun updateCourseScore(courseId: String, score: Int)

    @Query("update course set isDone = 1 where id like :courseId")
    suspend fun updateCourseDone(courseId: String)

    @Query("update course set isAvailable = 1 where id like :courseId")
    suspend fun updateCourseAvailable(courseId: String)

    //Question Section
    @Query("select * from question where courseId like :courseId")
    suspend fun getAllQuestion(courseId: String): List<Question>

    //Populate Section
    @Insert(onConflict = REPLACE)
    fun populateChapter(list: List<Chapter>)

    @Insert(onConflict = REPLACE)
    fun populateCourse(list: List<Course>)

    @Insert(onConflict = REPLACE)
    fun populateQuestion(list: List<Question>)
}