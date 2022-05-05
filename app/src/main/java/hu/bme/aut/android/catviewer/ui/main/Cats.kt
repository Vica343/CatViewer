package hu.bme.aut.android.catviewer.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import hu.bme.aut.android.catviewer.model.db.CatEntity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.statusBarsPadding
import hu.bme.aut.android.catviewer.utils.NetworkImage

@Composable
fun Cats(
    viewModel: MainViewModel
) {
    val posters: List<CatEntity> by viewModel.catList.collectAsState(initial = listOf())
    val isLoading: Boolean by viewModel.isLoading

    Column(
    ) {
            posters.forEach { poster ->
                HomePoster(
                    poster = poster)
        }
    }

}

@Composable
private fun HomePoster(
    poster: CatEntity,
) {
    Surface(

    ) {
        ConstraintLayout {
            val (image, title, content) = createRefs()
            NetworkImage(
                modifier = Modifier
                    .aspectRatio(0.8f)
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    },
                url = poster.url,
            )
        }
    }
}