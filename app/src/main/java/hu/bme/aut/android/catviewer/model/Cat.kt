package hu.bme.aut.android.catviewer.model

class Cat : ArrayList<CatItem>()

data class CatItem(
    val breed_ids: Any,
    val breeds: List<Any>,
    val created_at: String,
    val height: Int,
    val id: String,
    val original_filename: String,
    val sub_id: String,
    val url: String,
    val width: Int
)