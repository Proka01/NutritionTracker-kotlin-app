package rs.raf.vezbe11.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class IngredientWrapperResponse (
    val meals: List<IngredientResponse>
)