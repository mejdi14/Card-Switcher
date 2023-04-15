package com.example.card_switcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.card_switcher.ui.theme.CardSwitcherTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.card_switcher.data.SwitchedCardsData

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
                        SwitchedCard(SwitchedCardsData(
                            cardModifier = Modifier.size(200.dp, 350.dp),
                            topCardContent = { triggerAnimation ->
                                CardContent(
                                    imageResId = R.drawable.dog,
                                    text = "Top Card",
                                    onClick = triggerAnimation
                                )
                            },
                            bottomCardContent = { triggerAnimation ->
                                CardContent(
                                    imageResId = R.drawable.dog,
                                    text = "Bottom Card",
                                    onClick = triggerAnimation
                                )
                            }
                        ))

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(onClick = { /* trigger the animation */ }) {
                            Text("Switch Cards")
                        }
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


@Composable
fun CardContent(imageResId: Int, text: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = text,
            modifier = Modifier
                .size(128.dp)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = text, fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}

















