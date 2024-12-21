package com.example.ucp2.ui.view.dosenview

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ucp2.ui.viewmodel.dosen.DosenEvent
import com.example.ucp2.ui.viewmodel.dosen.FormErrorState


@Composable
fun FormDosen(
    dosenEvent: DosenEvent = DosenEvent(),
    onValueChange : (DosenEvent) -> Unit,
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
){

}