package com.example.lemonapp

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonapp.ui.theme.LemonAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonAppTheme {
        LemonApp()
    }
}
@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    when(currentStep) {
        1 -> {
            LemonTextAndImage(
                drawableResourceId = R.drawable.lemon_tree,
                stringResourceId = R.string.step_1,
                imageClick =  {
                    currentStep++;
                    squeezeCount = (2..4).random()
            }

            )
        }
        2 -> {
            LemonTextAndImage(
                drawableResourceId = R.drawable.lemon_squeeze,
                stringResourceId = R.string.step_2,
                imageClick = {
                    squeezeCount--
                    if (squeezeCount == 0){
                        currentStep++;
                    }
                }
            )
        }
        3 -> {
            LemonTextAndImage(
                drawableResourceId = R.drawable.lemon_drink,
                stringResourceId = R.string.step_3,
                imageClick = {
                    currentStep++
                }
            )
        }
        4 -> {
            LemonTextAndImage(
                drawableResourceId = R.drawable.lemon_restart,
                stringResourceId = R.string.step_4,
                imageClick = {
                    currentStep = 1;
                }
            )
        }
    }
}

@Composable
fun LemonTextAndImage(
    drawableResourceId: Int,
    stringResourceId: Int,
    imageClick : () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onClick =  imageClick) {
                    Box(modifier = Modifier) {
                        Image(
                            painter = painterResource(id = drawableResourceId),
                            contentDescription = stringResource(id = drawableResourceId)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
                Text(text = stringResource(id = stringResourceId), fontWeight = FontWeight.Bold)
        }
    }
}