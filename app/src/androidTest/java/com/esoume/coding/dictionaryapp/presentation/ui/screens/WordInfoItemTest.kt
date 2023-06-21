package com.esoume.coding.dictionaryapp.presentation.ui.screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.esoume.coding.dictionaryapp.domain.model.WordInfo
import com.esoume.coding.dictionaryapp.utils.TestTags
import com.esoume.coding.dictionaryapp.utils.TestUtils
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class WordInfoItemTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun getWordInfoAndDisplayedCorrectly(){
        val wordInfo = WordInfo(
            license = TestUtils.getLicense(),
            meanings = listOf(TestUtils.getMeaning()),
            phonetic = "phonetic",
            sourceUrls = listOf("https://developer.android.com/codelabs/jetpack-compose-testing?hl=fr#2"),
            word = "Test"
        )
        composeRule.setContent {
            WordInfoItem(wordInfo = wordInfo)
        }

        // Assertion with semantics tree
        composeRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")
        composeRule.onNode(hasText(wordInfo.word),true).assertExists()
        composeRule.onNodeWithText(wordInfo.phonetic).assertIsDisplayed()

        // Assertion with modifier.testTag method
        composeRule.onNodeWithTag(TestTags.TITLE_TAG,useUnmergedTree = true).assertExists()
        composeRule.onNode(hasTestTag(TestTags.PHONETIC_TAG) and
            hasText(wordInfo.phonetic), useUnmergedTree = true)
            .assertIsDisplayed()
        composeRule.onNodeWithTag(TestTags.SOURCE_URL_TAG).assertIsDisplayed()

        //Thread.sleep(5000)
    }
}