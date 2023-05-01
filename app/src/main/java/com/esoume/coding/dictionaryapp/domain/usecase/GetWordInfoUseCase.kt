package com.esoume.coding.dictionaryapp.domain.usecase

import com.esoume.coding.dictionaryapp.domain.model.WordInfo
import com.esoume.coding.dictionaryapp.domain.repository.WordInfoRepository
import com.esoume.coding.dictionaryapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWordInfoUseCase (
    private val repository: WordInfoRepository
) {

    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if(word.isBlank()){
            return flow{}
        }
        return repository.getWordInfo(word)
    }
}