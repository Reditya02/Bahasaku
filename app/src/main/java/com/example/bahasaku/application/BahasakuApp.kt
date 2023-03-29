package com.example.bahasaku.application

import android.app.Application
import com.example.bahasaku.data.Repository
import com.example.bahasaku.data.room.BDatabase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class BahasakuApp : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { BDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { Repository(database.dao()) }

}