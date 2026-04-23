package ug.ac.ndejje.cbc_teachers_toolkit.ui.resource

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.ResourceDetail
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.TeacherNote
import ug.ac.ndejje.cbc_teachers_toolkit.domain.repository.CbcRepository
import javax.inject.Inject

@HiltViewModel
class ResourceDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: CbcRepository
) : ViewModel() {
    private val topicId: Int = checkNotNull(savedStateHandle["topicId"])

    val detail: StateFlow<ResourceDetail?> = repository.getResource(topicId).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        null
    )

    val notes: StateFlow<List<TeacherNote>> = repository.getResource(topicId)
        .flatMapLatest { resource ->
            if (resource == null) kotlinx.coroutines.flow.flowOf(emptyList())
            else repository.getNotes(resource.resourceId)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun toggleFavorite() {
        viewModelScope.launch {
            detail.value?.let { repository.toggleFavorite(it.resourceId) }
        }
    }

    fun addNote(content: String) {
        if (content.isBlank()) return
        viewModelScope.launch {
            detail.value?.let { repository.addNote(it.resourceId, content) }
        }
    }
}
