package com.example.ui_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigator()
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "jetpack_compose_screen") {
        composable("jetpack_compose_screen") { JetpackComposeScreen(navController) }
        composable("ui_component_list_screen") { UIComponentListScreen(navController) }
        composable("text_detail_screen") { TextDetailScreen(navController) }
        composable("image_detail_screen") { ImageDetailScreen(navController) }
    }
}

@Composable
fun JetpackComposeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = 128.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_jetpackcompose),
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier
                .width(216.dp)
                .height(233.dp),
            contentScale = ContentScale.Fit
        )
        Column(
            modifier = Modifier
                .height(233.dp)
                .width(301.dp)
                .padding(top = 76.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                text = "Jetpack Compose",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
        }
        Spacer(modifier = Modifier.height(154.dp))
        Button(
            onClick = { navController.navigate("ui_component_list_screen") },
            modifier = Modifier
                .width(315.dp)
                .height(52.23.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            shape = RoundedCornerShape(50.dp)
        ) {
            Text(text = "I'm ready", fontSize = 18.sp, color = Color.White)
        }
    }
}

@Composable
fun UIComponentListScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 13.dp, top = 43.dp),
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "UI Component List",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp * 1.24f,
            letterSpacing = (-0.02).sp,
            color = Color(0xFF2196F3),
            modifier = Modifier
                //.width(204.dp)
                .height(30.dp)
                .padding( start = 86.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Display",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp * 1.24f,
            letterSpacing = (-0.02).sp,
            modifier = Modifier
                .width(77.dp)
                .height(32.dp)
                .padding( start = 13.dp)
        )
        Button(
            onClick = {navController.navigate("text_detail_screen") },
            modifier = Modifier
                .width(350.dp)
                .height(71.dp)
                .padding( start = 13.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0x4D2196F3)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Text",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold, // 700
                    letterSpacing = (-0.02).sp,
                    color = Color.Black
                )
                Text(
                    text = "Displays text",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal, // 400
                    letterSpacing = (-0.02).sp,
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {navController.navigate("image_detail_screen") },
            modifier = Modifier
                .width(350.dp)
                .height(71.dp)
                .padding( start = 13.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0x4D2196F3)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Image",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold, // 700
                    letterSpacing = (-0.02).sp,
                    color = Color.Black
                )
                Text(
                    text = "Displays an image",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal, // 400
                    letterSpacing = (-0.02).sp,
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Input",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp * 1.24f,
            letterSpacing = (-0.02).sp,
            modifier = Modifier
                .width(77.dp)
                .height(32.dp)
                .padding( start = 13.dp)
        )
        Button(
            onClick = { },
            modifier = Modifier
                .width(350.dp)
                .height(71.dp)
                .padding( start = 13.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0x4D2196F3)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "TextField",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold, // 700
                    letterSpacing = (-0.02).sp,
                    color = Color.Black
                )
                Text(
                    text = "Input field for text",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal, // 400
                    letterSpacing = (-0.02).sp,
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { },
            modifier = Modifier
                .width(350.dp)
                .height(71.dp)
                .padding( start = 13.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0x4D2196F3)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "PasswordField",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold, // 700
                    letterSpacing = (-0.02).sp,
                    color = Color.Black
                )
                Text(
                    text = "Input field for passwords",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal, // 400
                    letterSpacing = (-0.02).sp,
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Layout",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp * 1.24f,
            letterSpacing = (-0.02).sp,
            modifier = Modifier
                .width(77.dp)
                .height(32.dp)
                .padding( start = 13.dp)
        )
        Button(
            onClick = { },
            modifier = Modifier
                .width(350.dp)
                .height(71.dp)
                .padding( start = 13.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0x4D2196F3)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Column",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold, // 700
                    letterSpacing = (-0.02).sp,
                    color = Color.Black
                )
                Text(
                    text = "Arranges elements vertically",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal, // 400
                    letterSpacing = (-0.02).sp,
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { },
            modifier = Modifier
                .width(350.dp)
                .height(71.dp)
                .padding( start = 13.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0x4D2196F3)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Row",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold, // 700
                    letterSpacing = (-0.02).sp,
                    color = Color.Black
                )
                Text(
                    text = "Arranges elements horizontally",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal, // 400
                    letterSpacing = (-0.02).sp,
                    color = Color.Black
                )
            }
        }


    }
}

@Composable
fun TextDetailScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 47.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF2196F3)
                )
            }
            Text(
                text = "Text Detail",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = (-0.02).sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF2196F3),
                modifier = Modifier
                    .padding(start=80.dp)

            )
        }

        Spacer(modifier = Modifier.height(160.dp))
        Text(
            text = buildAnnotatedString {
                append("The ")
                pushStyle(SpanStyle(textDecoration = TextDecoration.LineThrough))
                append("quick ")
                pop()
                pushStyle(SpanStyle(color = Color(0xFFD2691E))) // Màu nâu sẫm (#D2691E - sienna)
                append("Brown")
                pop()
            },
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = buildAnnotatedString {
                append("fox ")
                append("jumps ")
                pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                append("over")
                pop()
            },
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = buildAnnotatedString {
                pushStyle(SpanStyle(textDecoration = TextDecoration.Underline))
                append("the ")
                pop()
                pushStyle(SpanStyle(fontStyle = FontStyle.Italic))
                append("lazy ")
                pop()
                append("dog.")
            },
            fontSize = 30.sp
        )
    }
}

@Composable
fun ImageDetailScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 47.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF2196F3)
                )
            }
            Text(
                text = "Image Detail Screen",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = "Image",
            modifier = Modifier
                .width(300.dp)
                .height(200.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ImReadyScreenPreview() {
    val navController = rememberNavController()
    ImageDetailScreen(navController = navController)
}
