package com.example.barangapp

import android.app.Activity
import android.graphics.Color
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.example.barangapp.data.User
import com.example.barangapp.data.Users
import com.example.barangapp.ui.theme.Colors
import androidx.compose.ui.graphics.toArgb


class LoginScreen(private val activity: Activity) {

    fun createView(): View {
        // Create main layout
        val mainLayout = LinearLayout(activity).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(80, 0, 80, 0)
            setBackgroundColor(Color.WHITE)
        }

        // Create title
        val titleText = TextView(activity).apply {
            text = "Login"
            textSize = 32f
            setTextColor(Color.parseColor("#333333"))
            setPadding(0, 0, 0, 120)
            gravity = Gravity.CENTER
        }

        // Create username field
        val usernameEditText = EditText(activity).apply {
            hint = "Username"
            inputType = InputType.TYPE_CLASS_TEXT
            setPadding(40, 40, 40, 40)
            textSize = 16f
            setBackgroundResource(android.R.drawable.edit_text)
        }

        // Create password field
        val passwordEditText = EditText(activity).apply {
            hint = "Password"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            setPadding(40, 40, 40, 40)
            textSize = 16f
            setBackgroundResource(android.R.drawable.edit_text)
        }

        // Create login button
        val loginButton = Button(activity).apply {
            text = "Login"
            setBackgroundColor(Colors.orange.toArgb())
            textSize = 16f
            setPadding(0, 30, 0, 30)
            setOnClickListener {
                val username = usernameEditText.text.toString().trim()
                val password = passwordEditText.text.toString().trim()

                if (username.isNotEmpty() && password.isNotEmpty()) {
                    val newUser = User(
                        id = System.currentTimeMillis().toString(),
                        username = username,
                        password = password
                    )

                    Users.addUser(newUser)
                    Users.setLoggedInUser(newUser)

                    val dashboardScreen = DashboardScreen(activity)
                    val view = dashboardScreen.createView()
                    activity.setContentView(view)
                } else {
                    android.util.Log.e("LoginScreen", "Please fill the username & password field.")
                }
            }
        }

        // Set layout params for views
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, 0, 0, 40)
        }

        // Add views to layout
        mainLayout.addView(titleText)
        mainLayout.addView(usernameEditText, layoutParams)
        mainLayout.addView(passwordEditText, layoutParams)
        mainLayout.addView(loginButton, layoutParams)

        return mainLayout
    }
}