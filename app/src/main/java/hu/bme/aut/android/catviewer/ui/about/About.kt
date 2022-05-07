package hu.bme.aut.android.catviewer.ui.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hu.bme.aut.android.catviewer.R

@Composable
fun About(
    viewModel: AboutViewModel,
    pressOnBack: () -> Unit = {}
    )
{
    Scaffold(
        topBar = {
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
        }  ,
        content = {
            val context = LocalContext.current
            Spacer(modifier = Modifier.height(20.dp))
            Column( horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(R.drawable.catlogo),
                    modifier = Modifier
                        .size(500.dp),
                    contentDescription ="cat viewer logo" )
                Text(
                    text = viewModel.copyright,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center
                )
            }

        })



}