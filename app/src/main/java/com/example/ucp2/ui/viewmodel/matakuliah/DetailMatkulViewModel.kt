package com.example.ucp2.ui.viewmodel.matakuliah

import com.example.ucp2.entity.Matakuliah


fun Matakuliah.toDetailUiEvent() : MatakuliahEvent {
    return MatakuliahEvent(
        kode = kode,
        nama = nama,
        sks = sks,
        semester = semester,
        jenis = jenis,
        dosenpengampu = dosenpengampu
    )
}