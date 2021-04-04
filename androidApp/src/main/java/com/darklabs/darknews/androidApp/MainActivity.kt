package com.darklabs.darknews.androidApp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.darklabs.darknews.androidApp.theme.AppTheme
import com.darklabs.darknews.cache.NewsTable
import com.darklabs.darknews.shared.util.Result
import com.google.accompanist.coil.CoilImage
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                DrawLayout(state = viewModel.news.collectAsState(initial = Result.Loading))
            }
        }
    }
}

@Composable
fun DrawLayout(state: State<Result<List<NewsTable>>>) {
    when (val value = state.value) {
        is Result.Success -> DrawList(data = value.data)
        is Result.Loading -> LoadingState()
        is Result.Error -> ErrorState()
    }
}


@Composable
fun DrawList(data: List<NewsTable>) {
    LazyColumn {
        items(data) { news ->
            Card(
                shape = RoundedCornerShape(8.dp),
                backgroundColor = MaterialTheme.colors.surface,
                modifier = Modifier.padding(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .height(200.dp)
                        .padding(16.dp),
                ) {
                    Text(
                        text = news.title.orEmpty(),
                        style = MaterialTheme.typography.h4
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    CoilImage(
                        data = news.urlToImage.orEmpty(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingState(loadingText: String = "Loading") {
    Column {
        Text(text = loadingText)
    }
}

@Composable
fun ErrorState(errorText: String = "Error") {
    Column {
        Text(text = errorText)
    }
}
