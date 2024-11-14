package com.mm.blogapplication.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.mm.domain.model.Blog
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mm.blogapplication.R
import java.util.Locale

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val res = viewModel.homeState.value
    if (res.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

    if (res.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = res.error, modifier = Modifier.align(Alignment.Center))
        }
    }

    res.data?.data?.let { blogs ->
        LazyColumn(modifier = Modifier) {
            items(blogs.size) {
                PostItem(it = blogs[it]) {
                    navController.navigate("details/${it}")
                }
            }
        }
    }
}

@Composable
fun PostItem(it: Blog, l: (String) -> Unit) {

    var isSpeaking by remember { mutableStateOf(false) }
    val tts = rememberTextToSpeech()



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { l.invoke(it.id) }, verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            CircularImage(50.0, 50.0, 25.0, it.owner.picture)
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "${it.owner.firstName} ${it.owner.lastName}",
            )
        }

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            painter = rememberImagePainter(data = it.image), contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = it.text,
                modifier = Modifier
                    .padding(start = 12.dp, bottom = 12.dp, top = 12.dp, end = 6.dp)
                    .width(350.dp),
                style = TextStyle(color = Color.Gray, fontSize = 20.sp)
            )
            Image(
                painter = painterResource(id = R.drawable.sound),
                contentDescription = stringResource(R.string.image_content_description),
                modifier = Modifier
                    .width(25.dp)
                    .height(25.dp)
                    .clickable(
                        enabled = true,
                        onClickLabel = stringResource(R.string.image_clickable),
                        onClick = {
                            if (tts.value?.isSpeaking == true) {
                                tts.value?.stop()
                                isSpeaking = false
                            } else {
                                tts.value?.speak(
                                    it.text, TextToSpeech.QUEUE_FLUSH, null, ""
                                )
                                isSpeaking = true
                            }
                        }
                    )
            )
        }
        Divider()
    }

}

@Composable
fun CircularImage(width: Double, height: Double, radius: Double, imageUrl: String) {

    Card(
        modifier = Modifier
            .width(width = width.dp)
            .height(height = height.dp), shape = RoundedCornerShape(radius.dp)
    ) {

        Image(
            painter = rememberImagePainter(data = imageUrl), contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun rememberTextToSpeech(): MutableState<TextToSpeech?> {
    val context = LocalContext.current
    val tts = remember { mutableStateOf<TextToSpeech?>(null) }
    DisposableEffect(context) {
        val textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.value?.language = Locale.UK
            }
        }
        tts.value = textToSpeech

        onDispose {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
    }
    return tts
}
