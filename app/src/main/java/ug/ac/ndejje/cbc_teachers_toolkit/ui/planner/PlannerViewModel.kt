package ug.ac.ndejje.cbc_teachers_toolkit.ui.planner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.PlannerItem
import ug.ac.ndejje.cbc_teachers_toolkit.domain.repository.CbcRepository
import javax.inject.Inject

@HiltViewModel
class PlannerViewModel @Inject constructor(
    private val repository: CbcRepository
) : ViewModel() {
    val plannerItems: StateFlow<List<PlannerItem>> = repository.getPlannerItems().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )

    fun addPlan(title: String, dateText: String, details: String) {
        if (title.isBlank() || dateText.isBlank()) return
        viewModelScope.launch {
            repository.addPlannerItem(title, dateText, details)
        }
    }
}
