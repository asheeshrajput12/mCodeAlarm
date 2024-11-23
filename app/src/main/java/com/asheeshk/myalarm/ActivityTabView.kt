package com.asheeshk.myalarm


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asheeshk.myalarm.ui.theme.Purple80
import kotlinx.coroutines.launch

class ActivityTabView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Assuming this method is defined elsewhere in your project
        setContent {
          //  window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            TabsView()
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_FULLSCREEN or            // Hide status bar
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or      // Keep immersive mode active
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION         // Hide navigation bar (optional)
        }
    }
}
@Preview(showBackground = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsView() {
    // Get the current context
    val context = LocalContext.current
    val tabs = listOf(
        TabItem("Home", painterResource(id=R.drawable.home_2)),
        TabItem("Shop", painterResource(id=R.drawable.ic_shop)),
        TabItem("Stats", painterResource(id=R.drawable.ic_gym)),
        TabItem("Profile", painterResource(id=R.drawable.ic_user_square))
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { tabs.size }
    )
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .background(Color.Gray)// Bottom Bar height
            // Bottom Bar background color
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tab_background), // Replace with your drawable resource
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()
                )
                // Left Tabs (Home and Shop)
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .fillMaxWidth(0.5f) // Ensure equal width for left and right sections
                        .padding(start = 0.dp)
                ) {
                    NavigationBarItem(
                        selected = pagerState.currentPage == 0,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(0)
                            }
                        },
                        icon = { Icon(painter = tabs[0].icon, contentDescription = tabs[0].title) }
                    )
                    NavigationBarItem(
                        selected = pagerState.currentPage == 1,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        },
                        icon = { Icon(painter = tabs[1].icon, contentDescription = tabs[1].title) }
                    )
                }

                // FAB in the center with rounded style
                val gradientBrush = linearGradient(
                    colors = listOf(Color.Magenta, Color.Cyan),  // Gradient colors
                    start = Offset(0f, 0f),  // Gradient start (top-left)
                    end = Offset(1f, 1f)     // Gradient end (bottom-right)
                )
                // Custom shadow properties using graphicsLayer
                val shadowColor = Color(0x80000000) // Semi-transparent black shadow color
                val shadowElevation = 8.dp  // Elevation (height of the shadow)
                val shadowOffset = Offset(8f, 8f)  // Shadow offset
                // Load the drawable icon resource
                val addIcon = painterResource(id = R.drawable.ic_add_square) // Replace with your actual drawable resource
                FloatingActionButton(
                    onClick = {
                        val intent = Intent(context, ActivityAddTask::class.java)
                        context.startActivity(intent)
                              },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = (-30).dp) // Push FAB above the curve
                        .clip(CircleShape) // Make FAB fully rounded
                        .background(Color.Transparent, shape = CircleShape)  // Transparent background (gradient applied to icon)
                        .shadow(20.dp, CircleShape) // FAB shadow for elevation

                    ,
                    containerColor = Purple80
                ) {
                    Icon(painter =addIcon, contentDescription = "Add", tint = Color.White)
                }

                // Right Tabs (Stats and Profile)
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .fillMaxWidth(0.5f) // Ensure equal width for left and right sections
                        .padding(end = 16.dp)
                ) {
                    NavigationBarItem(
                        selected = pagerState.currentPage == 2,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(2)
                            }
                        },
                        icon = { Icon(painter = tabs[2].icon, contentDescription = tabs[2].title) }
                    )
                    NavigationBarItem(
                        selected = pagerState.currentPage == 3,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(3)
                            }
                        },
                        icon = { Icon(painter = tabs[3].icon, contentDescription = tabs[3].title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) { page ->
            when (page) {
                0 -> TabContentHome()
                1 -> TabContentTimer()
                2 -> TabContentClock()
                3 -> TabContentProfile()
            }
        }
    }
}


@Composable
fun TabContentHome() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)),
        contentAlignment = Alignment.Center,

    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Adjust the scaling as needed
        )

        Text(
            text = "Home Content",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun TabContentClock() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Clock Content",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun TabContentTimer() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Timer Content",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun TabContentProfile() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Profile Content",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

data class TabItem(val title: String, val icon: Painter)



