package com.esoume.coding.dictionaryapp.domain.model

data class WordInfo(
    val license: License,
    val meanings: List<Meaning>,
    val phonetic: String,
    val sourceUrls: List<String>,
    val word: String
)
