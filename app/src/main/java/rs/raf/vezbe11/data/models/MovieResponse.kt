package rs.raf.vezbe11.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    val id: String,
    val title: String,
    val description: String,
    val director: String,
    val producer: String
)