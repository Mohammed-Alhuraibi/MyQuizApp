package com.example.myquizzapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultnActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultn)

        val tvUserName : TextView = findViewById(R.id.userName)
        val tvScore : TextView = findViewById(R.id.tv_score)
        val btnFinish : Button = findViewById(R.id.btn_finish)
        val btnRetry = findViewById<Button>(R.id.btn_retry)



        tvUserName.text = intent.getStringExtra(Contents.USER_NAME)
        val score = intent.getIntExtra(Contents.CORRECT_ANSWERS,0).toString()
        val totalQuestion = intent.getIntExtra(Contents.TOTAL_QUESTION,0).toString()
        tvScore.text = "Your Score is ${score} out of ${totalQuestion}"

        btnFinish.setOnClickListener {
            finish()
        }

        btnRetry.setOnClickListener {
            val intent = Intent(this,QuizQuestions::class.java)
            startActivity(intent)
            finish()
        }





    }
}