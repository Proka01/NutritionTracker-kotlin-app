package rs.raf.vezbe11.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealCategoryResponse(
    val idCategory: Long,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)