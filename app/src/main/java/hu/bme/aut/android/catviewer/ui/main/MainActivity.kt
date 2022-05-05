package hu.bme.aut.android.catviewer.ui.main
import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint
import androidx.annotation.VisibleForTesting
import androidx.activity.viewModels
import android.os.Bundle
import timber.log.Timber
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.skydoves.landscapist.coil.LocalCoilImageLoader


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @VisibleForTesting
    internal val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO

        Timber.d("MainActivity Started")
        var cl = viewModel.catList
        Timber.d(cl.toString())
        setContent {
            CompositionLocalProvider(LocalCoilImageLoader provides viewModel.imageLoader) {
                CatMainScreen()

            }
        }
    }
}
