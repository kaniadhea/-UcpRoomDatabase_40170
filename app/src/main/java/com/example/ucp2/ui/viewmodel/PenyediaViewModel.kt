package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.KrsApp
import com.example.ucp2.database.KrsDatabase
import com.example.ucp2.ui.view.matakuliahView.UpdateMatkulView
import com.example.ucp2.ui.viewmodel.dosen.DosenViewModel
import com.example.ucp2.ui.viewmodel.dosen.HomeDsnViewModel
import com.example.ucp2.ui.viewmodel.matakuliah.DetailMatkulViewModel
import com.example.ucp2.ui.viewmodel.matakuliah.HomeMatkulViewModel
import com.example.ucp2.ui.viewmodel.matakuliah.MatakuliahViewModel
import com.example.ucp2.ui.viewmodel.matakuliah.UpdateMatkulViewModel

object PenyediaViewModel{

    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                KrsApp().containerApp.repositoryDsn
            )
        }
        initializer {
            MatakuliahViewModel(
                KrsApp().containerApp.repositoryMatkul,
                KrsApp().containerApp.repositoryDsn)

        }
        initializer {
            HomeMatkulViewModel(
                KrsApp().containerApp.repositoryMatkul            )
        }

        initializer {
            DetailMatkulViewModel(
                createSavedStateHandle(),
                KrsApp().containerApp.repositoryMatkul)
        }

        initializer {
            UpdateMatkulViewModel(
                createSavedStateHandle(),
                KrsApp().containerApp.repositoryMatkul,
                KrsApp().containerApp.repositoryDsn
            )
        }

        initializer {
            HomeDsnViewModel(
                KrsApp().containerApp.repositoryDsn            )
        }

    }
}

fun CreationExtras.KrsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)