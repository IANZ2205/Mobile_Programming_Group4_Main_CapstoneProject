package ug.ac.ndejje.cbc_teachers_toolkit.domain.model

data class Subject(
    val id: Int,
    val name: String
)

data class Topic(
    val id: Int,
    val subjectId: Int,
    val subjectName: String,
    val title: String,
    val classLevel: String
)

data class ResourceDetail(
    val resourceId: Int,
    val topicId: Int,
    val topicTitle: String,
    val subjectName: String,
    val classLevel: String,
    val lessonPlan: String,
    val projectIdeas: String,
    val rubric: String,
    val teachingTips: String,
    val isFavorite: Boolean
)

data class SearchResult(
    val topicId: Int,
    val topicTitle: String,
    val subjectName: String,
    val classLevel: String
)

data class TeacherNote(
    val id: Int,
    val resourceId: Int,
    val content: String,
    val updatedAt: Long
)

data class PlannerItem(
    val id: Int,
    val title: String,
    val dateText: String,
    val details: String
)
