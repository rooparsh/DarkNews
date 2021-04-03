package com.darklabs.darknews.androidApp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import com.darklabs.darknews.androidApp.theme.AppTheme
import com.darklabs.darknews.cache.NewsTable
import com.darklabs.darknews.shared.util.Result
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
            Text(text = news.title.orEmpty())
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
