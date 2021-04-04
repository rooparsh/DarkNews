package com.darklabs.darknews.androidApp.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SingleSelectDialog(
    title: String,
    optionsList: List<String>,
    defaultSelected: Int,
    submitButtonText: String,
    onSubmitButtonClick: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {

    val selectedOption = mutableStateOf(defaultSelected)

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = listState) {
        coroutineScope.launch { listState.animateScrollToItem(selectedOption.value) }
    }

    Dialog(onDismissRequest = { onDismissRequest.invoke() }) {
        Surface(
            modifier = Modifier.wrapContentHeight(),
            shape = RoundedCornerShape(10.dp)
        ) {

            Column(modifier = Modifier.padding(10.dp)) {

                Text(text = title)

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn(modifier = Modifier.height(500.dp), state = listState) {
                    items(optionsList) {
                        RadioButton(it, optionsList[selectedOption.value]) { selectedValue ->
                            selectedOption.value = optionsList.indexOf(selectedValue)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        onSubmitButtonClick.invoke(selectedOption.value)
                        onDismissRequest.invoke()
                    },
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(text = submitButtonText)
                }
            }

        }
    }
}