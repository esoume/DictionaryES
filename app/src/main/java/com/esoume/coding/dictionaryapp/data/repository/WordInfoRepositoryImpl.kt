package com.esoume.coding.dictionaryapp.data.repository

import android.util.Log
import com.esoume.coding.dictionaryapp.data.local.WordInfoDao
import com.esoume.coding.dictionaryapp.data.remote.api.DictionaryApi
import com.esoume.coding.dictionaryapp.domain.model.WordInfo
import com.esoume.coding.dictionaryapp.domain.repository.WordInfoRepository
import com.esoume.coding.dictionaryapp.utils.Resource
import com.esoume.coding.dictionaryapp.utils.Resource.Loading
import com.esoume.coding.dictionaryapp.utils.toWordInfo
import com.esoume.coding.dictionaryapp.utils.toWordInfoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {

    val TAG: String = "WordInfoRepositoryImpl"

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Loading())

        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Loading(wordInfos))

        try {
            val remoteWordInfos = api.getInfoWord(word)
            dao.deleteWordInfoEntity(remoteWordInfos.map { it.word }.also {
                it.forEach{
                    Log.i(TAG," word : $it")
                }
            })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            Log.i(TAG," HttpException : $e")
            emit(Resource.Error(
                    message = "Something went wrong",
                    data = wordInfos
                )
            )
        } catch (e: IOException) {
            Log.i(TAG," IOException : $e")
            emit(
                Resource.Error(
                message = "Couldn't reach server, check your internet connection",
                data = wordInfos
                )
            )
        } catch (e: Exception) {
            Log.i(TAG," Exception : $e")
            emit(
                Resource.Error(
                message = "Oops problem App",
                data = wordInfos
                )
            )
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}