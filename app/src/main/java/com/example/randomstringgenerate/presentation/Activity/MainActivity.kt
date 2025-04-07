package com.example.randomstringgenerate.presentation.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.randomstringgenerate.presentation.screens.RandomStringScreen
import com.example.randomstringgenerate.presentation.theme.RandomStringGenerateTheme
import com.example.randomstringgenerate.presentation.viewmodel.RandomStringViewModel
import dagger.hilt.android.AndroidEntryPoint
/**
created by Rachit on 4/7/2025.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomStringGenerateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        val viewModel: RandomStringViewModel =
                            hiltViewModel<RandomStringViewModel>()
                        RandomStringScreen(viewModel)
                    }
                }
            }
        }
    }
}

