package ug.ac.ndejje.cbc_teachers_toolkit.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        SubjectEntity::class,
        TopicEntity::class,
        ResourceEntity::class,
        FavoriteEntity::class,
        NoteEntity::class,
        PlannerEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CbcDatabase : RoomDatabase() {
    abstract fun cbcDao(): CbcDao
}
