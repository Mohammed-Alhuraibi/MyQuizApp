package com.example.myquizzapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myquizzapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var btnStart:Button
    private lateinit var edText:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_main)

        setContentView(binding.root)

        btnStart = binding.btnStart
        edText = binding.edText

        btnStart.setOnClickListener {

            if(edText.text.isEmpty()){
                Toast.makeText(this,"Please Enter you name",Toast.LENGTH_LONG).show()

            }else{
                val intent = Intent(this,QuizQuestions::class.java)
                intent.putExtra(Contents.USER_NAME,edText.text.toString())
                startActivity(intent)
                finish()

            }
        }





    }
}