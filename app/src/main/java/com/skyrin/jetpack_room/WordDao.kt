package com.skyrin.jetpack_room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {
    @Insert
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(word: Word)

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAllWords() : LiveData<List<Word>>
}