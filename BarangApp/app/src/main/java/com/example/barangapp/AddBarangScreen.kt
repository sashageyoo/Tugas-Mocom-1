package com.example.barangapp

import android.app.Activity
import android.graphics.Color
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.compose.ui.graphics.toArgb
import com.example.barangapp.data.User
import com.example.barangapp.data.Users
import com.example.barangapp.data.Barang
import com.example.barangapp.data.Barangs
import com.example.barangapp.ui.theme.Colors

class AddBarangScreen(private val activity: Activity) {

    fun createView(): View {
        val scrollView = ScrollView(activity).apply {
            setBackgroundColor(Color.WHITE)
        }

        val mainLayout = LinearLayout(activity).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(80, 80, 80, 80)
        }

        val titleText = TextView(activity).apply {
            text = "Tambahkan Barang"
            textSize = 28f
            setTextColor(Color.parseColor("#333333"))
            setPadding(0, 0, 0, 60)
            gravity = Gravity.CENTER
        }

        val barangIdEditText = EditText(activity).apply {
            hint = "ID Barang"
            inputType = InputType.TYPE_CLASS_TEXT
            setPadding(40, 40, 40, 40)
            textSize = 16f
            setBackgroundResource(android.R.drawable.edit_text)
        }

        val barangNameEditText = EditText(activity).apply {
            hint = "Nama Barang"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
            setPadding(40, 40, 40, 40)
            textSize = 16f
            setBackgroundResource(android.R.drawable.edit_text)
        }

        val barangStockEditText = EditText(activity).apply {
            hint = "Stok Barang"
            inputType = InputType.TYPE_CLASS_NUMBER
            setPadding(40, 40, 40, 40)
            textSize = 16f
            setBackgroundResource(android.R.drawable.edit_text)
        }


        val addButton = Button(activity).apply {
            text = "Tambahkan"
            textSize = 16f
            setBackgroundColor(Colors.orange.toArgb())
            setPadding(0, 30, 0, 30)
            setOnClickListener {
                val barangId = barangIdEditText.text.toString().trim()
                val barangName = barangNameEditText.text.toString().trim()
                val barangStockText = barangStockEditText.text.toString().trim()

                if (barangId.isEmpty() || barangName.isEmpty() || barangStockText.isEmpty()) {
                    Toast.makeText(activity, "Tolong tambahkan semua input", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val stock = barangStockText.toIntOrNull()
                if (stock == null) {
                    Toast.makeText(activity, "Stock harus angka", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val barangStock =  barangStockEditText.text.toString().trim().toInt()

                val barang = Barang(
                    id = barangId,
                    name = barangName,
                    stock = barangStock
                )

                Barangs.addBarang(barang)

                Toast.makeText(activity, "Barang berhasil ditambahkan.", Toast.LENGTH_SHORT).show()

                val dashboardScreen = DashboardScreen(activity)
                activity.setContentView(dashboardScreen.createView())
            }
        }

        // Set layout params for views
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, 0, 0, 30)
        }

        // Add views to layout
        mainLayout.addView(titleText)
        mainLayout.addView(barangIdEditText, layoutParams)
        mainLayout.addView(barangNameEditText, layoutParams)
        mainLayout.addView(barangStockEditText, layoutParams)
        mainLayout.addView(addButton, layoutParams)

        scrollView.addView(mainLayout)
        return scrollView
    }
}