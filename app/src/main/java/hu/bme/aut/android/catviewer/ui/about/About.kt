package hu.bme.aut.android.catviewer.ui.about

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import hu.bme.aut.android.catviewer.ui.main.NavScreen

@Composable
fun About(
    viewModel: AboutViewModel,
    pressOnBack: () -> Unit = {}
    )
{
    TopAppBar(
        title = { Text(text = "CatViewer") },
        actions = {
            IconButton(
                onClick = {
                    pressOnBack()
                },
                modifier = Modifier.padding(
                    horizontal = 20.dp
                )
            )
            {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Icon",
                    tint = Color.White
                )
            }
        }
    )
}