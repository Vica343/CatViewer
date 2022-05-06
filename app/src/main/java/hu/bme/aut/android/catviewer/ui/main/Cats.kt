package hu.bme.aut.android.catviewer.ui.main

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import hu.bme.aut.android.catviewer.model.db.CatEntity
import hu.bme.aut.android.catviewer.utils.NetworkImage
import java.io.File


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Cats(
    viewModel: MainViewModel
) {
    val cats: List<CatEntity> by viewModel.catList.collectAsState(initial = listOf())
    val isLoading: Boolean by viewModel.isLoading
    val isRefreshing: Boolean by viewModel.isRefreshing

    val options = listOf("Random images", "Favorite images", "Uploaded images")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }


    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.refreshImages() },
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp)
                .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            TopAppBar(title = { Text(text = "CatViewer") })
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                UploadButton(viewModel)
                Spacer(Modifier.weight(1f))

                ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                TextField(
                    readOnly = true,
                    value = selectedOptionText,
                    onValueChange = { },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )
                ExposedDropdownMenu(
                    modifier = Modifier
                        .exposedDropdownSize(),
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                            }
                        ) {
                            Text(text = selectionOption)
                        }
                    }
                }
            }
            }
            Spacer(modifier = Modifier.height(20.dp))
            for (i in 0..cats.size-1) {
                if (selectedOptionText == "Random images")
                {
                    Cat(cat = cats[i], viewModel)
                }
                if (selectedOptionText == "Favorite images" && cats[i].favorite)
                {
                    Cat(cat = cats[i], viewModel)
                }
                if (selectedOptionText == "Uploaded images" && cats[i].uploaded)
                {
                    Cat(cat = cats[i], viewModel)
                }
            }
        }
    }
}

@Composable
private fun Cat(
    cat: CatEntity,
    viewModel: MainViewModel
) {
    var expanded by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier

            .padding(horizontal = 8.dp, vertical = 10.dp)
            .height(250.dp)
            .clickable { expanded = !expanded },

        elevation = 2.dp,



        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Box(contentAlignment = Alignment.TopEnd) {

            ConstraintLayout {

                val (image, title, content) = createRefs()

                val (body, progress) = createRefs()

                NetworkImage(
                    contentScale = if (expanded) ContentScale.Crop else ContentScale.Inside,
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

    }
}

@Composable
fun UploadButton(
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            val file = File(uri.toString())
            viewModel.uploadImage(uri.toString())
        };

    }

    IconButton(

        onClick = {
            launcher.launch("image/*")
        },
        ) {
        Icon(

            Icons.Filled.Upload,
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .padding(0.dp)

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

            if (isFavorite) {
                viewModel.addFavorite(cat.id, false)
            } else {
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

