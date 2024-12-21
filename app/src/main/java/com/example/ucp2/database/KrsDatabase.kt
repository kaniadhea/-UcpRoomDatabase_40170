package com.example.ucp2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ucp2.dao.dosendao.DosenDao
import com.example.ucp2.entity.Dosen

@Database(entities = [Dosen::class], version = 1, exportSchema = false)
abstract class KrsDatabase : RoomDatabase(){

    //Mendefinisikan fungsi untuk mengakses data Dosen
    abstract fun DosenDao(): DosenDao


}