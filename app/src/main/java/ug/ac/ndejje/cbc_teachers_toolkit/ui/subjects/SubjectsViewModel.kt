package ug.ac.ndejje.cbc_teachers_toolkit.ui.subjects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.Subject
import ug.ac.ndejje.cbc_teachers_toolkit.domain.repository.CbcRepository
import javax.inject.Inject

@HiltViewModel
class SubjectsViewModel @Inject constructor(
    repository: CbcRepository
) : ViewModel() {
    val subjects: StateFlow<List<Subject>> = repository.getSubjects().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )
}
