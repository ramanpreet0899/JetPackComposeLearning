package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CardImage()
                InfoText()
                DescriptionText()
                TextStyle()
                SpaceBox()
                ColorStateRow()
                TextFieldWithButtonBox()
            }
        }
    }

    /*
    * to show a snackBar, use scope,
    * SnackBarHostState to get showSnackBar function
    * */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TextFieldWithButtonBox() {
        var textFieldState by remember {
            mutableStateOf("")
        }
        val scope = rememberCoroutineScope()
        val snackBarHostState = remember {
            SnackbarHostState()
        }
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState)
            },
            content = { innerPadding ->
                Column {
                    TextField(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp), value = textFieldState,
                        label = {
                            Text("Please Enter your name")
                        },
                        singleLine = true,
                        onValueChange = {
                            textFieldState = it
                        }
                    )
                    Button(
                        modifier = Modifier.padding(innerPadding),
                        onClick = {
                            scope.launch {
                                snackBarHostState.showSnackbar(
                                    message = textFieldState,
                                    duration = SnackbarDuration.Long
                                )
                            }
                        }) {
                        Text(text = "please click me")
                    }
                }
            }
        )
    }

    /* Spacer, to add additional space anywhere
    * */
    @Composable
    fun SpaceBox() {
        Spacer(modifier = Modifier.padding(top = 20.dp))
    }

    /* Internal State, if it changes its own state
    * External State, if it changes another view's state
    * remember, to remember the changed state */
    @Composable
    fun ColorStateRow() {
        val colorState = remember {
            mutableStateOf(Color.Green)
        }
        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxHeight()
        ) {
            ColorBox(modifier = Modifier
                .weight(1f)
                .fillMaxHeight(), updateColor = {
                colorState.value = it
            })
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(colorState.value)
            )
        }
    }

    @Composable
    fun DescriptionText() {
        Text(
            text = stringResource(R.string.compose_learning_description),
            fontFamily = FontFamily.Cursive,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }

    @Composable
    fun TextStyle() {
        Text(buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontFamily = FontFamily.Cursive,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("T")
            }
            append("ext")
            withStyle(
                style = SpanStyle(
                    fontFamily = FontFamily.Cursive,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("S")
            }
            append("tyle")
        }, textDecoration = TextDecoration.Underline)
    }


    @Composable
    fun CardImage() {
        Card(
            modifier = Modifier.background(
                shape = RoundedCornerShape(10.dp),
                color = Color.Gray
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_car),
                contentDescription = stringResource(R.string.image_content_description)
            )
        }
    }

    @Composable
    fun InfoText() {
        Text(
            text = stringResource(R.string.compose_learning_title),
            Modifier.padding(10.dp),
            fontSize = 40.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Gray
        )
    }

    @Composable
    fun ColorBox(modifier: Modifier, updateColor: (Color) -> Unit) {
        Box(modifier = modifier
            .background(Color.Red)
            .clickable {
                updateColor(
                    Color(
                        Random.nextFloat(),
                        Random.nextFloat(),
                        Random.nextFloat(),
                        1f
                    )
                )
            })
    }
}