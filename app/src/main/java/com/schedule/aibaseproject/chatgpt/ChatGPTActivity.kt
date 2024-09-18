package com.schedule.aibaseproject.chatgpt

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.schedule.aibaseproject.databinding.ActivityChatGptactivityBinding

class ChatGPTActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatGptactivityBinding
    private val viewModel: ChatGPTViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_chat_gptactivity)
        binding = ActivityChatGptactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObserver()

        // Send a message to ChatGPT
        binding.chatGptGenerationButton.setOnClickListener {
           val inputTex =  binding.chatGptInputTextView.text
            inputTex?.let {
                sendMessageToChatGPT(inputTex.toString())
            }
        }

    }

    private fun setObserver(){
        viewModel.textFromChatGpt.observe(this){
            it?.let { output ->
                binding.resultTextView.text = output
            }
        }
    }

    private fun sendMessageToChatGPT(messageContent: String) {
        viewModel.getResultFromChatGPT(messageContent)
    }
}