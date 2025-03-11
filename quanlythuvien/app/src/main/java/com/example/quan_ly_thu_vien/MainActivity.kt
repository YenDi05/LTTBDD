package com.example.quan_ly_thu_vien

import android.os.Bundle
import android.widget.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var btnChange: Button
    private lateinit var btnAdd: Button
    private lateinit var ivDanhSachSach: ImageView

    private val bookList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        btnChange = findViewById(R.id.btnChange)
        btnAdd = findViewById(R.id.btnAdd)
        ivDanhSachSach = findViewById(R.id.ivDanhSachSach)


        // Danh sách nhân viên
        val employeeNames = arrayOf("Nguyen Van A", "Tran Thi B", "Le Van C", "Pham Van D")

        // Xử lý khi nhấn nút "Đổi"
        btnChange.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Chọn nhân viên")

            builder.setItems(employeeNames) { _, which ->
                etName.setText(employeeNames[which]) // Gán tên nhân viên vào EditText
            }

            builder.setNegativeButton("Hủy") { dialog, _ -> dialog.dismiss() }
            builder.create().show()
        }

        // Xử lý khi nhấn "Thêm" -> Thêm sách vào danh sách
        btnAdd.setOnClickListener {
            val newBook = "Sách ${bookList.size + 1}"
            bookList.add(newBook)
            Toast.makeText(this, "Thêm thành công: $newBook", Toast.LENGTH_SHORT).show()
        }
        // Xử lý khi nhấn "DS Sách" -> Hiển thị danh sách sách đã thêm
        ivDanhSachSach.setOnClickListener {
            if (bookList.isEmpty()) {
                Toast.makeText(this, "Danh sách sách trống!", Toast.LENGTH_SHORT).show()
            } else {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Danh sách sách đã thêm")

                val bookArray = bookList.toTypedArray()
                builder.setItems(bookArray, null) // Hiển thị danh sách sách

                builder.setPositiveButton("Đóng") { dialog, _ -> dialog.dismiss() }
                builder.create().show()
            }
        }

    }
}
