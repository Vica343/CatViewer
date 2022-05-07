package hu.bme.aut.android.catviewer.ui.main

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.room.Delete
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import hu.bme.aut.android.catviewer.model.db.CatEntity
import hu.bme.aut.android.catviewer.utils.NetworkImage

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
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                UploadButton(viewModel)
                Spacer(Modifier.weight(1f))
                ExposedDropdownMenuBox(
                    modifier = Modifier
                        .width(250.dp)
                        .padding(horizontal = 20.dp),
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    OutlinedTextField(
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
            for (i in 0..cats.size - 1) {
                if (selectedOptionText == "Random images") {
                    Cat(cat = cats[i], viewModel)
                    Spacer(modifier = Modifier.height(10.dp))
                }
                if (selectedOptionText == "Favorite images" && cats[i].favorite) {
                    Cat(cat = cats[i], viewModel)
                }
                if (selectedOptionText == "Uploaded images" && cats[i].uploaded) {
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
            Column() {
                FavoriteButton(
                    viewModel,
                    cat
                )
                Spacer(Modifier.weight(1f))
                if (cat.uploaded)
                    DeleteButton(viewModel, cat)
            }

        }

    }
}

@Composable
fun UploadButton(
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) {
        if (it != null) {
            viewModel.uploadImage(it, context)
        }
    }
    IconButton(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 20.dp),
        onClick = { launcher.launch("image/*") }) {
        Icon(
            Icons.Filled.Add,
            contentDescription = "Add",
            modifier = Modifier
                .size(40.dp)
                .fillMaxHeight()
        )
    }
}

@Composable
fun FavoriteButton(
    viewModel: MainViewModel,
    cat: CatEntity
) {
    var isFavorite = cat.favorite

    IconToggleButton(
        checked = isFavorite,
        modifier = Modifier
            .padding(4.dp),
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
    viewModel: MainViewModel,
    cat : CatEntity
) {

    IconButton(
        modifier = Modifier
            .padding(4.dp),
        onClick  = {
            viewModel.deleteImage(cat.id!!)
        }
    ) {
        Icon(
            tint = Color.White,
            imageVector = Icons.Filled.Delete,
            contentDescription = null
        )
    }

}

