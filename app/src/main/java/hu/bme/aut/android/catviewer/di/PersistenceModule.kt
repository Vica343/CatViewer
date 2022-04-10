package hu.bme.aut.android.catviewer.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Provides
import javax.inject.Singleton
import android.app.Application
import androidx.room.Room
import hu.bme.aut.android.catviewer.persistence.AppDatabase
import hu.bme.aut.android.catviewer.persistence.CatDao

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application) : AppDatabase{
        return Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                "CatViewerDB"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCatDao(appDatabase: AppDatabase): CatDao {
        // TODO
        return CatDao()
    }

}