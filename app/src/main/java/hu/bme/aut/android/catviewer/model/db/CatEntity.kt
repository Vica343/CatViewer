package hu.bme.aut.android.catviewer.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatEntity (
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "url") var url : String,
    @ColumnInfo(name = "uploaded") var uploaded : Boolean,
    @ColumnInfo(name = "favorite") var favorite : Boolean
)
