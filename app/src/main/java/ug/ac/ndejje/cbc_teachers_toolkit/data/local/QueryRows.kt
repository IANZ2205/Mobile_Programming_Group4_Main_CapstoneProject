package ug.ac.ndejje.cbc_teachers_toolkit.data.local

data class TopicRow(
    val topicId: Int,
    val subjectId: Int,
    val subjectName: String,
    val topicTitle: String,
    val classLevel: String
)

data class ResourceDetailRow(
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
