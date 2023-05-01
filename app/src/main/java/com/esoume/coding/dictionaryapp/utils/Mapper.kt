package com.esoume.coding.dictionaryapp.utils

import com.esoume.coding.dictionaryapp.data.local.entity.WordInfoEntity
import com.esoume.coding.dictionaryapp.data.remote.dto.*
import com.esoume.coding.dictionaryapp.domain.model.*

fun DefinitionDto.toDefinition(): Definition {
    return Definition(
        antonyms = antonyms,
        definition = definition,
        example = example,
        synonyms = synonyms
    )
}

fun LicenseDto.toLicense() =
    License(
        name = name,
        url = url
    )

fun MeaningDto.toMeaning() =
    Meaning(
        antonyms = antonyms,
        definitions = definitions.map { it.toDefinition() },
        partOfSpeech = partOfSpeech,
        synonyms = synonyms
    )

fun PhoneticDto.toPhonetic() =
    Phonetic(
        audio = audio,
        text = text
    )

fun WordInfoDto.toWordInfo() =
    WordInfo(
        phonetic = phonetic,
        sourceUrls = sourceUrls,
        word = word,
        meanings = meanings.map { it.toMeaning() },
        license = license.toLicense()
    )

fun WordInfoEntity.toWordInfo() =
    WordInfo(
        phonetic = phonetic,
        sourceUrls = sourceUrls,
        word = word,
        license = license,
        meanings = meanings
    )

fun WordInfoDto.toWordInfoEntity() =
    WordInfoEntity(
        phonetic = phonetic,
        sourceUrls = sourceUrls,
        word = word,
        meanings = meanings.map { it.toMeaning() },
        license = license.toLicense()
    )