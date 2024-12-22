package com.example.ucp2.ui.view.matakuliahView

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.matakuliah.UpdateMatkulViewModel

@Composable
fun UpdateMatkulView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateMatkulViewModel = viewModel(factory = PenyediaViewModel.Factory)
){

}