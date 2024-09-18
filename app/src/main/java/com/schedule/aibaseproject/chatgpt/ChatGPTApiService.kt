package com.schedule.aibaseproject.chatgpt

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatGPTApiService {
    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    fun sendMessage(
        @Body request: ChatRequest
    ): Call<ChatResponse>
}
