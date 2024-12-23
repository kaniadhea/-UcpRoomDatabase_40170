package com.example.ucp2.dependenciesinjection

import android.content.Context
import com.example.ucp2.database.KrsDatabase
import com.example.ucp2.repository.LocalRepoDosen
import com.example.ucp2.repository.LocalRepoMk
import com.example.ucp2.repository.RepositoryDsn
import com.example.ucp2.repository.RepositoryMatkul

interface InterfaceContainerApp{
    val repositoryDsn : RepositoryDsn
    val repositoryMatkul : RepositoryMatkul
}

class ContainerApp(private val context: Context) : InterfaceContainerApp{
    override val repositoryDsn : RepositoryDsn by lazy {
        LocalRepoDosen(KrsDatabase.getDatabase(context).DosenDao())
    }
    override val repositoryMatkul : RepositoryMatkul by lazy {
        LocalRepoMk(KrsDatabase.getDatabase(context).MatakuliahDao())
    }
}