package com.example.bahasaku.data.room

import android.content.Context
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bahasaku.data.Populate
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.Course
import com.example.bahasaku.data.model.Question
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Chapter::class, Course::class, Question::class], version = 1)
abstract class BDatabase : RoomDatabase() {
    abstract fun dao(): BDao

    companion object {
        @Volatile
        private var INSTANCE: BDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context, applicationScope: CoroutineScope): BDatabase {
            if (INSTANCE == null) {
                synchronized(BDatabase::class.java) {
                    INSTANCE = Room
                        .databaseBuilder(
                            context.applicationContext,
                            BDatabase::class.java,
                            "bahasaku"
                        )
                        .fallbackToDestructiveMigration()
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                INSTANCE?.let {
                                    applicationScope.launch {
                                        val bDao = it.dao()
                                        bDao.populateChapter(Populate.populateChapter)
                                        bDao.populateCourse(Populate.populateCourse)
                                    }
                                }
                            }
                        })
                        .build()
                }
            }
            return INSTANCE as BDatabase
        }
    }
}