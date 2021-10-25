package com.hfad.lettervault.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LetterRepository @Inject constructor(private val letterDao: LetterDao) {

    val allLetters: Flow<List<Letter>> = letterDao.getAllLetters()

    val getLetter: Flow<List<Letter>> = letterDao.getLetter()

    val getLetterSortedByRecent: Flow<List<Letter>> = letterDao.getLetterSortedByRecent()

    val getLetterSortedByReminder: Flow<List<Letter>> = letterDao.getLetterSortedByReminder()


    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
    suspend fun upsert(letter: Letter) = letterDao.upsert(letter)

    suspend fun delete(letter: Letter) = letterDao.deleteLetter(letter)

}