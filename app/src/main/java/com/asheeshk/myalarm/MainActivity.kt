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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
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
        setLightStatusBar(window,true)
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

                // Foreground content
               MainContent()
            }
            // Below MainContent, add a rounded corner card or view
            Box(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            ) {
                // Rounded corner view
                RoundedCard()
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
    var isSheetVisible by remember { mutableStateOf(false) }

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
            heartRate = 38,
            onShowSheet = { isSheetVisible = true }  // Trigger sheet visibility
        )
    }
}


@Composable
fun DashboardScreen(userName: String, steps: Int, calories: Int, coins: Double, distance: Double, heartRate: Int,onShowSheet: () -> Unit) {
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF2D2D39), Color(0xFF754B9C))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(

            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            GreetingSection(userName)
            Spacer(modifier = Modifier.height(24.dp))
            MetricsSection(steps = steps, calories = calories, coins = coins, distance = distance, heartRate = heartRate)

        }
    }
}
@Preview(showBackground = true)
@Composable
fun RoundedCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Add your content here, for example:
            Text(text = "Top 20 Display Picture", color = Color.Black)
        }
    }
}

@Composable
fun GreetingSection(userName: String) {
    Column(horizontalAlignment = Alignment.Start) {
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
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
            MetricCard(value = coins.toString(), label = "Coins",modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.padding(start = 20.dp))
            MetricCard(value = "${distance} KM", label = "Distance",modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.padding(start = 20.dp))
            MetricCard(value = heartRate.toString(), label = "Heart beat",modifier = Modifier.weight(1f))
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


/*@Composable
@Preview(showBackground = true)
fun PreviewDashboardScreen() {
    DashboardScreen(
        userName = "Akshay Syal",
        steps = 3524,
        calories = 952,
        coins = 12.19,
        distance = 2.4,
        heartRate = 38
    )
}*/


@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetExample() {
    var isSheetVisible by remember { mutableStateOf(false) }

    Button(onClick = { isSheetVisible = true }) {
        Text("Show Bottom Sheet")
    }

    if (isSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { isSheetVisible = false },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            BottomSheetContent(onClose = { isSheetVisible = false })
        }
    }
}
// Separate composable for bottom sheet UI content
@Composable
fun BottomSheetContent(onClose: () -> Unit) {
    Text(
        text = "This is a modal bottom sheet",
        modifier = Modifier.padding(16.dp)
    )
    Button(onClick = onClose) {
        Text("Close Sheet")
    }
}