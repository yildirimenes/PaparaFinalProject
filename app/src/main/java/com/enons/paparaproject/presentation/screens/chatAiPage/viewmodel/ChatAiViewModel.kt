package com.enons.paparaproject.presentation.screens.chatAiPage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enons.paparaproject.core.ApiResult.ApiResult
import com.enons.paparaproject.data.remote.dto.Message
import com.enons.paparaproject.data.remote.dto.MessageResponse
import com.enons.paparaproject.data.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatAiViewModel @Inject constructor(
    private val mealRepository: MealRepository,
) : ViewModel() {

    private val _chatState = MutableStateFlow<ChatAiState>(ChatAiState.Loading)
    val chatState: StateFlow<ChatAiState> = _chatState
    private val messageList = mutableListOf<MessageResponse>()

    fun initialize() {
        // Add initial system message
        val initialSystemMessage = Message(
            content = "You are a chef and can only provide information about recipes and cooking. If asked about other topics, politely inform that you can only discuss food and cooking.",
            role = "system"
        )
        messageList.add(MessageResponse(message = initialSystemMessage))
        updateChatState()
    }

    fun sendMessage(text: String) {
        val message = Message(content = text, role = "user")
        messageList.add(MessageResponse(message = message))
        updateChatState()

        viewModelScope.launch {
            try {
                mealRepository.sendMessageOpenAi(messageList.map { it.message }).collect { result ->
                    when (result) {
                        is ApiResult.Loading -> {
                            _chatState.value = ChatAiState.Loading
                        }
                        is ApiResult.Success -> {
                            val response = result.data.choices
                            messageList.add(response[0])
                            updateChatState()
                        }
                        is ApiResult.Error -> {
                            _chatState.value = ChatAiState.Error("Error fetching chat response")
                        }
                    }
                }
            } catch (e: Exception) {
                _chatState.value = ChatAiState.Error("No internet connection")
            }
        }
    }

    fun retryLastMessage() {
        val lastUserMessage = messageList.lastOrNull { it.message.role == "user" }?.message?.content
        lastUserMessage?.let {
            sendMessage(it)
        }
    }

    private fun updateChatState() {
        val filteredList = messageList.filter { it.message.role != "system" }
        _chatState.value = ChatAiState.Success(messageList = filteredList)
    }
}

