package hu.bme.aut.android.catviewer.persistence
import androidx.room.*
import hu.bme.aut.android.catviewer.model.Cat
import hu.bme.aut.android.catviewer.model.db.CatEntity

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatList(cats: List<CatEntity>)

    @Query("SELECT * FROM CatEntity")
    suspend fun getCatList(): List<CatEntity>

    @Query("SELECT * FROM CatEntity WHERE favorite = 1")
    suspend fun getFavoriteList(): List<CatEntity>

    @Query("SELECT * FROM CatEntity WHERE uploaded = 1")
    suspend fun getUploadedList(): List<CatEntity>

    @Update
    suspend fun updateFavorite(cat: CatEntity)

    @Query("DELETE FROM CatEntity WHERE id = :id")
    suspend fun deleteUploadedCat(id: Long)

}