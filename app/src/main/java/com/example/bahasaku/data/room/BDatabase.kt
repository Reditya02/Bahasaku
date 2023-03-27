package com.example.bahasaku.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.Course
import com.example.bahasaku.data.model.Question

@Database(entities = [Chapter::class, Course::class, Question::class], version = 1)
abstract class BDatabase : RoomDatabase() {
    abstract fun dao(): BDao

    companion object {
        @Volatile
        private var INSTANCE: BDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): BDatabase {
            if (INSTANCE == null) {
                synchronized(BDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BDatabase::class.java,
                        "chapter"
                    ).build()
                }
            }
            return INSTANCE as BDatabase
        }
    }
}