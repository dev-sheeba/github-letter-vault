package com.hfad.lettervault

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.hfad.lettervault.data.LetterDatabase
import com.hfad.lettervault.data.LetterRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class LetterApplication: Application() {


//    private val applicationScope = CoroutineScope(SupervisorJob())
//
//    private val database by lazy { LetterDatabase.getDatabase(this, applicationScope) }
//
//    val repository by lazy { LetterRepository(database.letterDao()) }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")