package com.example.bahasaku.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.Word

@Dao
interface BDao {
    //Chapter Section
    @Query("select * from chapter where id like '0%'")
    suspend fun getAllChapter(): List<Chapter>

    //Card Section
    @Query("select * from word where chapterId like :chapterId")
    suspend fun getAllWordById(chapterId: String): List<Word>

    @Query("select * from chapter where id like :id limit 1")
    suspend fun getWordById(id: String): Chapter

    //Dictionary
    @Query("select * from word where upper(balinese) like upper(:query) or upper(indonesian) like upper(:query)")
    suspend fun getWordByQuery(query: String): List<Word>

    //Populate Section
    @Insert(onConflict = REPLACE)
    fun populateChapter(list: List<Chapter>)

    @Insert(onConflict = REPLACE)
    fun populateWord(list: List<Word>)
}