package ug.ac.ndejje.cbc_teachers_toolkit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

data class TopicItem(
    val id: Int,
    val title: String,
    val subject: String,
    val classLevel: String
)

class SubjectViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<TopicItem>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<TopicItem>>> = _uiState.asStateFlow()

    init {
        loadTopics()
    }

    private fun loadTopics() {
        _uiState.value = UiState.Loading

        val topics = listOf(
            // Biology
            TopicItem(1, "Classification of Living Things", "Biology", "S1"),
            TopicItem(2, "Photosynthesis", "Biology", "S2"),
            TopicItem(3, "Cell Division and Mitosis", "Biology", "S2"),
            TopicItem(4, "Human Reproductive System", "Biology", "S3"),
            TopicItem(5, "Genetics and Heredity", "Biology", "S3"),
            TopicItem(6, "Ecology and Environment", "Biology", "S4"),

            // Mathematics
            TopicItem(7, "Set Theory and Venn Diagrams", "Mathematics", "S1"),
            TopicItem(8, "Linear Equations", "Mathematics", "S2"),
            TopicItem(9, "Quadratic Equations", "Mathematics", "S3"),
            TopicItem(10, "Statistics and Probability", "Mathematics", "S4"),

            // English
            TopicItem(11, "Grammar - Tenses", "English", "S1"),
            TopicItem(12, "Composition Writing", "English", "S2"),
            TopicItem(13, "Literature - Poetry", "English", "S3"),
            TopicItem(14, "Oral Communication", "English", "S4"),

            // Chemistry
            TopicItem(15, "States of Matter", "Chemistry", "S1"),
            TopicItem(16, "Chemical Bonding", "Chemistry", "S2"),
            TopicItem(17, "Acids, Bases and Salts", "Chemistry", "S3"),
            TopicItem(18, "Organic Chemistry", "Chemistry", "S4"),

            // Physics
            TopicItem(19, "Force and Motion", "Physics", "S1"),
            TopicItem(20, "Energy and Work", "Physics", "S2"),
            TopicItem(21, "Electricity", "Physics", "S3"),
            TopicItem(22, "Waves and Sound", "Physics", "S4"),

            // History
            TopicItem(23, "Early Man in East Africa", "History", "S1"),
            TopicItem(24, "Colonial Rule in Uganda", "History", "S2"),
            TopicItem(25, "Uganda's Independence", "History", "S3"),
            TopicItem(26, "Political Systems", "History", "S4"),

            // Geography
            TopicItem(27, "Map Reading", "Geography", "S1"),
            TopicItem(28, "Climate of Uganda", "Geography", "S2"),
            TopicItem(29, "Population and Settlement", "Geography", "S3"),
            TopicItem(30, "Natural Resources", "Geography", "S4"),

            // Entrepreneurship
            TopicItem(31, "Business Ideas", "Entrepreneurship", "S1"),
            TopicItem(32, "Business Planning", "Entrepreneurship", "S2"),
            TopicItem(33, "Marketing", "Entrepreneurship", "S3"),
            TopicItem(34, "Financial Management", "Entrepreneurship", "S4"),

            // Agriculture
            TopicItem(35, "Crop Production", "Agriculture", "S1"),
            TopicItem(36, "Soil Management", "Agriculture", "S2"),
            TopicItem(37, "Livestock Farming", "Agriculture", "S3"),
            TopicItem(38, "Agribusiness", "Agriculture", "S4"),

            // ICT
            TopicItem(39, "Computer Hardware", "ICT", "S1"),
            TopicItem(40, "Microsoft Office", "ICT", "S2"),
            TopicItem(41, "Internet and Cyber Safety", "ICT", "S3"),
            TopicItem(42, "Basic Programming", "ICT", "S4")
        )

        _uiState.value = UiState.Success(topics)
    }
}