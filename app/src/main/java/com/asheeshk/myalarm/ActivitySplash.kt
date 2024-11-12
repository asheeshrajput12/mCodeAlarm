package com.asheeshk.myalarm

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asheeshk.myalarm.ui.theme.MyAlarmTheme
import kotlinx.coroutines.delay

class ActivitySplash : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen {
                // Launch the MainActivity after the splash screen
                startActivity(Intent(this, MainActivity::class.java))
                finish() // Close the SplashActivity
            }
        }
    }
}

@Composable
fun SplashScreen() {
    // Gradient background
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF9C27B0), Color(0xFF7B1FA2)) // Change colors to match your image's gradient
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // "Wake" and "me up" text
            Text(
                text = "Wake",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                text = "me up",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            // "with fresh feels" text
            Text(
                text = "with fresh feels",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    var isVisible by remember { mutableStateOf(true) }

    // Launch delay effect to trigger navigation after 5 seconds
    LaunchedEffect(Unit) {
        delay(5000) // 5-second delay
        isVisible = false // Trigger animation
        delay(300) // Small delay for animation to complete
        onTimeout() // Navigate to MainActivity
    }

    // Gradient background for the splash screen
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF9C27B0), Color(0xFF7B1FA2))
    )

    // AnimatedVisibility for fade-out and scale-in effect
    AnimatedVisibility(
        visible = isVisible,
        exit = fadeOut(animationSpec = tween(300)) // Fade-out effect
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradient),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Text for "Wake" and "me up"
                Text(
                    text = "Wake",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "me up",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Text for "with fresh feels"
                Text(
                    text = "with fresh feels",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}