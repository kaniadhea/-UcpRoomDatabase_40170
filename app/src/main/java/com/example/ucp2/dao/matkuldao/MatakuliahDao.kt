package com.example.ucp2.dao.matkuldao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

 @Dao
 interface MatakuliahDao {
     @Insert
     suspend fun insertMatakuliah(matakuliah: Matakuliah)

     @Query("SELECT * FROM matakuliah ORDER BY nama ASC")
     fun getAllMatakuliah () : Flow<List<Matakuliah>>

     @Query("SELECT * FROM matakuliah WHERE kode = :kode")
     fun getMatkul(kode: String) : Flow<Matakuliah>

     @Delete
     suspend fun deleteMatakuliah(matakuliah: Matakuliah)

     @Update
     suspend fun updateMatakuliah(matakuliah: Matakuliah)

 }