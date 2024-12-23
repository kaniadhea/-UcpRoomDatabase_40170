package com.example.ucp2.ui.view.matakuliahView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.entity.Dosen
import com.example.ucp2.ui.costumwidget.CstAppBar
import com.example.ucp2.ui.navigation.AlamatNavigasi
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.matakuliah.FormErrorState
import com.example.ucp2.ui.viewmodel.matakuliah.MatakuliahEvent
import com.example.ucp2.ui.viewmodel.matakuliah.MatakuliahViewModel
import com.example.ucp2.ui.viewmodel.matakuliah.MatkulUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiInsertMatkul : AlamatNavigasi {
    override val route: String = "insert_matkul"
}
@Composable
fun InserMatkulView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,

    viewModel: MatakuliahViewModel = viewModel(factory = PenyediaViewModel.Factory)
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
                judul = "Tambah Mata Kuliah"
            )

            InsertBodyMatkul (
                uiState = uiState,
                dosenList = uiState.dosenList,
                onValueChange = {updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveData()
                        if (viewModel.validataFields()) {
                            viewModel.saveData()
                            delay(500)
                            withContext(Dispatchers.Main) {
                                onNavigate()
                            }
                        }
                    }
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
    onClick: () -> Unit,
    dosenList: List<Dosen>
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
            modifier = Modifier.fillMaxWidth(),
            dosenList = dosenList
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF8A8B6)
            )
        ) {
            Text("Simpan")
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormMatakuliah(
    matakuliahEvent: MatakuliahEvent,
    onValueChange : (MatakuliahEvent) -> Unit,
    errorState: FormErrorState= FormErrorState(),
    modifier: Modifier = Modifier,
    dosenList: List<Dosen>

) {
    var chosenDropDown by remember { mutableStateOf(matakuliahEvent.dosenpengampu) }
    var expanded by remember { mutableStateOf(false)}

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

        ExposedDropdownMenuBox (
            expanded = expanded,
            onExpandedChange = {expanded = !expanded}
        ) {
            OutlinedTextField(
                value = chosenDropDown,
                onValueChange = { },
                label = { Text("Pilih Dosen Pengampu")},
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Expanded Menu"
                    )
                },
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                readOnly = true,
                isError = errorState.dosenpengampu !=null
            )
            ExposedDropdownMenu (
                expanded = expanded,
                onDismissRequest = {expanded = false}

            ) {
                dosenList.forEach { dosen ->
                    DropdownMenuItem(
                        onClick = {
                            chosenDropDown = dosen.nama
                            expanded = false
                            onValueChange(matakuliahEvent.copy(dosenpengampu = dosen.nama))
                        },
                        text = { Text(text = dosen.nama)}
                    )
                }
            }
        }
        Text(text = errorState.dosenpengampu ?: " ", color = Color.Red)
    }
}