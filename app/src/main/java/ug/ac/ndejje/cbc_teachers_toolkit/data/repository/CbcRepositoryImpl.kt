package ug.ac.ndejje.cbc_teachers_toolkit.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ug.ac.ndejje.cbc_teachers_toolkit.data.local.CbcDao
import ug.ac.ndejje.cbc_teachers_toolkit.data.local.FavoriteEntity
import ug.ac.ndejje.cbc_teachers_toolkit.data.local.NoteEntity
import ug.ac.ndejje.cbc_teachers_toolkit.data.local.PlannerEntity
import ug.ac.ndejje.cbc_teachers_toolkit.data.local.SeedData
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.PlannerItem
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.ResourceDetail
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.SearchResult
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.Subject
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.TeacherNote
import ug.ac.ndejje.cbc_teachers_toolkit.domain.model.Topic
import ug.ac.ndejje.cbc_teachers_toolkit.domain.repository.CbcRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CbcRepositoryImpl @Inject constructor(
    private val dao: CbcDao
) : CbcRepository {

    override suspend fun ensureSeeded() {
        if (dao.subjectCount() == 0) {
            dao.insertSubjects(SeedData.subjects)
            dao.insertTopics(SeedData.topics)
            dao.insertResources(SeedData.resources)
        }
    }

    override fun getSubjects(): Flow<List<Subject>> =
        dao.getSubjects().map { items -> items.map { Subject(it.id, it.name) } }

    override fun getTopicsBySubject(subjectId: Int, classLevel: String?): Flow<List<Topic>> =
        dao.getTopicsBySubject(subjectId, classLevel).map { rows ->
            rows.map {
                Topic(
                    id = it.topicId,
                    subjectId = it.subjectId,
                    subjectName = it.subjectName,
                    title = it.topicTitle,
                    classLevel = it.classLevel
                )
            }
        }

    override fun searchTopics(query: String): Flow<List<SearchResult>> =
        dao.searchTopics(query).map { rows ->
            rows.map {
                SearchResult(
                    topicId = it.topicId,
                    topicTitle = it.topicTitle,
                    subjectName = it.subjectName,
                    classLevel = it.classLevel
                )
            }
        }

    override fun getResource(topicId: Int): Flow<ResourceDetail?> =
        dao.getResourceByTopic(topicId).map { row ->
            row?.let {
                ResourceDetail(
                    resourceId = it.resourceId,
                    topicId = it.topicId,
                    topicTitle = it.topicTitle,
                    subjectName = it.subjectName,
                    classLevel = it.classLevel,
                    lessonPlan = it.lessonPlan,
                    projectIdeas = it.projectIdeas,
                    rubric = it.rubric,
                    teachingTips = it.teachingTips,
                    isFavorite = it.isFavorite
                )
            }
        }

    override fun getFavoriteResources(): Flow<List<ResourceDetail>> =
        dao.getFavoriteResourceDetails().map { rows ->
            rows.map {
                ResourceDetail(
                    resourceId = it.resourceId,
                    topicId = it.topicId,
                    topicTitle = it.topicTitle,
                    subjectName = it.subjectName,
                    classLevel = it.classLevel,
                    lessonPlan = it.lessonPlan,
                    projectIdeas = it.projectIdeas,
                    rubric = it.rubric,
                    teachingTips = it.teachingTips,
                    isFavorite = true
                )
            }
        }

    override fun getNotes(resourceId: Int): Flow<List<TeacherNote>> =
        dao.getNotes(resourceId).map { items ->
            items.map { TeacherNote(it.id, it.resourceId, it.content, it.updatedAt) }
        }

    override suspend fun toggleFavorite(resourceId: Int) {
        if (dao.isFavorite(resourceId)) {
            dao.deleteFavorite(resourceId)
        } else {
            dao.insertFavorite(FavoriteEntity(resourceId))
        }
    }

    override suspend fun addNote(resourceId: Int, content: String) {
        dao.insertNote(
            NoteEntity(
                resourceId = resourceId,
                content = content.trim(),
                updatedAt = System.currentTimeMillis()
            )
        )
    }

    override suspend fun addPlannerItem(title: String, dateText: String, details: String) {
        dao.insertPlannerItem(
            PlannerEntity(
                title = title.trim(),
                dateText = dateText.trim(),
                details = details.trim()
            )
        )
    }

    override fun getPlannerItems(): Flow<List<PlannerItem>> =
        dao.getPlannerItems().map { items ->
            items.map { PlannerItem(it.id, it.title, it.dateText, it.details) }
        }
}
