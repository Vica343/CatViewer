package hu.bme.aut.android.catviewer.model


class Cat : ArrayList<CatItem>()

data class CatItem(
    val breeds: List<Any>,
    val categories: List<Category>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)

data class Category(
    val id: Int,
    val name: String
)