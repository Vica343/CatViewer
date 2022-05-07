package hu.bme.aut.android.catviewer.ui.main
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.FileUtils
import android.provider.MediaStore
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
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.distinctUntilChanged
import java.io.File


@HiltViewModel
class MainViewModel @Inject constructor(
    val imageLoader: ImageLoader,
    mainRepository: MainRepository
) : ViewModel() {

    var catList: Flow<List<CatEntity>> =
        mainRepository.loadCatImages(
            onStart = { _isLoading.value = true },
            onCompletion = { _isLoading.value = false },
            onError = { Timber.d(it) }
        )

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    private val _isRefreshing: MutableState<Boolean> = mutableStateOf(false)


    val isLoading: State<Boolean> get() = _isLoading
    val isRefreshing: State<Boolean> get() = _isRefreshing


    private val repository : MainRepository

    init {
        Timber.d("injection MainViewModel")
        repository = mainRepository

    }

    fun addFavorite(id: Int?, value : Boolean) = viewModelScope.launch(Dispatchers.IO){
        viewModelScope.launch {
            repository.AddToFavorite(id, value)
        }
    }

    fun refreshImages() = viewModelScope.launch(Dispatchers.IO){
        viewModelScope.launch {
            repository.RefreshImages()
        }
    }

    fun uploadImage(uri: Uri?, context: Context) = viewModelScope.launch(Dispatchers.IO) {
        viewModelScope.launch {
            repository.UploadImage(uri, context)
        }
    }

    fun deleteImage(id: Int?) = viewModelScope.launch(Dispatchers.IO) {
        viewModelScope.launch {
            repository.DeleteImage(id)
        }
    }

}