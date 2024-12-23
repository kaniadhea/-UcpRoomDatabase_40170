package com.example.ucp2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.dao.dosendao.DosenDao
import com.example.ucp2.dao.matkuldao.MatakuliahDao
import com.example.ucp2.entity.Dosen
import com.example.ucp2.entity.Matakuliah

@Database(entities = [Dosen::class, Matakuliah ::class], version = 1, exportSchema = false)
abstract class KrsDatabase : RoomDatabase(){

    //Mendefinisikan fungsi untuk mengakses data Dosen
    abstract fun DosenDao(): DosenDao
    abstract fun MatakuliahDao() : MatakuliahDao

    companion object{
        @Volatile
        private var Instance: KrsDatabase? = null

        fun getDatabase(context: Context): KrsDatabase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    KrsDatabase::class.java,
                    "KrsDatabase"
                )
                    .build().also { Instance = it }
            })
        }
    }


}