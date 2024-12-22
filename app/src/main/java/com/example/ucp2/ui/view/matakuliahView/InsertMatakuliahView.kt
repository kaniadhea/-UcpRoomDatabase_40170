package com.example.ucp2.ui.view.matakuliahView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.matakuliah.FormErrorState
import com.example.ucp2.ui.viewmodel.matakuliah.MatakuliahEvent

@Composable
fun FormMatakuliah(
    matakuliahEvent: MatakuliahEvent,
    onValueChange : (MatakuliahEvent) -> Unit,
    errorState: FormErrorState= FormErrorState(),
    modifier: Modifier = Modifier
) {
    Column (modifier = modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.nama,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(nama = it))
            },
            label = { Text("Nama") },
            isError = errorState.nama !=null,
            placeholder = { Text("Masukkan nama") },
        )
        Text(
            text = errorState.nama ?:"",
            color = Color.Red
        )
    }
}