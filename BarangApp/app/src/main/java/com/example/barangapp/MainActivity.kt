package com.example.barangapp

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.barangapp.ui.theme.BarangAppTheme

import android.app.Activity
import android.os.Bundle

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val dashboardScreen = DashboardScreen(this)
//        setContentView(dashboardScreen.createView())

//        val AddStudentScreen = AddStudentScreen(this)
//        setContentView(AddStudentScreen.createView())
        val loginScreen = LoginScreen(this)
        setContentView(loginScreen.createView())
    }
}