package com.example.thuc_hanh_1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các View
        val etName: EditText = findViewById(R.id.etName)
        val etAge: EditText = findViewById(R.id.etAge)
        val btnCheck: Button = findViewById(R.id.btnCheck)
        val tvResult: TextView = findViewById(R.id.tvResult)

        // Xử lý khi bấm nút kiểm t
        btnCheck.setOnClickListener {
            val name = etName.text.toString()
            val ageStr = etAge.text.toString()

            if (name.isEmpty() || ageStr.isEmpty()) {
                tvResult.text = "Vui lòng nhập đầy đủ thông tin!"
                return@setOnClickListener
            }

            val age = ageStr.toIntOrNull()
            if (age == null || age < 0) {
                tvResult.text = "Tuổi không hợp lệ!"
                return@setOnClickListener
            }

            // Xác định nhóm tuổi
            val ageGroup = when {
                age > 65 -> "Người già"
                age in 6..65 -> "Người lớn"
                age in 2..5 -> "Trẻ em"
                age >= 0 -> "Em bé"
                else -> "Không xác định"
            }

            // Hiển thị kết quả
            tvResult.text = "$name thuộc nhóm: $ageGroup"
        }
    }
}
