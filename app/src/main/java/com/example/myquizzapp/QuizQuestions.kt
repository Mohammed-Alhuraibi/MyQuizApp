package com.example.myquizzapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.myquizzapp.databinding.ActivityQuizQuestionsBinding

class QuizQuestions : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityQuizQuestionsBinding

    private var currentPosition : Int = 0
    private var selectedOptionPostion : Int = 0
    private var questionList = ArrayList<Question>()
    private var mOptions = ArrayList<TextView>()

    private var mCorrectAnswers:Int = 0
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        questionList = Contents.getQuestion()


        binding.btnSubmit.setOnClickListener(this)

        val collectionOfOptions = setOf(binding.tvOptionOne,binding.tvOptionTwo,binding.tvOptionThree
            ,binding.tvOptionFour)

        mOptions.addAll(collectionOfOptions)

        enableOnClick()
        setQuetion()


    }

    @SuppressLint("SetTextI18n")
    private fun setQuetion() {
        enableOnClick()
        setDefaultOptionViews()
//        binding.tvQuestion.text = questionList[currentPosition].question
        binding.imgFlag.setImageResource(questionList[currentPosition].image)
        binding.pb.progress = "${currentPosition + 1}".toInt()
        binding.tvCurrentQuestion.text = (currentPosition + 1).toString() + "/" + binding.pb.max
        binding.tvOptionOne.text = questionList[currentPosition].optionOne
        binding.tvOptionTwo.text = questionList[currentPosition].optionTwo
        binding.tvOptionThree.text = questionList[currentPosition].optionTree
        binding.tvOptionFour.text = questionList[currentPosition].optionFour
        binding.btnSubmit.text = resources.getString(R.string.submit)
    }

    private fun setDefaultOptionViews(){

        for ( option in mOptions ){
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,R.drawable.background_options_textviews)
            option.setTextColor(Color.GRAY)

        }
    }

    private fun selectedOptionView(tv : TextView, selectedPostion : Int){
        setDefaultOptionViews()
        selectedOptionPostion = selectedPostion
        tv.setTextColor(Color.BLACK)
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_bg)
    }

    private fun answerView(answer : Int, drawableView : Int){
        when(answer){
            1 -> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
                binding.tvOptionOne.setTextColor(Color.WHITE)
            }

            2 -> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
                binding.tvOptionTwo.setTextColor(Color.WHITE)
            }

            3 -> {
                binding.tvOptionThree.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
                binding.tvOptionThree.setTextColor(Color.WHITE)
            }

            4 -> {
                binding.tvOptionFour.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
                binding.tvOptionFour.setTextColor(Color.WHITE)
            }
        }
    }

    private fun disableOnClick(){
        for(option in mOptions){
            option.setOnClickListener(null)
        }

    }
    private fun enableOnClick(){
        for (option in mOptions){
            option.setOnClickListener(this)
        }
    }


    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_optionOne -> {
                selectedOptionView(binding.tvOptionOne,1)
            }

            R.id.tv_optionTwo -> {
                selectedOptionView(binding.tvOptionTwo,2)
            }

            R.id.tv_optionThree -> {
                selectedOptionView(binding.tvOptionThree, 3)
            }

            R.id.tv_optionFour -> {
                selectedOptionView(binding.tvOptionFour, 4)
            }

            R.id.btn_submit -> {
                if(selectedOptionPostion == 0){
                    currentPosition++
                    when{
                        currentPosition+1 <= questionList.size -> {
                            setQuetion()
                        }

                        else -> {
                            mUserName = intent.getStringExtra(Contents.USER_NAME)
                            val intent = Intent(this,ResultnActivity::class.java)
                            intent.putExtra(Contents.USER_NAME,mUserName)
                            intent.putExtra(Contents.CORRECT_ANSWERS,mCorrectAnswers)
                            intent.putExtra(Contents.TOTAL_QUESTION,questionList.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{
                    if(questionList[currentPosition].correctAnswer != selectedOptionPostion)
                        answerView(selectedOptionPostion,R.drawable.false_selected_option)
                    else
                        mCorrectAnswers++
                    answerView(questionList[currentPosition].correctAnswer,R.drawable.correct_selected_option)

                    if( (currentPosition+1) < questionList.size){
                        binding.btnSubmit.text = resources.getString(R.string.nextQuestion)
                        disableOnClick()
                    }
                    else{
                        binding.btnSubmit.text = resources.getString(R.string.finish)
                        disableOnClick()

                    }

                    selectedOptionPostion = 0
                }

            }
        }
    }


}