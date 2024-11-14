package com.asheeshk.myalarm

import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import com.asheeshk.myalarm.ui.theme.MyAlarmTheme
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.asheeshk.myalarm.ui.theme.MyAlarmTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION


                )

        // Make the status and navigation bars fully transparent
        window.statusBarColor = Color.Transparent.toArgb()
        window.navigationBarColor = Color.Transparent.toArgb()
        setLightStatusBar(window, true)
        // Pass WindowInsets for padding adjustments
        // Hide the status bar and navigation bar icons (optional)
        //  WindowInsetsControllerCompat(window, window.decorView).hide(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())

        setContent {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                // Background image
                Image(
                    painter = painterResource(id = R.drawable.bg), // Replace with your drawable
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Foreground content with MainContent and RoundedCard
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // MainContent wrapped in height
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        MainContent()
                    }

                    // Spacer to push RoundedCard to the bottom
                    Spacer(modifier = Modifier.weight(1f))

                    /*  // Rounded corner view that occupies remaining height
                      Box(modifier = Modifier
                          .fillMaxWidth()
                          .fillMaxHeight()){
                          RoundedCard(
                              modifier = Modifier
                                  .fillMaxWidth()
                                  .fillMaxHeight(0.9f) // Adjust this height as per your requirement
                                  .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                                  .background(Color.White) // Set the color for the rounded view
                          )
                      }*/

                }
            }
        }
    }

    fun setLightStatusBar(window: Window, isLight: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                if (isLight) 0 else WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = if (isLight) {
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
        }
    }
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.bg), // Replace with your drawable
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Foreground content
        DashboardScreen(
            userName = "Akshay Syal",
            steps = 3524,
            calories = 952,
            coins = 12.19,
            distance = 2.4,
            heartRate = 38
        )
    }
}


@Composable
fun DashboardScreen(
    userName: String,
    steps: Int,
    calories: Int,
    coins: Double,
    distance: Double,
    heartRate: Int
) {
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF2D2D39), Color(0xFF754B9C))
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(

            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            GreetingSection(userName)
            Spacer(modifier = Modifier.height(24.dp))
            MetricsSection(
                steps = steps,
                calories = calories,
                coins = coins,
                distance = distance,
                heartRate = heartRate
            )
            Spacer(modifier = Modifier.height(24.dp))
            RoundedCard(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight())
        }
    }
}

@Composable
fun RoundedCard(modifier: Modifier) {
    Card(
        modifier = modifier.padding(2.dp),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    ) {
        ActivityRow()
        AlarmPlanList()


    }

}

@Composable
fun GreetingSection(userName: String) {
    Column(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Hello,",
            color = Color.White,
            fontSize = 24.sp,
            textAlign = TextAlign.Start
        )
        Text(
            text = userName,
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun MetricsSection(steps: Int, calories: Int, coins: Double, distance: Double, heartRate: Int) {
    Column(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            MetricCard(
                value = steps.toString(),
                label = "Steps",
                icon = "🚶",
                modifier = Modifier.weight(1f) // Each card takes equal width
            )
            Spacer(modifier = Modifier.padding(start = 20.dp))
            MetricCard(
                value = calories.toString(),
                label = "Calories",
                icon = "🔥",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            MetricCard(value = coins.toString(), label = "Coins", modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.padding(start = 20.dp))
            MetricCard(value = "${distance} KM", label = "Distance", modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.padding(start = 20.dp))
            MetricCard(
                value = heartRate.toString(),
                label = "Heart beat",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun MetricCard(value: String, label: String, icon: String? = null, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color(0x40000000), shape = MaterialTheme.shapes.large)
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = label,
            color = Color.White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun ActivityCard(
    icon: ImageVector,
    time: String,
    activityName: String,
    iconColor: Color,
    backgroundColor: Color = Color.Green
) {
    Card(
        colors = CardColors(containerColor = Color.White, contentColor = Color.Transparent, disabledContentColor = Color.DarkGray, disabledContainerColor = Color.LightGray),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = time,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = activityName,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun ActivityRow() {
    LazyRow(
        modifier = Modifier.padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(activities) { activity ->
            ActivityCard(
                icon = activity.icon,
                time = activity.time,
                activityName = activity.name,
                iconColor = activity.iconColor
            )
        }
    }
}

// Sample data class and list of activities
data class Activity(
    val icon: ImageVector,
    val time: String,
    val name: String,
    val iconColor: Color
)

val activities = listOf(
    Activity(
        icon = Icons.Default.Favorite, // Replace with skipping icon
        time = "34 min",
        name = "Skipping",
        iconColor = Color(0xFFFF9800) // Orange color
    ),
    Activity(
        icon = Icons.Default.CheckCircle, // Replace with cycling icon
        time = "30 min",
        name = "Cycling",
        iconColor = Color(0xFF9C27B0) // Purple color
    ),
    Activity(
        icon = Icons.Default.Send, // Replace with meditation icon
        time = "15 min",
        name = "Meditation",
        iconColor = Color(0xFF4CAF50) // Green color
    )
)
@Composable
fun AlarmPlanList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "My Alarm Plans",
            color = Color.Black,
            fontSize = 24.sp,
            modifier=Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start)

        // LazyColumn for vertical scrolling
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp) // Space between items
        ) {
            items(listOf(
                Alarm("05:00 AM", "Jogging for 30 mins", Icons.Default.Face, Color(0xFF7B61FF), true),
                Alarm("05:30 AM", "Skipping for 30 mins", Icons.Default.Lock, Color(0xFFFF7B61), true),
                // Add more alarms as needed
            )) { alarm ->
                AlarmItem(
                    time = alarm.time,
                    activity = alarm.activity,
                    icon = alarm.icon,
                    iconTint = alarm.iconTint,
                    isChecked = alarm.isChecked
                )
            }
        }
    }
}

@Composable
fun AlarmItem(
    time: String,
    activity: String,
    icon: ImageVector,
    iconTint: Color,
    isChecked: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Icon with background
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(iconTint.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Time and Activity
                Column {
                    Text(
                        text = time,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = activity,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            // Toggle switch
            Switch(
                checked = isChecked,
                onCheckedChange = { /* Handle switch toggle */ },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFF7B61FF),
                    checkedTrackColor = Color(0xFFE3D7FF),
                    uncheckedThumbColor = Color.LightGray,
                    uncheckedTrackColor = Color.Gray
                )
            )
        }
    }
}

// Data model for Alarm
data class Alarm(
    val time: String,
    val activity: String,
    val icon: ImageVector,
    val iconTint: Color,
    val isChecked: Boolean
)



