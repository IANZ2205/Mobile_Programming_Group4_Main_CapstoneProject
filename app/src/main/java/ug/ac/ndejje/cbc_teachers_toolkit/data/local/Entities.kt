package ug.ac.ndejje.cbc_teachers_toolkit.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class SubjectEntity(
    @PrimaryKey val id: Int,
    val name: String
)

@Entity(tableName = "topics")
data class TopicEntity(
    @PrimaryKey val id: Int,
    val subjectId: Int,
    val title: String,
    val classLevel: String
)

@Entity(tableName = "resources")
data class ResourceEntity(
    @PrimaryKey val id: Int,
    val topicId: Int,
    val lessonPlan: String,
    val projectIdeas: String,
    val rubric: String,
    val teachingTips: String
)

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val resourceId: Int
)

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val resourceId: Int,
    val content: String,
    val updatedAt: Long
)

@Entity(tableName = "planner_items")
data class PlannerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val dateText: String,
    val details: String
)
