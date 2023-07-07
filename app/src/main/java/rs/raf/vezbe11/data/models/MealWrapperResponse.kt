package rs.raf.vezbe11.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MealWrapperResponse (
        val meals : List<MealResponse>?
)