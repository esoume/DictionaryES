package com.esoume.coding.dictionaryapp.presentation.viewmodels

import android.annotation.SuppressLint
import com.esoume.coding.dictionaryapp.domain.repository.FakeSuccessWordInfoRepository
import com.esoume.coding.dictionaryapp.domain.repository.WordInfoRepository
import com.esoume.coding.dictionaryapp.domain.usecase.GetWordInfoUseCase
import com.esoume.coding.dictionaryapp.utils.MainDispatcherRule
import com.esoume.coding.dictionaryapp.utils.Resource
import com.esoume.coding.dictionaryapp.utils.TestUtils
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@OptIn(ExperimentalCoroutinesApi::class)
class WordInfoViewModelTest {

    //https://developer.android.com/kotlin/coroutines/test?hl=fr#injecting-test-dispatchers    @get:Rule
    @get:Rule
    val mainCoroutineRule = MainDispatcherRule()

    // test double only with fake object JUnit4
    private val repository: WordInfoRepository = FakeSuccessWordInfoRepository()
    private lateinit var getWordInfoUseCase: GetWordInfoUseCase
    private lateinit var viewModel : WordInfoViewModel


    // test double only with mock of Mockito
    private val mockRepository: WordInfoRepository = mock(WordInfoRepository::class.java)
    private lateinit var viewModelUnderTest : WordInfoViewModel

    @Before
    fun setUp() {
        getWordInfoUseCase = GetWordInfoUseCase(repository)
        viewModel = WordInfoViewModel(getWordInfoUseCase)

        viewModelUnderTest = WordInfoViewModel(getWordInfoUseCase)
    }

    @Test
    fun `given 3 words and check if success with runTest`() = runTest{
        // Act
        viewModel.onSearch(TestUtils.word1.word)
        advanceUntilIdle() // Yields to perform the registrations
        val state = viewModel.wordInfoUiState.value

        // Assertion with Truth library
        Truth.assertThat(state.wordInfoItems.size).isEqualTo(3)
        Truth.assertThat(state.wordInfoItems[0].word).contains(TestUtils.word1.word)
        Truth.assertThat(state.wordInfoItems[1].word).contains(TestUtils.word2.word)
        Truth.assertThat(state.wordInfoItems[2].word).contains(TestUtils.word3.word)

        // Assertion with JUnit
        assert(state.wordInfoItems.size==3)
        assert(state.wordInfoItems[0].word.contentEquals(TestUtils.word1.word))
        assert(state.wordInfoItems[1].word.contentEquals(TestUtils.word2.word))
        assert(state.wordInfoItems[2].word.contentEquals(TestUtils.word3.word))
    }

    @SuppressLint("CheckResult")
    @Test
    fun `given a word after get success with a list of 3 words version runTest mockito`() = runTest {
        // Given
        `when`(mockRepository.getWordInfo(TestUtils.word1.word)).thenReturn(flow {
            emit(
                Resource.Success(listOf(TestUtils.word1,TestUtils.word2,TestUtils.word3))
            )
        })

        // When
        viewModelUnderTest.onSearch(TestUtils.word1.word)
        advanceUntilIdle()
        val state = viewModelUnderTest.wordInfoUiState.value

        // Then
        assert(state.wordInfoItems.size==3)
        assert(state.wordInfoItems[0].word.contentEquals(TestUtils.word1.word))
        assert(state.wordInfoItems[1].word.contentEquals(TestUtils.word2.word))
        assert(state.wordInfoItems[2].word.contentEquals(TestUtils.word3.word))

    }
}