package com.example.ucp2.dao.dosendao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2.entity.Dosen
import kotlinx.coroutines.flow.Flow
@Dao
interface DosenDao {

    @Insert
    suspend fun insertDosen(dosen: Dosen)

    @Query("SELECT * FROM dosen ORDER BY nama ASC")
    fun getAllDosen () : Flow<List<Dosen>>

    @Query("SELECT * FROM dosen WHERE nidn = :nidn")
    fun getDsn(nidn: String) : Flow<Dosen>
}