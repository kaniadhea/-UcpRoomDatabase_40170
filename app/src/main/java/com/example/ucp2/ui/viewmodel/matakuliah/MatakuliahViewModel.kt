package com.example.ucp2.ui.viewmodel.matakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.entity.Dosen
import com.example.ucp2.entity.Matakuliah
import com.example.ucp2.repository.RepositoryDsn
import com.example.ucp2.repository.RepositoryMatkul
import kotlinx.coroutines.launch


class MatakuliahViewModel(
    private val repositoryMatkul: RepositoryMatkul,
    private val repositoryDsn: RepositoryDsn
) : ViewModel() {

    var uiState by mutableStateOf(MatkulUIState())
        private set

    var dosenList by mutableStateOf<List<Dosen>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            repositoryDsn.getAllDsn().collect{ dosenList->
                this@MatakuliahViewModel.dosenList = dosenList
                updateUiState()
            }
        }
    }

    fun updateState(matakuliahEvent: MatakuliahEvent) {
        uiState = uiState.copy(
            matakuliahEvent = matakuliahEvent,

            )
    }

    fun validataFields(): Boolean {
        val event = uiState.matakuliahEvent
        val errorState = FormErrorState(
            kode = if (event.kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "Sks tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Jenis tidak boleh kosong",
            dosenpengampu = if (event.dosenpengampu.isNotEmpty()) null else "Dosenpengampu tidak boleh kosong"
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData () {
        val currentEvent = uiState.matakuliahEvent
        if (validataFields()) {
            viewModelScope.launch {
                try {
                    repositoryMatkul.insertMatkul(currentEvent.toMatakuliahEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        matakuliahEvent = MatakuliahEvent(),
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
    fun resetSnackBarMessage () {
        uiState = uiState.copy(snackBarMessage = null)
    }

    private fun updateUiState() {
        uiState = uiState.copy(dosenList = dosenList)
    }
}

data class MatkulUIState(
    val matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
    val dosenList: List<Dosen> = emptyList()
)

data class FormErrorState(
    val kode: String? = null,
    val nama : String? = null,
    val sks : String? = null,
    val semester : String? = null,
    val jenis : String? = null,
    val dosenpengampu : String? = null
){
    fun isValid(): Boolean {
        return kode == null && nama == null && sks == null &&
                semester == null && jenis == null && dosenpengampu == null
    }
}

data class MatakuliahEvent(
    val kode : String = "",
    val nama : String = "",
    val sks : String = "",
    val semester : String = "",
    val jenis : String ="",
    val dosenpengampu : String = ""
)

fun MatakuliahEvent.toMatakuliahEntity(): Matakuliah = Matakuliah(
    kode = kode,
    nama = nama,
    sks = sks,
    semester = semester,
    jenis = jenis,
    dosenpengampu = dosenpengampu,
)