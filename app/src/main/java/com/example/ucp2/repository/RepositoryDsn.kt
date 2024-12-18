package com.example.ucp2.repository

import com.example.ucp2.entity.Dosen
import kotlinx.coroutines.flow.Flow

interface RepositoryDsn {

    suspend fun intertDsn(dosen: Dosen)

    fun getAllDsn(): Flow<List<Dosen>>

    fun getDsn(nidn: String): Flow<Dosen>
}