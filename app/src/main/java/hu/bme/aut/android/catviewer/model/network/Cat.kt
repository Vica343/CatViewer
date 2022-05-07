package hu.bme.aut.android.catviewer.model

import hu.bme.aut.android.catviewer.model.db.CatEntity

data class Cat(
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

