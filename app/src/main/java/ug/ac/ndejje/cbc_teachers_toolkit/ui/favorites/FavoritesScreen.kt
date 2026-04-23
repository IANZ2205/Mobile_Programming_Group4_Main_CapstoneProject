package ug.ac.ndejje.cbc_teachers_toolkit.ui.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ug.ac.ndejje.cbc_teachers_toolkit.ui.export.PdfExporter

@Composable
fun FavoritesScreen(
    onOpenResource: (Int) -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val favorites by viewModel.favorites.collectAsStateWithLifecycle()
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("My Favorites", style = MaterialTheme.typography.headlineSmall)
        Button(
            onClick = {
                val lines = favorites.map { "${it.subjectName} - ${it.topicTitle}" }
                PdfExporter.exportNotesSummary(context, lines)
            }
        ) {
            Text("Export Favorites as PDF")
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(favorites, key = { it.resourceId }) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onOpenResource(item.topicId) }
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(item.topicTitle, style = MaterialTheme.typography.titleMedium)
                        Text("${item.subjectName} • ${item.classLevel}")
                    }
                }
            }
        }
    }
}
