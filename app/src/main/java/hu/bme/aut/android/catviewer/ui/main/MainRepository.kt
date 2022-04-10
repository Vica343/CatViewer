package hu.bme.aut.android.catviewer.ui.main
import hu.bme.aut.android.catviewer.network.CatService
import hu.bme.aut.android.catviewer.persistence.CatDao
import javax.inject.Inject
import hu.bme.aut.android.catviewer.model.Cat
import androidx.lifecycle.LiveData
import hu.bme.aut.android.catviewer.model.Favorite
import androidx.annotation.WorkerThread


class MainRepository @Inject constructor(
    private val catService: CatService,
    private val catDao: CatDao
) {
    // TODO
}