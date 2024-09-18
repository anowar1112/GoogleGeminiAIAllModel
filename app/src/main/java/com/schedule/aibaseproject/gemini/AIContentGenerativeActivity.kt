package com.schedule.aibaseproject.gemini

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import com.schedule.aibaseproject.R
import com.schedule.aibaseproject.databinding.ActivityAicontentGenerativeBinding

class AIContentGenerativeActivity : AppCompatActivity() {

    private val TAG = "AIContentGenerativeActivity"

    private lateinit var binding: ActivityAicontentGenerativeBinding
    private val viewModel: AIContentGenerativeViewModel by viewModels()

    private lateinit var resultTextView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAicontentGenerativeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val button: Button = findViewById(R.id.generation_button)
        resultTextView = findViewById(R.id.resultTextView)
        setObserver()
        button.setOnClickListener {
            Log.d(TAG,"button click")
            val text: EditText = findViewById(R.id.input_text_view)
            viewModel.getResultFromGoogle(text.text.toString())
        }


    }

    private fun setObserver(){
        viewModel.textFromGoogle.observe(this){
            it?.let { output ->
                resultTextView.text = output
            }
        }
    }

}