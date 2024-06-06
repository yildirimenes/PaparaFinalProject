package com.enons.paparaproject.presentation.screens.ChatAIPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.enons.paparaproject.R
import com.enons.paparaproject.presentation.components.CustomSuggestCard
import com.enons.paparaproject.presentation.components.AIChatMessage
import com.enons.paparaproject.presentation.components.CustomSubTitleText
import com.enons.paparaproject.presentation.components.SendAiField
import com.enons.paparaproject.presentation.components.CustomText
import com.enons.paparaproject.presentation.components.CustomTitleText
import com.enons.paparaproject.presentation.components.LoadingComponents
import com.enons.paparaproject.presentation.components.UserChatMessage
import com.enons.paparaproject.presentation.screens.ChatAIPage.utils.suggestions
import com.enons.paparaproject.presentation.screens.ChatAIPage.viewmodel.ChatAiViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatAiPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ChatAiViewModel = hiltViewModel(),
) {
    val chatState by viewModel.chatState.collectAsState()
    val randomSuggestions = remember { suggestions.shuffled().take(5) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    CustomText(
                        stringResource(id = R.string.chat_ai_page),
                        padding = 4
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },

        ) { it ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            if (chatState.error.isNotBlank()) {
                Text(text = chatState.error)
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                reverseLayout = true
            ) {
                items(chatState.messageList.reversed()) { messageItem ->
                    if (messageItem.message.isUser) {
                        UserChatMessage(text = messageItem.message.content, horizontalAlignment = Alignment.End)
                    } else {
                        AIChatMessage(
                            message = messageItem.message
                        )
                    }
                }
            }
            if (chatState.messageList.isEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                CustomTitleText(stringResource(id = R.string.hello))
                CustomSubTitleText(stringResource(id = R.string.chat_ai_hello_message))
                Spacer(modifier = Modifier.height(250.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    items(randomSuggestions) { suggestion ->
                        CustomSuggestCard(
                            suggestion = suggestion,
                            onClick = { viewModel.sendMessage(suggestion) },
                            modifier = Modifier.weight(1f),
                            textColor = Color.DarkGray,
                        )
                    }
                }
            }

            if(chatState.isLoading){
                LoadingComponents(modifier = Modifier.padding(12.dp))
            }
            SendAiField(
                modifier.padding(12.dp)
            ) { message ->
                viewModel.sendMessage(message)
            }
        }
    }
}