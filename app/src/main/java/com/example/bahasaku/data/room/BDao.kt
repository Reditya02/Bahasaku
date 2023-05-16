package com.example.bahasaku.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.Course
import com.example.bahasaku.data.model.Question
import com.example.bahasaku.data.model.Word

@Dao
interface BDao {
    //Chapter Section
    @Query("select * from chapter where id like '0%'")
    suspend fun getAllChapter(): List<Chapter>

//    @Query("update chapter set done = done + 1 where id like :chapterId")
//    suspend fun updateChapterProgress(chapterId: String)

//    @Query("update chapter set isAvailable = 1 where id like :chapterId")
//    suspend fun makeChapterAvailable(chapterId: String)

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

    //Card Section
    @Query("select * from word where chapterId like :chapterId")
    suspend fun getAllWordById(chapterId: String): List<Word>

    //Dictionary
    @Query("select * from word where upper(balinese) like upper(:query) or upper(indonesian) like upper(:query)")
    suspend fun getWordByQuery(query: String): List<Word>

    //Populate Section
    @Insert(onConflict = REPLACE)
    fun populateChapter(list: List<Chapter>)

    @Insert(onConflict = REPLACE)
    fun populateCourse(list: List<Course>)

    @Insert(onConflict = REPLACE)
    fun populateQuestion(list: List<Question>)

    @Insert(onConflict = REPLACE)
    fun populateWord(list: List<Word>)
}