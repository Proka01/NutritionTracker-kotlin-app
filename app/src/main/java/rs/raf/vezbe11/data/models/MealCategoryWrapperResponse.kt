package rs.raf.vezbe11.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MealCategoryWrapperResponse (
    val categories: List<MealCategoryResponse>
    )