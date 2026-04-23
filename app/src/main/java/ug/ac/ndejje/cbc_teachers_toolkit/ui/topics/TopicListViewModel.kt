package ug.ac.ndejje.cbc_teachers_toolkit.ui.topics

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.Topic
import ug.ac.ndejje.cbc_teachers_toolkit.domain.repository.CbcRepository
import javax.inject.Inject

@HiltViewModel
class TopicListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: CbcRepository
) : ViewModel() {
    data class UiState(
        val classLevel: String? = null,
        val query: String = ""
    )

    private val subjectId: Int = checkNotNull(savedStateHandle["subjectId"])
    private val filters = MutableStateFlow(UiState())

    val state: StateFlow<UiState> = filters

    private val topicsBase = filters.flatMapLatest { filter ->
        repository.getTopicsBySubject(subjectId, filter.classLevel)
    }

    val topics: StateFlow<List<Topic>> = combine(topicsBase, filters) { topics, filter ->
        val q = filter.query.trim()
        if (q.isBlank()) topics else topics.filter { it.title.contains(q, ignoreCase = true) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun onClassLevelChange(level: String?) {
        filters.update { it.copy(classLevel = level) }
    }

    fun onQueryChange(value: String) {
        filters.update { it.copy(query = value) }
    }
}
