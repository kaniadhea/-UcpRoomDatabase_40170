package com.example.ucp2.ui.view.matakuliahView

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.entity.Matakuliah
import com.example.ucp2.ui.costumwidget.CstAppBar
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.matakuliah.DetailMatkulViewModel
import com.example.ucp2.ui.viewmodel.matakuliah.DetailUiState
import com.example.ucp2.ui.viewmodel.matakuliah.toMatakuliahEntity

@Composable
fun DetailMatkulView(
    modifier: Modifier = Modifier,
    viewModel: DetailMatkulViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory =  PenyediaViewModel.Factory),
    onBack: () -> Unit= {},
    onEditClick: (String) -> Unit = {},
    onDeleteClick: () -> Unit
){
    Scaffold (
        topBar = {
            CstAppBar(
                judul = "Detail Mahasiswa",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEditClick(viewModel.detailUiState.value.detailUiEvent.kode) },
                shape = MaterialTheme.shapes.medium,
                containerColor = Color(0xFFF8A8B6),
                modifier = Modifier.padding(16.dp)
            ){
                Icon (
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Mahasiswa",
                )
            }
        }

    ) { innerPadding ->
        val detailUiState by viewModel.detailUiState.collectAsState()

        BodyBetailMatkul(
            modifier = Modifier.padding(innerPadding),
            detailUiState = detailUiState,
            onDeleteClick = {
                viewModel.deleteMhs()
                onDeleteClick()
            }
        )
    }
}

@Composable


fun BodyBetailMatkul(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState = DetailUiState(),
    onDeleteClick: () -> Unit = {}
) {
    var deleteConfirmationRequest by rememberSaveable { mutableStateOf(false) }
    when {
        detailUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        detailUiState.isUiEventNotEmpty -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailMatkul(
                    matakuliah = detailUiState.detailUiEvent.toMatakuliahEntity(),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        deleteConfirmationRequest = true
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF8A8B6),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Delete")
                }
                if (deleteConfirmationRequest) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequest = false
                            onDeleteClick()
                        },
                        onDeleteCancel = { deleteConfirmationRequest = false },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        detailUiState.isUiEventEmpty -> {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Data tidak ditemukan",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}


@Composable
fun ItemDetailMatkul(
    modifier: Modifier = Modifier,
    matakuliah: Matakuliah
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8A8B6), // Pink background color
            contentColor = Color.White // White text color for contrast
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailMatkul(judul = "Kode", isinya = matakuliah.kode)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMatkul(judul = "Nama", isinya = matakuliah.nama)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMatkul(judul = "Sks", isinya = matakuliah.sks)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMatkul(judul = "Semester", isinya = matakuliah.semester)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMatkul(judul = "Jenis", isinya = matakuliah.jenis)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMatkul(judul = "DosenPengampu", isinya = matakuliah.dosenpengampu)
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}



@Composable
fun ComponentDetailMatkul(
    modifier: Modifier = Modifier,
    judul : String,
    isinya : String,
){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya, fontSize = 20.sp,
            fontWeight = FontWeight.Bold,

            )
    }
}


@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data?") },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = onDeleteCancel,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color(0xFFF8A8B6) // Pink color for cancel button
                )
            ) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(
                onClick = onDeleteConfirm,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color(0xFFF8A8B6) // Pink color for confirm button
                )
            ) {
                Text(text = "Yes")
            }
        }
    )
}