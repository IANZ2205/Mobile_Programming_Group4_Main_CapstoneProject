package ug.ac.ndejje.cbc_teachers_toolkit.ui.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("About CBC Teaching Toolkit", style = MaterialTheme.typography.headlineSmall)
        Text(
            "This app supports Ugandan secondary school teachers with offline CBC lesson plans, project ideas, assessment rubrics and practical teaching tips."
        )
        Text("Developed for Ndejje University Capstone Project 2025/2026.")
    }
}
