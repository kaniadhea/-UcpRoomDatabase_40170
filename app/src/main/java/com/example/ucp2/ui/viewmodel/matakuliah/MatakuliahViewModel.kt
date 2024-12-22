package com.example.ucp2.ui.viewmodel.matakuliah

import com.example.ucp2.entity.Matakuliah


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