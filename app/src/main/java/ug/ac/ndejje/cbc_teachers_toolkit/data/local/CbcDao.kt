package ug.ac.ndejje.cbc_teachers_toolkit.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CbcDao {
    @Query("SELECT COUNT(*) FROM subjects")
    suspend fun subjectCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjects(items: List<SubjectEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopics(items: List<TopicEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResources(items: List<ResourceEntity>)

    @Query("SELECT * FROM subjects ORDER BY name")
    fun getSubjects(): Flow<List<SubjectEntity>>

    @Query(
        """
        SELECT t.id AS topicId, t.subjectId AS subjectId, s.name AS subjectName, t.title AS topicTitle, t.classLevel AS classLevel
        FROM topics t
        INNER JOIN subjects s ON s.id = t.subjectId
        WHERE t.subjectId = :subjectId AND (:classLevel IS NULL OR t.classLevel = :classLevel)
        ORDER BY t.classLevel, t.title
        """
    )
    fun getTopicsBySubject(subjectId: Int, classLevel: String?): Flow<List<TopicRow>>

    @Query(
        """
        SELECT t.id AS topicId, t.subjectId AS subjectId, s.name AS subjectName, t.title AS topicTitle, t.classLevel AS classLevel
        FROM topics t
        INNER JOIN subjects s ON s.id = t.subjectId
        WHERE t.title LIKE '%' || :query || '%' OR s.name LIKE '%' || :query || '%'
        ORDER BY s.name, t.classLevel, t.title
        """
    )
    fun searchTopics(query: String): Flow<List<TopicRow>>

    @Query(
        """
        SELECT r.id AS resourceId, t.id AS topicId, t.title AS topicTitle, s.name AS subjectName, t.classLevel AS classLevel,
               r.lessonPlan AS lessonPlan, r.projectIdeas AS projectIdeas, r.rubric AS rubric, r.teachingTips AS teachingTips,
               EXISTS(SELECT 1 FROM favorites f WHERE f.resourceId = r.id) AS isFavorite
        FROM resources r
        INNER JOIN topics t ON t.id = r.topicId
        INNER JOIN subjects s ON s.id = t.subjectId
        WHERE t.id = :topicId
        LIMIT 1
        """
    )
    fun getResourceByTopic(topicId: Int): Flow<ResourceDetailRow?>

    @Query(
        """
        SELECT r.id AS resourceId, t.id AS topicId, t.title AS topicTitle, s.name AS subjectName, t.classLevel AS classLevel,
               r.lessonPlan AS lessonPlan, r.projectIdeas AS projectIdeas, r.rubric AS rubric, r.teachingTips AS teachingTips,
               1 AS isFavorite
        FROM favorites f
        INNER JOIN resources r ON r.id = f.resourceId
        INNER JOIN topics t ON t.id = r.topicId
        INNER JOIN subjects s ON s.id = t.subjectId
        ORDER BY s.name, t.classLevel, t.title
        """
    )
    fun getFavoriteResourceDetails(): Flow<List<ResourceDetailRow>>

    @Query("SELECT * FROM notes WHERE resourceId = :resourceId ORDER BY updatedAt DESC")
    fun getNotes(resourceId: Int): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE resourceId = :resourceId)")
    suspend fun isFavorite(resourceId: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE resourceId = :resourceId")
    suspend fun deleteFavorite(resourceId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlannerItem(item: PlannerEntity)

    @Query("SELECT * FROM planner_items ORDER BY id DESC")
    fun getPlannerItems(): Flow<List<PlannerEntity>>
}
