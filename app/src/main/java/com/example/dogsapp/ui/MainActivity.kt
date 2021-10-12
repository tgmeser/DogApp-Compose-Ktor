package com.example.dogsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.dogsapp.ui.theme.CoolGreen
import com.example.dogsapp.ui.theme.CoolOrange
import com.example.dogsapp.ui.theme.CoolRed
import com.example.dogsapp.ui.theme.DogsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogsAppTheme {
                // A surface container using the 'background' color from the theme

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    val viewModel: DogViewModel = hiltViewModel()
                    val dog = viewModel.state.value.dog
                    val isLoading = viewModel.state.value.isLoading
                    dog?.let {
                        Image(
                            painter = rememberImagePainter(
                                data = dog.imgSource,
                                builder = { crossfade(true) }
                            ),
                            contentDescription = "Dog",
                            modifier = Modifier.align(Alignment.CenterHorizontally)

                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Breed : ${dog.type}",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 20.sp,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Origin : ${dog.origin}",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 20.sp,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Average Height : ${dog.height} Cm",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 20.sp,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Average Weight : ${dog.weight} Kg",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 20.sp,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        if (dog.protection) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Protection Breed",
                                color = CoolGreen,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 20.sp,
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Not Protection Breed",
                                color = CoolRed,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 20.sp,
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        onClick = viewModel::getDog,
                        colors = ButtonDefaults.buttonColors(backgroundColor = CoolOrange),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "Check Dog Breed")
                    }
                    Spacer(Modifier.height(10.dp))
                    if (isLoading) {
                        CircularProgressIndicator()
                    }
                }

            }
        }
    }
}

