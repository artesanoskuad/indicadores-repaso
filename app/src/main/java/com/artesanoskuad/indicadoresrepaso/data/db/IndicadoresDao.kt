package com.artesanoskuad.indicadoresrepaso.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IndicadoresDao {

    @Insert
    fun insert(indicadorInfoEntity: IndicadorInfoEntity)

    @Insert
    fun insertAll(indicadorInfoEntity: List<IndicadoresEntity>)

    @Query("DELETE FROM IndicadoresEntity")
    fun deleteIndicadoresEntity(): Int

    @Query("DELETE FROM IndicadorInfoEntity")
    fun deleteIndicadoresInfoEntity(): Int

    @Query("SELECT * FROM IndicadorInfoEntity")
    fun getIndicadorInfo(): IndicadorInfoEntity?

    @Query("SELECT * FROM IndicadoresEntity")
    fun getIndicadoresList(): List<IndicadoresEntity>?

}