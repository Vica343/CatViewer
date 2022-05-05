package hu.bme.aut.android.catviewer.ui.main
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import hu.bme.aut.android.catviewer.model.Cat
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import coil.ImageLoader
import hu.bme.aut.android.catviewer.model.db.CatEntity
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import androidx.annotation.StringRes
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf


@HiltViewModel
class MainViewModel @Inject constructor(
    val imageLoader: ImageLoader,
    mainRepository: MainRepository
) : ViewModel() {

    val catList: Flow<List<CatEntity>> =
        mainRepository.loadCatImages(
            onStart = { _isLoading.value = true },
            onCompletion = { _isLoading.value = false },
            onError = { Timber.d(it) }
        )

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    init {
        Timber.d("injection MainViewModel")

    }

    fun uploadImage(cat: Cat) = viewModelScope.launch(Dispatchers.IO) {
        // TODO
    }

    fun deleteImage(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        // TODO
    }

    fun addFavorite(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        // TODO
    }

    fun deleteFavorite(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        // TODO
    }

}