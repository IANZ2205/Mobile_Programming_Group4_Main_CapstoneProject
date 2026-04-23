package ug.ac.ndejje.cbc_teachers_toolkit.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ug.ac.ndejje.cbc_teachers_toolkit.data.local.CbcDao
import ug.ac.ndejje.cbc_teachers_toolkit.data.local.CbcDatabase
import ug.ac.ndejje.cbc_teachers_toolkit.data.repository.CbcRepositoryImpl
import ug.ac.ndejje.cbc_teachers_toolkit.domain.repository.CbcRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CbcDatabase =
        Room.databaseBuilder(
            context,
            CbcDatabase::class.java,
            "cbc_toolkit.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideDao(database: CbcDatabase): CbcDao = database.cbcDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(impl: CbcRepositoryImpl): CbcRepository
}
