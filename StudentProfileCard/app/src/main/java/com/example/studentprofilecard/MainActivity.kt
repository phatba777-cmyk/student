package com.example.studentprofilecard

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentprofilecard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private var currentStudent = Student(
        id = "2415053122228",
        name = "Nguyễn Bá Phát",
        className = "126LTTD03",
        gpa = 3.0
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindStudentData(currentStudent)

        binding.btnUpdateGpa.setOnClickListener {
            val inputStr = binding.edtNewGpa.text.toString().trim()
            val newGpa = inputStr.toDoubleOrNull()

            newGpa?.let { gpaValue ->
                if (gpaValue in 0.0..4.0) {
                    currentStudent = currentStudent.copy(gpa = gpaValue)
                    bindStudentData(currentStudent)
                    Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show()
                    binding.edtNewGpa.text.clear()
                } else {
                    Toast.makeText(this, "Điểm phải từ 0.0 đến 4.0!", Toast.LENGTH_SHORT).show()
                }
            } ?: run {
                Toast.makeText(this, "Vui lòng nhập số hợp lệ!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bindStudentData(student: Student) {
        with(binding) {
            tvName.text = student.name
            tvInfo.text = "MSSV: ${student.id} | Lớp: ${student.className}"

            val ranking = when {
                student.gpa >= 3.6 -> "Xuất sắc"
                student.gpa >= 3.2 -> "Giỏi"
                student.gpa >= 2.5 -> "Khá"
                student.gpa >= 2.0 -> "Trung bình"
                else -> "Yếu"
            }
            tvGpa.text = "${student.gpa} GPA ($ranking)"
        }
    }
}