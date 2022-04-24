package hu.bme.aut.android.catviewer.persistence
import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.aut.android.catviewer.model.db.CatEntity

@Database(entities = [CatEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase()  {
    abstract fun catDao(): CatDao
}