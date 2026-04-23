package ug.ac.ndejje.cbc_teachers_toolkit.ui.resource

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ResourceDetailScreen(viewModel: ResourceDetailViewModel = hiltViewModel()) {
    val detail by viewModel.detail.collectAsStateWithLifecycle()
    val notes by viewModel.notes.collectAsStateWithLifecycle()
    var noteText by remember { mutableStateOf("") }

    val d = detail ?: return
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(d.topicTitle, style = MaterialTheme.typography.headlineSmall)
            Text("${d.subjectName} • ${d.classLevel}")
        }
        item {
            IconButton(onClick = viewModel::toggleFavorite) {
                Icon(
                    imageVector = if (d.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite"
                )
            }
        }
        item { SectionCard("Lesson Plan", d.lessonPlan) }
        item { SectionCard("Project Ideas", d.projectIdeas) }
        item { SectionCard("Assessment Rubric", d.rubric) }
        item { SectionCard("Teaching Tips", d.teachingTips) }
        item {
            OutlinedTextField(
                value = noteText,
                onValueChange = { noteText = it },
                label = { Text("Write a private note") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            Button(onClick = {
                viewModel.addNote(noteText)
                noteText = ""
            }) { Text("Save Note") }
        }
        item { Text("Saved Notes", style = MaterialTheme.typography.titleMedium) }
        items(notes, key = { it.id }) { note ->
            Card { Text(note.content, modifier = Modifier.padding(12.dp)) }
        }
    }
}

@Composable
private fun SectionCard(title: String, content: String) {
    Card {
        Column(Modifier.padding(12.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(4.dp))
            Text(content)
        }
    }
}
