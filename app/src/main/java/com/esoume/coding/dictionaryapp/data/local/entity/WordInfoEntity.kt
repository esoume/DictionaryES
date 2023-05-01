package com.esoume.coding.dictionaryapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.esoume.coding.dictionaryapp.domain.model.License
import com.esoume.coding.dictionaryapp.domain.model.Meaning

@Entity
data class WordInfoEntity(
    val license: License,
    val meanings: List<Meaning>,
    val phonetic: String,
    val sourceUrls: List<String>,
    val word: String,
    @PrimaryKey (autoGenerate = true) val id: Int? = null
)