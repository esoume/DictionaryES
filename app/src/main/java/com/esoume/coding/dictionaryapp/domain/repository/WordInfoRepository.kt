package com.esoume.coding.dictionaryapp.domain.repository

import com.esoume.coding.dictionaryapp.domain.model.WordInfo
import com.esoume.coding.dictionaryapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository{

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}