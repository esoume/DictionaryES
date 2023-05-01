package com.esoume.coding.dictionaryapp.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esoume.coding.dictionaryapp.domain.model.WordInfoUIState
import com.esoume.coding.dictionaryapp.domain.usecase.GetWordInfoUseCase
import com.esoume.coding.dictionaryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfoUseCase: GetWordInfoUseCase
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _wordInfoUiState = mutableStateOf(WordInfoUIState())
    val wordInfoUiState: State<WordInfoUIState> = _wordInfoUiState

    private val _uiEvent = MutableSharedFlow<UIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getWordInfoUseCase(query)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _wordInfoUiState.value = WordInfoUIState().copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _wordInfoUiState.value = WordInfoUIState().copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _uiEvent.emit(UIEvent.ShowSnackBar(result.message ?: "Unknown Error"))
                        }
                        is Resource.Loading -> {
                            _wordInfoUiState.value = WordInfoUIState().copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                        else -> {}
                    }
                }.launchIn(this)
        }
    }

    // Ui state for event display SnackBar
    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }
}