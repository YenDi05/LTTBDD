package com.example.bt1_t4_lazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import java.util.Locale

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
        composable("root_screen") { RootScreen(navController) }
        composable("list_screen") { ListScreen(navController) }
        composable("detail_screen") { DetailScreen(navController) }
    }
}

@Composable
fun RootScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = 159.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                text = "Navigation",
                modifier = Modifier
                    .height(22.dp)
                    .width(89.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                lineHeight = 1.4.em,
                letterSpacing = 0.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "is a framework that simplifies the implementation of navigation between different UI components (activities, fragments, or composables) in an app",
                modifier = Modifier
                    .height(80.dp)
                    .width(339.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 1.4.em,
                letterSpacing = 0.sp,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(80.dp))
        Button(
            onClick = { navController.navigate("list_screen") },
            modifier = Modifier
                .width(315.dp)
                .height(52.23.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            shape = RoundedCornerShape(50.dp)
        ) {
            Text(text = "PUSH",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.W700,
                lineHeight = 1.26.em,
                letterSpacing = (-2).sp
                )
        }
    }
}


@Composable
fun ListScreen(navController: NavController) {
    val totalItems = 10
    val itemsToShow = minOf(totalItems, 6)
    val itemsList = (1..totalItems).map { index ->
        if (index == 6) "1.000.000 | The only way to do great work is to love what you do ."
        else String.format(Locale.getDefault(), "%02d | The only way to do great work\nis to love what you do .", index)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(52.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = "Quay lại",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.popBackStack() }
            )
            Text(
                text = "LazyColumn",
                fontWeight = FontWeight.W600,
                fontSize = 22.sp,
                lineHeight = 1.24.em,
                letterSpacing = (-0.02).em,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding (start = 90.dp)
                    .height(30.dp),
                color = Color(0xFF2196F3)
            )
        }


        Spacer(modifier = Modifier.height(45.dp))
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(itemsList.take(itemsToShow)) { item ->
                ListItem(text = item) {
                    navController.navigate("detail_screen")
                }
            }
        }
    }
}

@Composable
fun ListItem(text: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .width (350.dp)
            .height (93.dp)
            .clickable(onClick = onClick),
        color = Color(0xFF2196F3).copy(alpha = 0.3f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.W400,
                fontSize = 18.sp,
                lineHeight = 1.24.em,
                modifier = Modifier
                    .width (274.dp)
                    .height (48.46.dp)

                )
        }
    }
}

@Composable
fun DetailScreen(navController: NavController){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        ){
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_arrow),  
                contentDescription = "Quay lại",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.popBackStack() }
            )
            Text(
                text = "Detail",
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 1.24.em,
                color = Color(0xFF2196F3),
                modifier = Modifier
                    .height(30.dp)
                    .padding (start = 110.dp)

            )
        }
        Spacer(modifier = Modifier.height(40.dp))

        Text (
            text = "“The only way to do great work \n" +
                    "is to love what you do”",
            modifier = Modifier
                .width (336.dp)
                .height (48.dp),
            fontWeight = FontWeight.W400,
            fontSize = 18.sp,
            lineHeight = 1.24.em,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = "Image",
            modifier = Modifier
                .width(296.dp)
                .height(444.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { navController.navigate("root_screen") },
            modifier = Modifier
                .width(315.dp)
                .height(52.23.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            shape = RoundedCornerShape(50.dp)
        ) {
            Text(text = "BACK TO ROOT",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.W700,
                lineHeight = 1.26.em,
                letterSpacing = (-2).sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImReadyScreenPreview() {
    val navController = rememberNavController()
    ListScreen(navController = navController)
}