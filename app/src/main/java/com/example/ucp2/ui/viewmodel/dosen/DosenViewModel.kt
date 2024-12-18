package com.example.ucp2.ui.viewmodel.dosen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.entity.Dosen
import com.example.ucp2.repository.RepositoryDsn
import kotlinx.coroutines.launch

class DosenViewModel( private val repositoryDsn: RepositoryDsn) : ViewModel() {
    var uiState by mutableStateOf(DsnUIState())

    fun updateState (dosenEvent: DosenEvent) {
        uiState = uiState.copy(
            dosenEvent = dosenEvent,
        )
    }
    private fun validataFields(): Boolean{
        val event = uiState.dosenEvent
        val errorState = FormErrorState(
            nidn = if (event.nidn.isNotEmpty()) null else "NIDN tidak boleh kosong",
            nama= if (event.nidn.isNotEmpty()) null else "NIDN tidak boleh kosong",
            jenisKelamin = if (event.nidn.isNotEmpty()) null else "NIDN tidak boleh kosong"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    //menyimpan data dosen ke repository
    fun saveData() {
        val currentEvent = uiState.dosenEvent
        if (validataFields()) {
            viewModelScope.launch {
                try {
                    repositoryDsn.intertDsn(currentEvent.toDosenEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        dosenEvent = DosenEvent(),
                        isEntryValid = FormErrorState()
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        }else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data anda"
            )
        }
    }
}



data class DsnUIState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class FormErrorState(
    val nidn : String? = null,
    val nama : String? = null,
    val jenisKelamin : String? =null,
){
    fun isValid(): Boolean {
        return nidn == null && nama == null && jenisKelamin == null
    }
}

data class DosenEvent(
    val nidn : String = "",
    val nama : String = "",
    val jenisKelamin : String = "",
)

fun DosenEvent.toDosenEntity(): Dosen = Dosen (
    nidn = nidn,
    nama = nama,
    jenisKelamin = jenisKelamin
)