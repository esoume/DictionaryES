package com.esoume.coding.dictionaryapp.domain.model

data class WordInfoUIState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
