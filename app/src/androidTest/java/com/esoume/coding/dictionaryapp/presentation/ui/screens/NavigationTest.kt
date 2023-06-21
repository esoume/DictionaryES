package com.esoume.coding.dictionaryapp.presentation.ui.screens

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@Suppress("ANNOTATION_TARGETS_NON_EXISTENT_ACCESSOR")
@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTest = createComposeRule()

    @get:Rule
    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTest.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
        }
    }
}