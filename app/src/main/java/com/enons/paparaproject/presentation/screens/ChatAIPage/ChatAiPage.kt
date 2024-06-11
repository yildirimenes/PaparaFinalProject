package com.enons.paparaproject.presentation.screens.ChatAIPage

import android.annotation.SuppressLint
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.enons.paparaproject.presentation.components.ErrorComponent
import com.enons.paparaproject.presentation.components.LoadingComponents
import com.enons.paparaproject.presentation.components.UserChatMessage
import com.enons.paparaproject.presentation.screens.ChatAIPage.utils.getSuggestions
import com.enons.paparaproject.presentation.screens.ChatAIPage.viewmodel.ChatAiState
import com.enons.paparaproject.presentation.screens.ChatAIPage.viewmodel.ChatAiViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatAiPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ChatAiViewModel = hiltViewModel(),
) {
    val chatState by viewModel.chatState.collectAsState()
    val suggestions = remember { getSuggestions().shuffled().take(5) }
    val suggestionStrings = suggestions.map { id -> stringResource(id) }

    LaunchedEffect(Unit) {
        viewModel.initialize()
    }

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
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            when (chatState) {
                is ChatAiState.Success -> {
                    val messages = (chatState as ChatAiState.Success).messageList
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        reverseLayout = true
                    ) {
                        items(messages.reversed()) { messageItem ->
                            if (messageItem.message.isUser) {
                                UserChatMessage(text = messageItem.message.content, horizontalAlignment = Alignment.End)
                            } else {
                                AIChatMessage(message = messageItem.message)
                            }
                        }
                    }
                    if (messages.isEmpty()) {
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
                            items(suggestionStrings) { suggestion ->
                                CustomSuggestCard(
                                    suggestion = suggestion,
                                    onClick = { viewModel.sendMessage(suggestion) },
                                    modifier = Modifier.weight(1f),
                                    textColor = Color.DarkGray,
                                )
                            }
                        }                    }
                }
                is ChatAiState.Error -> {
                    val errorMessage = (chatState as ChatAiState.Error).message
                    ErrorComponent(message = errorMessage, onRefreshClicked = {
                        viewModel.retryLastMessage()
                    })
                }
                is ChatAiState.Loading -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(end = 12.dp, bottom = 12.dp),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.End
                    ) {
                        LoadingComponents(modifier = Modifier.padding(12.dp))

                    }
                }
            }
            SendAiField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) { message ->
                viewModel.sendMessage(message)
            }

        }

    }
}
