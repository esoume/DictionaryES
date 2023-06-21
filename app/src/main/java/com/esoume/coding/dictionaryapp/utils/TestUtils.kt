package com.esoume.coding.dictionaryapp.utils

import com.esoume.coding.dictionaryapp.data.local.entity.WordInfoEntity
import com.esoume.coding.dictionaryapp.domain.model.Definition
import com.esoume.coding.dictionaryapp.domain.model.License
import com.esoume.coding.dictionaryapp.domain.model.Meaning
import com.esoume.coding.dictionaryapp.domain.model.WordInfo

object TestUtils {

    val entity1 = getWordInfoEntity().copy(id = 1, word = "house")
    val entity2 = getWordInfoEntity().copy(id = 2, word = "cat")
    val entity3 = getWordInfoEntity().copy(id = 3, word = "dog")

    val word1 = WordInfo(
        license = getLicense(),
        meanings = listOf(getMeaning()),
        phonetic = "phonetic",
        sourceUrls = listOf("toto","cacao"),
        word = "school"
    )

    val word2 = WordInfo(
        license = getLicense(),
        meanings = listOf(getMeaning()),
        phonetic = "phonetic",
        sourceUrls = listOf("toto","cacao"),
        word = "soccer"
    )

    val word3 = WordInfo(
        license = getLicense(),
        meanings = listOf(getMeaning()),
        phonetic = "phonetic",
        sourceUrls = listOf("toto","cacao"),
        word = "flavour"
    )

    fun getDefinition(): Definition = Definition(
        antonyms = listOf("bb","cc","dd"),
        definition = "toto",
        example = "toto",
        synonyms = listOf("aa","tete")
    )

    fun getLicense(): License = License(
        name = "aaa",
        url = "http://ggogle.com"
    )

    fun getMeaning(): Meaning = Meaning(
        antonyms = listOf("coco","dad"),
        definitions = listOf(getDefinition()),
        partOfSpeech = "speech",
        synonyms = listOf("awa","wow")
    )

    fun getSuccessResultOfWordInfos(): Resource<List<WordInfo>>{
        return Resource.Success(listOf(word1, word2, word3))
    }

    fun getErrorsResultOfWordInfos(): Resource<List<WordInfo>>{
        return Resource.Error("Error")
    }

    fun getLoadingState(): Resource<List<WordInfo>>{
        return Resource.Loading()
    }

    fun getWordInfoEntity(): WordInfoEntity = WordInfoEntity(
        license = getLicense(),
        meanings = listOf(getMeaning()),
        phonetic = "phone",
        sourceUrls = listOf("",""),
        word = ""
    )
}