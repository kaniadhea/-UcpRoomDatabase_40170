package com.example.ucp2.repository

import com.example.ucp2.dao.dosendao.DosenDao
import com.example.ucp2.entity.Dosen
import kotlinx.coroutines.flow.Flow

class LocalRepoDosen (
    private val dosenDao: DosenDao
) : RepositoryDsn{
    override suspend fun intertDsn(dosen: Dosen) {
        dosenDao.insertDosen(dosen)
    }

    override fun getAllDsn(): Flow<List<Dosen>> {
        return dosenDao.getAllDosen()
    }

    override fun getDsn(nidn: String): Flow<Dosen> {
        return dosenDao.getDsn(nidn)
    }
}