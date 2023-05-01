package com.esoume.coding.dictionaryapp.data.remote.api

import com.esoume.coding.dictionaryapp.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getInfoWord(
        @Path("word") word: String
    ): List<WordInfoDto>
}