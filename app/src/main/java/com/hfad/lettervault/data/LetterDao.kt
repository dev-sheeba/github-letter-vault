package com.hfad.lettervault.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LetterDao {

    @Query("SELECT * FROM letter_table ORDER BY letterId DESC")
    fun getAllLetters(): Flow<List<Letter>>

    @Query("SELECT * FROM letter_table WHERE lastViewed NOT Null ORDER BY lastViewed DESC")
    fun getLetterSortedByRecent(): Flow<List<Letter>>

    @Query("SELECT * FROM letter_table WHERE reminder NOT Null ORDER BY reminder DESC")
    fun getLetterSortedByReminder(): Flow<List<Letter>>

    @Query("SELECT * FROM letter_table ORDER BY created DESC LIMIT 1")
    fun getLetter():Flow<List<Letter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(letter: Letter)

    @Delete
    suspend fun deleteLetter(letter: Letter)

}