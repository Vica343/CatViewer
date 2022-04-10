package hu.bme.aut.android.catviewer.ui.main
import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint
import androidx.annotation.VisibleForTesting
import androidx.activity.viewModels
import android.os.Bundle

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @VisibleForTesting
    internal val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO
    }
}
