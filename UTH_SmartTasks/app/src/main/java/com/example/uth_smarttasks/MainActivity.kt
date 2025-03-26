package com.example.uth_smarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Work
import com.example.uth_smarttasks.ui.theme.UTH_SmartTasksTheme
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Calendar
import androidx.annotation.RequiresApi
import android.os.Build
import androidx.navigation.NavController
import androidx.compose.animation.AnimatedVisibility
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import android.net.Uri













data class Task(val title: String, val description: String, val status: String, val color: Color, val category: String = "General", val priority :String = "Normal",val subtasks: List<Subtask> = emptyList(),val attachments: List<Attachment> = emptyList())
data class Subtask(val description: String, val isCompleted: Boolean)
data class Attachment(val name: String)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "task_screen"
            ) {
                composable("task_screen") {

                    TaskScreen(navController = navController)
                }

                composable("empty_task_screen") {
                    EmptyTaskScreen(onBackClick = { navController.popBackStack() })
                }

                composable("task_detail") {
                    TaskDetailScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
fun TopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo")
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                "SmartTasks",
                fontSize = 20.sp,
                color = Color(0xFF1E88E5),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                "A simple and efficient to-do app",
                fontSize = 14.sp,
                color = Color(0xFF64B5F6).copy(alpha = 0.7f),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.ic_notifications),
            contentDescription = "Notifications",
            modifier = Modifier
                .size(24.dp)
                .clickable { /* Handle click event */ }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskScreen(navController: NavController) {
    val tasks =remember {
        listOf(
            Task(
                "Complete Android Project",
                "Finish the UI, integrate API, and write documentation",
                "In Progress",
                Color(0xFFFFCDD2)
            ),
            Task(
                "Doctor Appointment",
                "This task is related to Work. It needs to be completed",
                "Pending",
                Color(0xFFC8E6C9)
            ),
            Task(
                "Meeting",
                "This task is related to Fitness. It needs to be completed",
                "Pending",
                Color(0xFFBBDEFB)
            )
        )
    }
    Scaffold(
        topBar = { TopAppBar() },
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tasks) { task ->
                TaskItem(task = task,navController = navController) // Ensure valid Task object is passed
            }
        }

    }
}
@Composable
fun TaskItem(task: Task, navController: NavController) {
    var isChecked by remember { mutableStateOf(false) }
    val currentHour = remember { Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }
    val showDetails = currentHour % 2 == 0 // ✅ Kiểm tra giờ khi render UI

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable {

                if (showDetails) {
                    navController.navigate("task_detail")
                } else {
                    navController.navigate("empty_task_screen")
                }
            },
        colors = CardDefaults.cardColors(containerColor = task.color)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Black,
                        uncheckedColor = Color.Gray,
                        checkmarkColor = Color.White
                    ),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = task.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = task.description,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Luôn hiển thị status và thời gian
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Status: ${task.status}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "14:00 2500-03-26",
                    fontSize = 14.sp
                )
            }
        }
    }
}




@Composable
fun BottomNavigationBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(72.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = "Home",
                modifier = Modifier.size(28.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = "Calendar",
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(72.dp)) // Leave space for FAB

            Image(
                painter = painterResource(id = R.drawable.ic_document),
                contentDescription = "Document",
                modifier = Modifier.size(28.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = "Settings",
                modifier = Modifier.size(28.dp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = "Add Task",
            modifier = Modifier
                .size(56.dp) // Increased size for add icon
                .align(Alignment.TopCenter)
                .offset(y = (-28).dp) // Raised position
        )
    }
}


// màn list
@Composable
fun EmptyTaskScreen(onBackClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Nút quay lại phía trên bên trái
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Nội dung chính
        Image(
            painter = painterResource(id = R.drawable.ic_empty_tasks),
            contentDescription = "No Tasks",
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "No Tasks Yet!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Stay productive—add something to do",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}

//màn detail
@Composable
fun TaskDetailScreen(navController: NavController,
    task: Task = Task("No Title", "No Description", "Pending", Color.Gray), onBackClick: () -> Unit= {}, onDeleteClick: () -> Unit= {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Thanh tiêu đề
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding (top = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Nút quay lại
            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = "Quay lại",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.popBackStack() }
            )
            // Tiêu đề màn hình
            Text(
                text = "Detail",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
            // Nút xóa
            Image(
                painter = painterResource(id = R.drawable.delete),
                contentDescription = "Thùng rác",
                modifier = Modifier
                    .size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tiêu đề & Mô tả
        Text(
            text = task.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = task.description,
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Thông tin nhiệm vụ
        TaskInfoSection(task)

        Spacer(modifier = Modifier.height(16.dp))

        // Danh sách Subtasks
        Text(
            text = "Subtasks",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        task.subtasks.forEach { subtask ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = subtask.isCompleted, onCheckedChange = null)
                Text(
                    text = subtask.description,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Danh sách tệp đính kèm
        Text(
            text = "Attachments",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        task.attachments.forEach { attachment ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.AttachFile, contentDescription = "Attachment")
                Text(
                    text = attachment.name,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
@Composable
fun TaskInfoSection(task: Task) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFCDD2), shape = RoundedCornerShape(8.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Filled.Work, contentDescription = "Category")
            Text(text = "Category", fontSize = 12.sp, color = Color.Black)
            Text(text = task.category, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Filled.Storage, contentDescription = "Status")
            Text(text = "Status", fontSize = 12.sp, color = Color.Black)
            Text(text = task.status, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Filled.PriorityHigh, contentDescription = "Priority")
            Text(text = "Priority", fontSize = 12.sp, color = Color.Black)
            Text(text = task.priority, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UTH_SmartTasksTheme {
        val navController = rememberNavController()
        TaskScreen(navController = navController)
    }
}
