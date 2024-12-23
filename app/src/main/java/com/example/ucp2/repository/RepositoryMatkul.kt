package com.example.ucp2.repository

import com.example.ucp2.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

interface RepositoryMatkul {
    suspend fun insertMatkul(matakuliah: Matakuliah)

    fun getAllMatkul(): Flow<List<Matakuliah>>

    fun getMatkul(kode: String): Flow<Matakuliah>

    suspend fun deleteMatkul(matakuliah: Matakuliah)

    suspend fun updateMatkul(matakuliah: Matakuliah)
}