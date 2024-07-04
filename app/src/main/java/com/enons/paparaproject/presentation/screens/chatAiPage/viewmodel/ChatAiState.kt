package com.enons.paparaproject.presentation.screens.chatAiPage.viewmodel

import com.enons.paparaproject.data.remote.dto.MessageResponse
sealed class ChatAiState {
    object Loading : ChatAiState()
    data class Success(val messageList: List<MessageResponse>) : ChatAiState()
    data class Error(val message: String) : ChatAiState()
}
