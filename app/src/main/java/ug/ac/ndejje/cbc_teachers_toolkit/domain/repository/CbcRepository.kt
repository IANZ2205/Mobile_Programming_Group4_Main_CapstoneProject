package ug.ac.ndejje.cbc_teachers_toolkit.domain.repository

import kotlinx.coroutines.flow.Flow
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.PlannerItem
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.ResourceDetail
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.SearchResult
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.Subject
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.TeacherNote
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.Topic

interface CbcRepository {
    suspend fun ensureSeeded()
    fun getSubjects(): Flow<List<Subject>>
    fun getTopicsBySubject(subjectId: Int, classLevel: String?): Flow<List<Topic>>
    fun searchTopics(query: String): Flow<List<SearchResult>>
    fun getResource(topicId: Int): Flow<ResourceDetail?>
    fun getFavoriteResources(): Flow<List<ResourceDetail>>
    fun getNotes(resourceId: Int): Flow<List<TeacherNote>>
    suspend fun toggleFavorite(resourceId: Int)
    suspend fun addNote(resourceId: Int, content: String)
    suspend fun addPlannerItem(title: String, dateText: String, details: String)
    fun getPlannerItems(): Flow<List<PlannerItem>>
}
