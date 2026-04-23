package ug.ac.ndejje.cbc_teachers_toolkit.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.ResourceDetail
import ug.ac.ndejje.cbc_teachers_toolkit.domain.repository.CbcRepository
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    repository: CbcRepository
) : ViewModel() {
    val favorites: StateFlow<List<ResourceDetail>> = repository.getFavoriteResources().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )
}
