package hu.bme.aut.android.catviewer.ui.about
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.catviewer.BuildConfig


@HiltViewModel
class AboutViewModel @Inject constructor(
) : ViewModel(){
    val copyright = "App version v." + BuildConfig.VERSION_NAME + "\nAll rights reserved\n2022 \u00a9"
}