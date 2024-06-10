package com.enons.paparaproject.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.enons.paparaproject.R

@Composable
fun DeleteAlertDialog(
    modifier: Modifier = Modifier,
    isDeleteDialogVisible: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    if (isDeleteDialogVisible) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = { onDismiss() },
            title = {
                Text(stringResource(id = R.string.delete))
            },
            text = {
                Text(stringResource(id = R.string.delete_operation))
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm()
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.color_two))
                ) {
                    Text(stringResource(id = R.string.confirm))
                }
            },
            dismissButton = {
                Button(
                    onClick = { onDismiss() },
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.color_one))

                ) {
                    Text(stringResource(id = R.string.dismiss))
                }
            }
        )
    }
}
