package com.schedule.aibaseproject.gemini

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.*

class AIContentGenerativeViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val TAG = "AIContentGenerativeViewModel"
    private val _textFromGoogle = MutableLiveData<String>()
    val textFromGoogle: LiveData<String>
        get() = _textFromGoogle

    fun getResultFromGoogle(testResult: String?){
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            getTextFromGoogle(testResult)
        }
        //}
    }

    private fun getTextFromGoogle(text: String?) {
        text?.let {
            runBlocking {
                val generativeModel =
                    GenerativeModel(
                        // Specify a Gemini model appropriate for your use case
                        modelName = "gemini-1.5-flash",
                        // Access your API key as a Build Configuration variable (see "Set up your API key" above)
                        apiKey = "AIzaSyCErMR8VLmxAtG6wx8gZ95iXIVXPy0YPww")

                val prompt = text
                val response = generativeModel.generateContent(prompt)
                response.text?.let { finalResult ->
                    withContext(Dispatchers.Main) {
                        _textFromGoogle.value = finalResult
                    }
                }?: run {
                    Log.d(TAG,"get Error from google")
                }
                print(response.text)
            }
        }
    }
}