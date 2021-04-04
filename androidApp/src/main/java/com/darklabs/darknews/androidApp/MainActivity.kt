package com.darklabs.darknews.androidApp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darklabs.darknews.androidApp.theme.AppTheme
import com.darklabs.darknews.androidApp.ui.component.SingleSelectDialog
import com.darklabs.darknews.androidApp.ui.component.Toolbar
import com.darklabs.darknews.androidApp.util.DATE_FORMAT_END_USER
import com.darklabs.darknews.androidApp.util.formatTo
import com.darklabs.darknews.androidApp.util.toDate
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
        viewModel.fetchCountries()
        setContent {
            AppTheme {
                Scaffold(topBar = {
                    Toolbar("News") {
                        viewModel.dialogState.value = true
                    }
                }) {
                    NewsScreen()
                    CountriesDialog()
                }
            }
        }
    }


    @Composable
    fun CountriesDialog() {
        if (viewModel.dialogState.value) {
            viewModel.countries.value?.let { map ->
                val keysList = map.keys.toList()
                val valuesList = map.values.toList()
                SingleSelectDialog(title = "Select Country",
                    optionsList = valuesList,
                    defaultSelected = keysList.indexOf(viewModel.country.value),
                    submitButtonText = "Apply",
                    onSubmitButtonClick = {
                        viewModel.country.value = keysList[it]
                    },
                    onDismissRequest = { viewModel.dialogState.value = false })
            }
        }
    }

    @Composable
    @Preview
    fun NewsScreen() {
        val state = viewModel.news.collectAsState(initial = Result.Loading)
        DrawLayout(state = state)
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


            NewsHeadLineItem(item = news) {

            }
        }
    }
}

@Composable
private fun NewsHeadLineItem(
    item: NewsTable,
    onHeadLineClick: (NewsTable) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp)
            .clickable(onClick = { onHeadLineClick(item) }),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(text = item.title ?: "", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Box(Modifier.weight(2f)) {
                    Text(
                        text = item.author.takeIf { it.isNullOrEmpty().not() }.orEmpty(),
                        color = MaterialTheme.colors.primary,
                        fontSize = 10.sp,
                    )
                }
                Text(
                    text = item.publishedAt.toDate().formatTo(DATE_FORMAT_END_USER),
                    color = MaterialTheme.colors.primary,
                    fontSize = 10.sp
                )

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
