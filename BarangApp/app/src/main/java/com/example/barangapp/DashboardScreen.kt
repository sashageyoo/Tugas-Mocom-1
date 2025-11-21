package com.example.barangapp

import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.compose.ui.graphics.toArgb
import com.example.barangapp.data.User
import com.example.barangapp.data.Users
import com.example.barangapp.data.Barang
import com.example.barangapp.data.Barangs
import com.example.barangapp.ui.theme.Colors

class DashboardScreen(private val activity: Activity) {

    fun createView(): View {
        // Create main container
        val mainContainer = LinearLayout(activity).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }

        // Create App Bar
        val appBar = LinearLayout(activity).apply {
            orientation = LinearLayout.HORIZONTAL
            setBackgroundColor(Colors.orange.toArgb())
            setPadding(40, 40, 40, 40)
            gravity = Gravity.CENTER
        }

        val appBarTitle = TextView(activity).apply {
            text = "Barang App"
            textSize = 20f
            setTextColor(Color.WHITE)
        }

        appBar.addView(appBarTitle)

        val userDisplay = LinearLayout(activity).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(40, 40, 40, 40)
            gravity = Gravity.CENTER
        }

        val userGreet = TextView(activity).apply {
            text = "Selamat datang, ${Users.getUsername()}!"
            textSize = 20f
            setTextColor(Color.BLACK)
        }

        userDisplay.addView(userGreet)

        // Create ScrollView for student list
        val scrollView = ScrollView(activity).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        }

        // Create container for barang
        val barangListLayout = LinearLayout(activity).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 40, 40, 40)
        }

        // Get all barang
        val barangs = Barangs.getBarang()

        if (barangs.isEmpty()) {
            // Show empty message
            val emptyText = TextView(activity).apply {
                text = "Belum ada barang yang ditambahkan. Pencet tombol + untuk menambahkan barang."
                textSize = 16f
                setTextColor(Color.parseColor("#666666"))
                gravity = Gravity.CENTER
                setPadding(0, 100, 0, 0)
            }
            barangListLayout.addView(emptyText)
        } else {
            // Display each student
            barangs.forEach { barang ->
                val barangColumn = createBarangColumn(barang)
                barangListLayout.addView(barangColumn)
            }
        }

        scrollView.addView(barangListLayout)

        // Create Floating Action Button container
        val fabContainer = LinearLayout(activity).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.END or Gravity.BOTTOM
            setPadding(0, 0, 40, 40)
        }

        val fab = Button(activity).apply {
            text = "+"
            textSize = 24f
            setTextColor(Color.WHITE)
            setBackgroundColor(Colors.orange.toArgb())
            layoutParams = LinearLayout.LayoutParams(150, 150)
            setOnClickListener {
                // Navigate to Add Student Screen
                val addBarangScreen = AddBarangScreen(activity)
                activity.setContentView(addBarangScreen.createView())
            }
        }

        fabContainer.addView(fab)

        // Add all components to main container
        mainContainer.addView(appBar)
        mainContainer.addView(userDisplay)
        mainContainer.addView(scrollView)
        mainContainer.addView(fabContainer)

        return mainContainer
    }

    private fun createBarangColumn(barang: Barang): View {
        val columnLayout = LinearLayout(activity).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(30, 30, 30, 30)
            setBackgroundColor(Color.parseColor("#F5F5F5"))
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 20)
            }
        }

        // Student ID
        val idText = TextView(activity).apply {
            text = "ID: ${barang.id}"
            textSize = 14f
            setTextColor(Color.parseColor("#666666"))
            setPadding(0,0,50,0)
        }

        // Student Name
        val nameText = TextView(activity).apply {
            text = "Name: ${barang.name}"
            textSize = 18f
            setTextColor(Color.parseColor("#333333"))
            setPadding(0,0,50,0)
        }

        // Student Phone
        val stockText = TextView(activity).apply {
            text = "Stock: ${barang.stock}"
            textSize = 16f
            setTextColor(Color.parseColor("#555555"))
            setPadding(0, 0, 0, 0)
        }


        columnLayout.addView(idText)
        columnLayout.addView(nameText)
        columnLayout.addView(stockText)

        return columnLayout
    }
}