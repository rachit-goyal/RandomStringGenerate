package com.example.randomstringgenerate.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.randomstringgenerate.R
import com.example.randomstringgenerate.presentation.viewmodel.RandomStringViewModel

/**
created by Rachit on 4/7/2025.
 */
@Composable
fun RandomStringScreen(viewModel: RandomStringViewModel) {
    val strings by viewModel.allStrings.collectAsState()
    var input by remember { mutableStateOf("") }
    val error by viewModel.errorMessage.collectAsState()

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text(text = stringResource(R.string.length)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { input.toIntOrNull()?.let(viewModel::generate) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.generate))
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            item {
                error?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
            items(strings) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(stringResource(R.string.string, item.value))
                        Text(stringResource(R.string.length,item.length))
                        Text(stringResource(R.string.created, item.created))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(onClick = { viewModel.deleteOne(item) }) {
                                Text(stringResource(R.string.delete))
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = viewModel::deleteAll,
            colors = ButtonDefaults.buttonColors(Color.Red),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.clear_all), color = Color.White)
        }
    }
}