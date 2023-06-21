package com.esoume.coding.dictionaryapp.domain.repository

import com.esoume.coding.dictionaryapp.domain.model.WordInfo
import com.esoume.coding.dictionaryapp.utils.Resource
import com.esoume.coding.dictionaryapp.utils.TestUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSuccessWordInfoRepository : WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> {
        return flow {
         emit(TestUtils.getSuccessResultOfWordInfos())
        }
    }
}