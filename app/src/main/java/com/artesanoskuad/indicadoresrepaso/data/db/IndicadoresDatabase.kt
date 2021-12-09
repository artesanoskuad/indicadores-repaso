package com.artesanoskuad.indicadoresrepaso.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [IndicadorInfoEntity::class, IndicadoresEntity::class],
    version = 1,
    exportSchema = false
)
abstract class IndicadoresDatabase : RoomDatabase() {

    abstract val indicadoresDao: IndicadoresDao

    companion object {

        @Volatile
        private var INSTANCE: IndicadoresDatabase? = null

        fun getDatabase(context: Context): IndicadoresDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IndicadoresDatabase::class.java,
                    "indicadores_database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
