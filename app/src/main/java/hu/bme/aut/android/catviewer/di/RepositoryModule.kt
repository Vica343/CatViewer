package hu.bme.aut.android.catviewer.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.Provides
import dagger.hilt.android.scopes.ViewModelScoped
import hu.bme.aut.android.catviewer.network.CatService
import hu.bme.aut.android.catviewer.persistence.CatDao
import hu.bme.aut.android.catviewer.ui.main.MainRepository
import timber.log.Timber
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        catService: CatService,
        catDao: CatDao
    ): MainRepository {
        Timber.d("MainRepository inicialized")
        return  MainRepository(catService, catDao)
    }

}