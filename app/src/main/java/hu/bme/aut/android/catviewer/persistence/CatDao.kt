package hu.bme.aut.android.catviewer.persistence
import androidx.room.*
import hu.bme.aut.android.catviewer.model.Cat
import hu.bme.aut.android.catviewer.model.db.CatEntity

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatList(cats: List<CatEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCat(cat: CatEntity)

    @Query("DELETE FROM CatEntity WHERE favorite = 0 AND uploaded = 0")
    suspend fun refreshCatListDelete()

    @Query("SELECT * FROM CatEntity")
    suspend fun getCatList(): List<CatEntity>

    @Query("UPDATE CatEntity SET favorite = :value WHERE id LIKE :id ")
    suspend fun updateFavorite(id: Int?, value: Boolean)

    @Query("DELETE FROM CatEntity WHERE id = :id")
    suspend fun deleteUploadedCat(id: Int?)

}