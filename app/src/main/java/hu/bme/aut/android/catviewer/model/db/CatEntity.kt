package hu.bme.aut.android.catviewer.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import hu.bme.aut.android.catviewer.model.Cat

@Entity
data class CatEntity (
    @PrimaryKey(autoGenerate = true) var id: Int?,
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
            uploaded = true,
            favorite = false
        )
    }
}

fun CatEntity.mapToCatEntity(cat : Cat): CatEntity {
    return CatEntity(
        id = 0,
        imageid = cat.id,
        url = this.url,
        uploaded = false,
        favorite = false
    )
}