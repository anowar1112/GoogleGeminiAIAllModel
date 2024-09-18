package com.schedule.aibaseproject.chatgpt

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatGPTViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val TAG = "ChatGPTViewModel"
    private val _textFromChatGpt = MutableLiveData<String>()
    val textFromChatGpt: LiveData<String>
        get() = _textFromChatGpt

    private var apiService: ChatGPTApiService

    init {
        // Initialize Retrofit and API service
        val retrofit = RetrofitInstance.getRetrofit()
        apiService = retrofit.create(ChatGPTApiService::class.java)
    }

    fun getResultFromChatGPT(testResult: String?){
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG,"input $testResult")
            getTextFromChatGPT(testResult)
        }
        //}
    }

    private fun getTextFromChatGPT(messageContent: String?) {
        messageContent?.let {
            runBlocking {
                // Create message request
                /// anowar - any variable name
                val message = Message("anowar", messageContent)
                val request = ChatRequest(
                    model = "gpt-3.5-turbo",  // You can also use gpt-4 if you have access
                    messages = listOf(message)
                )

                // Make API call
                apiService.sendMessage(request).enqueue(object : Callback<ChatResponse> {
                    override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                        if (response.isSuccessful) {
                            val chatResponse = response.body()
                            val reply = chatResponse?.choices?.firstOrNull()?.message?.content ?: "No response"
                            Log.d(TAG, "Reply: $reply")
                          //  withContext(Dispatchers.Main) {
                            _textFromChatGpt.value = reply
                           // }

                            // You can update the UI with the reply
                            // findViewById<TextView>(R.id.replyTextView).text = reply
                        } else {
                            Log.e(TAG, "Error: ${response.code()} - ${response.message()}")
                            _textFromChatGpt.value = response.message()
                        }
                    }

                    override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                        Log.e("ChatGPT", "Failed to send message: ${t.message}")
                        _textFromChatGpt.value = t.message
                    }
                })
            }
        }
    }
}