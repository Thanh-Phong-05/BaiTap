package com.example.bt2 // Thay thế bằng package của bạn

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Khai báo các biến để ánh xạ với các View trong layout
    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var buttonCheck: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Gán layout XML cho Activity này
        setContentView(R.layout.activity_main)

        // Ánh xạ các biến với ID của các View trong layout
        editTextName = findViewById(R.id.editTextName)
        editTextAge = findViewById(R.id.editTextAge)
        buttonCheck = findViewById(R.id.buttonCheck)
        textViewResult = findViewById(R.id.textViewResult)

        // Thiết lập sự kiện click cho nút "Kiểm tra"
        buttonCheck.setOnClickListener {
            checkUserInfo()
        }
    }

    private fun checkUserInfo() {
        // Lấy dữ liệu từ EditText
        val name = editTextName.text.toString().trim() // .trim() để loại bỏ khoảng trắng thừa
        val ageString = editTextAge.text.toString().trim()

        // Kiểm tra xem các trường có rỗng không
        if (name.isEmpty()) {
            editTextName.error = "Họ và tên không được để trống"
            return // Dừng hàm nếu tên rỗng
        }
        if (ageString.isEmpty()) {
            editTextAge.error = "Tuổi không được để trống"
            return // Dừng hàm nếu tuổi rỗng
        }

        // Chuyển đổi tuổi từ String sang Int
        val age: Int? = ageString.toIntOrNull() // toIntOrNull() an toàn hơn, trả về null nếu không phải số

        if (age == null) {
            editTextAge.error = "Tuổi phải là một số hợp lệ"
            return // Dừng hàm nếu tuổi không phải số
        }

        // Phân loại độ tuổi
        val ageCategory: String = when {
            age > 65 -> "Người già"
            age in 7..65 -> "Người lớn" // Từ 7 đến 65
            age in 3..6 -> "Trẻ em"    // Từ 3 đến 6
            age <= 2 -> "Em bé"      // Nhỏ hơn hoặc bằng 2
            else -> "Không xác định" // Trường hợp này thường không xảy ra nếu input hợp lệ
        }

        // Hiển thị kết quả lên TextView
        val resultText = "Thông tin của bạn:\n" +
                "Họ và tên: $name\n" +
                "Tuổi: $age\n" +
                "Phân loại: $ageCategory"
        textViewResult.text = resultText

        // (Tùy chọn) Hiển thị thông báo Toast ngắn
        Toast.makeText(this, "Đã kiểm tra thông tin!", Toast.LENGTH_SHORT).show()
    }
}