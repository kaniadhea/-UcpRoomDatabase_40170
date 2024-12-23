package com.example.ucp2.ui.viewmodel.matakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.entity.Dosen
import com.example.ucp2.entity.Matakuliah
import com.example.ucp2.repository.RepositoryDsn
import com.example.ucp2.repository.RepositoryMatkul
import com.example.ucp2.ui.navigation.DestinasiUpdate
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMatkulViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMatkul: RepositoryMatkul,
    private val repositoryDsn: RepositoryDsn
) : ViewModel() {

    var dosenList by mutableStateOf<List<Dosen>>(emptyList())
        private set
    var updateUIState by mutableStateOf(MatkulUIState())
        private set

    private val _kode: String = checkNotNull(savedStateHandle[DestinasiUpdate.KODE])

    init {
        viewModelScope.launch{
            updateUIState = repositoryMatkul.getMatkul(_kode)
                .filterNotNull()
                .first()
                .toUIStateMatkul()
        }
            viewModelScope.launch {
                val dosenListFromRepo = repositoryDsn.getAllDsn().first()
                dosenList = dosenListFromRepo
                updateUIState = updateUIState.copy(dosenList = dosenList)

        }
    }
    fun updateState(matakuliahEvent: MatakuliahEvent) {
        updateUIState = updateUIState.copy(
            matakuliahEvent = matakuliahEvent,
        )
    }
    fun validateFields() : Boolean{
        val event = updateUIState.matakuliahEvent
        val errorState = FormErrorState(
            kode = if (event.kode.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "Sks tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Jenis tidak boleh kosong",
            dosenpengampu = if (event.dosenpengampu.isNotEmpty()) null else "Dosenpengampu tidak boleh kosong",
        )
        updateUIState = updateUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
    fun updateData(){
        val currentEvent = updateUIState.matakuliahEvent
        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryMatkul.updateMatkul(currentEvent.toMatakuliahEntity())
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data berhasil diupdate",
                        matakuliahEvent = MatakuliahEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("snackBarMessage diatur: ${updateUIState.snackBarMessage}")
                } catch (e: Exception) {
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data gagal di update"
                    )
                }
            }
        } else {
            updateUIState = updateUIState.copy (
                snackBarMessage = "Data gagal diupdate"
            )
        }
    }
    fun resetSnackBarMessage(){
        updateUIState = updateUIState.copy(snackBarMessage = null)
    }

}

fun Matakuliah.toUIStateMatkul() : MatkulUIState = MatkulUIState(
    matakuliahEvent = this.toDetailUiEvent(),
)