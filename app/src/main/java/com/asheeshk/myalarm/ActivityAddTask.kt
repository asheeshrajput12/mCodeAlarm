package com.asheeshk.myalarm

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asheeshk.myalarm.ui.theme.MyAlarmTheme
import com.asheeshk.myalarm.ui.theme.Pink10
import com.asheeshk.myalarm.ui.theme.Pink80
import kotlinx.coroutines.launch

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
                //ShowBottomSheetModal()
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
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ExerciseAlarmScreen(userName: String = "Akshay") {
    val activity = LocalContext.current as ActivityAddTask
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // Enable scrolling
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
            IconButton(onClick = { /* Handle close action */
                activity.finish()

            },
                modifier = Modifier.size(60.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_rounded_back), // Replace with your close icon
                    contentDescription = "Close"
                )
            }
            IconButton(onClick = { /* Handle save action */
                activity.finish()},
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
        }

        // Mission Section
        SettingItem(title = "Mission", value = "Push-up")

        // Alarm Name Section
        Column {
            Text(text = "Alarm Name",
                style = MaterialTheme.typography.displaySmall,
                fontSize = 13.sp,
                fontFamily = FontFamily.Default,
                color = Color.Gray,
                modifier = Modifier.wrapContentWidth(),
                textAlign = TextAlign.Start)
            var text by remember { mutableStateOf("Hi Arjun") } // State to manage text input

            TextField(
                value = text,
                onValueChange = { text = it }, // Update the state with input value
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White), // Make TextField fill available width
                singleLine = true, // Restrict input to a single line
                colors = TextFieldDefaults.colors(
                    // Set the background color to white
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent, // Optional: remove underline on focus
                    unfocusedIndicatorColor = Color.Transparent // Optional: remove underline when not focused
                ),
                textStyle = TextStyle(
                    fontSize = 16.sp, // Set the text size
                    color = Color.Black, // Set the text color
                    fontFamily = FontFamily.SansSerif, // Set the font family (optional)
                    fontWeight = FontWeight.W500 // Set the font weight (optional)
                )
            )
        }
        HorizontalDivider(
            color = Color.Gray, // Set the color of the line
            thickness = 1.dp, // Set the height of the line
            modifier = Modifier.fillMaxWidth() // Make it span the full width
        )
        // Alarm Sound Section
        SettingItem(title = "Alarm Sound", value = "Fast and Furious.mp3")

        // Alarm Volume Section
        Column(modifier = Modifier.fillMaxWidth().padding(0.dp,0.dp,0.dp,50.dp)) {
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
        ShowBottomSheetModal()
        HorizontalDivider(
            color = Color.Gray, // Set the color of the line
            thickness = 1.dp, // Set the height of the line
            modifier = Modifier.fillMaxWidth() // Make it span the full width
        )
    }
}

@Composable
fun HorizontalSelectableDaysList(days: List<String>) {
    // State to track the selected days
    var selectedDays by remember { mutableStateOf<Set<String>>(emptySet()) }

    Row(
        modifier = Modifier.fillMaxWidth(), // Ensures the row spans the full width
        horizontalArrangement = Arrangement.SpaceEvenly // Space items evenly
    ) {
        days.forEach { day ->
            Box(
                modifier = Modifier
                    .padding()
                    .clickable {
                        selectedDays = if (selectedDays.contains(day)) {
                            selectedDays - day // Deselect if already selected
                        } else {
                            selectedDays + day // Add to selected set if not selected
                        }
                    }
                    .background(
                        color = if (selectedDays.contains(day)) Pink10 else Color.White,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = if (selectedDays.contains(day)) Pink80 else Color.Black,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(horizontal = 6.dp, vertical = 8.dp), // Inner padding for the Box
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        top = 20.dp,
                        start = 5.dp,
                        end = 5.dp,
                        bottom = 20.dp
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowBottomSheetModal() {
    // Remember bottom sheet state
    // State to control showing the bottom sheet
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<String?>("Push Up") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = selectedItem.toString(),
            fontSize = 16.sp,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Black
        )
        // Button to open bottom sheet
        IconButton(onClick = { showBottomSheet=true }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_square_right), // Replace with your arrow icon
                contentDescription = null
            )
        }
    }


    // Bottom Sheet
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false }, // Dismiss on outside tap
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            BottomSheetContent(
                items = listOf("Push Up", "Stretching", "Pulling","Dumble","Jumping","Running","Tradil"),
                onItemSelected = { item ->
                    selectedItem=item // Pass result back to parent
                    showBottomSheet = false // Close the bottom sheet
                }
            )
        }
    }
}

@Composable
fun BottomSheetContent(items: List<String>, onItemSelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Select an Option",
            style = TextStyle(fontSize = 20.sp, color = Color.Black),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        items.forEach { item ->
            Text(
                text = item,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemSelected(item) } // Call the callback with the selected item
                    .padding(vertical = 12.dp)
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetExample() {
    // State for managing the bottom sheet
    var isBottomSheetOpen by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }

    // List of items to show in the bottom sheet
    val items = listOf("Option 1", "Option 2", "Option 3")

    // Main content
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(paddingValues)
        ) {
            IconButton(onClick = { isBottomSheetOpen = true }
            , modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.TopEnd)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_square_right),
                    contentDescription = "Open Bottom Sheet",
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                )
            }

            // Show the selected item
            if (selectedItem.isNotEmpty()) {
                Text(
                    text = "Selected: $selectedItem",
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }

    // Bottom Sheet Content
    if (isBottomSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = { isBottomSheetOpen = false }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Select an Item",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Display items
                items.forEach { item ->
                    TextButton(
                        onClick = {
                            selectedItem = item
                            isBottomSheetOpen = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = item)
                    }
                }
            }
        }
    }
}


