package com.example.bt2t4.ui.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bt2t4.R
import com.example.bt2t4.data.Task
import com.example.bt2t4.data.TaskViewModel
import com.google.gson.Gson

@Composable
fun ListScreen(navController: NavController, taskViewModel: TaskViewModel = viewModel()) {
    val tasks by taskViewModel.tasks.collectAsState()

    LaunchedEffect(Unit) {
        taskViewModel.fetchTask()
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .padding(top = 10.dp)
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_4),
                        contentDescription = "Back",
                        modifier = Modifier.size(40.dp)
                    )
                }

                Text(
                    text = "List",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2196F3),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
            ) {
                if (tasks.isNotEmpty()) {
                    TaskList(tasks,navController)
                } else {
                    NoTasksView()
                }
            }
        }
    }
}

@Composable
fun TaskList(tasks: List<Task>, navController: NavController) {
    val colors = listOf(
        Color(0x4D2196F3),
        Color(0xFFE1BBC1),
        Color(0xFFDDE1B6),
    )

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        itemsIndexed(tasks) { index, task ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors[index % colors.size],shape = RoundedCornerShape(10.dp))
                    .padding(16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = task.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        task.description?.let {
                            Text(
                                text = it,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                    Image(
                        painter = painterResource(id = R.drawable.img_6),
                        contentDescription = "Go to Detail",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(end = 16.dp)
                            .clickable {
                                val taskJson = Uri.encode(Gson().toJson(task))
                                navController.navigate("DetailScreen/$taskJson")
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun NoTasksView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .width(350.dp)
            .height(255.dp)
            .background(Color(0x4DBBBBBB), shape = RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_5),
                contentDescription = "No Data",
                modifier = Modifier.size(112.dp)
            )
            Spacer(modifier = Modifier.height(22.dp))
            Text(
                text = "No Tasks Yet!",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Stay productive—add something to do",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    ListScreen(navController = rememberNavController())
}
