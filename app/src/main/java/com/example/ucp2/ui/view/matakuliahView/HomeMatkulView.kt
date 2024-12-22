package com.example.ucp2.ui.view.matakuliahView

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ucp2.entity.Matakuliah

@Composable

fun  CardMatkul(
    matkul: Matakuliah,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
){
    Card (
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){

    }
}