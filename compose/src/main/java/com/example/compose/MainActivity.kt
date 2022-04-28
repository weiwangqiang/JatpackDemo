package com.example.compose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            view()
            HelloContent()
        }
    }

    @Composable
    @Preview()
    fun view() {
        val col = Color(0xFF7E2828)
        val clickMe = "nihao,wojiao "
        val context = this
        Column {
            Button(onClick = {
                Toast.makeText(context, clickMe, Toast.LENGTH_SHORT).show()
            }) {
                Text(text = clickMe,
                        color = Color.Red,
                        modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally),
                        style = TextStyle(background = Color.Green, fontSize = 30.sp)
                )
            }
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "welcome")
        }
    }

    private val TAG = "MainActivity"

    @Composable
    fun HelloContent() {
        Column(modifier = Modifier.padding(16.dp)) {
            var name by remember { mutableStateOf("悠悠") }
            Log.d(TAG, "HelloContent: $name")
            if (name.isNotEmpty()) {
                Text(
                        text = "Hello, $name!",
                        modifier = Modifier
                                .padding(bottom = 8.dp),
                        style = MaterialTheme.typography.h5
                )
            }
            OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") }
            )
        }
    }
}