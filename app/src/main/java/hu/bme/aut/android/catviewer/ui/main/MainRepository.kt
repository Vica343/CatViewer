package hu.bme.aut.android.catviewer.ui.main

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import hu.bme.aut.android.catviewer.model.db.CatEntity
import hu.bme.aut.android.catviewer.network.CatService
import hu.bme.aut.android.catviewer.persistence.CatDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val catService: CatService,
    private val catDao: CatDao
) {
    init {
        Timber.d("Injection MainRepository")
    }

    @WorkerThread
    fun loadCatImages(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        while (true) {
            val cats: List<CatEntity> = catDao.getCatList().reversed();
            val randomcats = cats.filter { cat -> cat.uploaded == false && cat.favorite == false }
            if (cats.size < 15) {
                // request API network call asynchronously.
                catService.fetchCatList()
                    // handle the case when the API request gets a success response.
                    .suspendOnSuccess {
                        val catdata: List<CatEntity> = data.map { list ->
                            CatEntity(null, list.id, list.url, false, false)
                        }
                        catDao.insertCatList(catdata)
                        emit(catdata)
                    }
                    // handle the case when the API request is fails.
                    // e.g. internal server error.
                    .onFailure { onError(this) }
            } else {
                emit(cats)
            }
        }
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

    @WorkerThread
    suspend fun AddToFavorite(
        id: Int?,
        value: Boolean
    ) {
        catDao.updateFavorite(id, value)
    }

    @WorkerThread
    suspend fun RefreshImages(
    ) {
        catDao.refreshCatListDelete()
    }

    @WorkerThread
    suspend fun UploadImage(
        url: String,
    ) {
        catDao.insertCat(CatEntity(null, "0", url, true, false))
    }

}