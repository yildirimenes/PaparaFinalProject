package com.enons.paparaproject.presentation.screens.ChatAIPage.viewmodel

import com.enons.paparaproject.data.remote.dto.MessageResponse

data class ChatAiState (
    var isLoading : Boolean = false,
    var error : String = "",
    var messageList : List<MessageResponse> = emptyList()

)