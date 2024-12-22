package com.example.ucp2.ui.viewmodel.matakuliah

import com.example.ucp2.entity.Matakuliah




data class MatkulUIState(
    val matakuliahEvent: MatakuliahEvent,
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class FormErrorState(
    val kode: String? = null,
    val nama : String? = null,
    val sks : String? = null,
    val semester : String? = null,
    val jenis : String? = null,
    val dosenpengampu : String? = null
)

data class MatakuliahEvent(
    val kode : String,
    val nama : String,
    val sks : String,
    val semester : String,
    val jenis : String,
    val dosenpengampu : String,
)

fun MatakuliahEvent.toMatakuliahEntity(): Matakuliah = Matakuliah(
    kode = kode,
    nama = nama,
    sks = sks,
    semester = semester,
    jenis = jenis,
    dosenpengampu = dosenpengampu,
)