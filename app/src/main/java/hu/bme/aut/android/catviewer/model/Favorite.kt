package hu.bme.aut.android.catviewer.model

class Favorite : ArrayList<FavoriteItem>()

data class FavoriteItem(
    val created_at: String,
    val id: Int,
    val image: Image,
    val image_id: String,
    val sub_id: String,
    val user_id: String
)

data class Image(
    val id: String,
    val url: String
)