package com.halilkrkn.chatchef.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.enons.paparaproject.R

@Composable
fun CustomButton(modifier: Modifier, sendButtonClicked: () -> Unit) {
    Button(
        onClick = {
            sendButtonClicked()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.color_two),
            contentColor = Color.DarkGray
        ),
        shape = CircleShape,
        contentPadding = PaddingValues(12.dp),
        content = {
            Icon(
                modifier = Modifier
                    .size(48.dp),
                painter = painterResource(R.drawable.ic_send),
                contentDescription = "",
                tint = Color.LightGray
            )
        },
        modifier = modifier
    )
}

