package com.example.ucp2.ui.viewmodel.dosen

import com.example.ucp2.entity.Dosen

class DetailDsnViewModel(

)

data class DetailDsnViewModel(
    val detailUiEvent: DosenEvent = DosenEvent(),
    val isLoading: Boolean = false,
    val isError : Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty : Boolean
        get() = detailUiEvent == DosenEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != DosenEvent()
}

fun Dosen.toDetailUiEvent() : DosenEvent {
    return DosenEvent(
        nidn = nidn,
        nama = nama,
        jenisKelamin = jenisKelamin
    )
}