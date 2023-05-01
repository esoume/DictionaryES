package com.esoume.coding.dictionaryapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.esoume.coding.dictionaryapp.data.local.WordInfoDao
import com.esoume.coding.dictionaryapp.data.local.entity.WordInfoEntity
import com.esoume.coding.dictionaryapp.utils.Converters

@Database(
    entities = [WordInfoEntity ::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase: RoomDatabase() {

    abstract fun wordInfoDao(): WordInfoDao
}