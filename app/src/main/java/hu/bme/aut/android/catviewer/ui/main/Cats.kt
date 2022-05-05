package hu.bme.aut.android.catviewer.ui.main

import androidx.compose.ui.graphics.Color
import hu.bme.aut.android.catviewer.model.db.CatEntity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import hu.bme.aut.android.catviewer.utils.NetworkImage

@Composable
fun Cats(
    viewModel: MainViewModel
) {
    val cats: List<CatEntity> by viewModel.catList.collectAsState(initial = listOf())
    val isLoading: Boolean by viewModel.isLoading
    val isRefreshing: Boolean by viewModel.isRefreshing

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.refreshImages() },
    ){

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 10.dp)
            .fillMaxSize()

    )
    {
        TopAppBar(title = { Text(text = "CatViewer") })
            cats.forEach { cat ->
                Cat(cat = cat, viewModel)
            }
    }

}}

@Composable
private fun Cat(
    cat: CatEntity,
    viewModel: MainViewModel
) {
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 10.dp)
                .height(250.dp)
            ,
            elevation = 2.dp,

            shape = RoundedCornerShape(corner = CornerSize(16.dp))
        ) {
            Box {
                ConstraintLayout {

                    val (image, title, content) = createRefs()


                    NetworkImage(
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(0.dp)
                            .fillMaxSize()
                            .aspectRatio(1.0f)
                            .constrainAs(image) {
                                centerHorizontallyTo(parent)
                                top.linkTo(parent.top)
                            },
                        url = cat.url,


                        )

                }
                FavoriteButton(
                    modifier = Modifier
                        .padding(4.dp),
                    viewModel,
                    cat
                )

            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Transparent,
                                Black

                            ),
                            startY = 250f
                        )
                    )
            )
        }

}

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    cat: CatEntity
) {
    var isFavorite = cat.favorite

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {

            if (isFavorite)
            {
                viewModel.addFavorite(cat.id, false)
            }
            else {
                viewModel.addFavorite(cat.id, true)
            }
        }
    ) {
        Icon(
            tint = Color.White,

            imageVector = if (cat.favorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }
}

@Composable
fun DeleteButton(
    modifier: Modifier = Modifier,
    color: Color = Color.White
) {

    var isFavorite by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
        }
    ) {
        Icon(
            tint = color,

            imageVector = Icons.Filled.Delete,
            contentDescription = null
        )
    }

}