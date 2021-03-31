package com.mf.mipeso.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mf.mipeso.model.Peso

@Database(entities = [Peso::class], version = 1, exportSchema = false)
abstract class PesoDatabase(): RoomDatabase() {

    abstract fun pesoDao(): PesoDao

    companion object {
        @Volatile
        private var INSTANCE: PesoDatabase? = null

        fun getDatabase(context: Context): PesoDatabase {
            var tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PesoDatabase::class.java,
                    "peso_database_test"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}