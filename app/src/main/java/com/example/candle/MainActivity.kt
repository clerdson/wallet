package com.example.candle


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add


import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController

import com.example.candle.ui.theme.CandleTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CandleTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "call") {
                    composable("call") { Call(navController) }
                    composable("main") { MainScreen(navController) }
                    composable("list") { ListScreen() }

                }
            }
        }
    }
}
@Composable
fun Call(navController: NavHostController){


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),  verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        var enabled by remember {
            mutableStateOf(false)
        }
        val scale: Float by animateFloatAsState(
            targetValue = if (enabled) .9f else 1f,
            animationSpec = repeatable(
                iterations = 5,
                animation = tween(durationMillis = 50, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            finishedListener = {
                enabled = false
            }
        )

        val infiniteTransition = rememberInfiniteTransition()
        val scaleInfinite by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = .85f,
            animationSpec = infiniteRepeatable(
                animation = tween(30, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        val rotation by infiniteTransition.animateFloat(
            initialValue = -10f,
            targetValue = 10f,
            animationSpec = infiniteRepeatable(
                animation = tween(30, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )




        val density = LocalDensity.current
        val animVisibleState = remember { MutableTransitionState(false) }
            .apply { targetState = true }



        AnimatedVisibility(
            visibleState = animVisibleState,
            enter = slideInVertically {
                // Slide in from 40 dp from the top.
                with(density) { -40.dp.roundToPx() }
            },
            exit = slideOutVertically()
        ) {
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier .size(150.dp)
                    .clip(CircleShape)
                    .clickable { navController.navigate("main") }// clip to the circle shape
                    .border(2.dp, Color.Gray, CircleShape).graphicsLayer {
                        scaleX = scaleInfinite
                        scaleY = scaleInfinite
                        rotationZ = rotation
                    }

            )
        }




    }
}

@Composable
fun MainScreen(navController: NavHostController) {



    Column(

        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        var myList = arrayListOf(1,5,9)
        var myTest  = arrayListOf<Int>()
        var visible by remember { mutableStateOf(false) }
        var color = animateColorAsState(if (visible)Color.Green else Color.Red)
        var visible2 by remember { mutableStateOf(false) }
        var color2 = animateColorAsState(if (visible2)Color.Green else Color.Red)
        var visible3 by remember { mutableStateOf(false) }
        var color3 = animateColorAsState(if (visible3)Color.Green else Color.Red)
        val density = LocalDensity.current
        val animVisibleState = remember { MutableTransitionState(false) }
            .apply { targetState = true }
        var enabled by remember {
            mutableStateOf(false)
        }
        val scale: Float by animateFloatAsState(
            targetValue = if (enabled) .9f else 1f,
            animationSpec = repeatable(
                iterations = 5,
                animation = tween(durationMillis = 50, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            finishedListener = {
                enabled = false
            }
        )

        val infiniteTransition = rememberInfiniteTransition()
        val scaleInfinite by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = .85f,
            animationSpec = infiniteRepeatable(
                animation = tween(30, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        val rotation by infiniteTransition.animateFloat(
            initialValue = -10f,
            targetValue = 10f,
            animationSpec = infiniteRepeatable(
                animation = tween(30, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )




        AnimatedVisibility(
            visibleState = animVisibleState,
            enter = slideInVertically {
                // Slide in from 40 dp from the top.
                with(density) { -40.dp.roundToPx() }
            },
            exit = slideOutVertically()
        ) {
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier .size(150.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(2.dp, Color.Gray, CircleShape)

            )
        }
        Row {

                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(20.dp)
                        .size(30.dp) .graphicsLayer {
                            scaleX = scaleInfinite
                            scaleY = scaleInfinite
                            rotationZ = rotation
                        }// crop the image if it's not a square

                            // add a border (optional),  //avoid the oval shape
                    ,shape = CircleShape,
                    border = BorderStroke(1.dp, color.value),
                    contentPadding = PaddingValues(0.dp),  //avoid the little icon
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = color.value)
                ) {

                }
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(20.dp)
                    .size(30.dp) .graphicsLayer {
                        scaleX = scaleInfinite
                        scaleY = scaleInfinite
                        rotationZ = rotation
                    }// crop the image if it's not a square

                        // add a border (optional),  //avoid the oval shape
               , shape = CircleShape,
                border = BorderStroke(1.dp, color2.value),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.outlinedButtonColors(contentColor = color2.value)
            ) {


            }

            OutlinedButton(
                    onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(20.dp)
                .size(30.dp)
                .graphicsLayer {
                    scaleX = scaleInfinite
                    scaleY = scaleInfinite
                    rotationZ = rotation
                }// crop the image if it's not a square

                    // add a border (optional),  //avoid the oval shape
            ,shape = CircleShape,
            border = BorderStroke(1.dp, color3.value),
            contentPadding = PaddingValues(0.dp),  //avoid the little icon
            colors = ButtonDefaults.outlinedButtonColors(contentColor = color3.value)
            ) {

        }

        }
        Row {
            for (item in 1..3) {
                OutlinedButton(
                    onClick = {

                    if(item ==1){
                        visible = true
                        myTest.add(1)

                    }
                              },
                    modifier = Modifier.size(100.dp),  //avoid the oval shape
                    shape = CircleShape,
                    border =BorderStroke(1.dp, Color.Blue),
                    contentPadding = PaddingValues(0.dp),  //avoid the little icon
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
                ) {

                    Text(

                        text = item.toString())
                }
            }
        }

        Row {
            for (item in 4..6) {
                OutlinedButton(
                    onClick = {
                        if(item ==5){
                        visible2 = true
                            myTest.add(5)

                    }

                    },
                    modifier = Modifier.size(100.dp),  //avoid the oval shape
                    shape = CircleShape,
                    border = BorderStroke(1.dp, Color.Blue),
                    contentPadding = PaddingValues(0.dp),  //avoid the little icon
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
                ) {
                    Text(

                        text = item.toString())
                }
            }
        }
        Row {
            for (item in 7..9) {
                OutlinedButton(
                    onClick = {  if(item ==9){
                        visible3 = true
                        myTest.add(9)


                    }
                    },
                    modifier = Modifier.size(100.dp),  //avoid the oval shape
                    shape = CircleShape,
                    border = BorderStroke(1.dp, Color.Blue),
                    contentPadding = PaddingValues(0.dp),  //avoid the little icon
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
                ) {
                    Text(text = item.toString())
                }
            }


        }

        Row {

            OutlinedButton(
                onClick = {

                    println(myTest)
                    println(myList)
                    if (myList == myTest) {
                        navController.navigate("list")
                    }
                          },
                modifier = Modifier.size(100.dp),  //avoid the oval shape
                shape = CircleShape,
                border = BorderStroke(1.dp, Color.Blue),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
            ) {

               Text(text = ">")


            }


        }


    }



}
@Composable
fun ListScreen() {
    val names = listOf("Nelson", "Glauber", "Marcia", "Regina")
    LazyColumn {
        items(names) { name ->
            Text(name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CandleTheme {
        Greeting("Android")
    }
}