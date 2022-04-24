package hu.bme.aut.android.catviewer.ui.main
import hu.bme.aut.android.catviewer.network.CatService
import hu.bme.aut.android.catviewer.persistence.CatDao
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val catService: CatService,
    private val catDao: CatDao
) {
    // TODO
}