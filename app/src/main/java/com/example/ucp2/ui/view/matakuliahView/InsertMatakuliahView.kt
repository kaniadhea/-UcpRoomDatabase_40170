package com.example.ucp2.ui.view.matakuliahView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.costumwidget.CstAppBar
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.matakuliah.FormErrorState
import com.example.ucp2.ui.viewmodel.matakuliah.MatakuliahEvent
import com.example.ucp2.ui.viewmodel.matakuliah.MatkulUIState
import kotlinx.coroutines.launch

@Composable
fun InserMatkulView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MatakuliahEvent = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { messege ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(messege) //tampilkan snackbar
                viewModel.resetSnackBarMessage()
            }

        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ){
            CstAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Mahasiswa"
            )

            //isi bodi
            InsertBodyMatkul (
                uiState = uiState,
                onValueChange = {updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        viewModel.s()
                    }
                    onNavigate()
                }
            )
        }
    }
}


@Composable
fun InsertBodyMatkul(
    modifier: Modifier= Modifier,
    onValueChange: (MatakuliahEvent) -> Unit,
    uiState: MatkulUIState,
    onClick: () -> Unit
){
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormMatakuliah(
            matakuliahEvent = uiState.matakuliahEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Simpan")
        }
    }

}

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
            value = matakuliahEvent.kode,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(kode = it))
            },
            label = { Text("Kode") },
            isError = errorState.kode !=null,
            placeholder = { Text("Masukkan kode") },
        )
        Text(
            text = errorState.kode ?:"",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.nama,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(nama = it))
            },
            label = { Text("Nama")},
            isError = errorState.nama !=null,
            placeholder = { Text("Masukkan nama")},
        )
        Text(
            text = errorState.nama ?:"",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.sks,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(sks = it))
            },
            label = { Text("Sks")},
            isError = errorState.sks !=null,
            placeholder = { Text("Masukkan Sks")},
        )
        Text(
            text = errorState.sks ?:"",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.semester,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(semester = it))
            },
            label = { Text("Semester")},
            isError = errorState.semester !=null,
            placeholder = { Text("Masukkan semester")},
        )
        Text(
            text = errorState.semester ?:"",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.jenis,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(jenis = it))
            },
            label = { Text("Jenis")},
            isError = errorState.jenis !=null,
            placeholder = { Text("Masukkan jenis")},
        )
        Text(
            text = errorState.jenis ?:"",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.dosenpengampu,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(dosenpengampu = it))
            },
            label = { Text("Dosen Pengampu")},
            isError = errorState.nama !=null,
            placeholder = { Text("Masukkan Dosen Penampu")},
        )
        Text(
            text = errorState.dosenpengampu ?:"",
            color = Color.Red
        )
    }
}