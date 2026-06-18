package com.muhammadrafinovandi0108.moviewatchlist.ui.screen

import android.content.res.Configuration
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.muhammadrafinovandi0108.moviewatchlist.R
import com.muhammadrafinovandi0108.moviewatchlist.ui.theme.MovieWatchlistTheme

@Composable
fun FilmDialog(
    bitmap: Bitmap?,
    onDismissRequest: () -> Unit,
    onConfirmation: (String, String, String, String, String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var watchedDate by remember { mutableStateOf("") }
    var review by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    bitmap = bitmap!!.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().aspectRatio(2f/3f).clip(RoundedCornerShape(6.dp))
                )

                Column(
                    modifier = Modifier
                        .heightIn(max = 200.dp)
                        .padding(top = 16.dp)
                        .verticalScroll(rememberScrollState())

                ) {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text(stringResource(R.string.judul)) },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    OutlinedTextField(
                        value = genre,
                        onValueChange = { genre = it },
                        label = { Text(stringResource(R.string.genre)) },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    OutlinedTextField(
                        value = rating,
                        onValueChange = { rating = it },
                        label = { Text(stringResource(id = R.string.rating)) },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    OutlinedTextField(
                        value = watchedDate,
                        onValueChange = { watchedDate = it },
                        label = { Text(stringResource(id = R.string.watched_date)) },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    OutlinedTextField(
                        value = review,
                        onValueChange = { review = it },
                        label = { Text(stringResource(id = R.string.review)) },
                        minLines = 3,
                        maxLines = 5,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(stringResource(R.string.batal))
                    }
                    OutlinedButton(
                        onClick = { onConfirmation(title, genre, rating, watchedDate, review) },
                        enabled = title.isNotEmpty() && genre.isNotEmpty() && rating.isNotEmpty() && watchedDate.isNotEmpty() && review.isNotEmpty(),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(stringResource(R.string.simpan))
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AddDialogPreview() {
    MovieWatchlistTheme(){
        FilmDialog(
            bitmap = null,
            onDismissRequest = {},
            onConfirmation = {_, _, _, _, _ ->}
        )
    }
}
