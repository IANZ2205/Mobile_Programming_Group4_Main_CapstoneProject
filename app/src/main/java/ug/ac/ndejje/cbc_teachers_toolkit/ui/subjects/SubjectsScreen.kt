package ug.ac.ndejje.cbc_teachers_toolkit.ui.subjects

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SubjectsScreen(
    onSubjectClick: (Int) -> Unit,
    viewModel: SubjectsViewModel = hiltViewModel()
) {
    val subjects by viewModel.subjects.collectAsStateWithLifecycle()
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Select a Subject", style = MaterialTheme.typography.headlineSmall)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(subjects, key = { it.id }) { subject ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSubjectClick(subject.id) }
                ) { Text(subject.name, modifier = Modifier.padding(16.dp)) }
            }
        }
    }
}
