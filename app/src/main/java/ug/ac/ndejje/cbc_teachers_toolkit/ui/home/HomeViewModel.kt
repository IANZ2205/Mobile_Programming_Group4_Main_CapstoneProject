package ug.ac.ndejje.cbc_teachers_toolkit.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.SearchResult
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.Subject
import ug.ac.ndejje.cbc_teachers_toolkit.domain.repository.CbcRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CbcRepository
) : ViewModel() {
    data class UiState(
        val query: String = ""
    )

    private val uiState = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = uiState

    val subjects: StateFlow<List<Subject>> = repository.getSubjects().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )

    val results: StateFlow<List<SearchResult>> = uiState.flatMapLatest { s ->
        if (s.query.isBlank()) {
            kotlinx.coroutines.flow.flowOf(emptyList())
        } else {
            repository.searchTopics(s.query.trim())
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    init {
        viewModelScope.launch { repository.ensureSeeded() }
    }

    fun onQueryChange(newValue: String) {
        uiState.update { it.copy(query = newValue) }
    }
}
