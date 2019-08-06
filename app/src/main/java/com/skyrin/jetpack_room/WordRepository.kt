package com.skyrin.jetpack_room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class WordRepository (private val wordDao: WordDao){
    val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    @WorkerThread
    suspend fun insert(word: Word){
        wordDao.insert(word)
    }

    @WorkerThread
    suspend fun delete(word: Word){
        wordDao.delete(word)
    }
}