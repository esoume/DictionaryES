package com.esoume.coding.dictionaryapp.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.esoume.coding.dictionaryapp.data.local.db.WordInfoDatabase
import com.esoume.coding.dictionaryapp.data.local.entity.WordInfoEntity
import com.esoume.coding.dictionaryapp.utils.Converters
import com.esoume.coding.dictionaryapp.utils.GsonParser
import com.esoume.coding.dictionaryapp.utils.MainDispatcherRule
import com.esoume.coding.dictionaryapp.utils.TestUtils
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
@OptIn(ExperimentalCoroutinesApi::class)
class WordInfoDaoTest {

    @get:Rule
    val mainCoroutineRule = MainDispatcherRule()

    private lateinit var database: WordInfoDatabase
    private lateinit var wordInfoDao: WordInfoDao
    private val converters = Converters(GsonParser(Gson()))

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WordInfoDatabase::class.java
        )
            .addTypeConverter(converters)
            .allowMainThreadQueries().build()

        wordInfoDao = database.wordInfoDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertWordInfos() = runTest {
        // create 3 words and insert in database
        val entities = ArrayList<WordInfoEntity>()
        val entity1 = TestUtils.entity1
        val entity2 = TestUtils.entity2
        val entity3 = TestUtils.entity3

        entities.add(entity1)
        entities.add(entity2)
        entities.add(entity3)
        // Insert
        wordInfoDao.insertWordInfos(entities)

        assert(wordInfoDao.getWordInfos(TestUtils.entity1.word).contains(entity1))
    }

    @Test
    fun getWordInfoEntity() = runTest {
        wordInfoDao.insertWordInfos(listOf(TestUtils.entity1))
        val result = wordInfoDao.getWordInfos(TestUtils.entity1.word)

        assert(result.isNotEmpty())
        assert(result[0].word.contentEquals(TestUtils.entity1.word))
    }

    @Test
    fun deleteWordInfoEntity() = runTest {
        wordInfoDao.deleteWordInfoEntity(
            listOf(
                TestUtils.entity1.word,
                TestUtils.entity2.word,
                TestUtils.entity3.word,
            )
        )

        assert(!wordInfoDao.getWordInfos(TestUtils.entity1.word).contains(TestUtils.entity1))
        assert(!wordInfoDao.getWordInfos(TestUtils.entity2.word).contains(TestUtils.entity2))
        assert(!wordInfoDao.getWordInfos(TestUtils.entity3.word).contains(TestUtils.entity3))
        assert(wordInfoDao.getWordInfos(TestUtils.entity1.word).isEmpty())
    }
}