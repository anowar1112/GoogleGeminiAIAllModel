package com.schedule.aibaseproject.chatgpt

// Data class for request
data class ChatRequest(
    val model: String,
    val messages: List<Message>
)

data class Message(
    val role: String,   // e.g., "user" or "system" or "assistant"
    val content: String // the message content
)

// Data class for response
data class ChatResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: Message
)
