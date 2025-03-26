package com.example.bt2t4.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bt2t4.R
import com.example.bt2t4.data.Attachment
import com.example.bt2t4.data.Subtask
import com.example.bt2t4.data.Task

@Composable
fun DetailScreen(navController: NavController, task: Task) {
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
                    text = "Detail",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2196F3),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Task Title & Description
            Text(text = task.title, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = task.description ?: "", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(16.dp))

            // Subtasks Section
            if (task.subtasks.isNotEmpty()) {
                SectionTitle("Subtasks")
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn {
                    items(task.subtasks) { subtask ->
                        SubtaskItem(subtask)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Attachments Section
            if (task.attachments.isNotEmpty()) {
                SectionTitle("Attachments")
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn {
                    items(task.attachments) { attachment ->
                        AttachmentItem(attachment)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun SubtaskItem(subtask: Subtask) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Text(text = subtask.title, fontSize = 14.sp)
    }
}

@Composable
fun AttachmentItem(attachment: Attachment) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Text(text = attachment.fileName, fontSize = 14.sp)
    }
}
