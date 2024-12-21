package com.example.ucp2.ui.viewmodel.dosen

import com.example.ucp2.entity.Dosen


class HomeDsnViewModel(

)

data class HomeUiState(
    val listMhs: List<Dosen> = listOf(),
    val isLoading : Boolean = false,
    val isError: Boolean = false,
    val errorMessage : String = ""
)