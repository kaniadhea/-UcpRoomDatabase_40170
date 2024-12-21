package com.example.ucp2.ui.view.dosenview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ucp2.ui.viewmodel.dosen.DosenEvent
import com.example.ucp2.ui.viewmodel.dosen.FormErrorState


@Composable
fun FormDosen(
    dosenEvent: DosenEvent = DosenEvent(),
    onValueChange : (DosenEvent) -> Unit,
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
){
    val jenisKelamin = listOf("Laki-Laki", "Perempuan")

    Column (modifier= modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dosenEvent.nama,
            onValueChange = {
                onValueChange(dosenEvent.copy(nama = it))
            },
            label = { Text("Nama")},
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan nama")}
        )
    }
}