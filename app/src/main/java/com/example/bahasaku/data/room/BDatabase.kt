package com.example.bahasaku.data.room

import android.content.Context
import android.util.Log
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bahasaku.data.Populate
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Chapter::class, Word::class], version = 1)
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
                                        Log.d("Reditya", "populate DB")
                                        bDao.populateChapter(Populate.populateChapter)
                                        bDao.populateWord(Populate.populateWord)
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