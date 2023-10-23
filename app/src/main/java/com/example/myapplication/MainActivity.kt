package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            }
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
}