package com.asheeshk.myalarm

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asheeshk.myalarm.ui.theme.MyAlarmTheme
import com.asheeshk.myalarm.ui.theme.Pink10

class ActivityAddTask : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    )
            MyAlarmTheme {
                ExerciseAlarmScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyAlarmTheme {
        Greeting("Android")
    }
}
@Preview(showBackground = true)
@Composable
fun ExerciseAlarmScreen(userName: String = "Akshay") {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Action Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { /* Handle close action */ },
                modifier = Modifier.size(60.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_rounded_back), // Replace with your close icon
                    contentDescription = "Close"
                )
            }
            IconButton(onClick = { /* Handle save action */ },
                modifier = Modifier.size(60.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_square_check), // Replace with your check icon
                    contentDescription = "Save",
                    tint = Color.Blue

                )
            }
        }

        // Welcome Text
        Text(
            text = "Welcome $userName,",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            color = Color(0xFF6A1B9A) // Purple color
        )

        Text(
            text = "what time do you want to exercise?",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            fontSize = 24.sp,
            textAlign = TextAlign.Start
        )

        // Time Picker
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "20", // Replace with time state
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "44", // Replace with time state
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }

        // Repeat Section
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Repeat",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
            HorizontalSelectableDaysList(days = days)
           /* Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {

                days.forEach { day ->
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.White,
                            )
                            .border(
                                width = 0.5.dp, // Stroke width
                                color = Color.Black, // Stroke color
                                shape = RoundedCornerShape(5.dp) // Shape of the border
                            ),

                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = day, style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 10.dp, start = 5.dp, end = 5.dp, bottom = 10.dp))
                    }
                }
            }*/
        }

        // Mission Section
        SettingItem(title = "Mission", value = "Push-up")

        // Alarm Name Section
        SettingItem(title = "Alarm name", value = "Need to go gym for exercise")

        // Alarm Sound Section
        SettingItem(title = "Alarm Sound", value = "Fast and Furious.mp3")

        // Alarm Volume Section
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Alarm Volume",
                style = MaterialTheme.typography.displaySmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Slider(
                value = 0.5f, // Replace with volume state
                onValueChange = { /* Update volume */ },
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFF6A1B9A),
                    activeTrackColor = Color(0xFF6A1B9A)
                )
            )
        }
    }
}

@Composable
fun SettingItem(title: String, value: String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall,
            fontSize = 13.sp,
            fontFamily = FontFamily.Default,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = value,
                fontSize = 16.sp,
                style = MaterialTheme.typography.labelLarge,
                color = Color.Black
            )
            IconButton(onClick = { /* Handle click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_square_right), // Replace with your arrow icon
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun HorizontalSelectableDaysList(days: List<String>) {
    // State to track the selected day
    var selectedDay by remember { mutableStateOf<String?>(null) }

    Row(
        modifier = Modifier.fillMaxWidth(), // Ensures the row spans the full width
        horizontalArrangement = Arrangement.SpaceEvenly // Space items evenly
    ) {
        days.forEach { day ->
            Box(
                modifier = Modifier
                    .padding()
                    .clickable {
                        selectedDay = day // Update the selected day
                    }
                    .background(
                        color = if (selectedDay == day) Pink10 else Color.White,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = if (selectedDay == day) Color.Blue else Color.Black,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(horizontal = 6.dp, vertical = 8.dp), // Inner padding for the Box
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 20.dp, start = 5.dp, end = 5.dp, bottom = 20.dp)
                )
            }
        }
    }
}


