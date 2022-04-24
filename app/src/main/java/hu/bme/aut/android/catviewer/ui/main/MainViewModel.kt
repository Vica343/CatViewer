package hu.bme.aut.android.catviewer.ui.main
import hu.bme.aut.android.catviewer.model.Cat
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import coil.ImageLoader

@HiltViewModel
class MainViewModel @Inject constructor(
    val imageLoader: ImageLoader
) : ViewModel() {


    init {
        // TODO
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