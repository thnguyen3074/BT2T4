package com.example.bt2t4.ui.screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bt2t4.R


@Composable
fun SecondScreen(navController: NavController) {
    Box ( modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 32.dp),

        ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Page Dots (Indicator)
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Assuming you want 3 dots, you can dynamically change this later
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(
                                if (index == 1) Color(0xFF006EE9) else Color(0xFFEEF5FD),
                                shape = CircleShape
                            )
                    )
                }
            }
            // Skip Button
            Text(
                text = "Skip",
                color = Color(0xFF006EE9),
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.clickable {navController.navigate("ListScreen")}
            )
        }
        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(top = 180.dp)
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Center the elements
        )

        {

            Image(
                painter = painterResource(id = R.drawable.img_1), // Replace with your image resource
                contentDescription = "Background Image",
                modifier = Modifier
                    .fillMaxWidth() // Image takes full width
                    .height(250.dp) // Adjust the height as needed
            )

            Spacer(modifier = Modifier.height(16.dp)) // Space between image and text

            // Title
            Text(
                text = "Easy Time Management",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
            )

            // Description
            Text(
                text = "Time management and the determination of more important tasks will give your job statistics better and always improve",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = Color(0xFF4A4646),
                    fontSize = 14.sp,
                ),
                modifier = Modifier.padding(16.dp)
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Nút "Back" với hình ảnh
            Image(
                painter = painterResource(id = R.drawable.img_2), // Đổi thành tài nguyên hình ảnh của bạn
                contentDescription = "Back",
                modifier = Modifier
                    .size(48.dp)
                    .clickable {navController.popBackStack()}

            )

            // Nút "Next"
            Button(
                onClick = {navController.navigate("ThirdScreen")},
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                ),
                modifier = Modifier
                    .height(52.dp)
                    .width(260.dp),
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                    style = TextStyle(fontSize = 20.sp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    com.example.bt2t4.ui.screens.SecondScreen(navController = rememberNavController())
}
