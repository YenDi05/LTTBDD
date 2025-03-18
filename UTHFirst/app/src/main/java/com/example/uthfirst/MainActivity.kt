package com.example.uthfirst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.em
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

    NavHost(navController = navController, startDestination = "uth_screen") {
        composable("uth_screen") { UTHScreen(navController) }
        composable("first_screen"){FirstScreen(navController)}
        composable("second_screen"){SecondScreen(navController)}

    }
}
@Composable
fun UTHScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 316.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_uth),
            contentDescription = "UTH Logo",
            modifier = Modifier
                .width(102.37.dp)
                .height(70.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { navController.navigate("first_screen") },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ){
            Text(
                text = "UTH SmartTasks",
                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(R.font.righteous_regular, FontWeight.Normal)
                    ),
                    fontWeight = FontWeight(400),
                    fontSize = 30.sp,
                    lineHeight = 1.em,
                    letterSpacing = 0.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF006EE9)
                )
            )

        }
    }
}

@Composable
fun FirstScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top=45.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.bachamdau),
                contentDescription = "Ba_cham_dau",
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = "Skip",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 12.sp,
                letterSpacing = 0.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF006EE9)
            )
        }
        Spacer(modifier = Modifier.height(120.dp))

        Image(
            painter = painterResource(id = R.drawable.frame),
            contentDescription = "Frame",
            modifier = Modifier
                .width(318.91.dp)
                .height(410.09.dp),
            contentScale = ContentScale.Fit

        )
        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = { navController.navigate("second_screen") },
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006EE9)),
            modifier = Modifier
                .width(348.dp)
                .height(52.23.dp)
        ) {
            Text(text = "Next",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                lineHeight = (20.sp * 1.26),
                letterSpacing = (-0.02).em,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
                )
        }
    }
}

@Composable
fun SecondScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top=45.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.bachamgiua),
                contentDescription = "Ba_cham_giua",
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = "Skip",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 12.sp,
                letterSpacing = 0.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF006EE9)
            )
        }
        Spacer(modifier = Modifier.height(120.dp))

        Image(
            painter = painterResource(id = R.drawable.anh),
            contentDescription = "Anh second",
            modifier = Modifier
                .width(318.91.dp)
                .height(410.09.dp),
            contentScale = ContentScale.Fit

        )
        Spacer(modifier = Modifier.height(100.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_icon),
                contentDescription = "Back Button",
                modifier = Modifier
                    .size(53.dp)
                    .clickable { navController.popBackStack() }
            )

            Button(
                onClick = { navController.navigate("third_screen") },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006EE9)),

                modifier = Modifier
                    .width(260.dp)
                    .height(52.dp),
            ) {
                Text(text = "Next",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    lineHeight = (20.sp * 1.26),
                    letterSpacing = (-0.02).em,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun ThirdScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top=45.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.bachamcuoi),
                contentDescription = "Ba_cham_cuoi",
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = "Skip",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 12.sp,
                letterSpacing = 0.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF006EE9),
                modifier = Modifier.clickable{}
            )
        }
        Spacer(modifier = Modifier.height(120.dp))

        Image(
            painter = painterResource(id = R.drawable.cuoi),
            contentDescription = "Anh third",
            modifier = Modifier
                .width(318.91.dp)
                .height(410.09.dp),
            contentScale = ContentScale.Fit

        )
        Spacer(modifier = Modifier.height(100.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_icon),
                contentDescription = "Back Button",
                modifier = Modifier
                    .size(53.dp)
                    .clickable { navController.popBackStack() }
            )

            Button(
                onClick = { },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006EE9)),

                modifier = Modifier
                    .width(260.dp)
                    .height(52.dp),
            ) {
                Text(text = "Get Started",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    lineHeight = (20.sp * 1.26),
                    letterSpacing = (-0.02).em,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImReadyScreenPreview() {
    val navController = rememberNavController()
    ThirdScreen(navController = navController)
}
