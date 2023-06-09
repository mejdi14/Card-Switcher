package com.example.card_switcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.card_switcher.ui.theme.CardSwitcherTheme
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.example.card_switcher.data.SwitchedCardsData
import com.example.card_switcher.ui.component.CardContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardSwitcherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val animationListener = object : SwitchedCardAnimationListener {
                            override fun onAnimationStart() {
                                // Animation started
                            }

                            override fun onAnimationEnd() {
                                // Animation finished
                            }
                        }
                        SwitchedCard(SwitchedCardsData(
                            cardModifier = Modifier.size(200.dp, 350.dp),
                            triggerOnClick = false,
                            listener = animationListener,
                            topCardContent = { triggerAnimation ->
                                CardContent(
                                    imageResId = R.drawable.dog,
                                    text = "Main Card",
                                    onClick = triggerAnimation
                                )
                            },
                            bottomCardContent = { triggerAnimation ->
                                CardContent(
                                    imageResId = R.drawable.dog,
                                    text = "Details Card",
                                    onClick = triggerAnimation
                                )
                            }
                        ))
                    }
                }
            }
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
    CardSwitcherTheme {
        Greeting("Android")
    }
}

