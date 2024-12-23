package com.example.ucp2.repository

import com.example.ucp2.dao.matkuldao.MatakuliahDao
import com.example.ucp2.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

class LocalRepoMk (
    private val matakuliahDao: MatakuliahDao
) : RepositoryMatkul{
    override suspend fun insertMatkul(matakuliah: Matakuliah) {
        matakuliahDao.insertMatakuliah(matakuliah)
    }
    //getAllMhs
    override fun getAllMatkul(): Flow<List<Matakuliah>> {
        return matakuliahDao.getAllMatakuliah()
    }

    override fun getMatkul(kode: String): Flow<Matakuliah> {
        return matakuliahDao.getMatkul(kode )
    }

    override suspend fun deleteMatkul(matakuliah: Matakuliah) {
        matakuliahDao.deleteMatakuliah(matakuliah)
    }

    override suspend fun updateMatkul(matakuliah: Matakuliah) {
        matakuliahDao.updateMatakuliah(matakuliah)
    }
}