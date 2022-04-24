package hu.bme.aut.android.catviewer.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatEntity (
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "imageid") var imageid: String?,
    @ColumnInfo(name = "url") var url : String,
    @ColumnInfo(name = "uploaded") var uploaded : Boolean,
    @ColumnInfo(name = "favorite") var favorite : Boolean
) {

    companion object {

        fun mock() = CatEntity(
            id = 0,
            imageid = "6ft",
            url = "https://cdn2.thecatapi.com/images/6ft.jpg",
            uploaded = false,
            favorite = false
        )
    }
}
