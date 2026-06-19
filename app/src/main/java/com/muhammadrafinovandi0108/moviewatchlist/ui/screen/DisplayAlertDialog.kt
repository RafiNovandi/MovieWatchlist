package com.muhammadrafinovandi0108.moviewatchlist.ui.screen


import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.muhammadrafinovandi0108.moviewatchlist.R
import com.muhammadrafinovandi0108.moviewatchlist.ui.theme.GreyTitle
import com.muhammadrafinovandi0108.moviewatchlist.ui.theme.MovieWatchlistTheme

@Composable
fun DisplayAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    AlertDialog(
            text = { Text(text = stringResource(R.string.pesan_hapus))},
            confirmButton = {
                TextButton(onClick = { onConfirmation() }) {
                    Text(text = stringResource(R.string.tombol_hapus))
                }

            },
            dismissButton = {
                TextButton(onClick = { onDismissRequest() }) {
                    Text(text = stringResource(R.string.batal), color = GreyTitle)
                }
            },
            onDismissRequest = { onDismissRequest() }
    )
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DialogAlertPreview(){
    MovieWatchlistTheme {
        DisplayAlertDialog(
            onDismissRequest = {},
            onConfirmation = {}
        )
    }

}